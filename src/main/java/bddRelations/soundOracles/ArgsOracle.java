package bddRelations.soundOracles;

import bddRelations.BDDRel;
import bddRelations.BDDRelStaticEquationSystem;
import bddRelations.BDDRelUniverse;
import core.Assignment;
import core.SimpleVarSet;

public class ArgsOracle<D> extends BDDRelStaticOracle<D> {

    private BDDRelStaticEquationSystem<D> system;
    private BDDRel relation;
    /**
     * Construct the reachability oracle.
     * @param system an equation system on VxX
     */
    public ArgsOracle(BDDRelStaticEquationSystem<D> system){
        this.system = system;
        this.relation = null;
    }

    @Override
    public void update(Assignment<Integer, D> ass, BDDRel relation) {

        if(this.relation == null) {
            BDDRelUniverse u = system.getUniverse();
            this.relation = u.emptyRelation();
            system.discoveredVariablesIterator().forEachRemaining(
                    (Integer y) -> {
                        SimpleVarSet vars_fy = system.retrieveRHS(y).getVars();
                        vars_fy.allSetElements().forEachRemaining(
                                v -> this.relation.insertPair(v,y)
                        );
                        /*BDDRel vars_fy = u.rightSingleton(y); // V x {y}
                        vars_fy.intersectionWith(system.retrieveRHS(y).getVars()); // Vars(fy) x {y}
                        this.relation.unionWith(vars_fy);
                        vars_fy.clear(); // deref the helper*/
                    }
            );

            // perform the reflexive and transitive closure of R
            this.relation.reflexiveClosure();
            this.relation.transitiveClosure();
        }

        relation.assign(this.relation);
    }
}
