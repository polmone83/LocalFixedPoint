package bddRelations.termExtensionOracles;

import bddRelations.BDDRel;
import bddRelations.BDDRelEquationSystem;
import bddRelations.BDDRelUniverse;
import bddRelations.BDDSet;
import core.Assignment;
import domains.weightDomain.WValue;
import domains.weightDomain.WeightedExpression;
import domains.weightDomain.WeightedExpressionVisitor;

import java.util.HashMap;

public class WExpressionExtension implements WeightedExpressionVisitor<BDDSet> {

    private BDDRel relation;
    private Assignment<Integer, WValue> ass;
    private final BDDRelEquationSystem<WValue> system;
    private final BDDRelUniverse u;
    private final HashMap<WeightedExpression, WValue> cacheValues;
    private final HashMap<Integer,BDDSet> cacheVars;
    private LazyEvaluator evaluator;

    public WExpressionExtension(BDDRelEquationSystem<WValue> system,
                                BDDRel relation,
                                Assignment<Integer, WValue> ass) {
        this.system = system;
        this.u = system.getUniverse();
        this.relation = relation;
        this.ass = ass;
        cacheVars = new HashMap<>();
        cacheValues = new HashMap<>();
        evaluator = new LazyEvaluator();
    }

    private WValue lazyeval(WeightedExpression expr){
        return evaluator.visit(expr);
    }

    private class LazyEvaluator implements WeightedExpressionVisitor<WValue>{

        @Override
        public WValue visit(WeightedExpression expr) {
            WValue val = cacheValues.get(expr);
            if(val != null){
                return val;
            }else{
                WValue value = WeightedExpressionVisitor.super.visit(expr);
                cacheValues.put(expr, value);
                return value;
            }
        }

        @Override
        public WValue visitInfinity(WeightedExpression.Infinity expr) {
            return WValue.infinity;
        }

        @Override
        public WValue visitNatural(WeightedExpression.Natural expr) {
            return expr.n;
        }

        @Override
        public WValue visitBound(WeightedExpression.Bound expr) {
            WValue val =  WValue.leq(visit(expr.expr), visit(expr.bound)) ? WValue.zero : WValue.infinity;
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
            return minval;
        }

        @Override
        public WValue visitAdd(WeightedExpression.Add expr) {
            WValue val = WValue.add(visit(expr.left),visit(expr.right));
            return val;
        }

        @Override
        public WValue visitVar(WeightedExpression.Var expr) {
            return WExpressionExtension.this.ass.getValue(expr.var);
        }
    }

    @Override
    public BDDSet visit(WeightedExpression expr) {
        WValue val = lazyeval(expr); // this call caches the evaluation of the subterms too
        if(val.equals(WValue.zero)){
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
            if(subret.isEmpty() && lazyeval(subExpr).equals(WValue.infinity)){
                return new BDDSet(u); // empty set
            }
            ret.unionWith(subret);
        }
        return ret;
    }

    @Override
    public BDDSet visitMin(WeightedExpression.Min expr) {
        for (WeightedExpression subExpr : expr.subExpr) {
            if(lazyeval(subExpr).equals(WValue.zero)){
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
        if(left.isEmpty() && lazyeval(expr.left).equals(WValue.infinity)){
            return new BDDSet(u); // empty set
        }
        BDDSet right = visit(expr.right);
        if(right.isEmpty() && lazyeval(expr.right).equals(WValue.infinity)){
            return new BDDSet(u); // empty set
        }
        left.unionWith(right);
        return left;
    }

    @Override
    public BDDSet visitVar(WeightedExpression.Var expr) {
        BDDSet val = cacheVars.get(expr.var);
        if(val == null){
            val = BDDSet.toBDDSet(relation.sliceLeftAt(expr.var));
            cacheVars.put(expr.var, val);
        }
        return val;
    }

    @Override
    public BDDSet visitInfinity(WeightedExpression.Infinity expr) {
        return new BDDSet(u); // empty set
    }

    @Override
    public BDDSet visitBound(WeightedExpression.Bound expr) {
        WValue e_val = lazyeval(expr.expr);
        if(e_val.equals(WValue.zero))
            return new BDDSet(u); // empty set

        WValue bound_val = lazyeval(expr.bound);
        BDDSet bound_dep = visit(expr.bound);
        if(WValue.leq(e_val,bound_val) && bound_dep.isEmpty()){
            return new BDDSet(u); // empty set
        }
        BDDSet depExpr = visit(expr.expr);
        bound_dep.unionWith(depExpr);
        return bound_dep;
    }
}
