package bddRelations;

import com.github.javabdd.*;
import core.SimpleVarSet;
import core.VarPair;
import core.VarRelation;

import java.math.BigInteger;
import java.util.Iterator;

public class BDDRel implements VarRelation<Integer, SimpleVarSet> {
    public BDD bdd;
    BDDFactory f;
    BDDPairing swap;
    private BDDRelUniverse universe;

    protected BDDRel(BDDRelUniverse u, BDD bdd) {
        this.bdd = bdd;
        this.universe = u;
        f = bdd.getFactory();
        BDDDomain oldV = f.getDomain(0);
        BDDDomain newV = f.getDomain(1);
        swap = f.makePair(newV, oldV);
    }

    public boolean isEmpty() {
        this.removeDontCares(); // todo: maybe not necessary
        return bdd.isZero();
    }

    /**
     * Compute the relation {x : (x,rightElem) \in R } x V
     *
     * @param rightElem the variable where to project this relation
     * @return
     */
    public BDDRel sliceLeftAt(Integer rightElem) {
        BDDRel sing = universe.rightSingleton(rightElem);
        BDDVarSet rightComponent = f.getDomain(1).set();
        BDD s = bdd.relprod(sing.bdd, rightComponent);
        sing.clear();
        return new BDDRel(universe, s);
    }

    /**
     * Return the set V x {x : (var,x) \in R }
     *
     * @param leftElement the variable where to project this relation
     * @return
     */
    public BDDRel sliceRightAt(Integer leftElement) {
        BDDRel sing = universe.leftSingleton(leftElement);
        BDDVarSet leftComponent = f.getDomain(0).set();
        BDD s = bdd.relprod(sing.bdd, leftComponent);
        sing.clear();
        return new BDDRel(universe, s);
    }

    public BDDRel intersection(BDDRel that) {
        return new BDDRel(universe, bdd.and(that.bdd));
    }

    public void intersectionWith(BDDRel that) {
        bdd.andWith(that.bdd.id());
    }

    public BDDRel union(BDDRel that) {
        return new BDDRel(universe, bdd.or(that.bdd));
    }

    public void unionWith(BDDRel that) {
        bdd.orWith(that.bdd.id());
    }

    /**
     * Returns the left relational marginal of this BDDRel
     * @return {x | \exists y. (x,y) \in R} x V
     */
    public BDDRel leftMarginal(){
        return new BDDRel(universe, this.bdd.exist(f.getDomain(1).set()));
    }

    /**
     * Returns the right relational marginal of this BDDRel
     * @return V x {y | \exists x. (x,y) \in R}
     */
    public BDDRel rightMarginal(){
        return new BDDRel(universe, this.bdd.exist(f.getDomain(0).set()));
    }

    public BDDRel composition(BDDRel that) {
        BDDPairing rename;
        BDDDomain X = f.getDomain(0);
        BDDDomain Y = f.getDomain(1);
        BDDDomain Z = f.getDomain(2);
        rename = f.makePair(X,Y);
        rename.set(Y,Z);

        BDD R1 = this.bdd;
        BDD R2 = that.bdd.replace(rename);
        R2 = R2.and(R1).exist(f.getDomain(1).set());
        R2.replaceWith(f.makePair(Z,Y));
        return new BDDRel(universe, R2);
    }

    public void transitiveClosure() {
        BDDRel R = this.copy();
        BDDRel old;
        boolean termination;
        do {
            old = this.copy();
            this.compositionWith(R);
            this.unionWith(old);
            termination = !this.equals(old);
            old.clear();
        } while (termination);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BDDRel rel) {
            return bdd.equalsBDD(rel.bdd);
        }
        return false;
    }

    public BDDRel copy() {
        return new BDDRel(universe, bdd.id());
    }

    public void reflexiveClosure() {
        unionWith(universe.diagonal());
//        for (int i = 0; i < universe.getDomainSize(); i++) {
//            insertPair(i, i);
//        }
    }

    public void compositionWith(BDDRel that) {
        BDD c = composition(that).bdd;
        bdd.free();
        bdd = c;
    }

    public BDDRel inverse() {
        BDDDomain oldV = f.getDomain(0);
        BDDDomain newV = f.getDomain(1);
        BDDPairing swap = f.makePair(newV, oldV);
        swap.set(oldV,newV);
        return new BDDRel(universe, bdd.replace(swap));
    }

    public void inverseThis() {
        bdd.replaceWith(swap);
    }

    @Override
    public void insertPair(Integer v1, Integer v2) {
        BDD pair = f.getDomain(0).ithVar(v1);
        pair.andWith(f.getDomain(1).ithVar(v2));
        bdd.orWith(pair);
    }

    @Override
    public void removePair(Integer v1, Integer v2) {
        BDD pair = f.getDomain(0).ithVar(v1);
        pair.andWith(f.getDomain(1).ithVar(v2));
        bdd.andWith(pair.not());
    }

    public BDDRel complement() {
        BDDRel c = new BDDRel(universe, bdd.not());
        c.removeDontCares();
        return c;
    }

    private void removeDontCares() {
        bdd.and(universe.fullRelation().bdd);
    }

    public BDDRel diff(BDDRel that) {
        return this.intersection(that.complement());
    }

    public void diffWith(BDDRel that){
        BDD c = diff(that).bdd;
        bdd.free();
        bdd = c;
    }

    public void clear() {
        bdd.free();
        //bdd = f.zero();
    }

    public void assign(BDDRel that) {
        bdd.free(); // free the current bdd
        bdd = that.bdd.id(); // assign a copy of that
    }

    public BDDRelUniverse getUniverse() {
        return this.universe;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        Iterator<VarPair<Integer>> el = allPairs();
        while (el.hasNext()) {
            VarPair<Integer> pair = el.next();
            s.append("<");
            s.append(pair.left);
            s.append(",");
            s.append(pair.right);
            s.append(">");
        }
        return s.toString();
        //return bdd.toStringWithDomains();
    }

    /**
     * @param var
     * @return {x | (x, var) \in R}
     */
    @Override
    public SimpleVarSet sliceLeft(Integer var) {
        BDDRel slice = sliceLeftAt(var);
        slice.intersectionWith(universe.rightSingleton(0));
        Iterator<VarPair<Integer>> iter = slice.allPairs();
        slice.clear();
        SimpleVarSet simpleVarSet = new SimpleVarSet();
        iter.forEachRemaining(
                pair -> simpleVarSet.set.add(pair.left)
        );
        return simpleVarSet;
    }

    /**
     * @param var
     * @return {y | (var,y) \in R}
     */
    @Override
    public SimpleVarSet sliceRight(Integer var) {
        BDDRel slice = sliceRightAt(var);
        slice.intersectionWith(universe.leftSingleton(0));
        Iterator<VarPair<Integer>> iter = slice.allPairs();
        slice.clear();
        SimpleVarSet simpleVarSet = new SimpleVarSet();
        iter.forEachRemaining(
                pair -> simpleVarSet.set.add(pair.right)
        );
        return simpleVarSet;
    }

    public Iterator<VarPair<Integer>> allPairs() {
        BDDVarSet vars = bdd.getFactory().makeSet(new BDDDomain[]{f.getDomain(0), f.getDomain(1)});
        BDD.BDDIterator iter = bdd.iterator(vars); // iterator on the first 2 components

        return new Iterator() {
            @Override
            public boolean hasNext() {
                return iter.hasNext();
            }

            @Override
            public Element next() {
                Element pair = new Element(iter.nextTuple());
                return pair;
            }
        };
    }

    /*
     * Add all the elements from otherSet to this set
     *
     * @param otherSet
     * @return true iff this set changed after the operation
     */
    /*@Override
    public boolean unionWith(VarSet<Integer> otherSet) {
        if (otherSet instanceof BDDRel){
            BDDRel union = ((BDDRel) otherSet).copy();
            union.unionWith(this);
            if(!equals(union)){
                assign(union);
                return true;
            }else{
                union.clear();
            }
        }
        return false;
    }*/

    /*
     * Add all the elements from otherSet to this set
     *
     * @param otherSet
     * @return true iff this set changed after the operation
     */
    /*@Override
    public boolean intersectionWith(VarSet<Integer> otherSet) {
        if (otherSet instanceof BDDRel){
            BDDRel result = this.intersection((BDDRel) otherSet);
            if(! result.equals(this)){
                assign(result);
                return true;
            }else{
                result.clear();
            }
        }
        return false;
    }*/

    /*
     * Returns an iterator of the elements in the set
     * @return
     */
    /*@Override
    public Iterator<Integer> allSetElements() {
        *//* since this relation has the form S x V
           we first slice it at 0, to avoid repetitions
        *//*
        BDDRel set = intersection(universe.rightSingleton(0));
        Iterator<VarPair<Integer>> iter = set.allPairs();
        set.clear();

        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return iter.hasNext();
            }

            @Override
            public Integer next() {
                return iter.next().left;
            }
        };
    }*/

    /*
     * Returns the set of all variables
     * @return
     */
    /*@Override
    public void print() {
        allSetElements().forEachRemaining(
                x -> System.out.print(x + ",")
        );
        System.out.println();
    }*/

    /*
     * Check if variable {@code y} is contained in this VarSet
     *
     * @param y a variable
     * @return true iff {@code y} is in this VarSet
    @Override
    public boolean contains(Integer y) {
        BDDRel withY = union(universe.leftSingleton(y));
        boolean ret = equals(withY);
        withY.clear();
        return ret;
    }*/

    /*@Override
    public int cardinality() {
        BDDRel set = intersection(universe.rightSingleton(0));
        int size = (set.bdd.satCount()).intValue();
        return size;
    }*/

    public class Element extends VarPair<Integer> {
        protected Element(BigInteger[] vec) {
            this.left = vec[0].intValue();
            this.right = vec[1].intValue();
        }
    }
}
