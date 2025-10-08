package core;

public interface RightHandSide<V,D,S>{

    /**
     * Evaluate this right hand side of the equation
     * with the given assignment
     * @param ass the variable assignment
     * @return the interpretation of this rhs
     */
    D eval(Assignment<V,D> ass);

    /**
     * Returns the set of variables of this right-hand side
     * @return the relation vars(rhs) x V
     */
    S getVars();

}
