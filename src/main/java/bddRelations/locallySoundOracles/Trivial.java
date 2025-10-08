package bddRelations.locallySoundOracles;

import bddRelations.BDDRel;
import bddRelations.soundOracles.BDDRelOracle;
import core.Assignment;
import core.SimpleVarSet;

/**
 * The trivial oracle V x V. This is the version with partial observability
 * @param <D>
 */
public class Trivial<D> extends BDDRelOracle<D> {

    /**
     * Updates the relation passed as parameter
     *
     * @param visited
     * @param ass
     * @param relation
     */
    @Override
    public void update(SimpleVarSet visited, Assignment<Integer, D> ass, BDDRel relation) {
        relation.assign(relation.getUniverse().fullRelation());
    }
}
