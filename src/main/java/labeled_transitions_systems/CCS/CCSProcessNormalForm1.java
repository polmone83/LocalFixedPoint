package labeled_transitions_systems.CCS;

import java.util.*;

public class CCSProcessNormalForm1 implements CCSProcessVisitor<CCSProcess>{
    //HashMap<String, CCSProcess> table;

    @Override
    public CCSProcess visitNil(CCSProcess.Nil p) {
        return p;
    }

    @Override
    public CCSProcess visitAction(CCSProcess.Action p) {
        p.resetID();
        p.process = visit(p.process);
        return p;
    }

    @Override
    public CCSProcess visitParallel(CCSProcess.Parallel p) {
        ArrayList<CCSProcess> children = new ArrayList<>();
        for (CCSProcess child : p.children) {
            child = visit(child); // visit the child
            if(child instanceof CCSProcess.Parallel){
                // flatten nested parallel processes
                // Parallel(A, Parallel(B,C)) ~ Parallel(A,B,C)
                children.addAll(((CCSProcess.Parallel) child).children);
            }else if(child instanceof CCSProcess.Nil){
                // Parallel(A, 0) ~ Parallel(A)
                // skip nil processes
            }else{
                children.add(child);
            }
        }
        if(children.size() == 0){
            // Parallel() ~ 0
            return new CCSProcess.Nil();
        }else if (children.size() == 1){
            // Parallel(A) ~ A
            return children.getFirst();
        }
        // replace the children with the new list
        //p.children.clear();
        //p.children.addAll(children);
        Collections.sort(children);
        CCSProcess ret = new CCSProcess.Parallel(new ArrayList<>(children));
        return ret;
    }

    @Override
    public CCSProcess visitChoice(CCSProcess.Choice p) {
        TreeSet<CCSProcess> children = new TreeSet();
        for (CCSProcess child : p.children) {
            child = visit(child); // visit the child
            if(child instanceof CCSProcess.Choice){
                // flatten nested choices P + (Q + R) = P + Q + R
                children.addAll(((CCSProcess.Choice) child).children);
            }else if(child instanceof CCSProcess.Nil){
                // skip nil processes P + 0 = P
            }else{
                children.add(child);
            }
        }
        if(children.size() == 0){
            return new CCSProcess.Nil();
        }else if (children.size() == 1){
            return children.getFirst();
        }

        return new CCSProcess.Choice(children);
    }

    @Override
    public CCSProcess visitParens(CCSProcess.ParensProcess p) {
        // skip the parenthesis (P) ~ P
        return visit(p.process);
    }

    @Override
    public CCSProcess visitRestriction(CCSProcess.Restriction p) {
        p.resetID();
        p.process = visit(p.process);
        if(p.process instanceof CCSProcess.Nil) {
            // simplify nil process 0\S ~ 0
            return p.process;
        }else if(p.chanSet.size() == 0){
            return p.process;
        }else if(p.process instanceof CCSProcess.Action) {
            CCSProcess.Action child = (CCSProcess.Action) p.process;
            if(p.chanSet.contains(child.channel)){
                // if a \in L then (a.P)\S ~ 0
                return new CCSProcess.Nil();
            }
        }else if (p.process instanceof CCSProcess.Restriction){
            // flatten nested restrictions
            CCSProcess.Restriction child = (CCSProcess.Restriction) p.process;
            p.chanSet.addAll(child.chanSet); // merge the channel sets
            p.process = child.process; // skip this process
        }
        return p;
    }

    @Override
    public CCSProcess visitRenaming(CCSProcess.Renaming p) {
        p.resetID();
        p.process = visit(p.process);
        if(p.process instanceof CCSProcess.Nil) {
            // simplify nil process 0[\rho] ~ 0
            return p.process;
        }else if(p.renaming.size() == 0){
            return p.process;
        }else if(p.process instanceof CCSProcess.Renaming){
            // compose renaming P[\rho][\phi] ~ P[\phi \circ \rho]
            CCSProcess.Renaming child = (CCSProcess.Renaming) p.process;
            Map<String, String> renaming = composeRenaming(p.renaming, child.renaming);
            if(renaming.size() == 0){
                return child.process;
            }else{
                return new CCSProcess.Renaming(child.process, renaming);
            }
        }
        return p;
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

    @Override
    public CCSProcess visitPName(CCSProcess.PName p) {
        return p;
    }
}
