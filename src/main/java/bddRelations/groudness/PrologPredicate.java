package bddRelations.groudness;

public class PrologPredicate {
    private String name;
    private int arity;

    private final static String defaultVarName = "_X";

    public PrologPredicate(String name, int arity) {
        this.name = name;
        this.arity = arity;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PrologPredicate) {
            PrologPredicate p = (PrologPredicate) obj;
            return p.name.equals(name) && arity == p.arity;
        }
        return false;
    }

    @Override
    public String toString() {
        return defaultCallPattern();
    }

    public String defaultCallPattern(){
        StringBuilder s = new StringBuilder(name);
        if (arity > 0) {
            s.append("(").append(ithDefParam(0));
            for (int i = 1; i < arity; i++) {
                s.append(",").append(ithDefParam(i));
            }
            s.append(")");
        }
        return s.toString();
    }

    public String getSignature(){
        return name + "/" + arity;
    }

    public static String ithDefParam(int i){
        return defaultVarName + i;
    }

}
