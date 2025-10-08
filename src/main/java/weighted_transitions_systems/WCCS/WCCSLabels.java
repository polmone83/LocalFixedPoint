package weighted_transitions_systems.WCCS;
import java.util.HashMap;

public class WCCSLabels implements WCCSProcessVisitor<HashMap<String,Integer>>{

    // todo: reimplement using a shared hashmap

    @Override
    public HashMap<String, Integer> visitNil(WCCSProcess.Nil p) {
        return new HashMap<>();
    }

    @Override
    public HashMap<String, Integer> visitAtomicLabel(WCCSProcess.AtomicLabel p) {
        HashMap<String, Integer> labels = visit(p.process);
        for (String label : p.labels) {
            Integer arity = labels.getOrDefault(label,0) + 1;
            labels.put(label,arity);
        }
        return labels; // return the singleton label
    }

    @Override
    public HashMap<String, Integer> visitAction(WCCSProcess.Action p) {
        return new HashMap<>(); // no labels
    }

    @Override
    public HashMap<String, Integer> visitParallel(WCCSProcess.Parallel p) {
        HashMap<String,Integer> labels = new HashMap();
        for (WCCSProcess child : p.children) {
            // add the labels of the current child
            visit(child).forEach(
                    (String label, Integer arity) -> {
                        Integer updatedArity = arity + labels.getOrDefault(label,0);
                        labels.put(label,updatedArity);
                    }
            );
        }
        return labels;
    }

    @Override
    public HashMap<String, Integer> visitChoice(WCCSProcess.Choice p) {
        HashMap<String,Integer> labels = new HashMap();
        for (WCCSProcess child : p.children) {
            // add the labels of the current child
            visit(child).forEach(
                    (String label, Integer arity) -> {
                        Integer updatedArity = arity + labels.getOrDefault(label,0);
                        labels.put(label,updatedArity);
                    }
            );
        }
        return labels;
    }

    @Override
    public HashMap<String, Integer> visitParens(WCCSProcess.ParensProcess p) {
        return visit(p.process);
    }

    @Override
    public HashMap<String, Integer> visitRestriction(WCCSProcess.Restriction p) {
        return visit(p.process);
    }

    @Override
    public HashMap<String, Integer> visitChannelRenaming(WCCSProcess.ChannelRenaming p) {
        return visit(p.process);
    }

    @Override
    public HashMap<String, Integer> visitLabelRenaming(WCCSProcess.LabelRenaming p) {
        HashMap<String,Integer> labels = new HashMap<>();
        visit(p.process).forEach(
                (String label, Integer arity) ->{
                    String renamedLabel = p.renaming.getOrDefault(label, label);
                    labels.put(renamedLabel, arity);
                });
        return labels;
    }

    @Override
    public HashMap<String, Integer> visitPName(WCCSProcess.PName p) {
        return visit(p.getDef());
    }
}
