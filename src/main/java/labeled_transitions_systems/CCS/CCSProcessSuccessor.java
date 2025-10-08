package labeled_transitions_systems.CCS;

import java.util.*;

import static labeled_transitions_systems.CCS.CCSProcess.TAU;

public class CCSProcessSuccessor implements CCSProcessVisitor<Set<CCSProcess.CCS_Step>>{

    HashMap<CCSProcess,Set<CCSProcess.CCS_Step>> cacheStrong;
    HashMap<CCSProcess,Set<CCSProcess.CCS_Step>> cacheWeak;
    // put the successors in normal form
    private static final CCSProcessVisitor<CCSProcess> nf = new CCSProcessNormalForm1();
    private static final CCSProcessVisitor<CCSProcess> nf2 = new CCSProcessNormalForm2();

    public CCSProcessSuccessor(){
        cacheStrong = new HashMap<>();
        cacheWeak = new HashMap<>();
    }

    private CCSProcess toNormalForm(CCSProcess p){
        return nf.visit(nf2.visit(p));
    }

    public Set<CCSProcess.CCS_Step> getSuccessors(CCSProcess p){
        return visit(p);
    }

    public Set<CCSProcess.CCS_Step> getWeakSuccessors(CCSProcess p){
        CCSProcess nfP = toNormalForm(p);
        Set<CCSProcess.CCS_Step> wSteps = cacheWeak.getOrDefault(nfP,null);

        if(wSteps == null){ // the steps are not cached
            // compute the weak steps
            wSteps = new HashSet<>();
            for (CCSProcess next : tauSuccessors(nfP)) {
                // add immediate tau step
                wSteps.add(new CCSProcess.CCS_Step(next));
                // continue after tau steps
                for (CCSProcess.CCS_Step step : visit(next)) {
                    if(!step.action.equals(TAU)){
                        for (CCSProcess next2 : tauSuccessors(step.process)) {
                            wSteps.add(new CCSProcess.CCS_Step(step.inputQ,step.action,next2));
                        }
                    }
                }
            }
            // cache the weak steps
            cacheWeak.put(nfP,wSteps);
        }

        return wSteps;
    }

    @Override
    public Set<CCSProcess.CCS_Step> visit(CCSProcess p) {
        // make to normal form
        CCSProcess nfP = toNormalForm(p);
        Set<CCSProcess.CCS_Step> nfSteps = cacheStrong.getOrDefault(nfP, null);
        if(nfSteps == null){ // if the steps where not cached already
            // compute the steps
            Set<CCSProcess.CCS_Step> steps = CCSProcessVisitor.super.visit(p);
            // put them in normal form
            nfSteps = new HashSet<>();
            for (CCSProcess.CCS_Step step : steps) {
                step.process = toNormalForm(step.process);
                nfSteps.add(step);
            }
            // cache the result before returning
            cacheStrong.put(nfP,nfSteps);
        }
        return nfSteps;
    }

    private Set<CCSProcess> tauSuccessors(CCSProcess p){
        HashSet<CCSProcess> visited = new HashSet<>();
        ArrayList<CCSProcess> frontier = new ArrayList<>();

        frontier.add(p);
        while(!frontier.isEmpty()){
            // extract an element from the frontier
            CCSProcess proc = frontier.getFirst();
            frontier.remove(proc);
            visited.add(proc); // set as visited
            // scan the successors
            for (CCSProcess.CCS_Step step : visit(proc) ) { //
                if(step.action.equals(TAU) && visited.add(step.process)){
                    frontier.add(step.process);
                }
            }
        }
        return visited;
    }


    @Override
    public Set<CCSProcess.CCS_Step> visitNil(CCSProcess.Nil p) {
        return new HashSet();
    }

    @Override
    public Set<CCSProcess.CCS_Step> visitAction(CCSProcess.Action p) {
        HashSet<CCSProcess.CCS_Step> succ = new HashSet<>();
        succ.add(new CCSProcess.CCS_Step(p.isInput,p.channel,p.process));
        return succ;
    }

    @Override
    public Set<CCSProcess.CCS_Step> visitParallel(CCSProcess.Parallel p) {
        Set<CCSProcess.CCS_Step> succ = new HashSet<>();// the successors of this process

        ArrayList<Set<CCSProcess.CCS_Step>> stepsList = new ArrayList<>();
        for (CCSProcess child : p.children) {
            stepsList.add(visit(child));
        }

        for(int left = 0; left < p.children.size(); left++){
            Set<CCSProcess.CCS_Step> leftSteps = stepsList.get(left);

            // collect sync steps
            for(int right = left+1; right < p.children.size(); right++){
                Set<CCSProcess.CCS_Step> rightSteps = stepsList.get(right);

                for (CCSProcess.CCS_Step right_step : rightSteps) {
                    for (CCSProcess.CCS_Step left_step : leftSteps) {
                        if (! left_step.action.equals(TAU) &&
                                ! left_step.inputQ.equals(right_step.inputQ) &&
                                 left_step.action.equals(right_step.action)
                        ) {

                            ArrayList<CCSProcess> sync_P = new ArrayList<>(p.children);
                            sync_P.remove(right); sync_P.add(right_step.process);
                            sync_P.remove(left); sync_P.add(left_step.process);
                            CCSProcess.CCS_Step sync_step = new CCSProcess.CCS_Step(new CCSProcess.Parallel(sync_P));
                            succ.add(sync_step); // collect the step
                        }
                    }
                }
            }

            // collect async steps
            for (CCSProcess.CCS_Step left_step : leftSteps) {
                ArrayList<CCSProcess> async_P = new ArrayList<>(p.children);
                async_P.remove(left); async_P.add(left_step.process);
                CCSProcess.CCS_Step async_step =
                        new CCSProcess.CCS_Step(left_step.inputQ,
                                left_step.action,
                                new CCSProcess.Parallel(async_P));
                succ.add(async_step); // collect the step
            }
        }

        return succ;
    }

    @Override
    public Set<CCSProcess.CCS_Step> visitChoice(CCSProcess.Choice p) {
        Set<CCSProcess.CCS_Step> succ = new HashSet<>();
        for (CCSProcess child : p.children) {
            succ.addAll(visit(child));
        }
        return succ;
    }

    @Override
    public Set<CCSProcess.CCS_Step> visitParens(CCSProcess.ParensProcess p) {
        return visit(p.process);
    }

    @Override
    public Set<CCSProcess.CCS_Step> visitRestriction(CCSProcess.Restriction p) {
        Set<CCSProcess.CCS_Step> succ = new HashSet<>();
        for (CCSProcess.CCS_Step proc_step : visit(p.process)) {
            if(!p.chanSet.contains(proc_step.action)){
                CCSProcess.CCS_Step restritedStep =
                        new CCSProcess.CCS_Step(proc_step.inputQ,
                                proc_step.action,new CCSProcess.Restriction(proc_step.process,p.chanSet));
                succ.add(restritedStep);
            }
        }
        return succ;
    }

    @Override
    public Set<CCSProcess.CCS_Step> visitRenaming(CCSProcess.Renaming p) {
        Set<CCSProcess.CCS_Step> succ = new HashSet<>();
        for (CCSProcess.CCS_Step step : visit(p.process)) {
            CCSProcess.CCS_Step renamedStep =
                    new CCSProcess.CCS_Step(step.inputQ,
                            p.applyRenaming(step.action), // applyRenaming
                            new CCSProcess.Renaming(step.process,p.renaming)); // wrap process
            succ.add(renamedStep);
        }
        return succ;
    }

    @Override
    public Set<CCSProcess.CCS_Step> visitPName(CCSProcess.PName p) {
        return visit(p.getDef());
    }
}
