package bddRelations.locallySoundOracles;

import bddRelations.BDDRel;
import bddRelations.BDDRelEquationSystem;
import bddRelations.BDDRelUniverse;
import bddRelations.soundOracles.BDDRelOracle;
import core.Assignment;
import core.SimpleVarSet;

/**
 * The local version of the triggering oracle.
 * @param <D>
 */
public class trigLocal<D> extends BDDRelOracle<D> {

    BDDRelEquationSystem<D> system;

    public trigLocal(BDDRelEquationSystem<D> system){
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
        BDDRelUniverse universe = relation.getUniverse();

        // vc = (V\Visited) x V
        BDDRel vc = universe.fullRelation();
        visited.allSetElements().forEachRemaining(
                v -> vc.diffWith(universe.leftSingleton(v))
        );

        // visited.allSetElements()
        system.discoveredVariablesIterator().forEachRemaining(
                // add to vc the set {z | fz(A) != A(z), z in Visited } x V
                (Integer z) -> {
                    // if fx(A) != A(x)
                    if (! system.retrieveRHS(z).eval(ass).equals(ass.getValue(z))) {
                        vc.unionWith(universe.leftSingleton(z));
                    }
                }
        );
        // vc = ((V\Visited) x V) \cup {(z,x) | fz(ass) != ass(z) , z \in Visited}
        vc.intersectionWith(relation);

        // locTrig = Dep( (((V\Visited) x V) \cup {(z,x) | fz(ass) != ass(z) , z \in Visited}) \cap R)

        vc.assign(vc.rightMarginal().inverse());

        relation.intersectionWith(vc);
        vc.clear();
    }
}


