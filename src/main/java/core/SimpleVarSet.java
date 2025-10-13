package core;

import java.util.*;

public class SimpleVarSet implements VarSet<Integer> {

    public HashSet<Integer> set;

    public  SimpleVarSet(){
        set = new HashSet<>();
    }

    /**
     * Add all the elements from otherSet to this set
     *
     * @param otherSet
     * @return true iff this set changed after the operation
     */
    @Override
    public boolean unionWith(VarSet<Integer> otherSet) {
        if(otherSet instanceof SimpleVarSet other){
            return set.addAll(other.set);
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
        boolean changed = false;
        if(otherSet instanceof SimpleVarSet){
            HashSet<Integer> other = ((SimpleVarSet) otherSet).set;
            HashSet intersection = new HashSet();
            for (Integer v : set) {
                if(other.contains(v)) {
                    intersection.add(v);
                }else{
                    changed = true;
                }
            }
            set = intersection;
        }else{
            throw new RuntimeException("Incompatible set");
        }
        return changed;
    }

    /**
     * Returns an iterator of the elements in the set
     *
     * @return
     */
    @Override
    public Iterator<Integer> allSetElements() {
        // provide a randomly ordered iterator
//        ArrayList<Integer> list = new ArrayList<>(set);
//        Collections.sort(list,Collections.reverseOrder());
//        return list.iterator();
        return set.iterator(); //old implementation
    }

    /**
     * Returns the set of all variables
     *
     * @return
     */
    @Override
    public void print() {
        System.out.println(set);
    }

    /**
     * Check if variable {@code y} is contained in this VarSet
     *
     * @param y a variable
     * @return true iff {@code y} is in this VarSet
     */
    @Override
    public boolean contains(Integer y) {
        return set.contains(y);
    }

    @Override
    public void add(Integer var) {
        set.add(var);
    }

    @Override
    public int cardinality() {
        return set.size();
    }

    public boolean isEmpty(){
        return set.isEmpty();
    }
}
