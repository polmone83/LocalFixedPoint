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
                (Integer i) -> {
                    if(system.discoveredVariables().contains(i)) {
                        RightHandSide<Integer, D, SimpleVarSet> ti = system.retrieveRHS(i);
                        // the set B(ass,oracle.relation)xV
                        TermExtension<Integer, D, BDDRel, SimpleVarSet> t =
                                ((TermExtension<Integer, D, BDDRel, SimpleVarSet>) ti);
                        BDDRel b = t.evalExtension(system, relation, ass);
                        if (!b.isEmpty()) {
                            // add the set L(ass,oracle.relation)x{i} to rel
                            b.intersectionWith(u.rightSingleton(i));
                            rel.unionWith(b);
                            rel.insertPair(i, i);
                        }
                        if (!ti.eval(ass).equals(ass.getValue(i))) {
                            rel.insertPair(i, i);
                        }else if(b.isEmpty()){
                            // i has reached a fixpoint
                            ass.fixpoints.add(i);
                        }
                        b.clear();
                    }else{
                        rel.unionWith(u.rightSingleton(i));
                    }
        });
        relation.intersectionWith(rel);
        rel.clear();
    }
}
