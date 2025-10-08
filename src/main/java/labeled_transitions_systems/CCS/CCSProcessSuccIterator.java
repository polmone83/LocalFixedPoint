package labeled_transitions_systems.CCS;

import com.google.common.collect.Iterators;
import utilities.SetIterator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static labeled_transitions_systems.CCS.CCSProcess.TAU;

public class CCSProcessSuccIterator implements CCSProcessVisitor<Iterator<CCSProcess.CCS_Step>>{

    public static Iterator<CCSProcess.CCS_Step> getSuccessors(CCSProcess p){
        CCSProcessSuccIterator visitor = new CCSProcessSuccIterator();
        CCSProcessVisitor<CCSProcess> nf = new CCSProcessNormalForm1();
        CCSProcessVisitor<CCSProcess> nf2 = new CCSProcessNormalForm2();
        return new SetIterator<>(Iterators.transform(visitor.visit(p),
                step -> {
                    step.process = nf2.visit(step.process);
                    step.process = nf.visit(step.process);
                    return step;
                }));
    }

    public static Iterator<CCSProcess.CCS_Step> getWeakSuccessors(CCSProcess p){
        HashSet<CCSProcess> visited = new HashSet();
        Iterator<CCSProcess.CCS_Step> alphaSteps1 = Iterators.concat(Iterators.transform(
                    tauSuccessors(p),
                step -> {
                        //System.out.println("first step:" + step);
                        return Iterators.filter(getSuccessors(step.process), stp -> ! stp.action.equals(TAU));
                    }
                ));

        Iterator<CCSProcess.CCS_Step> alphaSteps = Iterators.concat(Iterators.transform(
                        alphaSteps1,
                step -> {
                            //System.out.println("second step:" + step);
                            return Iterators.transform(tauSuccessors(step.process),
                            stp -> {
                                //System.out.println("third step:" + stp + "from" + step.process);
                                return new CCSProcess.CCS_Step(step.inputQ,step.action,stp.process);
                            }
                    );
                }
                ));

        return new SetIterator<>(Iterators.concat(
                tauSuccessors(p,new HashSet<>()), // tau steps
                alphaSteps));
    }

    private static Iterator<CCSProcess.CCS_Step> tauSuccessors(CCSProcess p){
        return tauSuccessors(p, new HashSet<>());
    }

    private static Iterator<CCSProcess.CCS_Step> tauSuccessors(CCSProcess p, HashSet<CCSProcess> visited){

        if(! visited.add(p)){
            return Stream.<CCSProcess.CCS_Step>empty().iterator();
        }
        Iterator<CCSProcess.CCS_Step> tauSteps = Iterators.concat(Iterators.transform(
                Iterators.filter(getSuccessors(p),step -> step.action.equals(TAU)), // tau steps
                step -> tauSuccessors(step.process,visited)
        ));

        return Iterators.concat(Iterators.singletonIterator(new CCSProcess.CCS_Step(p)), tauSteps);
    }

    @Override
    public Iterator<CCSProcess.CCS_Step> visitNil(CCSProcess.Nil p) {
        return Stream.<CCSProcess.CCS_Step>empty().iterator();
    }

    @Override
    public Iterator<CCSProcess.CCS_Step> visitAction(CCSProcess.Action p) {
        return Iterators.singletonIterator(new CCSProcess.CCS_Step(p.isInput,p.channel,p.process));
    }

    @Override
    public Iterator<CCSProcess.CCS_Step> visitParallel(CCSProcess.Parallel p) {
        // synchronous steps
        Iterator<CCSProcess.CCS_Step> async =
                Iterators.concat(Iterators.transform(
                        p.children.iterator(),
                        child ->
                                Iterators.transform(visit(child),
                                        step-> {
                                            ArrayList<CCSProcess> async_P = new ArrayList<>(p.children);
                                            async_P.remove(child);
                                            async_P.add(step.process);
                                            return new CCSProcess.CCS_Step(
                                                    step.inputQ,
                                                    step.action,
                                                    new CCSProcess.Parallel(async_P)
                                            );
                                        })
                ));

        // synchronous steps
        Iterator<CCSProcess.CCS_Step> sync = Iterators.concat(Iterators.transform(
                IntStream.range(0,p.children.size()).iterator(),
                // left = 0 ... p.children.size()-1
                left -> Iterators.concat(Iterators.transform(
                                IntStream.range(left+1,p.children.size()).iterator(),
                                // right = left+1 ... p.children.size()-1
                                right-> syncSteps(p,left,right))
                        )
                )
        );

        return Iterators.concat(async,sync);
    }

    private Iterator<CCSProcess.CCS_Step> syncSteps(CCSProcess.Parallel p, Integer leftIndex, Integer rightIndex){
        return Iterators.concat(Iterators.transform(
                visit(p.children.get(leftIndex)), // steps of the left process
                left -> // the current left step
                    Iterators.transform(
                            // steps of the right process that can synchronize with the current left step
                            Iterators.filter(visit(p.children.get(rightIndex)),right -> syncQ(left,right)),
                            right-> { // the current right steps
                                // construct the successor
                                ArrayList<CCSProcess> sync_P = new ArrayList<>(p.children);
                                sync_P.remove((int) rightIndex);
                                sync_P.remove((int) leftIndex);
                                sync_P.add(left.process);
                                sync_P.add(right.process);
                                return new CCSProcess.CCS_Step(new CCSProcess.Parallel(sync_P));
                            })
                ));
    }

    private boolean syncQ(CCSProcess.CCS_Step left_step,CCSProcess.CCS_Step right_step){
        return ! left_step.action.equals(TAU) &&
                ! left_step.inputQ.equals(right_step.inputQ) &&
                left_step.action.equals(right_step.action);
    }

    @Override
    public Iterator<CCSProcess.CCS_Step> visitChoice(CCSProcess.Choice p) {
        return Iterators.concat(Iterators.transform(p.children.iterator(),child -> visit(child)));
        /*Iterator<CCSProcess.CCS_Step> iter = Stream.<CCSProcess.CCS_Step>empty().iterator();
        for (CCSProcess child : p.children) {
            iter = Iterators.concat(visit(child));
        }
        return iter;*/
    }

    @Override
    public Iterator<CCSProcess.CCS_Step> visitParens(CCSProcess.ParensProcess p) {
        return visit(p.process);
    }

    @Override
    public Iterator<CCSProcess.CCS_Step> visitRestriction(CCSProcess.Restriction p) {
        Iterator<CCSProcess.CCS_Step> iter = visit(p.process);
        iter = Iterators.filter(iter, step -> !p.chanSet.contains(step.action));
        iter = Iterators.transform(iter,step -> {
            CCSProcess nextproc = new CCSProcess.Restriction(step.process,p.chanSet);
            return new CCSProcess.CCS_Step(step.inputQ,step.action,nextproc);
        });
        return iter;
    }

    @Override
    public Iterator<CCSProcess.CCS_Step> visitRenaming(CCSProcess.Renaming p) {
        Iterator<CCSProcess.CCS_Step> iter = visit(p.process);
        iter = Iterators.transform(iter, step ->{
            CCSProcess nexrproc = new CCSProcess.Renaming(step.process,p.renaming);
            return new CCSProcess.CCS_Step(step.inputQ, p.applyRenaming(step.action),nexrproc);
        });
        return iter;
    }

    @Override
    public Iterator<CCSProcess.CCS_Step> visitPName(CCSProcess.PName p) {
        return visit(p.getDef());
    }
}
