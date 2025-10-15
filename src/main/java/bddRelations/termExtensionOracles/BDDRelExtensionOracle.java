package bddRelations.termExtensionOracles;

import bddRelations.BDDRel;
import bddRelations.BDDRelEquationSystem;
import bddRelations.BDDRelUniverse;
import bddRelations.soundOracles.BDDRelOracle;
import core.Assignment;
import core.SimpleVarSet;
import core.TermExtension;

public class BDDRelExtensionOracle<D> extends BDDRelOracle<D> {

    private BDDRelEquationSystem<D> system;

    public BDDRelExtensionOracle(BDDRelEquationSystem<D> system) {
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
        BDDRel rel = u.diagonal();

        system.discoveredVariablesIterator().forEachRemaining(
                (Integer i) -> {
                    // the set B(ass,oracle.relation)xV
                    TermExtension<Integer, D, BDDRel, SimpleVarSet> t =
                            ((TermExtension<Integer, D, BDDRel, SimpleVarSet>) system.retrieveRHS(i));
                    BDDRel b = t.evalExtension(system, relation, ass);
                    // add the set L(ass,oracle.relation)x{i} to rel
                    b.intersectionWith(u.rightSingleton(i));
                    rel.unionWith(b);
                    b.clear();
                });

        BDDRel relVisited = u.emptyRelation();
        visited.allSetElements().forEachRemaining(
                v -> relVisited.unionWith(u.rightSingleton(v))
        );
        // nVisited = V x (V\notVisited)
        rel.unionWith(relVisited.complement());
        relVisited.clear();

        relation.intersectionWith(rel);
        rel.clear();
    }
}
