package utilities;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Iterator wrapper that removes duplicate elements
 * The class does not implement the method remove.
 * @param <E>
 */
public class SetIterator<E> implements Iterator<E> {
    private HashSet<E> collected;
    private Iterator<E> iter;
    private E next;

    public SetIterator(Iterator<E> iter){
        this.iter = iter;
        collected = new HashSet<>();
        next = getNext();
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public E next() {
        E current = next; // set current element
        next = getNext(); // store next element
        return current;
    }

    private E getNext(){
        boolean duplicated = true;
        while (iter.hasNext() && duplicated){
            next = iter.next();
            duplicated = ! collected.add(next);
        }
        return duplicated ? null : next;
    }
}
