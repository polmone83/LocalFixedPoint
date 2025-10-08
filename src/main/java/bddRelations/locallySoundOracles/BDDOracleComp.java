package bddRelations.locallySoundOracles;

import bddRelations.BDDRel;
import bddRelations.soundOracles.BDDRelOracle;
import core.Assignment;
import core.OracleComp;
import core.SimpleVarSet;

import java.util.ArrayList;
import java.util.Collection;

public class BDDOracleComp<D> extends BDDRelOracle<D> {

    Collection<BDDRelOracle<D>> oracles;

    public BDDOracleComp(){
        oracles = new ArrayList<>();
    }

    public BDDOracleComp(Collection<BDDRelOracle<D>> oracles){
        this.oracles = oracles;
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
        OracleComp oracleComp = new OracleComp(oracles);
        oracleComp.update(visited,ass,relation);
    }

    public BDDOracleComp<D> addOracle(BDDRelOracle<D> oracle){
        this.oracles.add(oracle);
        return this;
    }
}
