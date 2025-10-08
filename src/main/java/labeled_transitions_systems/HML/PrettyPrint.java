package labeled_transitions_systems.HML;

public class PrettyPrint extends HMLBaseVisitor<StringBuilder>{
    StringBuilder pp;

    public PrettyPrint(){
        this.pp = new StringBuilder();
    }

    @Override
    public StringBuilder visitDecls(HMLParser.DeclsContext ctx) {
        for (HMLParser.FormulaDeclContext formulaDeclContext : ctx.formulaDecl()) {
            visit(formulaDeclContext);
        }

        for (HMLParser.SetDeclContext setDeclContext : ctx.setDecl()) {
            visit(setDeclContext);
        }

        return pp;
    }

    @Override
    public StringBuilder visitMinDecl(HMLParser.MinDeclContext ctx) {
        pp.append(ctx.ID().getText());
        pp.append(" min= ");
        visit(ctx.formula());
        pp.append(";\n");
        return pp;
    }

    @Override
    public StringBuilder visitMaxDecl(HMLParser.MaxDeclContext ctx) {
        pp.append(ctx.ID().getText());
        pp.append(" max= ");
        visit(ctx.formula());
        pp.append(";\n");
        return pp;
    }

    @Override
    public StringBuilder visitSetDecl(HMLParser.SetDeclContext ctx) {
        pp.append("set ");
        pp.append(ctx.ID().getText());
        pp.append(" = {");
        visit(ctx.actions());
        pp.append("};\n");
        return pp;
    }

    @Override
    public StringBuilder visitFalse(HMLParser.FalseContext ctx) {
        return pp.append("ff");
    }

    @Override
    public StringBuilder visitTrue(HMLParser.TrueContext ctx) {
        return pp.append("tt");
    }

    @Override
    public StringBuilder visitAnd(HMLParser.AndContext ctx) {
        visit(ctx.left);
        pp.append(" and ");
        return visit(ctx.right);
    }

    @Override
    public StringBuilder visitOr(HMLParser.OrContext ctx) {
        visit(ctx.left);
        pp.append(" or ");
        return visit(ctx.right);
    }

    @Override
    public StringBuilder visitStrongExists(HMLParser.StrongExistsContext ctx) {
        pp.append("<");
        visit(ctx.actions());
        pp.append(">");
        return visit(ctx.formula());
    }

    @Override
    public StringBuilder visitWeakExists(HMLParser.WeakExistsContext ctx) {
        pp.append("<<");
        visit(ctx.actions());
        pp.append(">>");
        return visit(ctx.formula());
    }

    @Override
    public StringBuilder visitStrongForAll(HMLParser.StrongForAllContext ctx) {
        pp.append("[");
        visit(ctx.actions());
        pp.append("]");
        return visit(ctx.formula());
    }

    @Override
    public StringBuilder visitWeakForAll(HMLParser.WeakForAllContext ctx) {
        pp.append("[[");
        visit(ctx.actions());
        pp.append("]]");
        return visit(ctx.formula());
    }

    @Override
    public StringBuilder visitParens(HMLParser.ParensContext ctx) {
        pp.append("(");
        visit(ctx.formula());
        return pp.append(")");
    }

    @Override
    public StringBuilder visitFormulaName(HMLParser.FormulaNameContext ctx) {
        return pp.append(ctx.ID().getText());
    }

    @Override
    public StringBuilder visitActionSequence(HMLParser.ActionSequenceContext ctx) {
        visit(ctx.action(0));
        //pp.append(ctx.CHANNEL(0));
        for(int i = 1; i < ctx.action().size(); i++){
            pp.append(", ");
            visit(ctx.action(i));
        }
        return pp;
    }

    @Override
    public StringBuilder visitOutputAction(HMLParser.OutputActionContext ctx) {
        pp.append("'");
        pp.append(ctx.CHANNEL().getText());
        return pp;
    }

    @Override
    public StringBuilder visitInputAction(HMLParser.InputActionContext ctx) {
        pp.append(ctx.CHANNEL().getText());
        return pp;
    }

    @Override
    public StringBuilder visitSync(HMLParser.SyncContext ctx) {
        pp.append("tau");
        return pp;
    }

    @Override
    public StringBuilder visitSetName(HMLParser.SetNameContext ctx) {
        return pp.append(ctx.ID().getText());
    }

    @Override
    public StringBuilder visitWildcard(HMLParser.WildcardContext ctx) {
        return pp.append("-");
    }
}
