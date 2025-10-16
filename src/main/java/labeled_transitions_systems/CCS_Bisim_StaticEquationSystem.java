package labeled_transitions_systems;

import bddRelations.BDDRelStaticEquationSystem;
import bddRelations.soundOracles.BDDRelOracle;
import core.VarPair;
import domains.boolDomain.BoolFormula;
import domains.boolDomain.BoolFormulaSimplify;
import labeled_transitions_systems.CCS.CCSInterpreter;
import labeled_transitions_systems.CCS.CCSProcess;
import labeled_transitions_systems.CCS.CCSProcessSuccessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static labeled_transitions_systems.CCS.CCSProcess.TAU;

public class CCS_Bisim_StaticEquationSystem extends BDDRelStaticEquationSystem<Boolean> {

    CCSInterpreter ccsInterpreter;
    CCSProcessSuccessor succGenerator;
    boolean strong;
    /**
     * Track the nodes that have been visited and assigns them their index
     */
    HashMap<String,Integer> visitedNode;
    /**
     * Retrieve the node assigned to an index
     */
    HashMap<Integer, VarPair<CCSProcess>> nodesIndex;
    Integer varCounter;

    ArrayList<VarPair<CCSProcess>> todo;

    public CCS_Bisim_StaticEquationSystem(String modelCode, boolean strong, boolean fileFlag){
        this.ccsInterpreter = new CCSInterpreter(modelCode,fileFlag);
        this.succGenerator = new CCSProcessSuccessor();
        this.strong = strong;
    }

    public Boolean localSolve(String p1, String p2, BDDRelOracle<Boolean> oracle) {
        flushEquations();
        makeEquationSystem(p1,p2);
        VarPair<CCSProcess> init = new OrderedVarPair(
                ccsInterpreter.getProcess(p1),
                ccsInterpreter.getProcess(p2)
        );
        return localSolve(init.toString(),false,oracle);
    }

    private void addVariable(VarPair<CCSProcess> pair){
        if(! visitedNode.containsKey(pair.toString())){
            visitedNode.put(pair.toString(),varCounter);
            nodesIndex.put(varCounter,pair);
            varCounter++;
            todo.add(pair);
        }
    }

    private BoolFormula.Var getVariable(VarPair<CCSProcess> pair){
        Integer var = visitedNode.get(pair.toString());
        if(var == null)
            throw new RuntimeException("The node variable for " + pair + " was not found");

        return new BoolFormula.Var(var,this);
    }

    @Override
    protected Boolean getBottomElement() {
        return false;
    }

    private void makeEquationSystem(String p1, String p2){
        visitedNode = new HashMap<>();
        nodesIndex = new HashMap<>();
        varCounter = 0;
        VarPair<CCSProcess> init = new OrderedVarPair(
                ccsInterpreter.getProcess(p1),
                ccsInterpreter.getProcess(p2)
        );

        todo = new ArrayList<>();
        addVariable(init);
        while (!todo.isEmpty()) {
            VarPair<CCSProcess> pair = todo.getFirst();
            todo.removeFirst();
            String varLabel = pair.toString();
            Integer varIndex = visitedNode.get(varLabel);
            this.addEquation(varLabel,varIndex,makeFormula(pair));
        }
    }

    private BoolFormula makeFormula(VarPair<CCSProcess> pair) {
        CCSProcess s = pair.left;
        CCSProcess t = pair.right;
        Set<CCSProcess.CCS_Step> succS = getSuccessors(s);
        Set<CCSProcess.CCS_Step> succT = getSuccessors(t);
        BoolFormula pairformula = new BoolFormula.Or(
                simulation(succS,succT),
                simulation(succT,succS),
                CCS_Bisim_StaticEquationSystem.this
                );
        return BoolFormulaSimplify.simplify(pairformula);
    }

    private Set<CCSProcess.CCS_Step> getSuccessors(CCSProcess p){
        return strong ? succGenerator.getSuccessors(p) : succGenerator.getWeakSuccessors(p);
    }

    private BoolFormula simulation(Set<CCSProcess.CCS_Step> succS, Set<CCSProcess.CCS_Step> succT){
        /* For all s -a-> sp  exists t -a-> tp . sp R tp */
        HashSet<BoolFormula> subFormulaAND = new HashSet<>();
        for (CCSProcess.CCS_Step stepS : succS) {
            HashSet<BoolFormula> subFormulaOR = new HashSet<>();
            for (CCSProcess.CCS_Step stepT : succT) {
                if(getAction(stepS).equals(getAction(stepT))){
                    VarPair<CCSProcess> sptp = new OrderedVarPair(stepS.process,stepT.process);
                    addVariable(sptp);
                    subFormulaOR.add(getVariable(sptp));
                }
            }
            subFormulaAND.add(
                    new BoolFormula.And(subFormulaOR, CCS_Bisim_StaticEquationSystem.this)
            );
        }
        return new BoolFormula.Or(subFormulaAND, CCS_Bisim_StaticEquationSystem.this);
    }

    private String getAction(CCSProcess.CCS_Step step){
        return (step.inputQ == null || step.action.equals(TAU) || step.inputQ ? "" : "'") + step.action;
    }
}
