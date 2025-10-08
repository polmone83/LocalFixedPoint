package bddRelations.locallySoundOracles;

import bddRelations.BDDRel;
import bddRelations.BDDRelEquationSystem;
import bddRelations.BDDRelUniverse;
import bddRelations.soundOracles.BDDRelOracle;
import core.Assignment;
import core.SimpleVarSet;

import java.util.Set;

/**
 * The local version of the triggering oracle.
 * @param <D>
 */
public class trigLocalv2<D> extends BDDRelOracle<D> {

    BDDRelEquationSystem<D> system;

    public trigLocalv2(BDDRelEquationSystem<D> system){
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
        Set<Integer> discovered = system.discoveredVariables();

        // we work under the assumption that V = Visited
        BDDRel vc = universe.emptyRelation();
        visited.allSetElements().forEachRemaining(
                (Integer z ) ->{
                    if(discovered.contains(z)){ // z has a known RHS
                        // if fx(A) != A(x)
                        if (! system.retrieveRHS(z).eval(ass).equals(ass.getValue(z))) {
                            vc.unionWith(universe.leftSingleton(z));
                        }
                    } else {
                        vc.unionWith(universe.leftSingleton(z));
                    }
                }
        );
        // vc = {(z,x) | fz(ass) != ass(z) , z \in Discovered }
        //      \cup {(z,x) | z \in Visited\Discovered }

        vc.intersectionWith(relation);
        // vc = {(z,x) | (z,x) \in R,  fz(ass) != ass(z) , z \in Discovered }
        //      \cup {(z,x) | (z,x) \in R,  z \in Visited\Discovered }

        vc.assign(vc.rightMarginal().inverse());
        // vc = {(x,y) | (z,x) \in R,  fz(ass) != ass(z) , z \in Discovered }
        //      \cup {(x,y) | (z,x) \in R,  z \in Visited\Discovered }

        relation.intersectionWith(vc);
        vc.clear();
    }
}


