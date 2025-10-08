package bddRelations.termExtensionOracles;

import bddRelations.BDDRel;
import bddRelations.BDDRelEquationSystem;
import bddRelations.BDDRelUniverse;
import bddRelations.BDDSet;
import core.Assignment;
import domains.weightDomain.WValue;
import domains.weightDomain.WeightedExpression;
import domains.weightDomain.WeightedExpressionVisitor;

public class WExpressionExtension implements WeightedExpressionVisitor<BDDSet> {

    private BDDRel relation;
    private Assignment<Integer, WValue> ass;
    private final BDDRelEquationSystem<WValue> system;
    private final BDDRelUniverse u;

    //private LazyEvaluator evaluator;

    public WExpressionExtension(BDDRelEquationSystem<WValue> system,
                                BDDRel relation,
                                Assignment<Integer, WValue> ass) {
        this.system = system;
        this.u = system.getUniverse();
        this.relation = relation;
        this.ass = ass;
        //evaluator = new LazyEvaluator();
    }

    /*public void setAssignment(Assignment<Integer, WValue> ass) {
        this.ass = ass;
    }*/

    /*public void setRelation(BDDRel relation) {
        this.relation = relation;
    }*/

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
    public BDDSet visit(WeightedExpression expr) {
        if(expr.eval(ass).equals(WValue.zero)){
            return new BDDSet(u); // empty set
        }else{
            return WeightedExpressionVisitor.super.visit(expr);
        }
    }

    @Override
    public BDDSet visitMax(WeightedExpression.Max expr) {
        BDDSet ret = new BDDSet(u); // empty set
        for (WeightedExpression subExpr : expr.subExpr) {
            BDDSet subret = visit(subExpr);
            if(subret.isEmpty() && subExpr.eval(ass).equals(WValue.infinity)){
                return new BDDSet(u); // empty set
            }
            ret.unionWith(subret);
        }
        return ret;
    }

    @Override
    public BDDSet visitMin(WeightedExpression.Min expr) {
        for (WeightedExpression subExpr : expr.subExpr) {
            if(subExpr.eval(ass).equals(WValue.zero)){
                return new BDDSet(u); // empty set
            }
        }

        BDDSet ret = new BDDSet(u); // empty set
        for (WeightedExpression subExpr : expr.subExpr) {
            ret.unionWith(visit(subExpr));
        }
        return ret;
    }

    @Override
    public BDDSet visitNatural(WeightedExpression.Natural expr) {
        return new BDDSet(u); // empty set
    }

    @Override
    public BDDSet visitAdd(WeightedExpression.Add expr) {
        BDDSet left = visit(expr.left);
        if(left.isEmpty() && expr.left.eval(ass).equals(WValue.infinity)){
            return new BDDSet(u); // empty set
        }
        BDDSet right = visit(expr.right);
        if(right.isEmpty() && expr.right.eval(ass).equals(WValue.infinity)){
            return new BDDSet(u); // empty set
        }
        left.unionWith(right);
        return left;
    }

    @Override
    public BDDSet visitVar(WeightedExpression.Var expr) {
        return BDDSet.toBDDSet(relation.sliceLeftAt(expr.var));
    }

    @Override
    public BDDSet visitInfinity(WeightedExpression.Infinity expr) {
        return new BDDSet(u); // empty set
    }

    @Override
    public BDDSet visitBound(WeightedExpression.Bound expr) {
        WValue e_val = expr.expr.eval(ass);
        if(e_val.equals(WValue.zero))
            return new BDDSet(u); // empty set

        WValue bound_val = expr.bound.eval(ass);

        BDDSet bound_dep = visit(expr.bound);
        if(WValue.leq(e_val,bound_val) && bound_dep.isEmpty()){
            return new BDDSet(u); // empty set
        }
        BDDSet depExpr = visit(expr.expr);
        bound_dep.unionWith(depExpr);
        return bound_dep;
    }
}
