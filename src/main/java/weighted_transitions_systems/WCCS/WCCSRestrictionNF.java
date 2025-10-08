package weighted_transitions_systems.WCCS;

/**
 * This visitor pushes down and aggregates all restrictions
 */
public class WCCSRestrictionNF extends WCCSBaseNFVisitor{

    @Override
    public WCCSProcess visitRestriction(WCCSProcess.Restriction p) {
        if(p.process instanceof WCCSProcess.Nil){
            return p.process;
        }else if(p.process instanceof WCCSProcess.AtomicLabel){
            // (a:P)\S ~ a:(P\S)
            WCCSProcess.AtomicLabel child = (WCCSProcess.AtomicLabel) p.process;
            child.process = visit(new WCCSProcess.Restriction(child.process,p.chanSet));
            child.resetID();
            return child;
        }else if(p.process instanceof WCCSProcess.Choice){
            // push restriction down (P + Q)\S ~ P\S + Q\S
            WCCSProcess.Choice c_child = (WCCSProcess.Choice) p.process;
            c_child.children.replaceAll(
                    child -> visit( new WCCSProcess.Restriction(child,p.chanSet))
            );
            c_child.resetID();
            return c_child;
        }else if(p.process instanceof WCCSProcess.Restriction){
            // flatten nested restrictions
            WCCSProcess.Restriction child = (WCCSProcess.Restriction) p.process;
            child.resetID();
            child.chanSet.addAll(p.chanSet); // merge the channel sets
            return visit(child); // skip this process
        }else if(p.process instanceof WCCSProcess.Action){
            WCCSProcess.Action child = (WCCSProcess.Action) p.process;
            if(! p.chanSet.contains(child.channel)){
                // (a.P) \ S ~ a.(P\S) because a is not in S
                child.resetID();
                child.process = visit(new WCCSProcess.Restriction(child.process,p.chanSet));
                return child;
            }else{
                // (a.P) \ S ~ 0 because a in S
                return new WCCSProcess.Nil();
            }
        }
        // default visitor
        return super.visitRestriction(p);
    }
}
