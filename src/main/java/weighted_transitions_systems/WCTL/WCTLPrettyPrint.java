package weighted_transitions_systems.WCTL;

import bddRelations.termExtensionOracles.WExpressionExtension;

public class WCTLPrettyPrint extends WCTLBaseVisitor<StringBuilder>{
    StringBuilder pp;
    public WCTLPrettyPrint(){
        pp = new StringBuilder();
    }

    public static String prettyPrint(WCTLParser.FormulaContext ctx){
        WCTLPrettyPrint printer = new WCTLPrettyPrint();
        return printer.visit(ctx).toString();
    }

    @Override
    public StringBuilder visitAnd(WCTLParser.AndContext ctx) {
        pp.append("(");
        visit(ctx.left);
        pp.append("&&");
        visit(ctx.right);
        pp.append(")");
        return pp;
    }

    @Override
    public StringBuilder visitOr(WCTLParser.OrContext ctx) {
        pp.append("(");
        visit(ctx.left);
        pp.append("||");
        visit(ctx.right);
        pp.append(")");
        return pp;
    }

    @Override
    public StringBuilder visitBoundedExistentialFinally(WCTLParser.BoundedExistentialFinallyContext ctx) {
        // 'EF' '[' '<=' bound=WEIGHT ']' formula
        pp.append("(");
        pp.append("EF[<=");
        pp.append(ctx.WEIGHT().getText());
        pp.append("]");
        visit(ctx.formula());
        pp.append(")");
        return pp;
    }

    @Override
    public StringBuilder visitBoundedExistentialNext(WCTLParser.BoundedExistentialNextContext ctx) {
        // 'EX' formula
        pp.append("(");
        pp.append("EX");
        visit(ctx.formula());
        pp.append(")");
        return pp;
    }

    @Override
    public StringBuilder visitBoundedExistentialUntil(WCTLParser.BoundedExistentialUntilContext ctx) {
        // 'E' left=formula 'U' '[' '<=' bound=WEIGHT ']' right=formula
        pp.append("(");
        pp.append("E");
        visit(ctx.left);
        pp.append("U[<=");
        pp.append(ctx.WEIGHT().getText());
        pp.append("]");
        visit(ctx.right);
        pp.append(")");
        return pp;
    }

    @Override
    public StringBuilder visitBoundedUniversalFinally(WCTLParser.BoundedUniversalFinallyContext ctx) {
        // 'AF' '[' '<=' bound=WEIGHT ']' formula
        pp.append("(");
        pp.append("AF[<=");
        pp.append(ctx.WEIGHT().getText());
        pp.append("]");
        visit(ctx.formula());
        pp.append(")");
        return pp;
    }

    @Override
    public StringBuilder visitBoundedUniversalNext(WCTLParser.BoundedUniversalNextContext ctx) {
        // 'AX' '[' '<=' bound=WEIGHT ']' formula
        pp.append("(");
        pp.append("AX[<=");
        pp.append(ctx.WEIGHT().getText());
        pp.append("]");
        visit(ctx.formula());
        pp.append(")");
        return pp;
    }

    @Override
    public StringBuilder visitBoundedUniversalUntil(WCTLParser.BoundedUniversalUntilContext ctx) {
        // 'A' left=formula 'U' '[' '<=' bound=WEIGHT ']' right=formula
        pp.append("(");
        pp.append("A");
        visit(ctx.left);
        pp.append("U[<=");
        pp.append(ctx.WEIGHT().getText());
        pp.append("]");
        visit(ctx.right);
        pp.append(")");
        return pp;
    }

    @Override
    public StringBuilder visitExistentialFinally(WCTLParser.ExistentialFinallyContext ctx) {
        // 'EF' formula
        pp.append("(");
        pp.append("EF");
        visit(ctx.formula());
        pp.append(")");
        return pp;
    }

    @Override
    public StringBuilder visitExistentialNext(WCTLParser.ExistentialNextContext ctx) {
        //'EX' formula
        pp.append("(");
        pp.append("EX");
        visit(ctx.formula());
        pp.append(")");
        return pp;
    }

    @Override
    public StringBuilder visitExistentialUntil(WCTLParser.ExistentialUntilContext ctx) {
        // 'E' left=formula 'U'  right=formula
        pp.append("(");
        pp.append("E");
        visit(ctx.left);
        pp.append("U");
        visit(ctx.right);
        pp.append(")");
        return pp;
    }

    @Override
    public StringBuilder visitUniversalFinally(WCTLParser.UniversalFinallyContext ctx) {
        // 'AF' formula
        pp.append("(");
        pp.append("AF");
        visit(ctx.formula());
        pp.append(")");
        return pp;
    }

    @Override
    public StringBuilder visitUniversalNext(WCTLParser.UniversalNextContext ctx) {
        // 'AX' formula
        pp.append("(");
        pp.append("AX");
        visit(ctx.formula());
        pp.append(")");
        return pp;
    }

    @Override
    public StringBuilder visitUniversalUntil(WCTLParser.UniversalUntilContext ctx) {
        // 'A' left=formula 'U' right=formula
        pp.append("(");
        pp.append("A");
        visit(ctx.left);
        pp.append("U");
        visit(ctx.right);
        pp.append(")");
        return pp;
    }

    @Override
    public StringBuilder visitParens(WCTLParser.ParensContext ctx) {
        // This method is not necessary it's here just to make it explicit
        visit(ctx.formula());
        return pp;
    }

    @Override
    public StringBuilder visitTrue(WCTLParser.TrueContext ctx) {
        pp.append("tt");
        return pp;
    }

    @Override
    public StringBuilder visitFalse(WCTLParser.FalseContext ctx) {
        pp.append("ff");
        return pp;
    }

    @Override
    public StringBuilder visitRelExpression(WCTLParser.RelExpressionContext ctx) {
        // expr rel=('<' | '<=' | '>' | '>=' | '!=' | '==') expr
        visit(ctx.expr(0));
        pp.append(ctx.rel.getText());
        visit(ctx.expr(1));
        return pp;
    }

    @Override
    public StringBuilder visitProposition(WCTLParser.PropositionContext ctx) {
        // ID
        pp.append(ctx.ID().getText());
        return pp;
    }

    @Override
    public StringBuilder visitDiv(WCTLParser.DivContext ctx) {
        // expr '/' expr
        pp.append("(");
        visit(ctx.expr(0));
        pp.append("/");
        visit(ctx.expr(1));
        pp.append(")");
        return pp;
    }

    @Override
    public StringBuilder visitMult(WCTLParser.MultContext ctx) {
        // expr '*' expr
        pp.append("(");
        visit(ctx.expr(0));
        pp.append("*");
        visit(ctx.expr(1));
        pp.append(")");
        return pp;
    }

    @Override
    public StringBuilder visitMinus(WCTLParser.MinusContext ctx) {
        // expr '-' expr
        pp.append("(");
        visit(ctx.expr(0));
        pp.append("-");
        visit(ctx.expr(1));
        pp.append(")");
        return pp;
    }

    @Override
    public StringBuilder visitInverse(WCTLParser.InverseContext ctx) {
        // '-' expr
        pp.append("(");
        pp.append("-");
        visit(ctx.expr());
        pp.append(")");
        return pp;
    }

    @Override
    public StringBuilder visitPlus(WCTLParser.PlusContext ctx) {
        // expr '+' expr
        pp.append("(");
        visit(ctx.expr(0));
        pp.append("+");
        visit(ctx.expr(1));
        pp.append(")");
        return pp;
    }

    @Override
    public StringBuilder visitParensExpr(WCTLParser.ParensExprContext ctx) {
        visit(ctx.expr());
        return pp;
    }

    @Override
    public StringBuilder visitPropVar(WCTLParser.PropVarContext ctx) {
        // ID
        pp.append(ctx.ID().getText());
        return pp;
    }

    @Override
    public StringBuilder visitWeight(WCTLParser.WeightContext ctx) {
        // WEIGHT
        pp.append(ctx.WEIGHT().getText());
        return pp;
    }

    @Override
    public StringBuilder visitExpression(WCTLParser.ExpressionContext ctx) {
        // relexpr
        visit(ctx.relexpr());
        return pp;
    }
}
