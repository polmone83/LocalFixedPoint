package weighted_transitions_systems.WCCS;

import java.util.*;

public class WCCSFlattenNF extends WCCSBaseNFVisitor{

    @Override
    public WCCSProcess visitParallel(WCCSProcess.Parallel p) {
        ArrayList<WCCSProcess> children = new ArrayList<>();
        for (WCCSProcess child : p.children) {
            child = visit(child);
            if(child instanceof WCCSProcess.Parallel){
                // flatten nested parallel processes
                // Parallel(A, Parallel(B,C)) ~ Parallel(A,B,C)
                children.addAll(((WCCSProcess.Parallel) child).children);
            }else if(child instanceof WCCSProcess.Nil){
                // Parallel(A, 0) ~ Parallel(A)
                // skip nil processes
            }else{
                children.add(child);
            }
        }
        if(children.size() == 0){
            // Parallel() ~ 0
            return new WCCSProcess.Nil();
        }else if (children.size() == 1){
            // Parallel(A) ~ A
            return children.getFirst();
        }

        p.children = children;
        p.resetID();
        return p;
    }

    @Override
    public WCCSProcess visitChoice(WCCSProcess.Choice p) {
        ArrayList<WCCSProcess> children = new ArrayList<>();
        for (WCCSProcess child : p.children) {
            child = visit(child); // visit the child
            if(child instanceof WCCSProcess.Choice){
                // flatten nested choices P + (Q + R) = P + Q + R
                children.addAll(((WCCSProcess.Choice) child).children);
            }else if(child instanceof WCCSProcess.Nil){
                // skip nil processes P + 0 = P
            }else{
                children.add(child);
            }
        }
        if(children.size() == 0){
            return new WCCSProcess.Nil();
        }else if (children.size() == 1){
            return children.getFirst();
        }

        return new WCCSProcess.Choice(children);
    }
}
