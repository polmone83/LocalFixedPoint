package labeled_transitions_systems.CCS;

import java.util.*;

public abstract class CCSProcess implements Comparable<CCSProcess>{
    public static final String TAU = "tau";
    private String id;

    public CCSProcess(){
        id = null;
    }

    public abstract <T> T accept(CCSProcessVisitor<T> visitor);

    @Override
    public int compareTo(CCSProcess o) {
        return toString().compareTo(o.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CCSProcess that)) return false;
        return toString().equals(that.toString());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString() {
        if (id == null) id = CCSProcessPrettyPrint.prettyPrint(this);
        return id;
    }

    public String getID(){
        return toString();
    }

    public void resetID(){
        id = null;
    }

    public Set<CCS_Step> successors(){
        CCSProcessSuccessor succBuilder = new CCSProcessSuccessor();
        Set<CCS_Step> succ = succBuilder.visit(this);
        // put the successors in normal form
        CCSProcessVisitor<CCSProcess> nf = new CCSProcessNormalForm1();
        CCSProcessVisitor<CCSProcess> nf2 = new CCSProcessNormalForm2();
        Set<CCS_Step> nfSucc = new HashSet<>();
        for (CCS_Step ccsStep : succ) {
            ccsStep.process = nf2.visit(ccsStep.process);
            ccsStep.process = nf.visit(ccsStep.process);
            nfSucc.add(ccsStep);
        }
        return nfSucc;
    }

    public Set<CCS_Step> weakSuccessors(){
        Set<CCS_Step> wSteps = new HashSet<>();

        for (CCSProcess next : tauSuccessors()) {
            // add immediate tau step
            wSteps.add(new CCS_Step(next));

            // continue after tau steps
            for (CCS_Step step : next.successors()) {
                if(!step.action.equals(TAU)){
                    for (CCSProcess next2 : step.process.tauSuccessors()) {
                        wSteps.add(new CCS_Step(step.inputQ,step.action,next2));
                    }
                }
            }
        }

        //System.out.println(tauSuccessors());
        return wSteps;
    }

    private Set<CCSProcess> tauSuccessors(){
        HashMap<String, CCSProcess> visited = new HashMap<>();
        visited.put(this.toString(),this);
        HashSet<CCSProcess> reachable = new HashSet<>();

        HashSet<CCSProcess> frontier = new HashSet<>();
        frontier.add(this);
        Iterator<CCSProcess> iterator = frontier.iterator();
        while(iterator.hasNext()){
            // extract an element from the frontier
            CCSProcess p = iterator.next();
            frontier.remove(p);
            reachable.add(p); // set as reached
            // scan the successors
            for (CCS_Step step : p.successors()) {
                if(step.action.equals(TAU) && !visited.containsKey(step.process.toString())){
                    frontier.add(step.process);
                    visited.put(step.process.toString(),step.process);
                }
            }
            // update iterator
            iterator = frontier.iterator();
        }

        return reachable;
    }

    public static class ParensProcess extends CCSProcess {
        public CCSProcess process;

        public ParensProcess(CCSProcess process){
            this.process = process;
        }

        @Override
        public <T> T accept(CCSProcessVisitor<T> visitor) {
            return visitor.visitParens(this);
        }

    }

    public static class Renaming extends CCSProcess {
        public Map<String, String> renaming;
        public CCSProcess process;

        public Renaming(CCSProcess process, Map<String, String> renaming){
            this.renaming = renaming;
            this.process = process;
        }

        @Override
        public <T> T accept(CCSProcessVisitor<T> visitor) {
            return visitor.visitRenaming(this);
        }

        public String applyRenaming(String chan){
            return renaming.getOrDefault(chan,chan);
        }
    }

    public static class Action extends CCSProcess {
        Boolean isInput;
        public CCSProcess process;
        public String channel;

        public Action(Boolean isInput, String channel, CCSProcess process){
            this.isInput = isInput;
            this.channel = channel;
            this.process = process;
        }

        @Override
        public <T> T accept(CCSProcessVisitor<T> visitor) {
            return visitor.visitAction(this);
        }
    }

    public static class Parallel extends CCSProcess {

        public ArrayList<CCSProcess> children;

        public Parallel(ArrayList<CCSProcess> children){
            this.children = children;
        }

        public Parallel(CCSProcess left, CCSProcess right){
            this.children = new ArrayList<>();
            children.add(left);
            children.add(right);
        }

        @Override
        public <T> T accept(CCSProcessVisitor<T> visitor) {
            return visitor.visitParallel(this);
        }
    }

    public static class Choice extends CCSProcess {
        public TreeSet<CCSProcess> children;

        public Choice(CCSProcess left, CCSProcess right){
            children = new TreeSet<>();
            children.add(left);
            children.add(right);
        }

        public Choice(Set<CCSProcess> children){
            this.children = new TreeSet<>(children);
        }

        @Override
        public <T> T accept(CCSProcessVisitor<T> visitor) {
            return visitor.visitChoice(this);
        }
    }

    public static class Restriction extends CCSProcess {
        public CCSProcess process;
        public TreeSet<String> chanSet;

        public Restriction(CCSProcess process, Set<String> chanSet){
            this.process = process;
            this.chanSet = new TreeSet<>(chanSet);
        }

        @Override
        public <T> T accept(CCSProcessVisitor<T> visitor) {
            return visitor.visitRestriction(this);
        }
    }

    public static class Nil extends CCSProcess {

        public Nil(){ }

        @Override
        public <T> T accept(CCSProcessVisitor<T> visitor) {
            return visitor.visitNil(this);
        }
    }

    public static class PName extends CCSProcess {
        public String pName;
        private final CCSInterpreter interpreter;

        public PName(String pName, CCSInterpreter interpreter){
            this.pName = pName;
            this.interpreter = interpreter;
        }

        public CCSProcess getDef(){
            return interpreter.getProcess(pName);
        }

        @Override
        public <T> T accept(CCSProcessVisitor<T> visitor) {
            return visitor.visitPName(this);
        }
        
    }

    public static class CCS_Step {
        public CCSProcess process;
        public String action;
        public Boolean inputQ;

        public CCS_Step(Boolean inputQ, String action, CCSProcess process){
            this.process = process;
            this.action = action;
            if (action.equals(TAU)){
                this.inputQ = null;
            }else {
                this.inputQ = inputQ;
            }

        }

        public CCS_Step(CCSProcess process){
            this.process = process;
            this.action = TAU;
            this.inputQ = null;
        }

        @Override
        public String toString() {
            return "<"+ (inputQ == null || action.equals(TAU) || inputQ ? "" : "'") + action + ", " + process + ">";
        }

        @Override
        public boolean equals(Object obj) {
            /*if(obj instanceof CCS_Step){
                this.toString().equals(obj.toString());
            }
            return false;*/
            return toString().equals(obj.toString());
        }

        @Override
        public int hashCode() {
            return toString().hashCode();
        }

    }

}
