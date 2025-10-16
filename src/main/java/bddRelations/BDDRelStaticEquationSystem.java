package bddRelations;

import core.RightHandSide;
import core.SimpleVarSet;

import java.util.HashMap;
import java.util.Iterator;

public abstract class BDDRelStaticEquationSystem<D>  extends BDDRelEquationSystem<D> {

    private HashMap<Integer,RightHandSide<Integer,D,SimpleVarSet>> equations;
    private HashMap<Integer,String> labels;
    private HashMap<String,Integer> varsIndex;

    /**
     * Construct an empty equation system
     */
    public BDDRelStaticEquationSystem(){
        flushEquations();
    }

    protected void flushEquations(){
        this.equations = new HashMap<>();
        this.labels = new HashMap<>();
        this.varsIndex = new HashMap<>();
    }

    /**
     * Adds a new equation to the equation system
     *
     * @param label the label for {@code x}
     * @param x the variable index
     * @param fx the right-hand-side of the equation of {@code x}
     * @throws RuntimeException when attempting to add a duplicated variable or label
     */
    public void addEquation(String label, Integer x, RightHandSide<Integer,D,SimpleVarSet> fx) throws RuntimeException{
        if(equations.containsKey(x) || varsIndex.containsKey(label)){
            throw new RuntimeException("The variable or the label is already present in the system");
        }
        equations.put(x,fx);
        labels.put(x,label);
        varsIndex.put(label,x);
    }

    @Override
    public RightHandSide<Integer, D, SimpleVarSet> getRHS(Integer x) {
        return equations.get(x);
    }

    @Override
    public String getLabel(Integer x) {
        return labels.get(x);
    }

    @Override
    public Integer getIndex(String label) {
        return varsIndex.get(label);
    }

    /**
     * The number of equations present in the system
     * @return
     */
    @Override
    public int varsCount() {
        return equations.size();
    }

    /**
     * All the variables in this system
     * @return
     */
    @Override
    public Iterator<Integer> discoveredVariablesIterator() {
        return equations.keySet().iterator();
    }

    @Override
    protected void initUniverse(Integer size) throws RuntimeException {
        if(getUniverse() == null) {
            super.initUniverse(varsCount());
        }
    }

    @Override
    protected BDDRel initRelation() {
        initUniverse(varsCount());
        this.relation = getUniverse().fullRelation();
        return relation;
    }

    @Override
    protected SimpleVarSet initVisited(Integer targetVar) {
        visited = new SimpleVarSet();
        discoveredVariablesIterator().forEachRemaining(
                v -> visited.add(v)
        );
        return visited;
    }
}
