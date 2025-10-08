package weighted_transitions_systems.WCCS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

public class WCCSSortedNF extends WCCSBaseNFVisitor{

    @Override
    public WCCSProcess visitParallel(WCCSProcess.Parallel p) {
        super.visitParallel(p);
        // sort the children
        Collections.sort(p.children);
        p.resetID();
        return p;
    }

    @Override
    public WCCSProcess visitChoice(WCCSProcess.Choice p) {
        super.visitChoice(p);
        // sort and remove duplicates
        TreeSet<WCCSProcess> childrenSet = new TreeSet<>();
        childrenSet.addAll(p.children);
        return new WCCSProcess.Choice(childrenSet);
    }
}
