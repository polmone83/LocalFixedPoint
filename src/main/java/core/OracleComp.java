package core;

import java.util.ArrayList;
import java.util.Collection;

public class OracleComp<V,D,R,S> implements LocalOracle<V,D,R,S>{
    private Collection<LocalOracle<V,D,R,S>> oracles;

    public OracleComp(Collection<LocalOracle<V,D,R,S>> oracles){
        this.oracles = oracles;
    }

    public OracleComp(LocalOracle<V,D,R,S> o1,LocalOracle<V,D,R,S> o2){
        this.oracles = new ArrayList<>();
        oracles.add(o1);
        oracles.add(o2);
    }

    /**
     * Updates the relation passed as parameter
     *
     * @param visited
     * @param ass
     * @param relation
     */
    @Override
    public void update(S visited, Assignment<V, D> ass, R relation) {
        for (LocalOracle<V, D, R, S> oracle : oracles) {
            oracle.update(visited,ass,relation);
        }
    }
}
