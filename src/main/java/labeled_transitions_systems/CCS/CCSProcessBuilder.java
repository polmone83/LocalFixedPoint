package labeled_transitions_systems.CCS;

import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class CCSProcessBuilder extends CCSBaseVisitor<CCSProcess>{

    private CCSInterpreter interpreter;

    public CCSProcessBuilder(CCSInterpreter interpreter){
        this.interpreter = interpreter;

    }

    @Override
    public CCSProcess visitRestricExplicit(CCSParser.RestricExplicitContext ctx) {
        HashSet<String> chanSet = new HashSet<>();
        for (TerminalNode chan : ctx.channelSet().CHANNEL()) {
            chanSet.add(chan.getText());
        }
        CCSProcess process = visit(ctx.process());
        return new CCSProcess.Restriction(process,chanSet);
    }

    @Override
    public CCSProcess visitRestricSet(CCSParser.RestricSetContext ctx) {
        Set<String> chanSet =  interpreter.getChannelSet(ctx.ID().getText());
        CCSProcess process = visit(ctx.process());
        return new CCSProcess.Restriction(process,chanSet);
    }

    @Override
    public CCSProcess visitParallel(CCSParser.ParallelContext ctx) {
        CCSProcess left = visit(ctx.process(0));
        CCSProcess right = visit(ctx.process(1));
        return new CCSProcess.Parallel(left,right);
    }

    @Override
    public CCSProcess visitChoice(CCSParser.ChoiceContext ctx) {
        CCSProcess left = visit(ctx.process(0));
        CCSProcess right = visit(ctx.process(1));
        return new CCSProcess.Choice(left,right);
    }

    @Override
    public CCSProcess visitInputAction(CCSParser.InputActionContext ctx) {
        CCSProcess process = visit(ctx.process());
        String chan = ctx.CHANNEL().getText();
        return new CCSProcess.Action(true,chan, process);
    }

    @Override
    public CCSProcess visitSyncAction(CCSParser.SyncActionContext ctx) {
        CCSProcess process = visit(ctx.process());
        return new CCSProcess.Action(null,"tau", process);
    }

    @Override
    public CCSProcess visitOutputAction(CCSParser.OutputActionContext ctx) {
        CCSProcess process = visit(ctx.process());
        String chan = ctx.CHANNEL().getText();
        return new CCSProcess.Action(false,chan, process);
    }

    @Override
    public CCSProcess visitParens(CCSParser.ParensContext ctx) {
        CCSProcess process = visit(ctx.process());
        return process;
    }

    @Override
    public CCSProcess visitRename(CCSParser.RenameContext ctx) {
        // create the renaming
        HashMap<String, String> renaming = new HashMap<>();
        for (CCSParser.RenamingContext renamingContext : ctx.renaming()) {
            renaming.put(renamingContext.from.getText(), renamingContext.to.getText());
        }
        CCSProcess p = visit(ctx.process());
        return new CCSProcess.Renaming(p, renaming);
    }

    @Override
    public CCSProcess visitNil(CCSParser.NilContext ctx) {
        return new CCSProcess.Nil();
    }

    @Override
    public CCSProcess visitPName(CCSParser.PNameContext ctx) {
        return new CCSProcess.PName(ctx.ID().getText(),interpreter);
    }
}

