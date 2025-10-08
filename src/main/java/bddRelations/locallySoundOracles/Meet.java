package bddRelations.locallySoundOracles;

import bddRelations.BDDRel;
import bddRelations.BDDRelEquationSystem;
import bddRelations.soundOracles.BDDRelOracle;
import core.Assignment;
import core.SimpleVarSet;

import java.util.ArrayList;
import java.util.Collection;

public class Meet<D> extends BDDRelOracle<D> {

    Collection<BDDRelOracle<D>> oracles;

    public Meet(Collection<BDDRelOracle<D>> oracles){
        this.oracles = oracles;
    }

    public Meet(BDDRelOracle<D> oracle1,
                BDDRelOracle<D> oracle2){
        this.oracles = new ArrayList();
        oracles.add(oracle1);
        oracles.add(oracle2);
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

        BDDRel rel = relation.getUniverse().fullRelation();
        for (BDDRelOracle<D> oracle : oracles) {
            BDDRel copy = relation.copy();
            oracle.update(visited,ass,copy);
            rel.intersectionWith(copy);
            copy.clear();
        }

        relation.intersectionWith(rel);
        rel.clear();
    }
}
