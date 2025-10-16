package weighted_transitions_systems.WCCS;

import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.K;

import java.util.*;

import static weighted_transitions_systems.WCCS.WCCSProcess.TAU;

public class WCCSProcesSuccessor implements WCCSProcessVisitor<Set<WCCSProcess.WCCS_Step>>{

    HashMap<WCCSProcess, Set<WCCSProcess.WCCS_Step>> cache;
    HashMap<WCCSProcess, Set<WCCSProcess.WCCS_Step>> Kcache;

    public WCCSProcesSuccessor(){
        cache = new HashMap<>();
        Kcache = new HashMap<>();
    }


    public Set<WCCSProcess.WCCS_Step> getSuccessors(WCCSProcess p){
        return visit(p);
    }

    public Set<WCCSProcess.WCCS_Step> getSuccessors(WCCSProcess p, int k){
        Set<WCCSProcess.WCCS_Step> steps = new HashSet<>();

        Set<WCCSProcess.WCCS_Step> frontier = new HashSet<>();
        frontier.add(new WCCSProcess.WCCS_Step(p,0)); // add the first step
        steps.addAll(frontier);

        for(int i = 0; i < k; i++){
            frontier = extendSuccByOne(frontier);
            steps.addAll(frontier);
        }
        return steps;
    }

    public Set<WCCSProcess.WCCS_Step> getSuccessors(WCCSProcess p, int k, int bound){
        Set<WCCSProcess.WCCS_Step> steps = new HashSet<>();

        Set<WCCSProcess.WCCS_Step> frontier = new HashSet<>();
        frontier.add(new WCCSProcess.WCCS_Step(p,0)); // add the first step
        steps.addAll(frontier);

        for(int i = 0; i < k; i++){
            frontier = extendSuccByOne(frontier);
            for (WCCSProcess.WCCS_Step step : frontier) {
                if(step.weight <= bound) steps.add(step);
            }
        }
        return steps;
    }

    public Set<WCCSProcess.WCCS_Step> DFSsuccessors(WCCSProcess p, int k, int bound) {
        Set<WCCSProcess.WCCS_Step> discovered = Kcache.getOrDefault(p, null);
        if (discovered == null) {
            discovered = new HashSet<>(); // discovered
            ArrayList<WCCSProcess.WCCS_Step> S = new ArrayList<>(); // stack
            WCCSProcess.WCCS_Step source = new WCCSProcess.WCCS_Step(p, 0); // source
            S.add(source);
            discovered.add(source);
            while (discovered.size() <= k && S.size() > 0) {
                WCCSProcess.WCCS_Step q = S.getLast();
                S.removeLast();
                for (WCCSProcess.WCCS_Step step : getSuccessors(q.process)) {
                    WCCSProcess.WCCS_Step accStep = // accumulate the weight
                            new WCCSProcess.WCCS_Step(step.process,
                                    q.weight + step.weight);
                    if (accStep.weight <= bound && !discovered.contains(accStep)) { //
                        discovered.add(accStep); // add to discovered
                        S.add(accStep); // push the new element in the stack
                    }
                }
            }
            Kcache.put(p,discovered);
        }
        return discovered;
    }

    public Set<WCCSProcess.WCCS_Step> BFSsuccessors(WCCSProcess p, int k, int bound) {
        Set<WCCSProcess.WCCS_Step> discovered = Kcache.getOrDefault(p, null);
        if (discovered == null) {
            discovered = new HashSet<>(); // discovered
            ArrayList<WCCSProcess.WCCS_Step> Q = new ArrayList<>(); // queue
            WCCSProcess.WCCS_Step source = new WCCSProcess.WCCS_Step(p, 0); // source
            Q.add(source);
            discovered.add(source);

            while (discovered.size() <= k && Q.size() > 0) {
                WCCSProcess.WCCS_Step q = Q.getFirst(); // extract the element from the queue
                Q.removeFirst();
                for (WCCSProcess.WCCS_Step step : getSuccessors(q.process)) {
                    WCCSProcess.WCCS_Step accStep = // accumulate the weight
                            new WCCSProcess.WCCS_Step(step.process,
                                    q.weight + step.weight);
                    if (accStep.weight <= bound && !discovered.contains(accStep)) {
                        discovered.add(accStep); // add to discovered

                        Q.add(accStep); // enqueue the new element in the stack
                    }
                }
            }
            Kcache.put(p, discovered);
        }
        return discovered;
    }



    public Set<WCCSProcess.WCCS_Step> extendSuccByOne(Set<WCCSProcess.WCCS_Step> steps){
        Set<WCCSProcess.WCCS_Step> nextSteps = new HashSet<>();
        for (WCCSProcess.WCCS_Step step : steps) {
            Set<WCCSProcess.WCCS_Step> succ = getSuccessors(step.process);
            for (WCCSProcess.WCCS_Step nextStep : succ) {
                nextSteps.add(new WCCSProcess.WCCS_Step(nextStep.process,
                        step.weight+nextStep.weight));
            }
        }
        return nextSteps;
    }

    @Override
    public Set<WCCSProcess.WCCS_Step> visit(WCCSProcess p) {
        // make to normal form
        WCCSProcess nfP = WCCSProcess.toNormalForm(p);
        // fetch cached steps
        Set<WCCSProcess.WCCS_Step> nfSteps = cache.getOrDefault(nfP,null);
        if(nfSteps == null){ // if the steps where not cached already
            // compute the steps
            Set<WCCSProcess.WCCS_Step> steps = WCCSProcessVisitor.super.visit(p);
            // put them in normal form
            nfSteps = new HashSet<>();
            for(WCCSProcess.WCCS_Step step : steps){
                step.process = WCCSProcess.toNormalForm(step.process);
                nfSteps.add(step);
            }
            // cache the result before returning
            cache.put(nfP,nfSteps);
        }
        return nfSteps;
    }

    @Override
    public Set<WCCSProcess.WCCS_Step> visitNil(WCCSProcess.Nil p) {
        return new HashSet<>();
    }

    @Override
    public Set<WCCSProcess.WCCS_Step> visitAtomicLabel(WCCSProcess.AtomicLabel p) {
        return visit(p.process);
    }

    @Override
    public Set<WCCSProcess.WCCS_Step> visitAction(WCCSProcess.Action p) {
        Set<WCCSProcess.WCCS_Step> step = new HashSet<>();
        step.add(new WCCSProcess.WCCS_Step(p.isInput, p.channel, p.weight, p.process));
        return step;
    }

    @Override
    public Set<WCCSProcess.WCCS_Step> visitParallel(WCCSProcess.Parallel p) {
        Set<WCCSProcess.WCCS_Step> succ = new HashSet<>(); // the successors of this process

        for(int left = 0; left < p.children.size(); left++){
            WCCSProcess leftP = p.children.get(left);
            Set<WCCSProcess.WCCS_Step> leftSteps = visit(leftP);

            // collect sync steps
            for(int right = left+1; right < p.children.size(); right++){
                WCCSProcess rightP = p.children.get(right);
                Set<WCCSProcess.WCCS_Step> rightSteps = visit(rightP);

                for (WCCSProcess.WCCS_Step right_step : rightSteps) {
                    for (WCCSProcess.WCCS_Step left_step : leftSteps) {
                        if (! left_step.action.equals(TAU) &&
                                ! left_step.inputQ.equals(right_step.inputQ) &&
                                left_step.action.equals(right_step.action)
                        ) {

                            ArrayList<WCCSProcess> sync_P = new ArrayList<>(p.children);
                            // do not switch the order of the following two lines!!
                            sync_P.remove(right); sync_P.add(right_step.process);
                            sync_P.remove(left); sync_P.add(left_step.process);
                            Integer weight = Math.max(left_step.weight, right_step.weight);
                            WCCSProcess.WCCS_Step sync_step =
                                    new WCCSProcess.WCCS_Step(new WCCSProcess.Parallel(sync_P),weight);
                            succ.add(sync_step); // collect the step
                        }
                    }
                }
            }

            // collect async steps
            for (WCCSProcess.WCCS_Step left_step : leftSteps) {
                ArrayList<WCCSProcess> async_P = new ArrayList<>(p.children);
                async_P.remove(left); async_P.add(left_step.process);
                WCCSProcess.WCCS_Step async_step =
                        new WCCSProcess.WCCS_Step(
                                left_step.inputQ,
                                left_step.action,
                                left_step.weight,
                                new WCCSProcess.Parallel(async_P));
                succ.add(async_step); // collect the step
            }
        }

        return succ;
    }

    @Override
    public Set<WCCSProcess.WCCS_Step> visitChoice(WCCSProcess.Choice p) {
        Set<WCCSProcess.WCCS_Step> succ = new HashSet<>();
        for (WCCSProcess child : p.children) {
            succ.addAll(visit(child));
        }
        return succ;
    }

    @Override
    public Set<WCCSProcess.WCCS_Step> visitParens(WCCSProcess.ParensProcess p) {
        return visit(p.process);
    }

    @Override
    public Set<WCCSProcess.WCCS_Step> visitRestriction(WCCSProcess.Restriction p) {
        Set<WCCSProcess.WCCS_Step> succ = new HashSet<>();
        for (WCCSProcess.WCCS_Step proc_step : visit(p.process)) {
            if(!p.chanSet.contains(proc_step.action)){
                WCCSProcess.WCCS_Step restrictedStep =
                        new WCCSProcess.WCCS_Step(proc_step.inputQ,
                                proc_step.action,
                                proc_step.weight,
                                new WCCSProcess.Restriction(proc_step.process,p.chanSet)
                        );
                succ.add(restrictedStep);
            }
        }
        return succ;
    }

    @Override
    public Set<WCCSProcess.WCCS_Step> visitChannelRenaming(WCCSProcess.ChannelRenaming p) {
        Set<WCCSProcess.WCCS_Step> succ = new HashSet<>();
        for (WCCSProcess.WCCS_Step proc_step : visit(p.process)) {
            WCCSProcess.WCCS_Step renamedStep =
                    new WCCSProcess.WCCS_Step(proc_step.inputQ,
                            p.rename(proc_step.action), // applyRenaming
                            proc_step.weight,
                            new WCCSProcess.ChannelRenaming(proc_step.process,p.renaming) // wrap process
                    );
            succ.add(renamedStep);
        }
        return succ;
    }

    @Override
    public Set<WCCSProcess.WCCS_Step> visitLabelRenaming(WCCSProcess.LabelRenaming p) {
        Set<WCCSProcess.WCCS_Step> succ = new HashSet<>();
        for(WCCSProcess.WCCS_Step step : visit(p.process)){
            WCCSProcess.WCCS_Step renamedStep =
                    new WCCSProcess.WCCS_Step(step.inputQ,
                            step.action,
                            step.weight,
                            new WCCSProcess.LabelRenaming(step.process, p.renaming)
                    );
            succ.add(renamedStep);
        }
        return succ;
    }

    @Override
    public Set<WCCSProcess.WCCS_Step> visitPName(WCCSProcess.PName p) {
        return visit(p.getDef());
    }
}
