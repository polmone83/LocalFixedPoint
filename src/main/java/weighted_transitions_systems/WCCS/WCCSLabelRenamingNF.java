package weighted_transitions_systems.WCCS;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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
        }else if(p.process instanceof WCCSProcess.Action) {
            // push the renaming down (a.P)[\rho] ~ a.(P[\rho])
            WCCSProcess.Action child = (WCCSProcess.Action) p.process;
            WCCSProcess p_rho = visit(new WCCSProcess.LabelRenaming(child.process, p.renaming));
            child.process = p_rho;
            child.resetID();
            return child;
        }else if(p.process instanceof WCCSProcess.Parallel){
            // push renaming down (P | Q)[\rho] ~ P[\rho] | Q[\rho]
            WCCSProcess.Parallel c_child = (WCCSProcess.Parallel) p.process;
            c_child.children.replaceAll(
                    child -> visit( new WCCSProcess.LabelRenaming(child,p.renaming))
            );
            c_child.resetID();
            return c_child;
        }else if(p.process instanceof WCCSProcess.Choice){
            // push renaming down (P + Q)[\rho] ~ P[\rho] + Q[\rho]
            WCCSProcess.Choice c_child = (WCCSProcess.Choice) p.process;
            c_child.children.replaceAll(
                    child -> visit( new WCCSProcess.LabelRenaming(child,p.renaming))
            );
            c_child.resetID();
            return c_child;
        }else if(p.process instanceof WCCSProcess.LabelRenaming) {
            // compose renaming P[\rho][\phi] ~ P[\phi \circ \rho]
            WCCSProcess.LabelRenaming child = (WCCSProcess.LabelRenaming) p.process;
            Map<String, String> renaming = composeRenaming(p.renaming, child.renaming);
            if (renaming.size() == 0) {
                return visit(child.process);
            } else {
                return visit(new WCCSProcess.LabelRenaming(child.process, renaming));
            }
        }else if(p.process instanceof WCCSProcess.AtomicLabel){
            // (a:P)[a => b] ~ b:(P[a => b])
            WCCSProcess.AtomicLabel child = (WCCSProcess.AtomicLabel) p.process;
            child.labels.replaceAll(label-> p.renaming.getOrDefault(label,label));
            child.process = visit(new WCCSProcess.LabelRenaming(child.process,p.renaming));
            child.resetID();
            return child;
        }
        // apply default visitor
        return super.visitLabelRenaming(p);
    }

}
