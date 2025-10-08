package bddRelations.soundOracles;

import bddRelations.BDDRel;
import bddRelations.BDDRelStaticEquationSystem;
import bddRelations.locallySoundOracles.Dep;
import core.Assignment;
import core.SimpleVarSet;

/**
 * The static version of the triggering oracle.
 * @param <D>
 */
public class trigStatic<D> extends Dep<D> {

    //BDDRelStaticEquationSystem<D> system;
    public trigStatic(BDDRelStaticEquationSystem<D> system) {
        super(system);
        //this.system = system;
    }

    @Override
    public void update(SimpleVarSet visited, Assignment<Integer, D> ass, BDDRel relation) {
        NsOracle<D> ns = new NsOracle<>((BDDRelStaticEquationSystem<D>) super.system);
        ns.update(ass,relation); // R = (ns \cap id)(R)
        super.update(visited, ass, relation); // R = dep(R)
    }
}
