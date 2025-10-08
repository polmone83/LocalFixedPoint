package bddRelations.locallySoundOracles;

import bddRelations.BDDRel;
import bddRelations.BDDRelEquationSystem;
import bddRelations.BDDRelStaticEquationSystem;
import bddRelations.BDDRelUniverse;
import bddRelations.soundOracles.BDDRelOracle;
import core.Assignment;
import core.SimpleVarSet;

public class ArgsLocal<D> extends BDDRelOracle<D> {

    private BDDRelEquationSystem<D> system;
    private BDDRel rel;
    private int DCardinality;
    private int VCardinality;

    /**
     * Construct the reachability oracle.
     * @param system an equation system on VxX
     */
    public ArgsLocal(BDDRelEquationSystem<D> system){
        this.system = system;
        this.DCardinality = -1;
        this.VCardinality = -1;
        this.rel = null;
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
        if(this.DCardinality < system.discoveredVariables().size()
        || this.VCardinality < visited.cardinality() ) {
            // the knowledge about variables increased w.r.t. the last time it was run
            if(this.rel != null) this.rel.clear(); // free space

            BDDRelUniverse u = relation.getUniverse();
            this.rel = u.emptyRelation();
            visited.allSetElements().forEachRemaining(
                    (Integer y) -> {
                        if(system.discoveredVariables().contains(y)){
                            SimpleVarSet vars_fy = system.retrieveRHS(y).getVars();
                            vars_fy.allSetElements().forEachRemaining(
                                    v -> this.rel.insertPair(v,y)
                            );
                        }else{
                            rel.unionWith(u.leftSingleton(y));
                        }
                    }
            );
            // perform the reflexive and transitive closure of R
            this.rel.reflexiveClosure();
            this.rel.transitiveClosure();

            DCardinality = system.discoveredVariables().size();
            VCardinality = visited.cardinality();
        }
        relation.intersectionWith(this.rel);
    }
}
