package bddRelations.locallySoundOracles;

import bddRelations.BDDRel;
import bddRelations.BDDRelEquationSystem;
import bddRelations.soundOracles.BDDRelOracle;
import core.Assignment;
import core.SimpleVarSet;

/**
 * The dependency oracle dep.
 * This is the version with partial observability
 * @param <D>
 */
public class Dep<D> extends BDDRelOracle<D> {

    protected BDDRelEquationSystem<D> system;

    public Dep(BDDRelEquationSystem<D> system) {
        this.system = system;
    }

    /**
     * Updates the relation passed as parameter
     *
     * @param visited
     * @param ass
     * @param relation
     */
    @Override
    public void update(SimpleVarSet visited, Assignment<Integer, D> ass, BDDRel relation) {
        BDDRel rel = relation.rightMarginal().inverse();
        relation.intersectionWith(rel);
        rel.clear();
    }
}
