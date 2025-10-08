package bddRelations.booleanSystemsVisitors;

import bddRelations.BDDRel;
import bddRelations.BDDRelEquationSystem;
import core.SimpleVarSet;
import domains.boolDomain.parser.BooleanSystemsBaseVisitor;
import domains.boolDomain.parser.BooleanSystemsParser;

/**
 * Implements a visitor that calculates a BDDRel representing the relation vars(t) x V
 * where t is the visited boolean term.
 */
public class BDDRelVarsVisitor extends BooleanSystemsBaseVisitor<SimpleVarSet> {

    /**
     * Where the variables in vars(t) will be collected
     */
    private SimpleVarSet vars;
    /**
     * The equation system where the term comes from.
     * It is used to retrieve the BDDRelUniverse used for representing variables
     */
    private BDDRelEquationSystem<Boolean> system;

    public BDDRelVarsVisitor(BDDRelEquationSystem<Boolean> system){
        this.system = system;
        vars = new SimpleVarSet();
    }

    @Override
    public SimpleVarSet visitTrue(BooleanSystemsParser.TrueContext ctx) {
        return vars;
    }

    @Override
    public SimpleVarSet visitFalse(BooleanSystemsParser.FalseContext ctx) {
        return vars;
    }

    @Override
    public SimpleVarSet visitParens(BooleanSystemsParser.ParensContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public SimpleVarSet visitOr(BooleanSystemsParser.OrContext ctx) {
        visit(ctx.left);
        visit(ctx.right);
        return vars;
    }

    @Override
    public SimpleVarSet visitAnd(BooleanSystemsParser.AndContext ctx) {
        visit(ctx.left);
        visit(ctx.right);
        return vars;
    }

    @Override
    public SimpleVarSet visitVar(BooleanSystemsParser.VarContext ctx) {
        String varID = ctx.ID().getText();
        Integer varIndex = system.getIndex(varID);
        vars.set.add(varIndex);
        return vars;
    }
}
