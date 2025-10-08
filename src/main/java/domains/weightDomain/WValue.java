package domains.weightDomain;

public abstract class WValue {

    public static WValue zero = new Natural(0);
    public static WValue infinity = new Infinity();

    public static class Infinity extends WValue{
        @Override
        public String toString() {
            return "infinity";
        }

        @Override
        public boolean equals(Object obj) {
            return (obj instanceof Infinity);
        }
    }

    public static class Natural extends WValue{
        private Integer val;
        public Natural(Integer val){
            this.val = val;
        }

        @Override
        public String toString() {
            return val.toString();
        }

        public Integer getVal() {
            return val;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Natural){
                return val.equals(((Natural) obj).getVal());
            }
            return false;
        }
    }

    public static WValue add(WValue a, WValue b){
        if(a instanceof Infinity || b instanceof Infinity)
            return new Infinity();

        // both a and b are Natural
        return new Natural(((Natural) a).getVal() + ((Natural) b).getVal());
    }

    public static WValue max(WValue a, WValue b){
        if(a instanceof Infinity) return a;
        if(b instanceof Infinity) return b;

        return new Natural(Math.max(((Natural) a).getVal(),((Natural) b).getVal()));
    }

    public static WValue min(WValue a, WValue b){
        if(a instanceof Infinity) return b;
        if(b instanceof Infinity) return a;
        return new Natural(Math.min(((Natural) a).getVal(),((Natural) b).getVal()));
    }

    public static boolean leq(WValue a, WValue b){
        if(a.equals(b)) return true;
        if(a instanceof Infinity) return false;
        if(b instanceof Infinity) return true;
        return ((Natural) a).getVal() <= ((Natural) b).getVal();
    }


}
