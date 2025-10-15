package core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Assignment<V,D> {
    public HashSet<V> fixpoints;
    private HashMap<V,D> values;
    private LabelMap<V> labelmap;
    D bottomElement;
    private V lastChanged;

    public Assignment(D bottomElement){
        this.bottomElement = bottomElement;
        this.values = new HashMap<V,D>();
        this.lastChanged = null;
        this.fixpoints = new HashSet<>();
    }

    public Assignment(D bottomElement, LabelMap<V> labelmap){
        this(bottomElement);
        this.labelmap = labelmap;
    }

    public D getValue(V x){
        return values.getOrDefault(x,bottomElement);
    }

    public void setValue(V x, D val){
        //if(!val.equals(bottomElement)) {
        values.put(x, val);
        lastChanged = x;
        //}
    }

    /**
     * The set of variables who got assignment
     * @return
     */
    public Set<V> getDomain(){
        return values.keySet();
    }

    public V getLastChanged(){return lastChanged;}

    @Override
    public String toString() {

        StringBuilder s = new StringBuilder();
        s.append("Assignment: {");
        values.forEach(
                (V var,D val) ->{
                    s.append(labelmap.getLabel(var));
                    s.append(" -> ");
                    s.append(val);
                    s.append(", ");
                }

        );
        // delete the last separator ", "
        if(!values.isEmpty()) {
            s.delete(s.length() - 2, s.length());
        }
        s.append("}");
        return s.toString();
    }
}
