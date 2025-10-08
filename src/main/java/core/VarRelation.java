package core;

import java.util.Iterator;
/**
 * A relation over variables
 * @param <V>
 */
public interface VarRelation<V, S extends VarSet<V>> {
    /**
     * @param var
     * @return {x | (x, var) \in R}
     */
    S sliceLeft(V var);

    /**
     * @param var
     * @return {y | (var,y) \in R}
     */
    S sliceRight(V var);

    /**
     * An iterator for all the elements in this relation
     * @return
     */
    Iterator<VarPair<V>> allPairs();

    void insertPair(V x, V y);

    void removePair(V x, V y);


}
