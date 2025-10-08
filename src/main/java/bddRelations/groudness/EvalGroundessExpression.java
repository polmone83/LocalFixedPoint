package bddRelations.groudness;

import com.github.javabdd.BDD;
import com.github.javabdd.BDDPairing;
import com.github.javabdd.BDDVarSet;
import core.Assignment;
import domains.posDomain.groudness.groundSystemBaseVisitor;
import domains.posDomain.groudness.groundSystemParser;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class EvalGroundessExpression extends groundSystemBaseVisitor<BDD> {

    private GroundnessEquationSystem system;
    private Assignment<Integer,BDD> ass;
    public EvalGroundessExpression(GroundnessEquationSystem system, Assignment<Integer,BDD> ass){
        this.system = system;
        this.ass = ass;
    }

    @Override
    public BDD visitOr(groundSystemParser.OrContext ctx) {
        return visit(ctx.left).orWith(visit(ctx.right));
    }

    @Override
    public BDD visitAnd(groundSystemParser.AndContext ctx) {
        BDD left = visit(ctx.left);
        BDD right = visit(ctx.right);
        return left.and(right);
    }

    @Override
    public BDD visitRestriction(groundSystemParser.RestrictionContext ctx) {
        // collect variables to restrict
        // todo: test and reimplement
        Set<Integer> varSet = system.getPosVarSet();
        for (TerminalNode var : ctx.VAR()) {
            varSet.remove(system.getPOSVarIndex(var.getText()));
        }
        // the non-restricted variables
        BDDVarSet exVars = system.getFactory().emptySet();
        varSet.iterator().forEachRemaining(
                (Integer var) -> {
                    exVars.unionWith(var);
                }
        );
        BDD expr = visit(ctx.expr()); // evaluate the expression
        return expr.exist(exVars);
    }

    @Override
    public BDD visitFalse(groundSystemParser.FalseContext ctx) {
        return system.getFactory().zero();
    }

    @Override
    public BDD visitTrue(groundSystemParser.TrueContext ctx) {
        return system.getFactory().one();
    }

    @Override
    public BDD visitParens(groundSystemParser.ParensContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public BDD visitVar(groundSystemParser.VarContext ctx) {
        return system.getPOSVar(ctx.VAR().getText());
    }

    @Override
    public BDD visitIff(groundSystemParser.IffContext ctx) {
        BDD left = visit(ctx.left);
        BDD right = visit(ctx.right);
        BDD left_iff_right = left.imp(right).and(right.imp(left));
        left.free();
        right.free();
        return left_iff_right;
    }

    @Override
    public BDD visitPredicate(groundSystemParser.PredicateContext ctx) {
        String pName = ctx.ID().getText();
        int arity = ctx.VAR().size();

        // construct the renaming X0/Y0,...,Xn/Yn
        BDDPairing renaming = system.getFactory().makePair();
        for(int i = 0; i < arity; i++) {
            String Yi = ctx.VAR(i).getText();
            String Xi = PrologPredicate.ithDefParam(i);
            renaming.set(system.getPOSVarIndex(Xi),system.getPOSVarIndex(Yi));
            //renaming.set(system.getPOSVarIndex(Xi),system.getPOSVar(Yi));
        }
        PrologPredicate p = new PrologPredicate(pName, arity);
        Integer pIndex = system.getIndex(p.getSignature());
        BDD Ap = ass.getValue(pIndex); // A(p)(X0...Y0)
        // return A(p)(Y0...Yn)
        return Ap.replace(renaming);
    }

    @Override
    public BDD visitCall(groundSystemParser.CallContext ctx) {
        return visit(ctx.predicate());
    }
}
