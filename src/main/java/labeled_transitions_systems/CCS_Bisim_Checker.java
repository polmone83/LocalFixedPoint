package labeled_transitions_systems;

import bddRelations.booleanSystemsVisitors.ParsedBooleanSystem;
import core.VarPair;
import domains.boolDomain.BoolFormula;
import domains.boolDomain.BoolFormulaPP;
import labeled_transitions_systems.CCS.CCSInterpreter;
import labeled_transitions_systems.CCS.CCSProcess;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static labeled_transitions_systems.CCS.CCSProcess.TAU;


public class CCS_Bisim_Checker {

    CCSInterpreter ccsInterpreter;
    boolean strong;
    ArrayList<VarPair<CCSProcess>> todo;

    BufferedWriter writer;

    /**
     * Equations strings
     */
    //HashMap<String, String> eqs;

    /**
     * Track the nodes that have been visited and assigns them their index
     */
    HashMap<String, Integer> visitedNode;

    /**
     * Retrieve the node assigned to an index
     */
    HashMap<Integer, VarPair<CCSProcess>> nodesIndex;
    Integer varCounter;

    public ParsedBooleanSystem getSystem(
            String modelCode, boolean strong, boolean fileFlag,
            String p1, String p2) {
        this.ccsInterpreter = new CCSInterpreter(modelCode, fileFlag);
        this.strong = strong;

        //eqs = new HashMap<>();
        visitedNode = new HashMap<>();
        nodesIndex = new HashMap<>();
        varCounter = 0;
        VarPair<CCSProcess> init = new OrderedVarPair(
                ccsInterpreter.getProcess(p1),
                ccsInterpreter.getProcess(p2)
        );

        try {
            //System.out.println(modelCode + ".bs");
            writer = new BufferedWriter(new FileWriter(modelCode + ".bs"));
            todo = new ArrayList<>();
            addVariable(init);
            while (todo.size() > 0) {
                VarPair<CCSProcess> pair = todo.get(0);
                todo.remove(0);
                makeEquation(pair);
            }
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        /*// generate the code
        StringBuilder code = new StringBuilder();
        eqs.forEach(
                (var, rhs) -> {
                    code.append(var);
                    code.append(" = ");
                    code.append(rhs);
                    code.append(";\n");
                }
        );*/
        //System.out.println(code);
        // compile the code
        return new ParsedBooleanSystem(modelCode + ".bs", true);
    }

    private void addVariable(VarPair<CCSProcess> pair) {
        if (!visitedNode.containsKey(pair.toString())) {
            visitedNode.put(pair.toString(), varCounter);
            nodesIndex.put(varCounter, pair);
            varCounter++;
            todo.add(pair);
        }
    }

    private void makeEquation(VarPair<CCSProcess> pair) {
        //if (visitedNode.containsKey(pair.toString())) {
            //addVariable(pair);
            String rhs = makeFormula(pair); // visit the formula
            //System.out.println(var + ": " + rhs);
            //eqs.put(getVarName(pair), rhs);
            try {
                writer.append(getVarName(pair));
                writer.append(" = ");
                writer.append(rhs);
                writer.append("; \n");
            } catch (Exception e) {

            }
        //}
    }

    private String getVarName(VarPair<CCSProcess> pair) {
        Integer var = visitedNode.get(pair.toString());
        if (var == null)
            throw new RuntimeException("The node variable for " + pair + " was not found");

        return "x" + var;
    }

    private BoolFormula.Var getVariable(VarPair<CCSProcess> pair) {
        Integer var = visitedNode.get(pair.toString());
        if (var == null)
            throw new RuntimeException("The node variable for " + pair + " was not found");

        return new BoolFormula.Var(var, null);
    }


    private String makeFormula(VarPair<CCSProcess> pair) {
        CCSProcess s = pair.left;
        CCSProcess t = pair.right;
        Set<CCSProcess.CCS_Step> succS = getSuccessors(s);
        Set<CCSProcess.CCS_Step> succT = getSuccessors(t);

        BoolFormula rhsFormula = new BoolFormula.Or(
                simulation(succS, succT),
                simulation(succT, succS),
                null
        );
        // todo: simplify the formula

        // generate a parsable boolean expression
        BoolFormulaPP visitor = new BoolFormulaPP();
        return visitor.visit(rhsFormula).toString();
    }

    private Set<CCSProcess.CCS_Step> getSuccessors(CCSProcess p) {
        return strong ? p.successors() : p.weakSuccessors();
    }

    private BoolFormula simulation(Set<CCSProcess.CCS_Step> succS, Set<CCSProcess.CCS_Step> succT) {
        /* For all s -a-> sp  exists t -a-> tp . sp R tp */
        HashSet<BoolFormula> subFormulaAND = new HashSet<>();
        for (CCSProcess.CCS_Step stepS : succS) {
            HashSet<BoolFormula> subFormulaOR = new HashSet<>();
            for (CCSProcess.CCS_Step stepT : succT) {
                if (getAction(stepS).equals(getAction(stepT))) {
                    VarPair<CCSProcess> sptp = new OrderedVarPair(stepS.process, stepT.process);
                    addVariable(sptp); // add a new equation for the pair (sp,tp)
                    subFormulaOR.add(getVariable(sptp));
                }
            }
            subFormulaAND.add(
                    new BoolFormula.And(subFormulaOR, null)
            );
        }
        return new BoolFormula.Or(subFormulaAND, null);
    }

    private String getAction(CCSProcess.CCS_Step step) {
        return (step.inputQ == null || step.action.equals(TAU) || step.inputQ ? "" : "\'") + step.action;
    }
}
