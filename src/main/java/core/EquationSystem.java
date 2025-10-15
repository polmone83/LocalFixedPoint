package core;

import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class EquationSystem<V, D, R extends VarRelation<V,S>, S extends VarSet<V>> implements LabelMap<V>{

    /**
     * The relation used by local solve
     */
    protected R relation;
    public S visited;
    public V lastUpdated;

    /**
     * The equations discovered by the solver
     */
    private Map<V, LazyRHS<V,D,R,S>> equations;

    /**
     * The logger
     */
    private StringBuilder log;

    /**
     * The labelling map: Variable -> Label
     */
    private Map<V, String> labels;

    /**
     * The inverse labelling map: Label -> Variable
     */
    private Map<String, V> varsIndex;

    private int iterationCount;
    private Long execTime;

    public EquationSystem() {
        this.equations = new HashMap<>();
        labels = new HashMap<>();
        varsIndex = new HashMap<>();
        this.log = new StringBuilder();
        //selectionStrategy = new SelectionStrategy<>(this);
    }

    public Map<V, String> getLabels() {
        return labels;
    }

    protected abstract R initRelation();
    protected abstract S initVisited(V targetVar);
    protected abstract D getBottomElement();

    private Iterator<V> getTodo(V targetVar) {
        S todoSet = relation.sliceLeft(targetVar);
        //return selectionStrategy.getTodo(targetVar,todoSet,visited);
        todoSet.intersectionWith(visited);
        Iterator<V> todo = todoSet.allSetElements();
        return todo;
    }

    public D localSolve(V targetVar, D bot, LocalOracle<V,D,R,S> oracle){
        // Start measuring execution time
        execTime = -1l; // reset the timer
        long startTime = System.nanoTime();

        // initializations:
        Assignment<V, D> assignment = new Assignment<>(getBottomElement(), this); // bottom assignment
        initVisited(targetVar);
        initRelation();
        oracle.update(visited,assignment,relation); // initialize the oracle

//        log = new StringBuilder(); // flush the log
//        log.append("-------------------------");
//        log.append('\n');
//        log.append("Target variable: " + getLabel(targetVar));
//        log.append('\n');
//        log.append(assignment);
//        log.append('\n');

        //log.append("Dependencies: " + oracle);
        //log.append('\n');
        iterationCount = 0;
        Iterator<V> todo = getTodo(targetVar);
        while (todo.hasNext()) {// check if targetVar has been solved
            iterationCount++;
            V x = todo.next(); // pick a variable

            // ---------- Trick to skip unnecessary updates -------------
            // if the variable is already discovered, and we know that it won't change
            // value after the last update, we can simply skip it
            LazyRHS testrhs = equations.get(x);
            if(testrhs != null && testrhs.value != null) continue;
            // ----------------------------------------------------------

            //log.append("pick: " + getLabel(x));
            //log.append('\n');
            //System.out.println(assignment);
            D val_x = assignment.getValue(x);
            RightHandSide<V, D, S> rhs = retrieveRHS(x); // retrieve tx
            D val_tx = rhs.eval(assignment); // interpretation of tx
            S vars_tx = rhs.getVars(); // variables of tx
            //System.out.println("Vars t"+ x + ": "); vars_tx.print();
            //System.out.println("Visited: "); visited.print();
            boolean updateAss = !val_tx.equals(val_x);
            boolean updateVisited = visited.unionWith(vars_tx);
            if (updateAss || updateVisited) { // new value for x
                if(updateAss) {
                    assignment.setValue(x, val_tx); // update the current assignment
                    lastUpdated = x;
                    notifyEquations(x); // notify that x has changed value (for memoization update)
                    //log.append(assignment);
                    //log.append('\n');
                }else{
                    lastUpdated = null;
                }
                if(updateVisited) {
                    initRelation();
                }
                oracle.update(visited,assignment,relation); // update the oracle w.r.t the current assignment
                todo = getTodo(targetVar);
                //log.append("Dependencies: " + oracle);
                //log.append('\n');
            }
        }

        D val = assignment.getValue(targetVar);
//        log.append("Result: " + getLabel(targetVar) + " = " + val);
//        log.append('\n');
//        log.append('\n');

        equations.forEach(
                (V x, LazyRHS rhs) ->{
                    System.out.print(x + " = ");
                    System.out.print(rhs.counter + ":");
                    System.out.println(rhs.rhs);
                }
        );

        // Stop measuring execution time
        execTime = Duration.ofNanos(System.nanoTime()-startTime).toMillis();

        return val;
    }

    public RightHandSide<V, D, S> retrieveRHS(V x) {
        RightHandSide<V, D, S> tx = equations.get(x);
        if (tx == null) { // if the equation was not generated yet
            tx = getRHS(x); // generate the equation
            tx = addEquation(x, tx);
        }
        return tx;
    }

    public D localSolve(String targetVar, D bot, LocalOracle<V, D, R,S> oracle) {
        return localSolve(getIndex(targetVar),bot,oracle);
    }

    public abstract RightHandSide<V, D, S> getRHS(V x);

    /**
     * Adds the equation as visited.
     *
     * @param x
     * @param rhs
     */
    private RightHandSide<V, D, S> addEquation(V x, RightHandSide<V, D, S> rhs) {
        if (equations.containsKey(x)) {
            throw new RuntimeException("The system has already an equation for " + x);
        } else {
            LazyRHS lazyRhs =  new LazyRHS<>(rhs);
            equations.put(x, lazyRhs);
            return lazyRhs;
        }
    }

    /**
     * Returns the variable index associated with the given label
     *
     * @param varName the label naming the variable
     * @return the index associated with the label
     */
    public abstract V getIndex(String varName);

    /**
     * Returns the number of variable indices that have been already associated
     * with their right-hand-side.
     * The value may increase while the equation system is solved.
     *
     * @return
     */
    public int varsCount() {
        return equations.size();
    }

    /**
     * The variable indices of the equations that have been discovered
     * so far. The set increases while the equation system is solved.
     *
     * @return
     */
    public Iterator<V> discoveredVariablesIterator() {
        return equations.keySet().iterator();
    }

    public Set<V> discoveredVariables(){
        return equations.keySet();
    }

    public String getLog() {
        return log.toString();
    }

    /**
     * Reset the memoized value for all the right-hand sides whose
     * evaluation may depend on the value of x
     * @param x the variable that has just changed value
     */
    private void notifyEquations(V x) {
        Iterator<V> canchange = relation.sliceRight(x).allSetElements();
        while (canchange.hasNext()){
            V y = canchange.next();
            LazyRHS<V,D,R,S> rhs = this.equations.get(y);
            if(rhs != null && rhs.getVars().contains(x)){
                this.equations.get(y).resetVal();
            }
        }
    }

    /**
     * Return the number of iterations performed during the latest
     * execution of the method {@link #localSolve(Object, Object, LocalOracle)}
     * @return iteration count
     */
    public int getIterationCount(){
        return iterationCount;
    }

    /**
     * Return the execution time in milliseconds of the latest execution of
     * the method {@link #localSolve(Object, Object, LocalOracle)}
     * @return time in milliseconds
     */
    public long getExecTime(){
        return execTime;
    }

    /**
     * Return the overall number of times an RHS was actually evaluated
     * during the latest execution of the method {@link #localSolve(Object, Object, LocalOracle)}
     * @return
     */
    public int getRHSEvalCount(){
        AtomicInteger counter = new AtomicInteger();
        equations.forEach(
                (x, rhs) -> {
                    counter.set(counter.get() + rhs.getCounter());
                }
        );
        return counter.get();
    }
}
