package core;

public interface LabelMap<V> {
    /**
     * Returns the label associated with the given variable index
     *
     * @param x the index of a variable in the system
     * @return the label associated with the variable
     */
    public String getLabel(V x);
}
