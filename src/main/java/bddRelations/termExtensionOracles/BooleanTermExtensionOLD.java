package bddRelations.termExtensionOracles;

import bddRelations.BDDRel;
import bddRelations.BDDRelEquationSystem;
import core.Assignment;
import core.SimpleVarSet;
import domains.boolDomain.EvalVisitor;
import domains.boolDomain.parser.BooleanSystemsBaseVisitor;
import domains.boolDomain.parser.BooleanSystemsParser;

public class BooleanTermExtensionOLD extends BooleanSystemsBaseVisitor<SimpleVarSet> {

    private final BDDRel relation;
    private final BDDRelEquationSystem<Boolean> system;
    private final EvalVisitor<Integer,BDDRel, SimpleVarSet> eval;

    public BooleanTermExtensionOLD(BDDRelEquationSystem<Boolean> system,
                                   BDDRel relation, Assignment<Integer,Boolean> ass) {
        this.relation = relation;
        this.eval = new EvalVisitor<>(ass,system);
        this.system = system;
    }

    @Override
    public SimpleVarSet visitVar(BooleanSystemsParser.VarContext ctx) {
        //  return the relation {x : (x,var) \in R } x V
        Integer var = system.getIndex(ctx.ID().getText());
        return relation.sliceLeft(var);
    }

    @Override
    public SimpleVarSet visitAnd(BooleanSystemsParser.AndContext ctx) {
        SimpleVarSet leftSet, rightSet;
        Boolean leftval, rightval;

        leftSet = visit(ctx.left);
        leftval = eval.visit(ctx.left);
        if(leftSet.isEmpty() && leftval.equals(false)){
            // the left part is false and does not change now
            return new SimpleVarSet(); // empty set
        }

        rightSet = visit(ctx.right);
        rightval = eval.visit(ctx.right);
        if(rightSet.isEmpty() && rightval.equals(false)){
            // the right part is false and does not change now
            return new SimpleVarSet(); // empty set
        }

        if(leftval.equals(true) && rightval.equals(true)){
            return new SimpleVarSet();
        }

        if(leftval.equals(true)){
            return rightSet; // can't be empty
        }

        if(rightval.equals(true)){
            return leftSet; // can't be empty
        }

        leftSet.unionWith(rightSet);

        return leftSet;
    }

    @Override
    public SimpleVarSet visitOr(BooleanSystemsParser.OrContext ctx) {
        Boolean leftval = eval.visit(ctx.left);
        Boolean rightval = eval.visit(ctx.right);
        if(leftval || rightval){
            return new SimpleVarSet();
        }
        // both sub-terms evaluate to false
        SimpleVarSet ret = visit(ctx.left);
        ret.unionWith(visit(ctx.right));
        return ret;
    }

    @Override
    public SimpleVarSet visitParens(BooleanSystemsParser.ParensContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public SimpleVarSet visitFalse(BooleanSystemsParser.FalseContext ctx) {
        return new SimpleVarSet();
    }

    @Override
    public SimpleVarSet visitTrue(BooleanSystemsParser.TrueContext ctx) {
        return new SimpleVarSet();
    }
}
