package bddRelations.groudness;

import bddRelations.BDDRel;
import bddRelations.BDDRelEquationSystem;
import com.github.javabdd.BDD;
import core.SimpleVarSet;
import domains.posDomain.groudness.groundSystemBaseVisitor;
import domains.posDomain.groudness.groundSystemParser;

public class BDDRelVarsVisitor extends groundSystemBaseVisitor<SimpleVarSet> {
    private SimpleVarSet vars;
    private BDDRelEquationSystem<BDD> system;

    public BDDRelVarsVisitor(BDDRelEquationSystem<BDD> system){
        this.system = system;
        vars = new SimpleVarSet(); // empty set
    }

    @Override
    public SimpleVarSet visitAnd(groundSystemParser.AndContext ctx) {
        visit(ctx.left);
        visit(ctx.right);
        return vars;
    }

    @Override
    public SimpleVarSet visitOr(groundSystemParser.OrContext ctx) {
        visit(ctx.left);
        visit(ctx.right);
        return vars;
    }

    @Override
    public SimpleVarSet visitParens(groundSystemParser.ParensContext ctx) {
        visit(ctx.expr());
        return vars;
    }

    @Override
    public SimpleVarSet visitVar(groundSystemParser.VarContext ctx) {
        return vars;
    }

    @Override
    public SimpleVarSet visitTrue(groundSystemParser.TrueContext ctx) {
        return vars;
    }

    @Override
    public SimpleVarSet visitFalse(groundSystemParser.FalseContext ctx) {
        return vars;
    }

    @Override
    public SimpleVarSet visitCall(groundSystemParser.CallContext ctx) {
        visit(ctx.predicate());
        return vars;
    }

    @Override
    public SimpleVarSet visitPredicate(groundSystemParser.PredicateContext ctx) {
        PrologPredicate p = new PrologPredicate(ctx.ID().getText(),ctx.VAR().size());
        vars.add(system.getIndex(p.getSignature()));
        return vars;
    }

    @Override
    public SimpleVarSet visitIff(groundSystemParser.IffContext ctx) {
        visit(ctx.left);
        visit(ctx.right);
        return vars;
    }

    @Override
    public SimpleVarSet visitRestriction(groundSystemParser.RestrictionContext ctx) {
        visit(ctx.expr());
        return vars;
    }
}
