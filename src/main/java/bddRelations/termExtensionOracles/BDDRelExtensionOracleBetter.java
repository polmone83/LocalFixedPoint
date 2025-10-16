package bddRelations.termExtensionOracles;

import bddRelations.BDDRel;
import bddRelations.BDDRelEquationSystem;
import bddRelations.BDDRelUniverse;
import bddRelations.soundOracles.BDDRelOracle;
import core.Assignment;
import core.RightHandSide;
import core.SimpleVarSet;
import core.TermExtension;

public class BDDRelExtensionOracleBetter<D> extends BDDRelOracle<D> {

    private BDDRelEquationSystem<D> system;

    public BDDRelExtensionOracleBetter(BDDRelEquationSystem<D> system){
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

        BDDRelUniverse u = relation.getUniverse();
        BDDRel rel = u.emptyRelation();

        // system.discoveredVariablesIterator()
        visited.allSetElements().forEachRemaining(
                (Integer y) -> {
                    if(system.discoveredVariables().contains(y)) {
                        RightHandSide<Integer, D, SimpleVarSet> ti = system.retrieveRHS(y);
                        // the set B(ass,oracle.relation)xV
                        TermExtension<Integer, D, BDDRel, SimpleVarSet> t =
                                ((TermExtension<Integer, D, BDDRel, SimpleVarSet>) ti);
                        BDDRel b = t.evalExtension(system, relation, ass);
                        if (!b.isEmpty()) {
                            // add the set L(ass,oracle.relation)x{y} to rel
                            b.intersectionWith(u.rightSingleton(y));
                            rel.unionWith(b);
                            rel.insertPair(y, y);
                        }
                        if (!ti.eval(ass).equals(ass.getValue(y))) {
                            rel.insertPair(y, y);
                        }else if(b.isEmpty()){
                            // y has reached a fixpoint
                            ass.fixpoints.add(y);
                        }
                        b.clear();
                    }else{
                        rel.unionWith(u.rightSingleton(y));
                    }
        });
        relation.intersectionWith(rel);
        rel.clear();
    }
}
