package weighted_transitions_systems.WCCS;

import java.util.*;

public abstract class WCCSProcess implements Comparable<WCCSProcess>{
    public static final String TAU = "tau";
    private String id;
    private HashMap<String,Integer> labels;

    private final static WCCSProcessVisitor<WCCSProcess>[] nfs = new WCCSProcessVisitor[]{
            new WCCSLabelRenamingNF(),
            new WCCSChannelRenamingNF(),
            new WCCSRestrictionNF(),
            new WCCSLabelNF(),
            new WCCSFlattenNF(),
            new WCCSSortedNF() };

    public WCCSProcess(){
        id = null;
    }

    public int compareTo(WCCSProcess o) {
        return toString().compareTo(o.toString());
    }

    public abstract <T> T accept(WCCSProcessVisitor<T> visitor);

    @Override
    public String toString() {
        if (id == null){
            WCCSProcessPrettyPrint prettyPrint = new WCCSProcessPrettyPrint();
            id = prettyPrint.visit(this).toString();
        }
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof WCCSProcess that)) return false;
        return toString().equals(that.toString());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    public String getID(){
        return toString();
    }

    public void resetID(){
        id = null;
        labels = null;
    }

    public static class WCCS_Step {
        public WCCSProcess process;
        public String action;
        public Boolean inputQ;
        public Integer weight;


        /**
         * Construct a weighted step
         * @param inputQ true iff input action
         * @param action action/channel name
         * @param weight weight value
         * @param process successor
         */
        public WCCS_Step(Boolean inputQ, String action, Integer weight, WCCSProcess process){
            this.process = process;
            this.action = action;
            this.weight = weight;
            if (action.equals(TAU)){
                this.inputQ = null;
            }else {
                this.inputQ = inputQ;
            }
        }

        /**
         * Construct a TAU step with the given successor process and weight
         * @param process successor
         * @param weight weight value
         */
        public WCCS_Step(WCCSProcess process, Integer weight){
            this(null, TAU,weight,process);
        }

        @Override
        public String toString() {
            return "<"+ action + (inputQ == null || action.equals(TAU) || inputQ ? "" : "!") + ", " + weight + ", " + process + ">";
        }

        @Override
        public boolean equals(Object obj) {
            return toString().equals(obj.toString());
        }

        @Override
        public int hashCode() {
            return toString().hashCode();
        }
    }

    public Set<WCCSProcess.WCCS_Step> successors(){
        WCCSProcesSuccessor successorsVisitor = new WCCSProcesSuccessor();
        Set<WCCSProcess.WCCS_Step> nfSuccessors = new HashSet<>();
        for (WCCS_Step step : successorsVisitor.visit(this)) {
            step.process = toNormalForm(step.process);
            nfSuccessors.add(step);
        }
        return nfSuccessors;
    }

    public static WCCSProcess toNormalForm(WCCSProcess p) {
        WCCSProcess q = p;
        for (WCCSProcessVisitor<WCCSProcess> nf : nfs) {
            q = nf.visit(q);
        }
        return q;
    }

    public HashMap<String,Integer> getLabels(){
        if(labels == null){
            WCCSLabels labelsVisitor = new WCCSLabels();
            labels = labelsVisitor.visit(this);
        }
        return labels;
    }

    public static class ParensProcess extends WCCSProcess {
        public WCCSProcess process;

        public ParensProcess(WCCSProcess process){
            this.process = process;
        }

        @Override
        public <T> T accept(WCCSProcessVisitor<T> visitor) {
            return visitor.visitParens(this);
        }

    }

    public static class ChannelRenaming extends WCCSProcess {
        public Map<String, String> renaming;
        public WCCSProcess process;

        public ChannelRenaming(WCCSProcess process, Map<String, String> renaming){
            this.renaming = renaming;
            this.process = process;
        }

        @Override
        public <T> T accept(WCCSProcessVisitor<T> visitor) {
            return visitor.visitChannelRenaming(this);
        }

        public String rename(String chan){
            return renaming.getOrDefault(chan,chan);
        }

    }

    public static class LabelRenaming extends WCCSProcess {
        public Map<String, String> renaming;
        public WCCSProcess process;

        public LabelRenaming(WCCSProcess process, Map<String, String> renaming){
            this.renaming = renaming;
            this.process = process;
        }

        @Override
        public <T> T accept(WCCSProcessVisitor<T> visitor) {
            return visitor.visitLabelRenaming(this);
        }
    }

    public static class AtomicLabel extends WCCSProcess {
        WCCSProcess process;
        ArrayList<String> labels;

        public AtomicLabel(ArrayList<String> labels, WCCSProcess process){
            this.labels = labels;
            this.process = process;
        }

        public AtomicLabel(String label, WCCSProcess process){
            this.labels = new ArrayList<>();
            addLabel(label);
            this.process = process;
        }

        public void addLabel(String label){
            labels.add(label);
        }

        @Override
        public <T> T accept(WCCSProcessVisitor<T> visitor) {
            return visitor.visitAtomicLabel(this);
        }

    }

    public static class Action extends WCCSProcess {
        Boolean isInput;
        public WCCSProcess process;
        public String channel;
        public Integer weight;

        public Action(Boolean isInput, String channel, Integer weight, WCCSProcess process){
            this.isInput = isInput;
            this.channel = channel;
            this.process = process;
            this.weight = weight;
        }

        public Action(Integer weight, WCCSProcess process){
            this(true,TAU,weight,process);
        }

        @Override
        public <T> T accept(WCCSProcessVisitor<T> visitor) {
            return visitor.visitAction(this);
        }
    }

    public static class Parallel extends WCCSProcess {
        public ArrayList<WCCSProcess> children;
        public WCCSProcess left, right;

        public Parallel(ArrayList<WCCSProcess> children){
            this.children = children;

        }
        public Parallel(WCCSProcess left, WCCSProcess right){
            this.children = new ArrayList<>();
            children.add(left);
            children.add(right);
        }

        @Override
        public <T> T accept(WCCSProcessVisitor<T> visitor) {
            return visitor.visitParallel(this);
        }
    }

    public static class Choice extends WCCSProcess {
        public WCCSProcess left, right;
        public ArrayList<WCCSProcess> children;

        public Choice(ArrayList<WCCSProcess> children){
            this.children = children;
        }

        public Choice(Set<WCCSProcess> children){
            this.children = new ArrayList<>(children);
        }

        public Choice(WCCSProcess left, WCCSProcess right){
            children = new ArrayList<>();
            children.add(left);
            children.add(right);
        }

        @Override
        public <T> T accept(WCCSProcessVisitor<T> visitor) {
            return visitor.visitChoice(this);
        }
    }

    public static class Restriction extends WCCSProcess {
        public WCCSProcess process;
        public Set<String> chanSet;

        public Restriction(WCCSProcess process, Set<String> chanSet){
            this.process = process;
            this.chanSet = chanSet;
        }

        @Override
        public <T> T accept(WCCSProcessVisitor<T> visitor) {
            return visitor.visitRestriction(this);
        }
    }

    public static class Nil extends WCCSProcess {

        @Override
        public <T> T accept(WCCSProcessVisitor<T> visitor) {
            return visitor.visitNil(this);
        }

    }

    public static class PName extends WCCSProcess {
        public String pName;
        private WCCSInterpreter interpreter;

        public PName(String pName, WCCSInterpreter interpreter){
            this.pName = pName;
            this.interpreter = interpreter;
        }

        public WCCSProcess getDef(){
            return toNormalForm(interpreter.getProcess(pName));
        }

        @Override
        public <T> T accept(WCCSProcessVisitor<T> visitor) {
            return visitor.visitPName(this);
        }
    }

}
