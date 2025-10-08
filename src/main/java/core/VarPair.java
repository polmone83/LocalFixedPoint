package core;

public class VarPair<V> {
    public V left, right;

    public VarPair(){}

    public VarPair(V left, V right){
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "<" + left + ", " + right + ">";
    }
}
