package bddRelations.soundOracles;

import bddRelations.BDDRel;
import bddRelations.BDDRelStaticEquationSystem;
import bddRelations.BDDRelUniverse;
import core.Assignment;

/**
 * This is the implementation of the max' oracle
 * @param <D>
 */
public class MaxPrimeOracle<D> extends BDDRelStaticOracle<D> {
    private BDDRelStaticEquationSystem<D> system;
    private D top;

    public MaxPrimeOracle(BDDRelStaticEquationSystem<D> system, D top){
        this.system = system;
        this.top = top;
    }

    @Override
    public void update(Assignment<Integer, D> ass, BDDRel relation) {

        BDDRelUniverse universe = system.getUniverse();
        BDDRel rel = universe.fullRelation();
        system.discoveredVariablesIterator().forEachRemaining(
                (Integer x) -> {
                    if(ass.getValue(x).equals(top)){
                        // remove x to the left
                        BDDRel toRemove = universe.leftSingleton(x);
                        // remove x to the right (because we are sure that also fx(ass) = top)
                        toRemove.unionWith(universe.rightSingleton(x));
                        // R = R \ (({x} x V) u (V x {x}))
                        rel.diffWith(toRemove);
                        toRemove.clear();
                    }else if(system.retrieveRHS(x).eval(ass).equals(top)){
                        // remove x to the right (because fx(ass) = top)
                        rel.diffWith(universe.rightSingleton(x));
                    }
                }
        );

        // remove the diagonal
        rel.diffWith(system.getUniverse().diagonal());

        // re-add the diagonal that is not stuck
        system.discoveredVariablesIterator().forEachRemaining(
                (Integer x) -> {
                    if(! system.retrieveRHS(x).eval(ass).equals(ass.getValue(x))){
                        rel.insertPair(x,x);
                    }
                }
        );

        // finally intersect rel with the given relation
        relation.intersectionWith(rel);
        rel.clear();

    }
}
