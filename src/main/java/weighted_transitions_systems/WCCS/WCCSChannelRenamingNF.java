package weighted_transitions_systems.WCCS;

import labeled_transitions_systems.CCS.CCSProcess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * This visitor pushes down and aggregates all channel renamings
 */
public class WCCSChannelRenamingNF extends WCCSBaseNFVisitor{

    @Override
    public WCCSProcess visitChannelRenaming(WCCSProcess.ChannelRenaming p) {
        if(p.process instanceof WCCSProcess.Nil){
            // 0[\rho] ~ 0
            return p.process;
        }else if(p.process instanceof WCCSProcess.AtomicLabel child){
            // (a:P)[\rho] ~ a:(P[\rho])
            child.resetID();
            child.process = visit(new WCCSProcess.ChannelRenaming(child.process, p.renaming));
            return child;
        }else if(p.process instanceof WCCSProcess.Action child) {
            // push the renaming down (a.P)[\rho] ~ \rho(a).(P[\rho])
            String renamedChan = p.renaming.getOrDefault(child.channel,child.channel); // \rho(a)
            WCCSProcess p_rho = visit(new WCCSProcess.ChannelRenaming(child.process, p.renaming));
            return new WCCSProcess.Action(child.isInput, renamedChan, child.weight, p_rho);
//        }else if(p.process instanceof WCCSProcess.Parallel c_child){
//            // push renaming down (P | Q)[\rho] ~ P[\rho] | Q[\rho]
              // this is not correct unless \rho is a permutation
//            //WCCSProcess.Parallel c_child = (WCCSProcess.Parallel) p.process;
//            c_child.children.replaceAll(
//                    child -> visit(new WCCSProcess.ChannelRenaming(child,p.renaming))
//            );
//            c_child.resetID();
//            return c_child;
        }else if(p.process instanceof WCCSProcess.Choice c_child){
            // push renaming down (P + Q)[\rho] ~ P[\rho] + Q[\rho]

            ArrayList<WCCSProcess> renamedChildren = new ArrayList<>();
            for (WCCSProcess child : c_child.children) {
                renamedChildren.add(visit(new WCCSProcess.ChannelRenaming(child,p.renaming)));
            }
            return new WCCSProcess.Choice(renamedChildren);
        }else if(p.process instanceof WCCSProcess.ChannelRenaming child){
            // compose renaming P[\rho][\phi] ~ P[\phi \circ \rho]
            Map<String, String> renaming = composeRenaming(p.renaming, child.renaming);
            if(renaming.size() == 0){
                return visit(child.process);
            }else{
                return visit(new WCCSProcess.ChannelRenaming(child.process, renaming));
            }
        }
        return super.visitChannelRenaming(p);
    }

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

}
