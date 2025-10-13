package weighted_transitions_systems.WCCS;

import org.checkerframework.checker.units.qual.A;

import java.util.*;

/**
 * This visitor pushes down and aggregates all label renaming
 */
public class WCCSLabelRenamingNF extends WCCSBaseNFVisitor{

    /**
     * Calculate the remaming obtained as {@code f(g(x))} from {@code f(x)} and {@code g(x)}
     * @param f
     * @param g
     * @return {@code f(g(x))}
     */
    private Map<String, String> composeRenaming(Map<String, String> f, Map<String, String> g) {
        // compute the composition
        Map<String, String> fg = new TreeMap<>();
        fg.putAll(f);
        g.forEach((x,gx)->{
            String value = f.getOrDefault(gx,gx);
            fg.put(x, f.getOrDefault(gx,gx));
        });
        removeIdentity(fg); // simplify
        return fg;
    }
    private void removeIdentity(Map<String, String> map){
        // remove identity
        Map<String,String> retmap = new HashMap<>();
        map.forEach((x, fgx)->{
            if(!fgx.equals(x)) retmap.put(x,fgx);
        });
        map.clear();
        map.putAll(retmap);
    }

    @Override
    public WCCSProcess visitLabelRenaming(WCCSProcess.LabelRenaming p) {
        if(p.process instanceof WCCSProcess.Nil){
            // 0[\rho] ~ 0
            return p.process;
        }else if(p.process instanceof WCCSProcess.Action child) {
            // push the renaming down (a.P)[\rho] ~ a.(P[\rho])
            WCCSProcess p_rho = visit(new WCCSProcess.LabelRenaming(child.process, p.renaming));
            return new WCCSProcess.Action(child.isInput, child.channel, child.weight,p_rho);
        }else if(p.process instanceof WCCSProcess.Parallel){
            // push renaming down (P | Q)[\rho] ~ P[\rho] | Q[\rho]
            WCCSProcess.Parallel c_child = (WCCSProcess.Parallel) p.process;
            ArrayList<WCCSProcess> newChildren = new ArrayList<>();
            for (WCCSProcess child : c_child.children) {
                newChildren.add(visit( new WCCSProcess.LabelRenaming(child,p.renaming)));
            }
            return new WCCSProcess.Parallel(newChildren);
        }else if(p.process instanceof WCCSProcess.Choice c_child){
            // push renaming down (P + Q)[\rho] ~ P[\rho] + Q[\rho]
            ArrayList<WCCSProcess> newChildren = new ArrayList<>();
            for (WCCSProcess child : c_child.children) {
                newChildren.add(visit( new WCCSProcess.LabelRenaming(child,p.renaming)));
            }
            return new WCCSProcess.Choice(newChildren);
        }else if(p.process instanceof WCCSProcess.LabelRenaming) {
            // compose renaming P[\rho][\phi] ~ P[\phi \circ \rho]
            WCCSProcess.LabelRenaming child = (WCCSProcess.LabelRenaming) p.process;
            Map<String, String> renaming = composeRenaming(p.renaming, child.renaming);
            if (renaming.size() == 0) {
                return visit(child.process);
            } else {
                return visit(new WCCSProcess.LabelRenaming(child.process, renaming));
            }
        }else if(p.process instanceof WCCSProcess.AtomicLabel child){
            // (a:P)[a => b] ~ b:(P[a => b])
            HashSet<String> renamedLabels = new HashSet<>();
            for (String label : child.labels) {
                renamedLabels.add(p.renaming.getOrDefault(label,label));
            }
            return new WCCSProcess.AtomicLabel(new ArrayList<>(renamedLabels),
                    visit(new WCCSProcess.LabelRenaming(child.process,p.renaming)));
        }
        // apply default visitor
        return super.visitLabelRenaming(p);
    }

}
