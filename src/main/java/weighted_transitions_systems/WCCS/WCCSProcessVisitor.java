package weighted_transitions_systems.WCCS;


public interface WCCSProcessVisitor<T> {

    default T visit(WCCSProcess p){
        return p.accept(this);
    };

    T visitNil(WCCSProcess.Nil p);
    T visitAtomicLabel(WCCSProcess.AtomicLabel p);
    T visitAction(WCCSProcess.Action p);
    T visitParallel(WCCSProcess.Parallel p);
    T visitChoice(WCCSProcess.Choice p);
    T visitParens(WCCSProcess.ParensProcess p);
    T visitRestriction(WCCSProcess.Restriction p);
    T visitChannelRenaming(WCCSProcess.ChannelRenaming p);
    T visitLabelRenaming(WCCSProcess.LabelRenaming p);
    T visitPName(WCCSProcess.PName p);

}
