package weighted_transitions_systems;

import bddRelations.BDDRel;
import bddRelations.BDDRelEquationSystem;
import core.LocalOracle;
import core.RightHandSide;
import core.SimpleVarSet;
import domains.weightDomain.WValue;
import domains.weightDomain.WeightedExpression;
import weighted_transitions_systems.WCCS.WCCSProcesSuccessor;
import weighted_transitions_systems.WCCS.WCCSProcess;
import weighted_transitions_systems.WCCS.WCCSInterpreter;
import weighted_transitions_systems.WCTL.WCTLBaseVisitor;
import weighted_transitions_systems.WCTL.WCTLInterpreter;
import weighted_transitions_systems.WCTL.WCTLParser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class WKS_CTL_EquationSystem extends BDDRelEquationSystem<WValue> {

    private final WeightedExpression zero, infinity;
    /**
     * The interpreter used to generate WCCS processes and their successors
     */
    private final WCCSInterpreter wccsInterpreter;
    private final WCTLInterpreter wctlInterpreter;

    private final WCCSProcesSuccessor succGenerator;
    /**
     * Track the nodes that have been visited and assigns them their index
     */
    private HashMap<String, Integer> visitedNode;

    /**
     * Retrieve the node assigned to an index
     */
    private HashMap<Integer, Node> nodesIndex;
    private Integer varCounter;

    public WKS_CTL_EquationSystem(String specCode, String modelCode){
        this(specCode, modelCode,false);
    }

    public WKS_CTL_EquationSystem(String specCode, String modelCode,
                                  boolean fileFlag) {
        zero = new WeightedExpression.Natural(0,this);
        infinity = new WeightedExpression.Infinity(this);
        // parse the WCTL formula
        this.wctlInterpreter = new WCTLInterpreter(specCode, fileFlag);
        // parse the WCCS code
        this.wccsInterpreter = new WCCSInterpreter(modelCode, fileFlag);
        // set the successor generator
        this.succGenerator = new WCCSProcesSuccessor();
    }

    public WValue localSolve(String pName, String fName, LocalOracle<Integer,WValue,BDDRel,SimpleVarSet> oracle) {
        visitedNode = new HashMap<>();
        nodesIndex = new HashMap<>();
        varCounter = 0;
        WCCSProcess process = wccsInterpreter.getProcess(pName);
        WCTLParser.FormulaContext phi = wctlInterpreter.getFormula(fName);
        Node inititialNode = new Node(process, phi);
        addVariable(inititialNode);
        return localSolve(visitedNode.get(inititialNode.toString()), WValue.infinity, oracle);
    }

    private void addVariable(Node node) {
        if (!visitedNode.containsKey(node.toString())) {
            visitedNode.put(node.toString(), varCounter);
            nodesIndex.put(varCounter, node);
            varCounter++;
        }
    }

    /**
     * Return the successors of the given process
     * @param p the process
     * @return the set of successor steps
     */
    private Set<WCCSProcess.WCCS_Step> getSuccessors(WCCSProcess p){
        /* Using the succGenerator instead of calling p.successors()
        allow to exploit cached successor sets and improve performance */
        return succGenerator.visit(p);
    }

    @Override
    protected WValue getBottomElement() {
        return new WValue.Infinity();
    }

    @Override
    public RightHandSide<Integer, WValue, SimpleVarSet> getRHS(Integer x) {
        // we assume the index has been encountered
        Node node = nodesIndex.get(x);
        if (node == null)
            throw new RuntimeException("The index is not known");

        return node.visit(node.formula);
    }

    @Override
    public Integer getIndex(String varName) {
        throw new RuntimeException("getIndex not implemented");
        //return 0;
    }

    @Override
    public String getLabel(Integer x) {
        //return x.toString();
        if (nodesIndex.containsKey(x)) {
            return x.toString(); //nodesIndex.get(x).toString();
        }
        return "NaL" + x;
    }

    private WeightedExpression.Var getVariable(Node node) {
        Integer var = visitedNode.get(node.toString());
        if (var == null)
            throw new RuntimeException("The node variable for " + node + " was not found");

        return new WeightedExpression.Var(var, this);
    }

    /**
     * WCTL formula visitor
     */
    private class Node extends WCTLBaseVisitor<WeightedExpression> {
        public WCCSProcess process;
        public WCTLParser.FormulaContext formula;
        public boolean symbolic;
        private String nodeID;

        public Node(WCCSProcess process, WCTLParser.FormulaContext formula) {
            this.process = process;
            this.formula = formula;
            this.symbolic = false; // concrete by default
        }

        public Node(WCCSProcess process, WCTLParser.FormulaContext formula, boolean symbolic) {
            this.process = process;
            this.formula = formula;
            this.symbolic = symbolic;
        }

        @Override
        public int hashCode() {
            return this.toString().hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Node n) {
                return this.toString().equals(n.toString());
            }
            return false;
        }

        @Override
        public String toString() {
            if(nodeID == null){
                nodeID = "<" + process.toString() + "," + formula.toString() + "," + symbolic + ">";
            }
            return nodeID;
        }

        // Implement the visitors

        @Override
        public WeightedExpression visitTrue(WCTLParser.TrueContext ctx) {
            return zero;
        }

        @Override
        public WeightedExpression visitFalse(WCTLParser.FalseContext ctx) {
            return infinity;
        }

        @Override
        public WeightedExpression visitAnd(WCTLParser.AndContext ctx) {
            /* Remark: here we are not introducing new variables in the system
               making right-hand-sides deeper. */
            WeightedExpression left = visit(ctx.left);
            WeightedExpression right = visit(ctx.right);
            return new WeightedExpression.Max(left, right, WKS_CTL_EquationSystem.this);
        }

        @Override
        public WeightedExpression visitOr(WCTLParser.OrContext ctx) {
            /* Remark: here we are not introducing new variables in the system
               making right-hand-sides deeper. */
            WeightedExpression left = visit(ctx.left);
            WeightedExpression right = visit(ctx.right);
            return new WeightedExpression.Min(left, right, WKS_CTL_EquationSystem.this);
        }

        @Override
        public WeightedExpression visitBoundedUniversalUntil(WCTLParser.BoundedUniversalUntilContext ctx) {
            // A phi U[<= WEIGHT] psi
            WCTLParser.FormulaContext phi = ctx.left;
            WCTLParser.FormulaContext psi = ctx.right;

            if (!symbolic) {
                // construct the cover edge
                Node n = new Node(process, formula, true);
                addVariable(n); // the same node but symbolic
                // the bound
                Integer w = Integer.parseInt(ctx.bound.getText());
                // return the bound expression
                return new WeightedExpression.Bound(w, getVariable(n),
                        WKS_CTL_EquationSystem.this);
            } else {
                Node phiNode = new Node(process, phi, false);
                addVariable(phiNode);
                Node psiNode = new Node(process, psi, false);
                addVariable(psiNode);
                HashSet<WeightedExpression> subExpressions = new HashSet<>();
                subExpressions.add(getVariable(phiNode));
                for (WCCSProcess.WCCS_Step step : getSuccessors(process)) {
                    Node succNode = new Node(step.process, this.formula, true);
                    addVariable(succNode);
                    // create the expression
                    WeightedExpression stepExpr =
                            new WeightedExpression.Add(step.weight, getVariable(succNode),
                                    WKS_CTL_EquationSystem.this);
                    subExpressions.add(stepExpr);
                }
                // compose the expression
                return new WeightedExpression.Min(getVariable(psiNode),
                        new WeightedExpression.Max(subExpressions, WKS_CTL_EquationSystem.this),
                        WKS_CTL_EquationSystem.this);

            }
        }

        @Override
        public WeightedExpression visitBoundedExistentialUntil(WCTLParser.BoundedExistentialUntilContext ctx) {
            // E phi U[<= WEIGHT] psi
            WCTLParser.FormulaContext phi = ctx.left;
            WCTLParser.FormulaContext psi = ctx.right;

            if (!symbolic) {
                // construct the cover edge
                Node n = new Node(process, formula, true);
                addVariable(n); // the same node but symbolic
                // the bound
                Integer w = Integer.parseInt(ctx.bound.getText());
                // return the bound expression
                return new WeightedExpression.Bound(w, getVariable(n),
                        WKS_CTL_EquationSystem.this);
            } else {
                Node phiNode = new Node(process, phi, false);
                addVariable(phiNode);
                Node psiNode = new Node(process, psi, false);
                addVariable(psiNode);

                HashSet<WeightedExpression> subExpressions = new HashSet<>();
                subExpressions.add(getVariable(psiNode));
                for (WCCSProcess.WCCS_Step step : getSuccessors(process)) {
                    Node succNode = new Node(step.process, this.formula, true);
                    addVariable(succNode);

                    // create the expression
                    WeightedExpression stepExpr =
                            new WeightedExpression.Add(step.weight, getVariable(succNode),
                                    WKS_CTL_EquationSystem.this);
                    subExpressions.add(
                            new WeightedExpression.Max(
                                    getVariable(phiNode),
                                    stepExpr,
                                    WKS_CTL_EquationSystem.this
                            )
                    );
                }
                return new WeightedExpression.Min(subExpressions, WKS_CTL_EquationSystem.this);
            }
        }

        @Override
        public WeightedExpression visitBoundedUniversalFinally(WCTLParser.BoundedUniversalFinallyContext ctx) {
            // AF[<= WEIGHT] psi
            WCTLParser.FormulaContext psi = ctx.formula();

            if (!symbolic) {
                // construct the cover edge
                Node n = new Node(process, formula, true);
                addVariable(n); // the same node but symbolic
                // the bound
                Integer w = Integer.parseInt(ctx.bound.getText());
                // return the bound expression
                return new WeightedExpression.Bound(w, getVariable(n),
                        WKS_CTL_EquationSystem.this);
            } else {
                Node psiNode = new Node(process, psi, false);
                addVariable(psiNode);
                HashSet<WeightedExpression> subExpressions = new HashSet<>();
                for (WCCSProcess.WCCS_Step step : getSuccessors(process)) {
                    Node succNode = new Node(step.process, this.formula, true);
                    addVariable(succNode);
                    // create the expression
                    WeightedExpression stepExpr =
                            new WeightedExpression.Add(step.weight, getVariable(succNode),
                                    WKS_CTL_EquationSystem.this);
                    subExpressions.add(stepExpr);
                }
                // compose the expression
                return new WeightedExpression.Min(getVariable(psiNode),
                        new WeightedExpression.Max(subExpressions, WKS_CTL_EquationSystem.this),
                        WKS_CTL_EquationSystem.this);

            }
        }

        @Override
        public WeightedExpression visitBoundedExistentialFinally(WCTLParser.BoundedExistentialFinallyContext ctx) {
            // EF[<= WEIGHT] psi
            WCTLParser.FormulaContext psi = ctx.formula();

            if (!symbolic) {
                // construct the cover edge
                Node n = new Node(process, formula, true);
                addVariable(n); // the same node but symbolic
                // the bound
                Integer w = Integer.parseInt(ctx.bound.getText());
                // return the bound expression
                return new WeightedExpression.Bound(w, getVariable(n),
                        WKS_CTL_EquationSystem.this);
            } else {
                Node psiNode = new Node(process, psi, false);
                addVariable(psiNode);

                HashSet<WeightedExpression> subExpressions = new HashSet<>();
                subExpressions.add(getVariable(psiNode));
                for (WCCSProcess.WCCS_Step step : getSuccessors(process)) {
                    Node succNode = new Node(step.process, this.formula, true);
                    addVariable(succNode);

                    // create the expression
                    WeightedExpression stepExpr =
                            new WeightedExpression.Add(step.weight, getVariable(succNode),
                                    WKS_CTL_EquationSystem.this);
                    subExpressions.add(stepExpr);
                }
                return new WeightedExpression.Min(subExpressions, WKS_CTL_EquationSystem.this);
            }
        }

        @Override
        public WeightedExpression visitUniversalUntil(WCTLParser.UniversalUntilContext ctx) {
            // A phi U[<= WEIGHT] psi
            WCTLParser.FormulaContext phi = ctx.left;
            WCTLParser.FormulaContext psi = ctx.right;

            if (symbolic)
                throw new RuntimeException("The formula " + ctx + "cannot be symbolic");

            Node phiNode = new Node(process, phi);
            addVariable(phiNode);
            Node psiNode = new Node(process, psi);
            addVariable(psiNode);
            HashSet<WeightedExpression> subExpressions = new HashSet<>();
            subExpressions.add(getVariable(phiNode));
            for (WCCSProcess.WCCS_Step step : getSuccessors(process)) {
                Node succNode = new Node(step.process, this.formula);
                addVariable(succNode);
                subExpressions.add(getVariable(succNode));
            }
            // compose the expression
            return new WeightedExpression.Min(getVariable(psiNode),
                    new WeightedExpression.Max(subExpressions, WKS_CTL_EquationSystem.this),
                    WKS_CTL_EquationSystem.this);


        }

        @Override
        public WeightedExpression visitExistentialUntil(WCTLParser.ExistentialUntilContext ctx) {
            // E phi U[<= WEIGHT] psi
            WCTLParser.FormulaContext phi = ctx.left;
            WCTLParser.FormulaContext psi = ctx.right;

            if (symbolic)
                throw new RuntimeException("The formula " + ctx + "cannot be symbolic");


            Node phiNode = new Node(process, phi);
            addVariable(phiNode);
            Node psiNode = new Node(process, psi);
            addVariable(psiNode);

            HashSet<WeightedExpression> subExpressions = new HashSet<>();
            subExpressions.add(getVariable(psiNode));
            for (WCCSProcess.WCCS_Step step : getSuccessors(process)) {
                Node succNode = new Node(step.process, this.formula);
                addVariable(succNode);

                subExpressions.add(
                        new WeightedExpression.Max(
                                getVariable(phiNode),
                                getVariable(succNode),
                                WKS_CTL_EquationSystem.this
                        )
                );
            }
            return new WeightedExpression.Min(subExpressions, WKS_CTL_EquationSystem.this);
        }

        @Override
        public WeightedExpression visitBoundedExistentialNext(WCTLParser.BoundedExistentialNextContext ctx) {
            // EX[<= WEIGHT] phi
            WCTLParser.FormulaContext phi = ctx.formula();
            Integer bound = Integer.parseInt(ctx.WEIGHT().getText());

            if (symbolic)
                throw new RuntimeException("The formula " + ctx + "cannot be symbolic");

            Node phiNode = new Node(process, phi);
            addVariable(phiNode);

            HashSet<WeightedExpression> subExpressions = new HashSet<>();
            for (WCCSProcess.WCCS_Step step : getSuccessors(process)) {
                if (step.weight <= bound) {
                    Node succNode = new Node(step.process, ctx.formula());
                    addVariable(succNode);

                    subExpressions.add(getVariable(succNode));
                }
            }
            return new WeightedExpression.Min(subExpressions, WKS_CTL_EquationSystem.this);

        }

        @Override
        public WeightedExpression visitExistentialNext(WCTLParser.ExistentialNextContext ctx) {
            // EX phi
            WCTLParser.FormulaContext phi = ctx.formula();

            if (symbolic)
                throw new RuntimeException("The formula " + ctx + "cannot be symbolic");

            Node phiNode = new Node(process, phi);
            addVariable(phiNode);

            HashSet<WeightedExpression> subExpressions = new HashSet<>();
            for (WCCSProcess.WCCS_Step step : getSuccessors(process)) {
                Node succNode = new Node(step.process, ctx.formula());
                addVariable(succNode);

                subExpressions.add(getVariable(succNode));
            }
            return new WeightedExpression.Min(subExpressions, WKS_CTL_EquationSystem.this);
        }

        @Override
        public WeightedExpression visitBoundedUniversalNext(WCTLParser.BoundedUniversalNextContext ctx) {
            // AX[<= WEIGHT] phi
            WCTLParser.FormulaContext phi = ctx.formula();
            Integer bound = Integer.parseInt(ctx.WEIGHT().getText());

            if (symbolic)
                throw new RuntimeException("The formula " + ctx + "cannot be symbolic");

            Node phiNode = new Node(process, phi);
            addVariable(phiNode);

            HashSet<WeightedExpression> subExpressions = new HashSet<>();
            for (WCCSProcess.WCCS_Step step : getSuccessors(process)) {
                if (step.weight <= bound) {
                    Node succNode = new Node(step.process, ctx.formula());
                    addVariable(succNode);
                    subExpressions.add(getVariable(succNode));
                }
            }

            return new WeightedExpression.Max(subExpressions, WKS_CTL_EquationSystem.this);
        }

        @Override
        public WeightedExpression visitUniversalNext(WCTLParser.UniversalNextContext ctx) {
            // AX phi
            WCTLParser.FormulaContext phi = ctx.formula();

            if (symbolic)
                throw new RuntimeException("The formula " + ctx + "cannot be symbolic");

            Node phiNode = new Node(process, phi);
            addVariable(phiNode);

            HashSet<WeightedExpression> subExpressions = new HashSet<>();
            for (WCCSProcess.WCCS_Step step : getSuccessors(process)) {
                Node succNode = new Node(step.process, ctx.formula());
                addVariable(succNode);

                subExpressions.add(getVariable(succNode));
            }
            return new WeightedExpression.Max(subExpressions, WKS_CTL_EquationSystem.this);

        }

        @Override
        public WeightedExpression visitExistentialFinally(WCTLParser.ExistentialFinallyContext ctx) {
            // EF psi
            WCTLParser.FormulaContext psi = ctx.formula();

            if (symbolic)
                throw new RuntimeException("The formula " + ctx + "cannot be symbolic");


            Node psiNode = new Node(process, psi);
            addVariable(psiNode);

            HashSet<WeightedExpression> subExpressions = new HashSet<>();
            subExpressions.add(getVariable(psiNode));
            for (WCCSProcess.WCCS_Step step : getSuccessors(process)) {
                Node succNode = new Node(step.process, this.formula);
                addVariable(succNode);

                subExpressions.add(getVariable(succNode));
            }
            return new WeightedExpression.Min(subExpressions, WKS_CTL_EquationSystem.this);

        }

        @Override
        public WeightedExpression visitUniversalFinally(WCTLParser.UniversalFinallyContext ctx) {
            // AF psi
            WCTLParser.FormulaContext psi = ctx.formula();

            if (symbolic)
                throw new RuntimeException("The formula " + ctx + "cannot be symbolic");


            Node psiNode = new Node(process, psi);
            addVariable(psiNode);

            HashSet<WeightedExpression> subExpressions = new HashSet<>();
            subExpressions.add(getVariable(psiNode));
            for (WCCSProcess.WCCS_Step step : getSuccessors(process)) {
                Node succNode = new Node(step.process, this.formula);
                addVariable(succNode);

                subExpressions.add(getVariable(succNode));
            }
            return new WeightedExpression.Max(subExpressions, WKS_CTL_EquationSystem.this);

        }

        @Override
        public WeightedExpression visitParens(WCTLParser.ParensContext ctx) {
            return visit(ctx.formula());
        }

        @Override
        public WeightedExpression visitProposition(WCTLParser.PropositionContext ctx) {
            Integer arity = process.getLabels().getOrDefault(ctx.ID().getText(),0);
            return arity > 0 ? zero : infinity;
        }

        @Override
        public WeightedExpression visitRelExpression(WCTLParser.RelExpressionContext ctx) {
            AtomicExprEvaluator evaluator = new AtomicExprEvaluator(process);
            Integer left = evaluator.visit(ctx.expr(0));
            Integer right = evaluator.visit(ctx.expr(1));

            boolean sat = false;

            switch (ctx.rel.getText()){
                case "<": sat = left < right; break;
                case "<=": sat = left <= right; break;
                case ">": sat = left > right; break;
                case ">=": sat = left >= right; break;
                case "!=": sat = left != right; break;
                case "==": sat = left == right; break;
                default: throw new RuntimeException("Unknown relation in line " + ctx.rel.getLine());
            }

            return sat ? zero : infinity;
        }
    }

    private class AtomicExprEvaluator extends WCTLBaseVisitor<Integer>{
        WCCSProcess process;

        public AtomicExprEvaluator(WCCSProcess process){
            this.process = process;
        }

        @Override
        public Integer visitMult(WCTLParser.MultContext ctx) {
            return visit(ctx.expr(0)) * visit(ctx.expr(1));
        }

        @Override
        public Integer visitDiv(WCTLParser.DivContext ctx) {
            return Math.floorDiv(visit(ctx.expr(0)),visit(ctx.expr(1)));
        }

        @Override
        public Integer visitPlus(WCTLParser.PlusContext ctx) {
            return visit(ctx.expr(0)) + visit(ctx.expr(1));
        }

        @Override
        public Integer visitMinus(WCTLParser.MinusContext ctx) {
            return visit(ctx.expr(0)) - visit(ctx.expr(1));
        }

        @Override
        public Integer visitInverse(WCTLParser.InverseContext ctx) {
            return - visit(ctx.expr());
        }

        @Override
        public Integer visitParensExpr(WCTLParser.ParensExprContext ctx) {
            return visit(ctx.expr());
        }

        @Override
        public Integer visitPropVar(WCTLParser.PropVarContext ctx) {
            return process.getLabels().getOrDefault(ctx.ID().getText(),0);
        }

        @Override
        public Integer visitWeight(WCTLParser.WeightContext ctx) {
            return Integer.parseInt(ctx.WEIGHT().getText());
        }
    }
}
