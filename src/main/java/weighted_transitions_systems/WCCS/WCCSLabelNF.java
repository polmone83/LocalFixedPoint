package weighted_transitions_systems.WCCS;

import java.util.ArrayList;

/**
 * This visitor pushes upwards all process labes while aggregating them
 */
public class WCCSLabelNF extends WCCSBaseNFVisitor{

    @Override
    public WCCSProcess visitAtomicLabel(WCCSProcess.AtomicLabel p) {
        WCCSProcess child = visit(p.process);
        if(child instanceof WCCSProcess.AtomicLabel){
            // a:(b:P) ~ (a:b:P) aggregate labeling together
            WCCSProcess.AtomicLabel al_child = (WCCSProcess.AtomicLabel) child;
            p.labels.addAll(al_child.labels);
            p.process = al_child.process;
        }else{
            p.process = child;
        }
        p.resetID();
        return p;
    }

    @Override
    public WCCSProcess visitParallel(WCCSProcess.Parallel p) {
        ArrayList<WCCSProcess> children = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();
        // (l2:P | l3:Q) ~ l2:l3:(P|Q)
        for (WCCSProcess child : p.children) {
            child = visit(child);
            if(child instanceof WCCSProcess.AtomicLabel){
                WCCSProcess.AtomicLabel al_child = (WCCSProcess.AtomicLabel) child;
                labels.addAll(al_child.labels);
                child = al_child.process;
            }
            children.add(child);
        }
        p.resetID();
        p.children = children;
        WCCSProcess ret = p;
        if (labels.size() > 0){
            ret = new WCCSProcess.AtomicLabel(labels, ret);
        }
        return ret;
    }

    @Override
    public WCCSProcess visitChoice(WCCSProcess.Choice p) {
        ArrayList<WCCSProcess> children = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();
        // (l2:P + l3:Q) ~ l2:l3:(P+Q)
        for (WCCSProcess child : p.children) {
            child = visit(child);
            if(child instanceof WCCSProcess.AtomicLabel){
                WCCSProcess.AtomicLabel al_child = (WCCSProcess.AtomicLabel) child;
                labels.addAll(al_child.labels);
                child = al_child.process;
            }
            children.add(child);
        }
        p.resetID();
        p.children = children;
        WCCSProcess ret = p;
        if (labels.size() > 0){
            ret = new WCCSProcess.AtomicLabel(labels, ret);
        }
        return ret;
    }

    @Override
    public WCCSProcess visitRestriction(WCCSProcess.Restriction p) {
        // p = Q\S
        WCCSProcess child = visit(p.process); // child ~ Q
        p.resetID();
        if(child instanceof WCCSProcess.AtomicLabel){
            // p ~ child\S = (a:P)\S ~ a:(P\S)
            WCCSProcess.AtomicLabel a_child = (WCCSProcess.AtomicLabel) child; // a_child = (a:P)
            p.process = a_child.process; // p = P\S
            return new WCCSProcess.AtomicLabel(a_child.labels,p);
        }else{
            p.process = child;
            return p;
        }
    }

    @Override
    public WCCSProcess visitChannelRenaming(WCCSProcess.ChannelRenaming p) {
        // p = Q[c => c']
        WCCSProcess child = visit(p.process); // Q ~ child
        p.resetID();
        if(child instanceof WCCSProcess.AtomicLabel){
            // p = Q[c => c'] ~ child[c => c'] = (a:P)[c => c'] ~ a:(P\[c => c'])
            WCCSProcess.AtomicLabel a_child = (WCCSProcess.AtomicLabel) child;
            p.process = a_child.process; // p = P[c => c']
            return new WCCSProcess.AtomicLabel(a_child.labels,p);
        }else{
            p.process = child;
            return p;
        }
    }

    @Override
    public WCCSProcess visitLabelRenaming(WCCSProcess.LabelRenaming p) {
        WCCSProcess child = visit(p.process);
        p.resetID();
        if(child instanceof WCCSProcess.AtomicLabel){
            // (a:P)[a => b] ~ b:(P[a => b])
            WCCSProcess.AtomicLabel al_child = (WCCSProcess.AtomicLabel) child;
            al_child.labels.replaceAll(label -> p.renaming.getOrDefault(label,label));
            /*ArrayList<String> renamedLabels = new ArrayList<>();
            // apply the renaming to all labels
            for (String label : al_child.labels) {
                renamedLabels.add(p.renaming.getOrDefault(label,label));
            }*/
            p.process = al_child.process;
            return new WCCSProcess.AtomicLabel(al_child.labels,p);
        }else{
            p.process = child;
            return p;
        }
    }
}
