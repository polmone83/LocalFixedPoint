package bddRelations;

import core.EquationSystem;
import core.SimpleVarSet;

import java.util.Iterator;

public abstract class BDDRelEquationSystem<D>  extends EquationSystem<Integer,D,BDDRel, SimpleVarSet> {

    private BDDRelUniverse universe;
    /**
     * Returns the BDDRelUniverse that has been passed during initialization,
     * or {@code null} if the universe has not been yet initialized.
     * @return
     */
    public BDDRelUniverse getUniverse(){
        return universe;
    }

    /**
     * (re)initialise the universe
     * @param size the number of known variables
     * @throws RuntimeException when the method is called more than once
     */
    protected void initUniverse(Integer size) throws RuntimeException{
        if(universe == null){
            universe = new BDDRelUniverse();
        }
        universe.init(size, size);
    }

    @Override
    protected BDDRel initRelation() {
        int max = 0;
        Iterator<Integer> iter = visited.allSetElements();
        while (iter.hasNext()){
            Integer v = iter.next();
            max = Math.max(max, v);
        }
        // reset reinitialize the universe
        initUniverse(max+1);
        // create the relation
        this.relation = universe.fullRelation();
        return relation;
    }

    @Override
    protected SimpleVarSet initVisited(Integer targetVar) {
        this.visited = new SimpleVarSet();
        visited.add(targetVar);
        return visited;
    }
}
