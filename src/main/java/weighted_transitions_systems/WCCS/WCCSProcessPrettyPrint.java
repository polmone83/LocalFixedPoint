package weighted_transitions_systems.WCCS;
import java.util.Collections;

import static weighted_transitions_systems.WCCS.WCCSProcess.TAU;

public class WCCSProcessPrettyPrint implements WCCSProcessVisitor<StringBuilder>{
    StringBuilder pp;

    public WCCSProcessPrettyPrint(){
        this.pp = new StringBuilder();
    }

    @Override
    public StringBuilder visit(WCCSProcess p) {
        pp.append("(");
        WCCSProcessVisitor.super.visit(p);
        pp.append(")");
        return pp;
    }

    @Override
    public StringBuilder visitNil(WCCSProcess.Nil p) {
        return pp.append("0");
    }

    @Override
    public StringBuilder visitAtomicLabel(WCCSProcess.AtomicLabel p) {
        Collections.sort(p.labels);
        for (String label : p.labels) {
            pp.append(label);
            pp.append(":");
        }
        visit(p.process);
        return pp;
    }

    @Override
    public StringBuilder visitAction(WCCSProcess.Action p) {
        pp.append("<");
        pp.append(p.channel);
        if(!p.channel.equals(TAU) && !p.isInput) pp.append("!");
        pp.append(",");
        pp.append(p.weight);
        pp.append(">.");
        visit(p.process);
        return pp;
    }

    @Override
    public StringBuilder visitParallel(WCCSProcess.Parallel p) {
        if(p.children.size() > 0 ){
            for (WCCSProcess child : p.children) {
                visit(child);
                pp.append("|");
            }
            pp.delete(pp.length()-1, pp.length());
        }else{
            // the process is equivalent to Nil
            pp.append('0');
        }
        return pp;
    }

    @Override
    public StringBuilder visitChoice(WCCSProcess.Choice p) {
        if(p.children.size() > 0){
            for (WCCSProcess child : p.children) {
                visit(child);
                pp.append("+");
            }
            pp.delete(pp.length()-1,pp.length()); //remove the last "+"
        }else{
            pp.append("0");
        }
        return pp;
    }

    @Override
    public StringBuilder visitParens(WCCSProcess.ParensProcess p) {
        visit(p.process);
        return pp;
    }

    @Override
    public StringBuilder visitRestriction(WCCSProcess.Restriction p) {
        visit(p.process);
        if(p.chanSet.size()> 0) {
            pp.append("\\{");
            for (String chan : p.chanSet) {
                pp.append(chan).append(", ");
            }
            pp.delete(pp.length() - 2, pp.length());
            pp.append("}");
        }
        return pp;
    }

    @Override
    public StringBuilder visitChannelRenaming(WCCSProcess.ChannelRenaming p) {
        visit(p.process);
        if(p.renaming.size() > 0) {
            pp.append("[");
            p.renaming.forEach((String from, String to) -> {
                pp.append(from);
                pp.append("->");
                pp.append(to);
                pp.append(", ");
            });
            pp.delete(pp.length()-2, pp.length());
            pp.append("]");
        }
        return pp;
    }

    @Override
    public StringBuilder visitLabelRenaming(WCCSProcess.LabelRenaming p) {
        visit(p.process);
        if(p.renaming.size() > 0) {
            pp.append("[");
            p.renaming.forEach((String from, String to) -> {
                pp.append(from);
                pp.append("=>");
                pp.append(to);
                pp.append(", ");
            });
            pp.delete(pp.length()-2, pp.length());
            pp.append("]");
        }
        return pp;
    }

    @Override
    public StringBuilder visitPName(WCCSProcess.PName p) {
        pp.append(p.pName);
        return pp;
    }
}
