package bddRelations.termExtensionOracles;

import bddRelations.BDDRel;
import bddRelations.BDDRelStaticEquationSystem;
import bddRelations.BDDRelUniverse;
import bddRelations.soundOracles.BDDRelStaticOracle;
import core.Assignment;
import core.SimpleVarSet;
import core.TermExtension;

public class BDDRelStaticExtensionOracle<D> extends BDDRelStaticOracle<D> {

    private BDDRelStaticEquationSystem<D> system;

    public BDDRelStaticExtensionOracle(BDDRelStaticEquationSystem<D> system){
        this.system = system;
    }

    @Override
    public void update(Assignment<Integer, D> ass, BDDRel relation) {

        BDDRelUniverse universe = relation.getUniverse();

        BDDRel rel = universe.diagonal(); // start from the diagonal
        system.discoveredVariablesIterator().forEachRemaining((Integer i) -> {

            // the set B(ass,oracle.relation)xV
            TermExtension<Integer,D,BDDRel,SimpleVarSet> t =
                    ((TermExtension<Integer,D,BDDRel,SimpleVarSet>) system.getRHS(i));
            BDDRel b = t.evalExtension(system, relation,ass);
            // add the set L(ass,oracle.relation)x{i} to rel
            b.intersectionWith(universe.rightSingleton(i));
            rel.unionWith(b);
            b.clear();
        });

        relation.intersectionWith(rel);
        rel.clear();
    }
}
