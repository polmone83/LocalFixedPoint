package labeled_transitions_systems.CCS;

import java.util.*;

public class CCSProcessNormalForm2 implements CCSProcessVisitor<CCSProcess>{
    //HashMap<String, CCSProcess> table;

    @Override
    public CCSProcess visitNil(CCSProcess.Nil p) {
        return p;
    }

    @Override
    public CCSProcess visitAction(CCSProcess.Action p) {
        return new CCSProcess.Action(p.isInput,p.channel,visit(p.process));
    }

    @Override
    public CCSProcess visitParallel(CCSProcess.Parallel p) {
        ArrayList<CCSProcess> children = new ArrayList<>();
        for (CCSProcess child : p.children) {
            child = visit(child); // visit the child
            children.add(child);
        }
        return new CCSProcess.Parallel(children);
    }

    @Override
    public CCSProcess visitChoice(CCSProcess.Choice p) {
        Set<CCSProcess> children = new HashSet<>();
        for (CCSProcess child : p.children) {
            child = visit(child); // visit the child
            children.add(child);
        }
        return new CCSProcess.Choice(children);
    }

    @Override
    public CCSProcess visitParens(CCSProcess.ParensProcess p) {
        // skip the parenthesis (P) ~ P
        return visit(p);
    }

    @Override
    public CCSProcess visitRestriction(CCSProcess.Restriction p) {
        if(p.process instanceof CCSProcess.Choice){
            // push restriction down (P + Q)\S ~ P\S + Q\S
            HashSet<CCSProcess> list = new HashSet<>();
            HashSet<CCSProcess> oldList = ((CCSProcess.Choice) p.process).children;
            for (CCSProcess child : oldList) {
                CCSProcess subproc = visit(new CCSProcess.Restriction(child,p.chanSet));
                list.add(subproc);
            }
            return new CCSProcess.Choice(list);
        }else if (p.process instanceof CCSProcess.Restriction child){
            // flatten nested restrictions
            child.chanSet.addAll(p.chanSet); // merge the channel sets
            return visit(child); // skip this process
        }else if(p.process instanceof CCSProcess.Action child){
            if(! p.chanSet.contains(child.channel)){
                // (a.P) \ S ~ a.(P\S)
                CCSProcess subproc = visit(new CCSProcess.Restriction(child.process,p.chanSet));
                return new CCSProcess.Action(child.isInput,child.channel,subproc);
            }
        }
        return p;
    }

    @Override
    public CCSProcess visitRenaming(CCSProcess.Renaming p) {
        if(p.process instanceof CCSProcess.Action child) {
            // push the renaming down (a.P)[\rho] ~ \rho(a).(P[\rho])
            String renamedChan = p.applyRenaming(child.channel); // \rho(a)
            CCSProcess p_rho = visit(new CCSProcess.Renaming(child.process, p.renaming));
            return new CCSProcess.Action(child.isInput, renamedChan, p_rho);
//        }else if(p.process instanceof CCSProcess.Parallel){
//            // push renaming down (P | Q)[\rho] ~ P[\rho] | Q[\rho]
//            ArrayList<CCSProcess> list = new ArrayList<>();
//            ArrayList<CCSProcess> oldList = ((CCSProcess.Parallel) p.process).children;
//            for (CCSProcess child : oldList) {
//                CCSProcess subproc = visit(new CCSProcess.Renaming(child,p.renaming));
//                list.add(subproc);
//            }
//            return new CCSProcess.Parallel(list);
        }else if(p.process instanceof CCSProcess.Choice){
            // push renaming down (P + Q)[\rho] ~ P[\rho] + Q[\rho]
            Set<CCSProcess> list = new HashSet<>();
            Set<CCSProcess> oldList = ((CCSProcess.Choice) p.process).children;
            for (CCSProcess child : oldList) {
                CCSProcess subproc = visit(new CCSProcess.Renaming(child,p.renaming));
                list.add(subproc);
            }
            return new CCSProcess.Choice(list);
        }
        return p;
    }

    @Override
    public CCSProcess visitPName(CCSProcess.PName p) {
        return p;
    }
}
