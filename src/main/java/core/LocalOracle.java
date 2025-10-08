package core;

public interface LocalOracle<V,D,R,S> {
    /**
     * Updates the relation passed as parameter
     * @param visited
     * @param ass
     * @param relation
     */
    void update(S visited, Assignment<V,D> ass, R relation);
}
