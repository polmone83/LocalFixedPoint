package weighted_transitions_systems.WCCS;

public class WCCSBaseNFVisitor implements WCCSProcessVisitor<WCCSProcess>{

    @Override
    public WCCSProcess visitNil(WCCSProcess.Nil p) {
        return p;
    }

    @Override
    public WCCSProcess visitAtomicLabel(WCCSProcess.AtomicLabel p) {
        p.resetID();
        p.process = visit(p.process);
        return p;
    }

    @Override
    public WCCSProcess visitAction(WCCSProcess.Action p) {
        p.resetID();
        p.process = visit(p.process);
        return p;
    }

    @Override
    public WCCSProcess visitParallel(WCCSProcess.Parallel p) {
        p.resetID();
        p.children.replaceAll(child -> visit(child));
        return p;
    }

    @Override
    public WCCSProcess visitChoice(WCCSProcess.Choice p) {
        p.resetID();
        p.children.replaceAll(child -> visit(child));
        return p;
    }

    @Override
    public WCCSProcess visitParens(WCCSProcess.ParensProcess p) {
        return visit(p);
    }

    @Override
    public WCCSProcess visitRestriction(WCCSProcess.Restriction p) {
        p.resetID();
        p.process = visit(p.process);
        return p;
    }

    @Override
    public WCCSProcess visitChannelRenaming(WCCSProcess.ChannelRenaming p) {
        p.resetID();
        p.process = visit(p.process);
        return p;
    }

    @Override
    public WCCSProcess visitLabelRenaming(WCCSProcess.LabelRenaming p) {
        p.resetID();
        p.process = visit(p.process);
        return p;
    }

    @Override
    public WCCSProcess visitPName(WCCSProcess.PName p) {
        return p;
    }
}
