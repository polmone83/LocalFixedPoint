package bddRelations.soundOracles;

import bddRelations.BDDRel;
import bddRelations.BDDRelStaticEquationSystem;
import bddRelations.BDDRelUniverse;
import core.Assignment;
import core.SimpleVarSet;

public class Args2Oracle<D> extends BDDRelStaticOracle<D> {

    private BDDRelStaticEquationSystem<D> system;

    /**
     * Construct the reachability oracle.
     *
     * @param system an equation system on VxX
     */
    public Args2Oracle(BDDRelStaticEquationSystem<D> system) {
        this.system = system;
    }

    public void update(Assignment<Integer, D> ass, BDDRel relation) {
        BDDRelUniverse u = system.getUniverse();
        BDDRel rel = u.emptyRelation();
        system.discoveredVariablesIterator().forEachRemaining(
                (Integer y) -> {
                    SimpleVarSet vars_fy = system.retrieveRHS(y).getVars();
                    vars_fy.allSetElements().forEachRemaining(
                            v -> rel.insertPair(v,y)
                    );
                    /*BDDRel vars_fy = u.rightSingleton(y); // V x {y}
                    vars_fy.intersectionWith(system.retrieveRHS(y).getVars()); // Vars(fy) x {y}
                    rel.unionWith(vars_fy);
                    vars_fy.clear(); // deref the helper*/

                    // when fy(ass) != ass(y) add the pair (y,y) to R
                    if (!system.retrieveRHS(y).eval(ass).equals(ass.getValue(y))) {
                        rel.insertPair(y, y);
                    }
                }
        );

        // perform the reflexive and transitive closure of R
        // rel.reflexiveClosure();
        relation.assign(rel);
    }

}
