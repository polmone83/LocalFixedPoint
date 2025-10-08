package bddRelations.soundOracles;

import bddRelations.BDDRel;
import bddRelations.BDDRelStaticEquationSystem;
import bddRelations.BDDRelUniverse;
import core.Assignment;

public class NsOracle<D> extends BDDRelStaticOracle<D> {

    BDDRelStaticEquationSystem<D> system;

    public NsOracle(BDDRelStaticEquationSystem<D> system){
        this.system = system;
    }

    @Override
    public void update(Assignment<Integer, D> ass, BDDRel relation) {
        BDDRelUniverse universe = relation.getUniverse();
        BDDRel rel = universe.emptyRelation();
        // construct the relation {x | tx(ass) != ass(x) } \times V
        system.discoveredVariablesIterator().forEachRemaining(
                (Integer x) -> {
                    // if fx(A) != A(x)
                    if (! system.retrieveRHS(x).eval(ass).equals(ass.getValue(x))) {
                        rel.unionWith(universe.leftSingleton(x));
                    }
                }
        );
        relation.intersectionWith(rel);
        rel.clear();
    }
}
