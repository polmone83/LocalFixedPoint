package labeled_transitions_systems.CCS;

public class PrettyPrint extends CCSBaseVisitor<StringBuilder>{
    StringBuilder pp;

    public PrettyPrint(){
        pp = new StringBuilder();
    }

    @Override
    public StringBuilder visitSystem(CCSParser.SystemContext ctx) {
        for (CCSParser.SetDeclContext setDeclContext : ctx.setDecl()) {
            visit(setDeclContext);
            pp.append('\n');
        }

        for (CCSParser.PDeclContext pDeclContext : ctx.pDecl()) {
            visit(pDeclContext);
            pp.append('\n');
        }
        return pp;
    }

    @Override
    public StringBuilder visitPDecl(CCSParser.PDeclContext ctx) {
        pp.append(ctx.ID().getText());
        pp.append(" = ");
        visit(ctx.process());
        pp.append(";");
        return pp;
    }

    @Override
    public StringBuilder visitSetDecl(CCSParser.SetDeclContext ctx) {
        pp.append("set ");
        pp.append(ctx.ID().getText());
        pp.append(" = ");
        visit(ctx.channelSet());
        pp.append(";");
        return pp;
    }

    @Override
    public StringBuilder visitNil(CCSParser.NilContext ctx) {
        pp.append("0");
        return pp;
    }

    @Override
    public StringBuilder visitOutputAction(CCSParser.OutputActionContext ctx) {
        pp.append('\'').append(ctx.CHANNEL().getText());
        pp.append(".");
        visit(ctx.process());
        return pp;
    }

    @Override
    public StringBuilder visitInputAction(CCSParser.InputActionContext ctx) {
        pp.append(ctx.CHANNEL().getText());
        pp.append(".");
        visit(ctx.process());
        return pp;
    }

    @Override
    public StringBuilder visitSyncAction(CCSParser.SyncActionContext ctx) {
        pp.append("tau.");
        visit(ctx.process());
        return pp;
    }

    @Override
    public StringBuilder visitChoice(CCSParser.ChoiceContext ctx) {
        visit(ctx.process(0));
        pp.append(" + ");
        visit(ctx.process(1));
        return pp;
    }

    @Override
    public StringBuilder visitParallel(CCSParser.ParallelContext ctx) {
        visit(ctx.process(0));
        pp.append(" | ");
        visit(ctx.process(1));
        return pp;
    }

    @Override
    public StringBuilder visitRestricSet(CCSParser.RestricSetContext ctx) {
        visit(ctx.process());
        pp.append("\\").append(ctx.ID().getText());
        return pp;
    }

    @Override
    public StringBuilder visitRestricExplicit(CCSParser.RestricExplicitContext ctx) {
        visit(ctx.process());
        pp.append("\\");
        visit(ctx.channelSet());
        return pp;
    }

    @Override
    public StringBuilder visitRename(CCSParser.RenameContext ctx) {
        visit(ctx.process());
        pp.append("[");
        pp.append(ctx.renaming(0));
        for(int i = 1; i < ctx.renaming().size(); i++){
            pp.append(", ");
            pp.append(ctx.renaming(i));
        }
        pp.append("]");
        return pp;
    }

    @Override
    public StringBuilder visitParens(CCSParser.ParensContext ctx) {
        pp.append("(");
        visit(ctx.process());
        pp.append(")");
        return pp;
    }

    @Override
    public StringBuilder visitPName(CCSParser.PNameContext ctx) {
        pp.append(ctx.ID().getText());
        return pp;
    }

    @Override
    public StringBuilder visitChannelSet(CCSParser.ChannelSetContext ctx) {
        pp.append("{");
        pp.append(ctx.CHANNEL(0));
        for(int i = 1; i < ctx.CHANNEL().size(); i++){
            pp.append(ctx.CHANNEL(i));
        }
        pp.append("}");
        return pp;
    }

    @Override
    public StringBuilder visitRenaming(CCSParser.RenamingContext ctx) {
       pp.append(ctx.to.getText());
       pp.append("/");
       pp.append(ctx.from.getText());
       return pp;
    }
}
