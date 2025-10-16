package bddRelations.locallySoundOracles;

import bddRelations.BDDRel;
import bddRelations.BDDRelEquationSystem;
import bddRelations.BDDRelUniverse;
import bddRelations.soundOracles.BDDRelOracle;
import core.Assignment;
import core.SimpleVarSet;

public class FixPoint<D> extends BDDRelOracle<D> {

    /**
     * Remove from the relation all occurences of
     * variables that have reached their fixpoint
     *
     * @param visited
     * @param ass
     * @param relation
     */
    @Override
    public void update(SimpleVarSet visited, Assignment<Integer, D> ass, BDDRel relation) {
        BDDRelUniverse universe = relation.getUniverse();
        for (Integer x : ass.fixpoints) {
            System.out.print(x +",");
            // remove x both from left and right
            BDDRel toRemove = universe.rightSingleton(x);
            toRemove.unionWith(universe.leftSingleton(x));
            // R = R \ (({x} x V) u (V x {x}))
            relation.diffWith(toRemove);
            toRemove.clear();
        }
        System.out.println();
    }
}
