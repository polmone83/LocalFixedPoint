package bddRelations.locallySoundOracles;

import bddRelations.BDDRel;
import bddRelations.BDDRelEquationSystem;
import bddRelations.BDDRelUniverse;
import bddRelations.soundOracles.BDDRelOracle;
import core.Assignment;
import core.SimpleVarSet;

public class FMax<D> extends BDDRelOracle<D> {
    private BDDRelEquationSystem<D> system;
    private D top;

    public FMax(BDDRelEquationSystem<D> system, D top){
        this.system = system;
        this.top = top;
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

        BDDRelUniverse universe = relation.getUniverse();
        BDDRel rel = universe.fullRelation();

        visited.allSetElements().forEachRemaining(
                (Integer x) -> {
                    if(ass.getValue(x).equals(top)){
                        // remove x to the left
                        rel.diffWith(universe.leftSingleton(x));
                    }
                }
        );
        system.discoveredVariablesIterator().forEachRemaining(
                (Integer y) ->{
                    if(system.retrieveRHS(y).eval(ass).equals(top)){
                        // remove t to the right
                        rel.diffWith(universe.rightSingleton(y));
                    }
                }
        );
        rel.unionWith(universe.diagonal());

        relation.intersectionWith(rel);
        rel.clear();
    }
}
