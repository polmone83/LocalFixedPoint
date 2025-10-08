package labeled_transitions_systems.CCS;

import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static labeled_transitions_systems.CCS.CCSProcess.TAU;

public class CCSProcessSuccStream implements CCSProcessVisitor<Stream<CCSProcess.CCS_Step>>{

    public static Stream<CCSProcess.CCS_Step> getSuccessors(CCSProcess p){
        CCSProcessSuccStream visitor = new CCSProcessSuccStream();
        return visitor.visit(p);
    }

    @Override
    public Stream<CCSProcess.CCS_Step> visitNil(CCSProcess.Nil p) {
        return Stream.empty();
    }

    @Override
    public Stream<CCSProcess.CCS_Step> visitAction(CCSProcess.Action p) {
        System.out.println("Action");
        return Stream.of(new CCSProcess.CCS_Step(p.isInput,p.channel,p.process));
    }

    @Override
    public Stream<CCSProcess.CCS_Step> visitParallel(CCSProcess.Parallel p) {
        // async steps
        Stream<CCSProcess.CCS_Step> async =
                p.children.stream().map(child ->
                                visit(child).map(step -> {
                                    ArrayList<CCSProcess> async_P = new ArrayList<>(p.children);
                                    async_P.remove(child);
                                    async_P.add(step.process);
                                    return new CCSProcess.CCS_Step(
                                            step.inputQ,
                                            step.action,
                                            new CCSProcess.Parallel(async_P)
                                    );
                                })).reduce(Stream.empty(),Stream::concat);

        // sync steps
        Stream<CCSProcess.CCS_Step> sync = IntStream.range(0,p.children.size()).boxed()
                .flatMap(left ->
                        IntStream.range(left+1,p.children.size()).boxed()
                                .flatMap(right ->syncSteps(p,left,right))
                );

        return Stream.concat(async,sync).distinct();
    }

    private Stream<CCSProcess.CCS_Step> syncSteps(CCSProcess.Parallel p, Integer leftIndex, Integer rightIndex){
        return visit(p.children.get(leftIndex)).flatMap(left ->
                    visit(p.children.get(rightIndex)).map(right ->
                    new CCSProcess.CCS_Step[]{left, right}
                )).filter(pair -> syncQ(pair[0],pair[1])).map(
                pair ->{
                    ArrayList<CCSProcess> sync_P = new ArrayList<>(p.children);
                    sync_P.remove((int) leftIndex);
                    sync_P.add(pair[0].process);
                    sync_P.remove((int) rightIndex);
                    sync_P.add(pair[1].process);
                    return new CCSProcess.CCS_Step(new CCSProcess.Parallel(sync_P));
                });
    }

    private boolean syncQ(CCSProcess.CCS_Step left_step,CCSProcess.CCS_Step right_step){
        return ! left_step.action.equals(TAU) &&
                ! left_step.inputQ.equals(right_step.inputQ) &&
                left_step.action.equals(right_step.action);
    }

    @Override
    public Stream<CCSProcess.CCS_Step> visitChoice(CCSProcess.Choice p) {
        // concatenate the streams of each subprocess
        return p.children.stream().flatMap(this::visit).distinct();
    }

    @Override
    public Stream<CCSProcess.CCS_Step> visitParens(CCSProcess.ParensProcess p) {
        return visit(p.process);
    }

    @Override
    public Stream<CCSProcess.CCS_Step> visitRestriction(CCSProcess.Restriction p) {
        return visit(p.process)
                .filter(step -> !p.chanSet.contains(step.action));
    }

    @Override
    public Stream<CCSProcess.CCS_Step> visitRenaming(CCSProcess.Renaming p) {
        return visit(p.process)
                .map(step->
                        new CCSProcess.CCS_Step(
                            step.inputQ,
                            p.applyRenaming(step.action), // applyRenaming
                            new CCSProcess.Renaming(step.process,p.renaming) // wrap process
                        )
                ).distinct();
    }

    @Override
    public Stream<CCSProcess.CCS_Step> visitPName(CCSProcess.PName p) {
        return visit(p.getDef());
    }
}
