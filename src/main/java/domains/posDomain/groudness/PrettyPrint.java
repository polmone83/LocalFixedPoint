package domains.posDomain.groudness;

import org.antlr.v4.runtime.tree.TerminalNode;

public class PrettyPrint extends groundSystemBaseVisitor<String>{

    StringBuilder ppPrint;
    public PrettyPrint(){
        this.ppPrint = new StringBuilder();
    }

    @Override
    public String visitSystem(groundSystemParser.SystemContext ctx) {
        for (groundSystemParser.EquationContext equation : ctx.equation()) {
            visit(equation);
        }
        return ppPrint.toString();
    }

    @Override
    public String visitEquation(groundSystemParser.EquationContext ctx) {
        visit(ctx.predicate());
        ppPrint.append(" = ");
        visit(ctx.expr());
        ppPrint.append(";\n");
        return ppPrint.toString();
    }

    @Override
    public String visitPredicate(groundSystemParser.PredicateContext ctx) {
        ppPrint.append(ctx.ID().getText())
                .append("(");
        int arity = ctx.VAR().size();
        if(arity > 0){
            ppPrint.append(ctx.VAR(0).getText());
            for (int i = 1; i < arity; i++) {
                ppPrint.append(",").append(ctx.VAR(i).getText());
            }
        }
        ppPrint.append(")");

        return ppPrint.toString();
    }

    @Override
    public String visitAnd(groundSystemParser.AndContext ctx) {
        visit(ctx.left);
        ppPrint.append(" & ");
        visit(ctx.right);
        return ppPrint.toString();
    }

    @Override
    public String visitOr(groundSystemParser.OrContext ctx) {
        visit(ctx.left);
        ppPrint.append(" | ");
        visit(ctx.right);
        return ppPrint.toString();
    }

    @Override
    public String visitIff(groundSystemParser.IffContext ctx) {
        visit(ctx.left);
        ppPrint.append(" <=> ");
        visit(ctx.right);
        return ppPrint.toString();
    }

    @Override
    public String visitFalse(groundSystemParser.FalseContext ctx) {
        ppPrint.append("ff");
        return "ff";
    }

    @Override
    public String visitTrue(groundSystemParser.TrueContext ctx) {
        ppPrint.append("tt");
        return "tt";
    }

    @Override
    public String visitCall(groundSystemParser.CallContext ctx) {
        visit(ctx.predicate());
        return ppPrint.toString();
    }

    @Override
    public String visitRestriction(groundSystemParser.RestrictionContext ctx) {
        ppPrint.append("<");
        visit(ctx.expr());
        ppPrint.append(",[");
        int varsCount = ctx.VAR().size();
        if(varsCount > 0){
            ppPrint.append(ctx.VAR(0).getText());
            for (int i = 1; i < varsCount; i++) {
                ppPrint.append(",").append(ctx.VAR(i).getText());
            }
        }
        ppPrint.append("]>");
        return ppPrint.toString();
    }

    @Override
    public String visitParens(groundSystemParser.ParensContext ctx) {
        ppPrint.append("(");
        visit(ctx.expr());
        ppPrint.append(")");
        return ppPrint.toString();
    }

    @Override
    public String visitVar(groundSystemParser.VarContext ctx) {
        ppPrint.append(ctx.VAR().getText());
        return ppPrint.toString();
    }
}
