package domains.boolDomain;

import core.Assignment;
import core.EquationSystem;
import core.VarRelation;
import core.VarSet;
import domains.boolDomain.parser.BooleanSystemsBaseVisitor;
import domains.boolDomain.parser.BooleanSystemsParser;

public class EvalVisitor<V,R extends VarRelation<V,S>, S extends VarSet<V>> extends BooleanSystemsBaseVisitor<Boolean> {
    Assignment<V, Boolean> ass;
    EquationSystem<V,Boolean,R,S> system;
    public EvalVisitor(Assignment<V,Boolean> ass,
                       EquationSystem<V,Boolean,R,S> system){
        this.ass = ass;
        this.system = system;
    }

    @Override
    public Boolean visitVar(BooleanSystemsParser.VarContext ctx) {
        V var = system.getIndex(ctx.ID().getText());
        return ass.getValue(var);
    }

    @Override
    public Boolean visitParens(BooleanSystemsParser.ParensContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Boolean visitOr(BooleanSystemsParser.OrContext ctx) {
        return visit(ctx.left) || visit(ctx.right);
    }

    @Override
    public Boolean visitAnd(BooleanSystemsParser.AndContext ctx) {
        return visit(ctx.left) && visit(ctx.right);
    }

    @Override
    public Boolean visitFalse(BooleanSystemsParser.FalseContext ctx) {
        return false;
    }

    @Override
    public Boolean visitTrue(BooleanSystemsParser.TrueContext ctx) {
        return true;
    }
}
