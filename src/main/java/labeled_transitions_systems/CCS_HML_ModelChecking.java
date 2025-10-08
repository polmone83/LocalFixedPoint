package labeled_transitions_systems;

import labeled_transitions_systems.CCS.CCSInterpreter;
import labeled_transitions_systems.CCS.CCSProcess;
import labeled_transitions_systems.HML.ActionSet;
import labeled_transitions_systems.HML.HMLBaseVisitor;
import labeled_transitions_systems.HML.HMLInterpreter;
import labeled_transitions_systems.HML.HMLParser;

import java.util.HashMap;
import java.util.HashSet;

/**
 * This class is useful to encode CCS-HML model checking problems
 * as a boolean equation system.
 * See {@link #getEquationSystem(String pName, String fName)}}
 */
public class CCS_HML_ModelChecking {
    HMLInterpreter hmlInterpreter;
    CCSInterpreter ccsInterpreter;
    HashMap<String,Integer> visited;
    HashMap<String,String> equations;
    Integer varCounter;


    public CCS_HML_ModelChecking(String specCode,String modelCode){
        this.hmlInterpreter = new HMLInterpreter(specCode,false);
        this.ccsInterpreter = new CCSInterpreter(modelCode,false);
    }

    public CCS_HML_ModelChecking(String specCode,String modelCode, boolean fileFlag){
        this.hmlInterpreter = new HMLInterpreter(specCode,fileFlag);
        this.ccsInterpreter = new CCSInterpreter(modelCode,fileFlag);
    }

    /**
     * Returns the system of equations with initial variable {@code (pName, fName)}
     * @param pName the name of a CCS process
     * @param fName the name of a HML formula
     * @return boolean system of equations modeling the model
     *         checking problem {@code pName |= fName}
     */
    public String getEquationSystem(String pName, String fName){
        visited = new HashMap<>();
        equations = new HashMap<>();
        varCounter = 0;
        CCSProcess process = ccsInterpreter.getProcess(pName);
        HMLParser.FormulaDeclContext formula = hmlInterpreter.getFormulaDef(fName);
        Node init = new Node(process,formula);
        init.makeEquation();

        // return equations as a string
        StringBuilder eqSys = new StringBuilder();
        // write comments
        eqSys.append("<!--\n");
        equations.forEach((String nodeVar, String rhs) ->{
            eqSys.append(nodeVar);
            eqSys.append(": ");
            eqSys.append(getVariable(nodeVar));
            eqSys.append("\n");
        });
        eqSys.append("-->\n");

        // write equations
        equations.forEach((String nodeVar, String rhs) ->{
            eqSys.append(getVariable(nodeVar));
            eqSys.append(" = ");
            eqSys.append(rhs);
            eqSys.append(";\n");
        });
        return eqSys.toString();
    }

    private void addVariable(String varName){
        if(!visited.containsKey(varName)){
            visited.put(varName,varCounter++);
        }
    }

    private String getVariable(String varName){
        if(visited.containsKey(varName)){
            return "x" + visited.get(varName);
        }else{
            throw new RuntimeException("The node variable " + varName + " was not found");
        }
    }

    private class Node extends HMLBaseVisitor<String> {
        public CCSProcess process;
        public HMLParser.FormulaContext formula;
        public boolean complement;

        public Node(CCSProcess process,HMLParser.FormulaDeclContext formulaDecl){
            this.process = process;
            if(formulaDecl instanceof HMLParser.MinDeclContext){
                this.complement = false;
                this.formula = ((HMLParser.MinDeclContext) formulaDecl).formula();
            }else {
                complement = true;
                this.formula = ((HMLParser.MaxDeclContext) formulaDecl).formula();
            }
        }

        public Node(CCSProcess process,HMLParser.FormulaContext formula, boolean complement){
            this.process = process;
            this.formula = formula;
            this.complement = complement;
        }

        @Override
        public String toString() {
            return "<"+ process.toString() + "," + formula.toString() + "," + complement +">";
        }


        /**
         * Add a new equation to the system if the node has not been explored yet
         * @return
         */
        public void makeEquation(){
            String var = this.toString();
            if(! visited.containsKey(var)){
                addVariable(var);
                String rhs = visit(this.formula); // visit the formula
                //System.out.println(var + ": " + rhs);
                equations.put(var,rhs);
            }
        }

        @Override
        public String visitOr(HMLParser.OrContext ctx) {
            Node left = new Node(process,ctx.left,complement);
            String leftVar = left.toString();
            Node right = new Node(process, ctx.right,complement);
            String rightVar = right.toString();
            //addVariable(leftVar);
            //addVariable(rightVar);

            // add equations for the successors
            left.makeEquation();
            right.makeEquation();

            String op = complement? " & " : " | ";

            return getVariable(leftVar) + op + getVariable(rightVar);
        }

        @Override
        public String visitAnd(HMLParser.AndContext ctx) {
            Node left = new Node(process,ctx.left,complement);
            String leftVar = left.toString();
            Node right = new Node(process, ctx.right,complement);
            String rightVar = right.toString();
            //addVariable(leftVar);
            //addVariable(rightVar);

            // add equations for the successors
            left.makeEquation();
            right.makeEquation();

            String op = complement? " | " : " & ";

            return getVariable(leftVar) + op + getVariable(rightVar);
        }

        @Override
        public String visitFormulaName(HMLParser.FormulaNameContext ctx) {
            Node node = new Node(process,hmlInterpreter.getFormulaDef(ctx.ID().getText()));
            String var = node.toString();
            //addVariable(var);

            node.makeEquation();

            return getVariable(var);
        }

        @Override
        public String visitTrue(HMLParser.TrueContext ctx) {
            return complement? "ff" : "tt";
        }

        @Override
        public String visitFalse(HMLParser.FalseContext ctx) {
            return complement? "tt" : "ff";
        }


        @Override
        public String visitStrongExists(HMLParser.StrongExistsContext ctx) {
            StringBuilder rhs = new StringBuilder();
            HashSet<String> subFormulas = new HashSet<>();

            // create the set of actions
            ActionSet actions = new ActionSet(hmlInterpreter, ctx.actions());

            boolean enter = false;
            for (CCSProcess.CCS_Step step : process.successors()) {
                // filter steps by actions
                if(actions.contains(step.inputQ, step.action)){
                    enter = true;
                    // visit successor nodes
                    Node node = new Node(step.process,ctx.formula(),complement);
                    String var = node.toString();
                    if(! subFormulas.contains(var)){
                        subFormulas.add(var);
                        node.makeEquation();

                        // update the rhs
                        rhs.append(getVariable(var));
                        rhs.append(complement? " & " : " | ");
                    }
                }
            }
            if (enter) {
                rhs.delete(rhs.length()-3, rhs.length());
            }else{
                rhs.append(complement? "tt" : "ff");
            }

            return rhs.toString();
        }

        @Override
        public String visitStrongForAll(HMLParser.StrongForAllContext ctx) {
            StringBuilder rhs = new StringBuilder();
            HashSet<String> subFormulas = new HashSet<>();

            // create the set of actions
            ActionSet actions = new ActionSet(hmlInterpreter, ctx.actions());
            boolean enter = false;
            for (CCSProcess.CCS_Step step : process.successors()) {
                // filter steps by actions
                if(actions.contains(step.inputQ, step.action)){
                    enter = true;
                    // visit successor nodes
                    Node node = new Node(step.process,ctx.formula(),complement);
                    String var = node.toString();
                    if(! subFormulas.contains(var)){
                        subFormulas.add(var);
                        node.makeEquation();

                        // update the rhs
                        rhs.append(getVariable(var));
                        rhs.append(complement? " | " : " & ");
                    }
                }
            }
            if (enter) {
                rhs.delete(rhs.length()-3, rhs.length());
            }else{
                rhs.append(complement? "ff" : "tt");
            }

            return rhs.toString();
        }

        @Override
        public String visitWeakExists(HMLParser.WeakExistsContext ctx) {
            StringBuilder rhs = new StringBuilder();
            HashSet<String> subFormulas = new HashSet<>();

            // create the set of actions
            ActionSet actions = new ActionSet(hmlInterpreter, ctx.actions());
            boolean enter = false;
            for (CCSProcess.CCS_Step step : process.weakSuccessors()) {
                // filter steps by actions
                if(actions.contains(step.inputQ,step.action)){
                    enter = true;
                    // visit successor nodes
                    Node node = new Node(step.process,ctx.formula(),complement);
                    String var = node.toString();
                    if(! subFormulas.contains(var)){
                        subFormulas.add(var);
                        node.makeEquation();

                        // update the rhs
                        rhs.append(getVariable(var));
                        rhs.append(complement? " & " : " | ");
                    }
                }
            }
            if (enter){
                rhs.delete(rhs.length()-3, rhs.length());
            }else{
                rhs.append(complement? "tt" : "ff");
            }

            return rhs.toString();
        }

        @Override
        public String visitWeakForAll(HMLParser.WeakForAllContext ctx) {
            StringBuilder rhs = new StringBuilder();
            HashSet<String> subFormulas = new HashSet<>();

            // create the set of actions
            ActionSet actions = new ActionSet(hmlInterpreter, ctx.actions());
            boolean enter = false;
            for (CCSProcess.CCS_Step step : process.weakSuccessors()) {
                // filter steps by actions
                if(actions.contains(step.inputQ, step.action)){
                    enter = true;
                    // visit successor nodes
                    Node node = new Node(step.process,ctx.formula(),complement);
                    String var = node.toString();
                    if(! subFormulas.contains(var)){
                        subFormulas.add(var);
                        node.makeEquation();

                        // update the rhs
                        rhs.append(getVariable(var));
                        rhs.append(complement? " | " : " & ");
                    }
                }
            }
            if (enter){
                rhs.delete(rhs.length()-3, rhs.length());
            }else{
                rhs.append(complement? "ff" : "tt");
            }

            return rhs.toString();
        }

        @Override
        public String visitParens(HMLParser.ParensContext ctx) {
            return visit(ctx.formula());
        }

    }





}
