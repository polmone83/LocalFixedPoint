package bddRelations;

import com.google.common.collect.Iterators;
import core.VarSet;
import java.util.Iterator;

public class BDDSet implements VarSet<Integer> {
    protected BDDRel relSet;
    private BDDRelUniverse universe;

    public BDDSet(BDDRelUniverse universe){
        this.universe = universe;
        this.relSet = universe.emptyRelation();
    }

    /**
     * Add all the elements from otherSet to this set
     *
     * @param otherSet
     * @return true iff this set changed after the operation
     */
    @Override
    public boolean unionWith(VarSet<Integer> otherSet) {
        if(otherSet instanceof BDDSet){
            BDDRel other = ((BDDSet) otherSet).relSet; // retrieve the ralation
            BDDRel old = relSet.copy(); // make a copy for comparison
            relSet.unionWith(other); // perform the union
            boolean changed = !old.equals(relSet); // compare before-after
            old.clear();
            return changed;
        }else {
            throw new RuntimeException("Incompatible set");
        }
    }

    /**
     * Add all the elements from otherSet to this set
     *
     * @param otherSet
     * @return true iff this set changed after the operation
     */
    @Override
    public boolean intersectionWith(VarSet<Integer> otherSet) {
        if(otherSet instanceof BDDSet){
            BDDRel other = ((BDDSet) otherSet).relSet; // retrieve the ralation
            BDDRel old = relSet.copy(); // make a copy for comparison
            relSet.intersectionWith(other); // perform the union
            boolean changed = !old.equals(relSet); // compare before-after
            old.clear();
            return changed;
        }else {
            throw new RuntimeException("Incompatible set");
        }
    }

    /**
     * Returns an iterator of the elements in the set
     *
     * @return
     */
    @Override
    public Iterator<Integer> allSetElements() {
        BDDRel set = universe.rightSingleton(0);
        set.intersectionWith(relSet);
        Iterator<Integer> iter = Iterators.transform(set.allPairs(),
                pair -> pair.left);
        set.clear();
        return iter;
    }

    /**
     * Returns the set of all variables
     *
     * @return
     */
    @Override
    public void print() {
        System.out.println(this);
    }

    /**
     * Check if variable {@code y} is contained in this VarSet
     *
     * @param y a variable
     * @return true iff {@code y} is in this VarSet
     */
    @Override
    public boolean contains(Integer y) {
        BDDRel ySet = universe.leftSingleton(y);
        ySet.intersectionWith(relSet);
        boolean containsQ = isEmpty();
        ySet.clear();
        return containsQ;
    }

    @Override
    public void add(Integer var) {
        BDDRel varSet = universe.leftSingleton(var);
        relSet.unionWith(varSet);
    }

    @Override
    public int cardinality() {
        BDDRel set = universe.rightSingleton(0);
        set.intersectionWith(relSet);
        int size = (set.bdd.satCount()).intValue();
        set.clear();
        return size;
    }

    @Override
    public boolean isEmpty() {
        return relSet.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder pp = new StringBuilder();
        pp.append("{");
        allSetElements().forEachRemaining(x -> pp.append(x + ", "));
        pp.delete(pp.length()-2, pp.length());
        pp.append("}");
        return pp.toString();
    }

    public static BDDSet toBDDSet(BDDRel rel){
        BDDSet set = new BDDSet(rel.getUniverse());
        set.relSet = rel;
        return set;
    }

    public BDDRel toBDDRel(){
        return relSet;
    }
}
