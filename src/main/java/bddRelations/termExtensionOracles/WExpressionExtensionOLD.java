package bddRelations.termExtensionOracles;

import bddRelations.BDDRel;
import bddRelations.BDDRelEquationSystem;
import bddRelations.BDDRelUniverse;
import core.Assignment;
import core.SimpleVarSet;
import domains.weightDomain.WValue;
import domains.weightDomain.WeightedExpression;
import domains.weightDomain.WeightedExpressionVisitor;

public class WExpressionExtensionOLD implements WeightedExpressionVisitor<SimpleVarSet> {

    private BDDRel relation;
    private Assignment<Integer, WValue> ass;
    private final BDDRelEquationSystem<WValue> system;
    private final BDDRelUniverse u;

    //private LazyEvaluator evaluator;

    public WExpressionExtensionOLD(BDDRelEquationSystem<WValue> system) {
        this.system = system;
        this.u = system.getUniverse();
        //evaluator = new LazyEvaluator();
    }

    public void setAssignment(Assignment<Integer, WValue> ass) {
        this.ass = ass;
    }

    public void setRelation(BDDRel relation) {
        this.relation = relation;
    }

    /*private class LazyEvaluator implements WeightedExpressionVisitor<WValue>{
        private HashMap<WeightedExpression, WValue> store;

        public LazyEvaluator(){
            this.store = new HashMap<>(); // reset the store
        }

        @Override
        public WValue visit(WeightedExpression expr) {
            WValue val = store.get(expr);
            if(val != null){
                return val;
            }else{
                return expr.accept(this);
            }
        }

        @Override
        public WValue visitInfinity(WeightedExpression.Infinity expr) {
            return WValue.infinity;
        }

        @Override
        public WValue visitNatural(WeightedExpression.Natural expr) {
            WValue val = expr.eval(WExpressionExtension.this.ass);
            store.put(expr,val);
            return val;
        }

        @Override
        public WValue visitBound(WeightedExpression.Bound expr) {
            WValue val =  WValue.leq(visit(expr.expr), visit(expr.bound)) ? WValue.zero : WValue.infinity;
            store.put(expr,val);
            return val;
        }

        @Override
        public WValue visitMax(WeightedExpression.Max expr) {

            WValue maxval = new WValue.Natural(0);
            for (WeightedExpression e : expr.subExpr) {
                WValue e_val = visit(e);
                if(e_val.equals(WValue.infinity)) {
                    maxval = WValue.infinity;
                    break;
                }
                maxval = WValue.max(maxval,e_val);
            }
            store.put(expr,maxval);
            return maxval;
        }

        @Override
        public WValue visitMin(WeightedExpression.Min expr) {
            WValue minval = new WValue.Infinity();
            for (WeightedExpression e : expr.subExpr) {
                WValue e_val = visit(e);
                if(e_val.equals(WValue.zero)){
                    minval = WValue.zero;
                    break;
                }
                minval = WValue.min(minval,e_val);
            }
            store.put(expr,minval);
            return minval;
        }

        @Override
        public WValue visitAdd(WeightedExpression.Add expr) {
            WValue val = WValue.add(visit(expr.left),visit(expr.right));
            store.put(expr,val);
            return val;
        }

        @Override
        public WValue visitVar(WeightedExpression.Var expr) {
            WValue val = expr.eval(WExpressionExtension.this.ass);
            store.put(expr,val);
            return val;
        }
    }*/

    @Override
    public SimpleVarSet visit(WeightedExpression expr) {
        /*if(expr.eval(ass).equals(WValue.zero)){
            return new SimpleVarSet(); // empty set
        }else{*/
            return expr.accept(this);
        //}
    }

    @Override
    public SimpleVarSet visitMax(WeightedExpression.Max expr) {
        SimpleVarSet ret = new SimpleVarSet(); // empty set
        for (WeightedExpression subExpr : expr.subExpr) {
            SimpleVarSet subret = visit(subExpr);
            if(subret.isEmpty() && subExpr.eval(ass).equals(WValue.infinity)){
                return new SimpleVarSet(); // empty set
            }
            ret.unionWith(subret);
        }
        return ret;
    }

    @Override
    public SimpleVarSet visitMin(WeightedExpression.Min expr) {
        for (WeightedExpression subExpr : expr.subExpr) {
            if(subExpr.eval(ass).equals(WValue.zero)){
                return new SimpleVarSet(); // empty set
            }
        }

        SimpleVarSet ret = new SimpleVarSet(); // empty set
        for (WeightedExpression subExpr : expr.subExpr) {
            ret.unionWith(visit(subExpr));
        }
        return ret;
    }

    @Override
    public SimpleVarSet visitNatural(WeightedExpression.Natural expr) {
        return new SimpleVarSet(); // empty set
    }

    @Override
    public SimpleVarSet visitAdd(WeightedExpression.Add expr) {
        SimpleVarSet left = visit(expr.left);
        if(left.isEmpty() && expr.left.eval(ass).equals(WValue.infinity)){
            return new SimpleVarSet(); // empty set
        }
        SimpleVarSet right = visit(expr.right);
        if(right.isEmpty() && expr.right.eval(ass).equals(WValue.infinity)){
            return new SimpleVarSet(); // empty set
        }
        left.unionWith(right);
        return left;
    }

    @Override
    public SimpleVarSet visitVar(WeightedExpression.Var expr) {
        /*SimpleVarSet sigleton = new SimpleVarSet(); // empty set
        sigleton.add(expr.var);
        return sigleton;*/
        return relation.sliceLeft(expr.var);
    }

    @Override
    public SimpleVarSet visitInfinity(WeightedExpression.Infinity expr) {
        return new SimpleVarSet(); // empty set
    }

    @Override
    public SimpleVarSet visitBound(WeightedExpression.Bound expr) {
        WValue e_val = expr.expr.eval(ass);
        if(e_val.equals(WValue.zero))
            return new SimpleVarSet(); // empty set

        WValue bound_val = expr.bound.eval(ass);

        SimpleVarSet bound_dep = visit(expr.bound);
        if(WValue.leq(e_val,bound_val) && bound_dep.isEmpty()){
            return new SimpleVarSet(); // empty set
        }
        SimpleVarSet depExpr = visit(expr.expr);
        bound_dep.unionWith(depExpr);
        return bound_dep;
    }
}
