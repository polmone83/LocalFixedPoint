package bddRelations.locallySoundOracles;

import bddRelations.BDDRel;
import bddRelations.BDDRelEquationSystem;
import bddRelations.BDDRelUniverse;
import bddRelations.soundOracles.BDDRelOracle;
import core.Assignment;
import core.SimpleVarSet;

public class SMax<D> extends BDDRelOracle<D> {
    private BDDRelEquationSystem<D> system;
    private D top;

    public SMax(BDDRelEquationSystem<D> system, D top){
        this.system = system;
        this.top = top;
    }

    /**
     * Updates the relation passed as parameter
     * @param visited
     * @param ass
     * @param relation
     */
    @Override
    public void update(SimpleVarSet visited, Assignment<Integer, D> ass, BDDRel relation) {
        BDDRelUniverse universe = relation.getUniverse();
        for (Integer x : ass.getDomain()) {
            if(ass.getValue(x).equals(top)){
                // remove x both from left and right
                BDDRel toRemove = universe.rightSingleton(x);
                toRemove.unionWith(universe.leftSingleton(x));
                // R = R \ (({x} x V) u (V x {x}))
                relation.diffWith(toRemove);
                toRemove.clear();
            }
        }
        /*system.discoveredVariablesIterator().forEachRemaining(
                (Integer x) -> {
                    if(ass.getValue(x).equals(top)){
                        // remove x both from left and right
                        BDDRel toRemove = universe.rightSingleton(x);
                        toRemove.unionWith(universe.leftSingleton(x));
                        // R = R \ (({x} x V) u (V x {x}))
                        relation.diffWith(toRemove);
                        toRemove.clear();
                    }
                }
        );*/
        /*Remark: at this point we are sure that NotVisited x NotVisited is
         * included in R. This is correct w.r.t. the definition of smax
         * because such variables are implicitly assigned to bottom */
    }
}
