package labeled_transitions_systems;

import bddRelations.BDDRelEquationSystem;
import bddRelations.BDDRelStaticEquationSystem;
import bddRelations.soundOracles.BDDRelOracle;
import core.RightHandSide;
import core.SimpleVarSet;
import core.VarPair;
import domains.boolDomain.BoolFormula;
import domains.boolDomain.BoolFormulaSimplify;
import labeled_transitions_systems.CCS.CCSInterpreter;
import labeled_transitions_systems.CCS.CCSProcess;
import labeled_transitions_systems.CCS.CCSProcessSuccessor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static labeled_transitions_systems.CCS.CCSProcess.TAU;

public class CCS_Bisim_EquationSystemUpTo extends BDDRelEquationSystem<Boolean> {

    CCSInterpreter ccsInterpreter;
    CCSProcessSuccessor succGenerator;
    boolean strong;

    HashSet<BoolFormula> collectedFormulas;
    /**
     * Track the nodes that have been visited and assigns them their index
     */
    HashMap<String,Integer> visitedNode;
    /**
     * Retrieve the node assigned to an index
     */
    HashMap<Integer, VarPair<CCSProcess>> nodesIndex;
    Integer varCounter;

    public CCS_Bisim_EquationSystemUpTo(String modelCode, boolean strong, boolean fileFlag){
        this.ccsInterpreter = new CCSInterpreter(modelCode,fileFlag);
        this.succGenerator = new CCSProcessSuccessor();
        this.strong = strong;
        this.collectedFormulas = new HashSet<>();
    }

    public Boolean localSolve(String p1, String p2, BDDRelOracle<Boolean> oracle) {
        visitedNode = new HashMap<>();
        nodesIndex = new HashMap<>();
        varCounter = 0;
        VarPair<CCSProcess> init = new OrderedVarPair(
                ccsInterpreter.getProcess(p1),
                ccsInterpreter.getProcess(p2)
        );
        addVariable(init);
        return localSolve(visitedNode.get(init.toString()),false,oracle);
    }

    private void addVariable(VarPair<CCSProcess> pair){
        if(! visitedNode.containsKey(pair.toString())){
            visitedNode.put(pair.toString(),varCounter);
            nodesIndex.put(varCounter,pair);
            varCounter++;
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



    @Override
    public RightHandSide<Integer, Boolean, SimpleVarSet> getRHS(Integer x) {
        // we assume the index has been encountered
        VarPair<CCSProcess> pair = nodesIndex.get(x);
        if(pair == null)
            throw new RuntimeException("The index is not known");

        collectedFormulas.add(new BoolFormula.Var(x, this));
        return makeFormula(pair);
    }

    private BoolFormula makeFormula(VarPair<CCSProcess> pair) {
        CCSProcess s = pair.left;
        CCSProcess t = pair.right;
        Set<CCSProcess.CCS_Step> succS = getSuccessors(s);
        Set<CCSProcess.CCS_Step> succT = getSuccessors(t);
        BoolFormula pairformula = new BoolFormula.Or(
                simulation(succS,succT),
                simulation(succT,succS),
                CCS_Bisim_EquationSystemUpTo.this
                );
        return uptoClosure(BoolFormulaSimplify.simplify(pairformula));
    }

    private BoolFormula uptoClosure(BoolFormula phi){
        if(phi instanceof BoolFormula.Or psi){
            HashSet<BoolFormula> psiSubs = new HashSet<>();
            for (BoolFormula psiSub : psi.subformulas) {
                if(!collectedFormulas.contains(psiSub)){
                    psiSubs.add(psiSub);
                    collectedFormulas.add(psiSub);
                }
            }
            if(psiSubs.size() == 0){
                return new BoolFormula.False(this);
            }else if(psiSubs.size() == 1){
                return psiSubs.iterator().next();
            }else{
                return new BoolFormula.Or(psiSubs,this);
            }
        }
        // nothing to remove
        collectedFormulas.add(phi);
        return phi;
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
                    new BoolFormula.And(subFormulaOR, CCS_Bisim_EquationSystemUpTo.this)
            );
        }
        return new BoolFormula.Or(subFormulaAND, CCS_Bisim_EquationSystemUpTo.this);
    }

    private String getAction(CCSProcess.CCS_Step step){
        return (step.inputQ == null || step.action.equals(TAU) || step.inputQ ? "" : "'") + step.action;
    }




    /**
     * Returns the variable index associated with the given label
     *
     * @param varName the label naming the variable
     * @return the index associated with the label
     */
    @Override
    public Integer getIndex(String varName) {
        throw new RuntimeException("getIndex not implemented");
        //return 0;
    }

    /**
     * Returns the label associated with the given variable index
     *
     * @param x the index of a variable in the system
     * @return the label associated with the variable
     */
    @Override
    public String getLabel(Integer x) {
        //return x.toString();
        if(nodesIndex.containsKey(x)){
            return x.toString(); //nodesIndex.get(x).toString();
        }
        return "NaL"+x;
    }
}
