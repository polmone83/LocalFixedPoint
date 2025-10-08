package labeled_transitions_systems.HML;

import java.util.HashSet;

public class ActionSet extends HMLBaseVisitor<HashSet<String>> {
        
        HMLInterpreter hmlInterpreter;
        HashSet<String> actions;

        public ActionSet(HMLInterpreter hmlInterpreter, HMLParser.ActionsContext ctx){
            this.hmlInterpreter = hmlInterpreter;
            actions = visit(ctx);
        }

        @Override
        public HashSet<String> visitWildcard(HMLParser.WildcardContext ctx) {
            return null;
        }

        @Override
        public HashSet<String> visitActionSequence(HMLParser.ActionSequenceContext ctx) {
            actions = new HashSet<>();
            for (HMLParser.ActionContext action : ctx.action()) {
                visit(action);
            }
            return actions;
        }

        @Override
        public HashSet<String> visitInputAction(HMLParser.InputActionContext ctx) {
            actions.add(ctx.CHANNEL().getText());
            return actions;
        }

        @Override
        public HashSet<String> visitOutputAction(HMLParser.OutputActionContext ctx) {
            actions.add("'" + ctx.CHANNEL().getText());
            return actions;
        }

        @Override
        public HashSet<String> visitSync(HMLParser.SyncContext ctx) {
            actions.add("tau");
            return actions;
        }

        @Override
        public HashSet<String> visitSetName(HMLParser.SetNameContext ctx) {
            HMLParser.ActionsContext def = hmlInterpreter.getActionsDef(ctx.ID().getText());
            return visit(def);
        }

        public boolean contains(Boolean inputQ, String action){
            String actionEnc;
            if(action.equals("tau") || inputQ){
                actionEnc = action;
            }else{
                actionEnc = "'" + action;
            }
            return actions == null || actions.contains(actionEnc);
        }

    }