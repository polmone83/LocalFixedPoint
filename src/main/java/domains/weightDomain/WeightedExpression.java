package domains.weightDomain;

import bddRelations.BDDRel;
import bddRelations.BDDRelEquationSystem;
import bddRelations.termExtensionOracles.WExpressionExtension;
import core.*;
import weighted_transitions_systems.WCCS.WCCSProcess;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public abstract class WeightedExpression implements RightHandSide<Integer, WValue, SimpleVarSet>, TermExtension<Integer,WValue,BDDRel,SimpleVarSet>{ //
    BDDRelEquationSystem<WValue> system;
    SimpleVarSet vars;
    String stringEnc;

    public WeightedExpression(BDDRelEquationSystem<WValue> system){
        vars = null;
        stringEnc = null;
        this.system = system;
    }

    public SimpleVarSet getVars() {
        if (vars == null) {
            VarsVisitor visitor = new VarsVisitor();
            vars = visitor.visit(this);
        }
        return vars;
    }

    @Override
    public String toString() {
        if (stringEnc == null) {
            PrettyPrint pp = new PrettyPrint();
            stringEnc = pp.visit(this).toString();
        }
        return stringEnc;
    }

    //@Override
    public BDDRel evalExtension(EquationSystem<Integer, WValue, BDDRel, SimpleVarSet> system,
                                BDDRel relation, Assignment<Integer, WValue> ass) {
        /* OLD VERSION
        WExpressionExtension extension;
        extension = new WExpressionExtension((BDDRelEquationSystem<WValue>)system);
        extension.setAssignment(ass);
        extension.setRelation(relation);
        return extension.visit(this);*/

        if(system instanceof BDDRelEquationSystem<WValue> wsystem) {
            WExpressionExtension e = new WExpressionExtension(wsystem, relation, ass);
            return accept(e).toBDDRel();
        }
        throw new RuntimeException("This extension does not work with this RHS type");
    }

    public abstract <T> T accept(WeightedExpressionVisitor<T> visitor);

    private class VarsVisitor implements WeightedExpressionVisitor<SimpleVarSet>{
        SimpleVarSet vars;
        public VarsVisitor() {
            vars = new SimpleVarSet(); // empty set
        }

        @Override
        public SimpleVarSet visitNatural(Natural expr) {
            return vars;
        }

        @Override
        public SimpleVarSet visitInfinity(Infinity expr) {
            return vars;
        }

        @Override
        public SimpleVarSet visitVar(Var expr) {
            vars.add(expr.var);
            return vars;
        }

        @Override
        public SimpleVarSet visitAdd(Add expr) {
            visit(expr.left);
            visit(expr.right);
            return vars;
        }

        @Override
        public SimpleVarSet visitBound(Bound expr) {
            visit(expr.bound);
            visit(expr.expr);
            return vars;
        }

        @Override
        public SimpleVarSet visitMax(Max expr) {
            for (WeightedExpression e : expr.subExpr) {
                visit(e);
            }
            return vars;
        }

        @Override
        public SimpleVarSet visitMin(Min expr) {
            for (WeightedExpression e : expr.subExpr) {
                visit(e);
            }
            return vars;
        }
    }

    private class PrettyPrint implements WeightedExpressionVisitor<StringBuilder>{
        private StringBuilder pp;

        public PrettyPrint() {
            pp = new StringBuilder();
        }

        @Override
        public StringBuilder visitNatural(Natural expr) {
            return pp.append(expr.n);
        }

        @Override
        public StringBuilder visitInfinity(Infinity expr) {
            return pp.append("Infty");
        }

        @Override
        public StringBuilder visitVar(Var expr) {
            return pp.append("v"+expr.var);
        }

        @Override
        public StringBuilder visitAdd(Add expr) {
            pp.append("(");
            visit(expr.left);
            pp.append(" + ");
            visit(expr.right);
            pp.append(")");
            return pp;
        }

        @Override
        public StringBuilder visitBound(Bound expr) {
            pp.append("[");
            visit(expr.expr);
            pp.append(" <= ");
            visit(expr.bound);
            pp.append("]");
            return pp;
        }

        @Override
        public StringBuilder visitMax(Max expr) {
            if(!expr.subExpr.isEmpty()){
                pp.append("Max(");
                for (WeightedExpression e : expr.subExpr) {
                    visit(e);
                    pp.append(", ");
                }
                pp.delete(pp.length()-2, pp.length());
                pp.append(")");
            }
            return pp;
        }

        @Override
        public StringBuilder visitMin(Min expr) {
            if(!expr.subExpr.isEmpty()){
                pp.append("Min(");
                for (WeightedExpression e : expr.subExpr) {
                    visit(e);
                    pp.append(", ");
                }
                pp.delete(pp.length()-2, pp.length());
                pp.append(")");
            }
            return pp;
        }
    }

    public static class Infinity extends WeightedExpression{
        public Infinity(BDDRelEquationSystem<WValue> system){
            super(system);
        }

        @Override
        public <T> T accept(WeightedExpressionVisitor<T> visitor) {
            return visitor.visitInfinity(this);
        }

        @Override
        public WValue eval(Assignment<Integer, WValue> ass) {
            return WValue.infinity;
        }
    }

    public static class Natural extends WeightedExpression{
        public WValue.Natural n;
        public Natural(Integer val, BDDRelEquationSystem<WValue> system){
            super(system);
            this.n = new WValue.Natural(val);
        }

        @Override
        public <T> T accept(WeightedExpressionVisitor<T> visitor) {
            return visitor.visitNatural(this);
        }

        @Override
        public WValue eval(Assignment<Integer, WValue> ass) {
            return n;
        }
    }

    public static class Var extends WeightedExpression {
        public Integer var;
        public Var(Integer var, BDDRelEquationSystem<WValue> system) {
            super(system);
            this.var = var;
        }

        @Override
        public <T> T accept(WeightedExpressionVisitor<T> visitor) {
            return visitor.visitVar(this);
        }

        @Override
        public WValue eval(Assignment<Integer, WValue> ass) {
            return ass.getValue(var);
        }
    }

    public static class Add extends WeightedExpression {
        public WeightedExpression left, right;

        public Add(Integer weight, WeightedExpression right,
                   BDDRelEquationSystem<WValue> system) {
            super(system);
            this.left = new Natural(weight,system);
            this.right = right;
        }

        public Add(WeightedExpression left, WeightedExpression right,
                BDDRelEquationSystem<WValue> system) {
            super(system);
            this.left = left;
            this.right = right;
        }

        @Override
        public <T> T accept(WeightedExpressionVisitor<T> visitor) {
            return visitor.visitAdd(this);
        }

        @Override
        public WValue eval(Assignment<Integer, WValue> ass) {
            WValue r = right.eval(ass);
            if (r.equals(WValue.infinity)) {
                // we can skip evaluation of the left side
                return WValue.infinity;
            }else if(r.equals(WValue.zero) && ! (right instanceof Natural)){
                // this is the top
                // we can replace the subterm with a constant
                right = new Natural(0, system);
            }

            WValue l = left.eval(ass);
            if (l.equals(WValue.zero) && !(left instanceof Natural)) {
                // this is the top
                // we can replace the subterm with a constant
                left = new Natural(0, system);
            }

            return WValue.add(l,r);
        }
    }

    public static class Bound extends WeightedExpression {
        public WeightedExpression bound, expr;

        public Bound(Integer bound, WeightedExpression expr,
                     BDDRelEquationSystem<WValue> system){
            super(system);
            this.expr = expr;
            this.bound = new Natural(bound,system);
        }

        public Bound(WeightedExpression bound, WeightedExpression expr,
                BDDRelEquationSystem<WValue> system) {
            super(system);
            this.expr = expr;
            this.bound = bound;
        }

        @Override
        public <T> T accept(WeightedExpressionVisitor<T> visitor) {
            return visitor.visitBound(this);
        }

        @Override
        public WValue eval(Assignment<Integer, WValue> ass) {
            // NB: the bound is typically a constant, hence we focus
            return WValue.leq(expr.eval(ass), bound.eval(ass)) ?
                    WValue.zero : WValue.infinity;
        }
    }

    public static class Max extends WeightedExpression {
        public Set<WeightedExpression> subExpr;

        public Max(WeightedExpression left, WeightedExpression right,
                BDDRelEquationSystem<WValue> system) {
            super(system);
            subExpr = new HashSet<>();
            subExpr.add(left);
            subExpr.add(right);
        }

        public Max(Set<WeightedExpression> subExpr,
                   BDDRelEquationSystem<WValue> system) {
            super(system);
            this.subExpr = subExpr;
        }

        @Override
        public <T> T accept(WeightedExpressionVisitor<T> visitor) {
            return visitor.visitMax(this);
        }

        @Override
        public WValue eval(Assignment<Integer, WValue> ass) {
            WValue maxval = WValue.zero;
            for (WeightedExpression e : subExpr) {
                WValue e_val = e.eval(ass);
                if(e_val instanceof WValue.Infinity) return e_val;
                //if(e_val.equals(WValue.zero)) subExpr.remove(e); // optimization
                maxval = WValue.max(maxval,e_val);
            }
            return maxval;
        }
    }

    public static class Min extends WeightedExpression {
        public Set<WeightedExpression> subExpr;

        public Min(WeightedExpression left, WeightedExpression right,
                   BDDRelEquationSystem<WValue> system) {
            super(system);
            subExpr = new HashSet<>();
            subExpr.add(left);
            subExpr.add(right);
        }

        public Min(Set<WeightedExpression> subExpr,
                   BDDRelEquationSystem<WValue> system) {
            super(system);
            this.subExpr = subExpr;
        }

        @Override
        public <T> T accept(WeightedExpressionVisitor<T> visitor) {
            return visitor.visitMin(this);
        }

        @Override
        public WValue eval(Assignment<Integer, WValue> ass) {
            WValue minval = WValue.infinity;
            for (WeightedExpression e : subExpr) {
                WValue e_val = e.eval(ass);
                if(e_val.equals(WValue.zero)){
                    return WValue.zero;
                }
                minval = WValue.min(minval,e_val);
            }
            return minval;
        }
    }

}
