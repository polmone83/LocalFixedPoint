package weighted_transitions_systems.WCCS;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.HashMap;
import java.util.HashSet;

public class WCCSInterpreter {

    private HashMap<String, WCCSParser.ProcessContext> procesDecl;
    private HashMap<String, WCCSParser.ChannelSetContext> channelSetDecl;
    private WCCSProcessVisitor astBuilder;

    public WCCSInterpreter(String code, Boolean fileFlag) {
        try {
            /* Take parse the file and */
            CharStream input;
            if (fileFlag) {
                input = CharStreams.fromFileName(code);
            } else {
                input = CharStreams.fromString(code);
            }

            WCCSLexer lexer = new WCCSLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            WCCSParser parser = new WCCSParser(tokens);
            ParseTree ccs_system = parser.system();
            init(ccs_system);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init(ParseTree wccs_system) {
        this.procesDecl = new HashMap<>();
        this.channelSetDecl = new HashMap<>();
        this.astBuilder = new WCCSProcessVisitor();

        // check NIL processes are correct
        NilProcessChecker checker = new NilProcessChecker();
        checker.visit(wccs_system);

        // visit the declarations and populate the maps
        DeclVisitor declVisitor = new DeclVisitor();
        declVisitor.visit(wccs_system);
    }

    public WCCSProcess getProcess(String pName) {
        return astBuilder.visit(procesDecl.get(pName));
    }

    private class NilProcessChecker extends WCCSBaseVisitor<Boolean>{
        @Override
        public Boolean visitNil(WCCSParser.NilContext ctx) {
            if(! ctx.WEIGHT().getText().equals("0"))
                throw new RuntimeException("Expected 0 at line " + ctx.WEIGHT().getSymbol().getLine());
            return true;
        }
    }

    private class WCCSProcessVisitor extends WCCSBaseVisitor<WCCSProcess> {

        @Override
        public WCCSProcess visitRestricExplicit(WCCSParser.RestricExplicitContext ctx) {
            HashSet<String> chanSet = new HashSet<>();
            for (TerminalNode chan : ctx.channelSet().ID()) {
                chanSet.add(chan.getText());
            }
            WCCSProcess process = visit(ctx.process());
            return new WCCSProcess.Restriction(process, chanSet);
        }

        @Override
        public WCCSProcess visitRestricSet(WCCSParser.RestricSetContext ctx) {
            HashSet<String> chanSet = new HashSet<>();
            for (TerminalNode chan : channelSetDecl.get(ctx.PID().getText()).ID()) {
                chanSet.add(chan.getText());
            }
            WCCSProcess process = visit(ctx.process());
            return new WCCSProcess.Restriction(process, chanSet);
        }

        @Override
        public WCCSProcess visitParallel(WCCSParser.ParallelContext ctx) {
            WCCSProcess left = visit(ctx.process(0));
            WCCSProcess right = visit(ctx.process(1));
            return new WCCSProcess.Parallel(left, right);
        }

        @Override
        public WCCSProcess visitChoice(WCCSParser.ChoiceContext ctx) {
            WCCSProcess left = visit(ctx.process(0));
            WCCSProcess right = visit(ctx.process(1));
            return new WCCSProcess.Choice(left, right);
        }

        @Override
        public WCCSProcess visitAtomicLabel(WCCSParser.AtomicLabelContext ctx) {
            WCCSProcess process = visit(ctx.process());
            return new WCCSProcess.AtomicLabel(ctx.ID().getText(), process);
        }

        @Override
        public WCCSProcess visitActionPrefix(WCCSParser.ActionPrefixContext ctx) {
            WCCSProcess process = visit(ctx.process());
            int weight = ctx.WEIGHT() == null ? 0 : Integer.parseInt(ctx.WEIGHT().getText());

            WCCSParser.ActionContext action = ctx.action();
            if (action instanceof WCCSParser.SyncActionContext) {
                return new WCCSProcess.Action(weight, process);
            } else {
                boolean isInput = (action instanceof WCCSParser.InputActionContext);
                String chan = getActionChannel(action);
                return new WCCSProcess.Action(isInput, chan, weight, process);
            }
        }

        private String getActionChannel(WCCSParser.ActionContext action) {
            if (action instanceof WCCSParser.OutputActionContext) {
                return ((WCCSParser.OutputActionContext) action).ID().getText();
            } else if (action instanceof WCCSParser.InputActionContext) {
                return ((WCCSParser.InputActionContext) action).ID().getText();
            } else {
                return WCCSProcess.TAU;
            }
        }

        @Override
        public WCCSProcess visitParens(WCCSParser.ParensContext ctx) {
            WCCSProcess process = visit(ctx.process());
            return process;
        }

        @Override
        public WCCSProcess visitRename(WCCSParser.RenameContext ctx) {
            // create the renaming
            HashMap<String, String> renaming = new HashMap<>();
            for (WCCSParser.RenamingContext renamingContext : ctx.renaming()) {
                renaming.put(renamingContext.from.getText(), renamingContext.to.getText());
            }
            WCCSProcess p = visit(ctx.process());
            return new WCCSProcess.ChannelRenaming(p, renaming);
        }

        @Override
        public WCCSProcess visitLabelRename(WCCSParser.LabelRenameContext ctx) {
            // create the renaming
            HashMap<String, String> renaming = new HashMap<>();
            for (WCCSParser.LabelRenamingContext labelRenamingContext : ctx.labelRenaming()) {
                renaming.put(labelRenamingContext.from.getText(), labelRenamingContext.to.getText());
            }
            WCCSProcess p = visit(ctx.process());
            return new WCCSProcess.LabelRenaming(p, renaming);
        }

        @Override
        public WCCSProcess visitNil(WCCSParser.NilContext ctx) {
            return new WCCSProcess.Nil();
        }

        @Override
        public WCCSProcess visitPName(WCCSParser.PNameContext ctx) {
            return new WCCSProcess.PName(ctx.PID().getText(), WCCSInterpreter.this);
        }

    }

    private class DeclVisitor extends WCCSBaseVisitor<Boolean> {

        /* we inherit the method visitSystem because it only
         * calls the children setDecl and pDecl */

        @Override
        public Boolean visitSetDecl(WCCSParser.SetDeclContext ctx) {
            channelSetDecl.put(ctx.PID().getText(), ctx.channelSet());
            return true;
        }

        @Override
        public Boolean visitPDecl(WCCSParser.PDeclContext ctx) {
            procesDecl.put(ctx.PID().getText(), ctx.process());
            return true;
        }
    }
}
