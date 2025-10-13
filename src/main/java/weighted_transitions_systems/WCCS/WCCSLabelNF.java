package weighted_transitions_systems.WCCS;

import java.util.ArrayList;

/**
 * This visitor pushes upwards all process labes while aggregating them
 */
public class WCCSLabelNF extends WCCSBaseNFVisitor{

    @Override
    public WCCSProcess visitAtomicLabel(WCCSProcess.AtomicLabel p) {
        WCCSProcess child = visit(p.process);
        ArrayList<String> labels = new ArrayList<>(p.labels);
        if(child instanceof WCCSProcess.AtomicLabel al_child){
            // a:(b:P) ~ (a:b:P) aggregate labeling together
            labels.addAll(al_child.labels);
            return new WCCSProcess.AtomicLabel(labels,al_child.process);
        }else{
            return new WCCSProcess.AtomicLabel(labels,child);
        }
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
                children.add(al_child.process);
            }else {
                children.add(child);
            }
        }
        WCCSProcess ret = new WCCSProcess.Choice(children);
        if (labels.size() > 0){
            ret = new WCCSProcess.AtomicLabel(labels, ret);
        }
        return ret;
    }

    @Override
    public WCCSProcess visitRestriction(WCCSProcess.Restriction p) {
        // p = Q\S
        WCCSProcess child = visit(p.process); // child ~ Q
        if(child instanceof WCCSProcess.AtomicLabel a_child){
            // child = (a:P)
            // p ~ child\S = (a:P)\S ~ a:(P\S)
            return new WCCSProcess.AtomicLabel(a_child.labels,
                    new WCCSProcess.Restriction(a_child.process,p.chanSet));
        }else{
            p.resetID();
            p.process = child;
            return p;
        }
    }

    @Override
    public WCCSProcess visitChannelRenaming(WCCSProcess.ChannelRenaming p) {
        // p = Q[c => c']
        WCCSProcess child = visit(p.process); // Q ~ child
        if(child instanceof WCCSProcess.AtomicLabel a_child){
            // child = (a:P)
            // p = Q[c => c'] ~ child[c => c'] = (a:P)[c => c'] ~ a:(P[c => c'])
            return new WCCSProcess.AtomicLabel(a_child.labels,
                    new WCCSProcess.ChannelRenaming(a_child.process,p.renaming));
        }else{
            p.resetID();
            p.process = child;
            return p;
        }
    }

    @Override
    public WCCSProcess visitLabelRenaming(WCCSProcess.LabelRenaming p) {
        WCCSProcess child = visit(p.process);
        if(child instanceof WCCSProcess.AtomicLabel al_child){
            // (a:P)[a => b] ~ b:(P[a => b])
            ArrayList<String> renamedLabels = new ArrayList<>();
            // apply the renaming to all labels
            for (String label : al_child.labels) {
                renamedLabels.add(p.renaming.getOrDefault(label,label));
            }
            return new WCCSProcess.AtomicLabel(renamedLabels,
                    new WCCSProcess.LabelRenaming(al_child.process,p.renaming));
        }else{
            p.resetID();
            p.process = child;
            return p;
        }
    }
}
