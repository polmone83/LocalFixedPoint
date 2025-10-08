package core;

public class LazyRHS<V, D, R extends VarRelation<V,S>, S extends VarSet<V>> implements RightHandSide<V, D, S>, TermExtension<V,D,R,S> {

    RightHandSide<V, D, S> rhs;
    D value;
    S vars;
    int counter;

    public LazyRHS(RightHandSide<V, D, S> rhs) {
        this.rhs = rhs;
        this.counter = 0;
        this.value = null;
        this.vars = null;
    }

    @Override
    public D eval(Assignment<V, D> ass) {
        if (value == null) {
            counter++;
            value = rhs.eval(ass);
        }
        return value;
    }

    @Override
    public S getVars() {
        if (vars == null) {
            vars = rhs.getVars();
        }
        return vars;
    }

    /**
     * Method used to notify that the value
     * stored for of this rhs is no longer valid.
     */
    void resetVal() {
        value = null;
    }

    public int getCounter() {
        return counter;
    }

    @Override
    public R evalExtension(EquationSystem<V, D, R, S> system, R relation, Assignment<V, D> ass) {
        if(rhs instanceof TermExtension){
            TermExtension<V, D, R, S> eRhs = (TermExtension<V, D, R, S>) rhs;
            return eRhs.evalExtension(system,relation,ass);
        }
        throw new RuntimeException("Does not implement the extension");
    }
}