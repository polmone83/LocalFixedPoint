package labeled_transitions_systems;

import bddRelations.BDDRelEquationSystem;
import bddRelations.soundOracles.BDDRelOracle;
import core.RightHandSide;
import core.SimpleVarSet;
import domains.boolDomain.BoolFormula;
import domains.boolDomain.BoolFormulaSimplify;
import labeled_transitions_systems.CCS.CCSInterpreter;
import labeled_transitions_systems.CCS.CCSProcess;
import labeled_transitions_systems.CCS.CCSProcessSuccessor;
import labeled_transitions_systems.HML.ActionSet;
import labeled_transitions_systems.HML.HMLBaseVisitor;
import labeled_transitions_systems.HML.HMLInterpreter;
import labeled_transitions_systems.HML.HMLParser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class CCS_HML_EquationSystem extends BDDRelEquationSystem<Boolean> {
    HMLInterpreter hmlInterpreter;
    CCSInterpreter ccsInterpreter;
    CCSProcessSuccessor succGenerator;

    /**
     * Track the nodes that have been visited and assigns them their index
     */
    HashMap<Node,Integer> visitedNode;
    /**
     * Retrieve the node assigned to an index
     */
    HashMap<Integer,Node> nodesIndex;
    Integer varCounter;

    public CCS_HML_EquationSystem(String specCode, String modelCode){
        this.hmlInterpreter = new HMLInterpreter(specCode,false);
        this.ccsInterpreter = new CCSInterpreter(modelCode,false);
        this.succGenerator = new CCSProcessSuccessor();
        //initUniverse(new BDDRelUniverse((int)Math.pow(2,11),(int)Math.pow(2,11)));
    }

    public Boolean localSolve(String pName, String fName, Boolean bot, BDDRelOracle<Boolean> oracle) {
        visitedNode = new HashMap<>();
        nodesIndex = new HashMap<>();
        varCounter = 0;
        CCSProcess process = ccsInterpreter.getProcess(pName);
        HMLParser.FormulaDeclContext formula = hmlInterpreter.getFormulaDef(fName);
        Node init = new Node(process,formula);
        addVariable(init);
        return localSolve(visitedNode.get(init), bot, oracle);
    }

    private Set<CCSProcess.CCS_Step> getSuccessors(CCSProcess p){
        return succGenerator.getSuccessors(p);
    }

    private Set<CCSProcess.CCS_Step> getWeakSuccessors(CCSProcess p){
        return succGenerator.getWeakSuccessors(p);
    }

    private void addVariable(Node node){
        if(! visitedNode.containsKey(node)){
            visitedNode.put(node,varCounter);
            nodesIndex.put(varCounter,node);
            varCounter++;
        }
    }

    private BoolFormula.Var getVariable(Node node){
        Integer var = visitedNode.get(node);
        if(var == null)
            throw new RuntimeException("The node variable for " + node + " was not found");

        return new BoolFormula.Var(var,this);
    }

    @Override
    protected Boolean getBottomElement() {
        return false;
    }

    @Override
    public RightHandSide<Integer, Boolean, SimpleVarSet> getRHS(Integer x) {
        // we assume the index has been encountered
        Node node = nodesIndex.get(x);
        if(node == null)
            throw new RuntimeException("The index is not known");

        return BoolFormulaSimplify.simplify(node.visit(node.formula));
    }

    @Override
    public Integer getIndex(String varName) {
        throw new RuntimeException("getIndex not implemented");
        //return 0;
    }

    @Override
    public String getLabel(Integer x) {
        //return x.toString();
        if(nodesIndex.containsKey(x)){
            return x.toString(); //nodesIndex.get(x).toString();
        }
        return "NaL"+x;
    }

    private class Node extends HMLBaseVisitor<BoolFormula> {
        public CCSProcess process;
        public HMLParser.FormulaContext formula;
        public boolean complement;
        private String nodeID;

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
        public int hashCode() {
            return this.toString().hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return this.toString().equals(obj.toString());
        }

        @Override
        public String toString() {
            if (nodeID == null) {
                nodeID = "<" + process.toString() + "," + formula.toString() + "," + complement + ">";
            }
            return nodeID;
        }

        @Override
        public BoolFormula visitOr(HMLParser.OrContext ctx) {
            Node left = new Node(process,ctx.left,complement);
            Node right = new Node(process, ctx.right,complement);
            addVariable(left);
            addVariable(right);

            if(complement){
                return new BoolFormula.And(getVariable(left),getVariable(right),CCS_HML_EquationSystem.this);
            }else{
                return new BoolFormula.Or(getVariable(left),getVariable(right),CCS_HML_EquationSystem.this);
            }
        }

        @Override
        public BoolFormula visitAnd(HMLParser.AndContext ctx) {
            Node left = new Node(process,ctx.left,complement);
            Node right = new Node(process, ctx.right,complement);
            addVariable(left);
            addVariable(right);

            if(! complement){
                return new BoolFormula.And(getVariable(left),getVariable(right),CCS_HML_EquationSystem.this);
            }else{
                return new BoolFormula.Or(getVariable(left),getVariable(right),CCS_HML_EquationSystem.this);
            }
        }

        @Override
        public BoolFormula visitFormulaName(HMLParser.FormulaNameContext ctx) {
            Node node = new Node(process,hmlInterpreter.getFormulaDef(ctx.ID().getText()));
            addVariable(node);

            return getVariable(node);
        }

        @Override
        public BoolFormula visitTrue(HMLParser.TrueContext ctx) {
            if(complement){
                return new BoolFormula.False(CCS_HML_EquationSystem.this);
            }else{
                return new BoolFormula.True(CCS_HML_EquationSystem.this);
            }
        }

        @Override
        public BoolFormula visitFalse(HMLParser.FalseContext ctx) {
            if(complement){
                return new BoolFormula.True(CCS_HML_EquationSystem.this);
            }else{
                return new BoolFormula.False(CCS_HML_EquationSystem.this);
            }
        }

        @Override
        public BoolFormula visitStrongExists(HMLParser.StrongExistsContext ctx) {
            HashSet<BoolFormula> subFormulas = new HashSet<>();
            HashSet<String> subNodes = new HashSet<>();

            // create the set of actions
            ActionSet actions = new ActionSet(hmlInterpreter, ctx.actions());


            for (CCSProcess.CCS_Step step : getSuccessors(process)) {
                // filter steps by actions
                if(actions.contains(step.inputQ, step.action)){
                    // visit successor nodes
                    Node node = new Node(step.process,ctx.formula(),complement);
                    if(! subNodes.contains(node.toString())){
                        subNodes.add(node.toString());
                        addVariable(node);
                        subFormulas.add(getVariable(node));
                    }
                }
            }

            if(complement){
                return new BoolFormula.And(subFormulas,CCS_HML_EquationSystem.this);
            }else{
                return new BoolFormula.Or(subFormulas,CCS_HML_EquationSystem.this);
            }
        }

        @Override
        public BoolFormula visitStrongForAll(HMLParser.StrongForAllContext ctx) {
            HashSet<BoolFormula> subFormulas = new HashSet<>();
            HashSet<String> subNodes = new HashSet<>();

            // create the set of actions
            ActionSet actions = new ActionSet(hmlInterpreter, ctx.actions());

            for (CCSProcess.CCS_Step step : getSuccessors(process)) {
                // filter steps by actions
                if(actions.contains(step.inputQ, step.action)){
                    // visit successor nodes
                    Node node = new Node(step.process,ctx.formula(),complement);
                    if(! subNodes.contains(node.toString())){
                        subNodes.add(node.toString());
                        addVariable(node);
                        subFormulas.add(getVariable(node));
                    }
                }
            }

            if(complement){
                return new BoolFormula.Or(subFormulas,CCS_HML_EquationSystem.this);
            }else{
                return new BoolFormula.And(subFormulas,CCS_HML_EquationSystem.this);
            }
        }

        @Override
        public BoolFormula visitWeakExists(HMLParser.WeakExistsContext ctx) {
            HashSet<BoolFormula> subFormulas = new HashSet<>();
            HashSet<String> subNodes = new HashSet<>();

            // create the set of actions
            ActionSet actions = new ActionSet(hmlInterpreter, ctx.actions());

            for (CCSProcess.CCS_Step step : getWeakSuccessors(process)) {
                // filter steps by actions
                if(actions.contains(step.inputQ, step.action)){
                    // visit successor nodes
                    Node node = new Node(step.process,ctx.formula(),complement);
                    if(! subNodes.contains(node.toString())){
                        subNodes.add(node.toString());
                        addVariable(node);
                        subFormulas.add(getVariable(node));
                    }
                }
            }

            if(complement){
                return new BoolFormula.And(subFormulas,CCS_HML_EquationSystem.this);
            }else{
                return new BoolFormula.Or(subFormulas,CCS_HML_EquationSystem.this);
            }
        }

        @Override
        public BoolFormula visitWeakForAll(HMLParser.WeakForAllContext ctx) {
            HashSet<BoolFormula> subFormulas = new HashSet<>();
            HashSet<String> subNodes = new HashSet<>();

            // create the set of actions
            ActionSet actions = new ActionSet(hmlInterpreter, ctx.actions());

            for (CCSProcess.CCS_Step step : getWeakSuccessors(process)) {
                // filter steps by actions
                if(actions.contains(step.inputQ, step.action)){
                    // visit successor nodes
                    Node node = new Node(step.process,ctx.formula(),complement);
                    if(! subNodes.contains(node.toString())){
                        subNodes.add(node.toString());
                        addVariable(node);
                        subFormulas.add(getVariable(node));
                    }
                }
            }

            if(complement){
                return new BoolFormula.Or(subFormulas,CCS_HML_EquationSystem.this);
            }else{
                return new BoolFormula.And(subFormulas,CCS_HML_EquationSystem.this);
            }
        }

        @Override
        public BoolFormula visitParens(HMLParser.ParensContext ctx) {
            return visit(ctx.formula());
        }

    }

}
