package domains.boolDomain;

import bddRelations.BDDRelEquationSystem;
import domains.boolDomain.parser.BooleanSystemsBaseVisitor;
import domains.boolDomain.parser.BooleanSystemsParser;

public class BoolTerm2BoolFormula extends BooleanSystemsBaseVisitor<BoolFormula> {
    BDDRelEquationSystem<Boolean> system;

    public static BoolFormula encode(BooleanSystemsParser.ExprContext ctx, BDDRelEquationSystem<Boolean> system){
        BoolTerm2BoolFormula vistor = new BoolTerm2BoolFormula(system);
        return BoolFormulaSimplify.simplify(vistor.visit(ctx));
    }

    public BoolTerm2BoolFormula(BDDRelEquationSystem<Boolean> system){
        this.system = system;
    }
    /**
     * Visit a parse tree produced by the {@code parens}
     * labeled alternative in {@link BooleanSystemsParser#expr()}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public BoolFormula visitParens(BooleanSystemsParser.ParensContext ctx) {
        return visit(ctx.expr());
    }

    /**
     * Visit a parse tree produced by the {@code or}
     * labeled alternative in {@link BooleanSystemsParser#expr()}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public BoolFormula visitOr(BooleanSystemsParser.OrContext ctx) {
        return new BoolFormula.Or(visit(ctx.left), visit(ctx.right),system);
    }

    /**
     * Visit a parse tree produced by the {@code var}
     * labeled alternative in {@link BooleanSystemsParser#expr()}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public BoolFormula visitVar(BooleanSystemsParser.VarContext ctx) {
        Integer var = system.getIndex(ctx.ID().getText());
        return new BoolFormula.Var(var,system);
    }

    /**
     * Visit a parse tree produced by the {@code and}
     * labeled alternative in {@link BooleanSystemsParser#expr()}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public BoolFormula visitAnd(BooleanSystemsParser.AndContext ctx) {
        return new BoolFormula.And(visit(ctx.left), visit(ctx.right),system);
    }

    /**
     * Visit a parse tree produced by the {@code false}
     * labeled alternative in {@link BooleanSystemsParser#expr()}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public BoolFormula visitFalse(BooleanSystemsParser.FalseContext ctx) {
        return new BoolFormula.False(system);
    }

    /**
     * Visit a parse tree produced by the {@code true}
     * labeled alternative in {@link BooleanSystemsParser#expr()}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public BoolFormula visitTrue(BooleanSystemsParser.TrueContext ctx) {
        return new BoolFormula.True(system);
    }
}
