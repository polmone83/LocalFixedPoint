package core;

import java.util.Iterator;

public interface VarSet<V>{

    /**
     * Add all the elements from otherSet to this set
     * @param otherSet
     * @return true iff this set changed after the operation
     */
    boolean unionWith(VarSet<V> otherSet);

    /**
     * Add all the elements from otherSet to this set
     * @param otherSet
     * @return true iff this set changed after the operation
     */
    boolean intersectionWith(VarSet<V> otherSet);

    /**
     * Returns an iterator of the elements in the set
     * @return
     */
    Iterator<V> allSetElements();

    /**
     * Returns the set of all variables
     * @return
     */
    void print();

    /**
     * Check if variable {@code y} is contained in this VarSet
     * @param y a variable
     * @return true iff {@code y} is in this VarSet
     */
    boolean contains(V y);

    void add(V var);

    int cardinality();

    boolean isEmpty();
}
