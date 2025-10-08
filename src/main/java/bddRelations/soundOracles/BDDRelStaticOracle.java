package bddRelations.soundOracles;

import bddRelations.BDDRel;
import core.Assignment;
import core.SimpleVarSet;

public abstract class BDDRelStaticOracle<D> extends BDDRelOracle<D> {

    @Override
    public void update(SimpleVarSet visited, Assignment<Integer, D> ass, BDDRel relation) {
        update(ass,relation);
    }

    public abstract void update(Assignment<Integer, D> ass, BDDRel relation);
}
