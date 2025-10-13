package domains.weightDomain;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;

public class WESimplify implements WeightedExpressionVisitor<WeightedExpression>{
    
    public static WeightedExpression simplify(WeightedExpression expr){
        WESimplify s = new WESimplify();
        return s.visit(expr);
    }

    @Override
    public WeightedExpression visitNatural(WeightedExpression.Natural expr) {
        return expr;
    }

    @Override
    public WeightedExpression visitInfinity(WeightedExpression.Infinity expr) {
        return expr;
    }

    @Override
    public WeightedExpression visitVar(WeightedExpression.Var expr) {
        return expr;
    }

    @Override
    public WeightedExpression visitAdd(WeightedExpression.Add expr) {
        
        WeightedExpression left = visit(expr.left);
        WeightedExpression right = visit(expr.right);
        
        if(isZero(left)) return right;
        if(isZero(right)) return left;
        if(isInfinity(left) || isInfinity(right))
            return new WeightedExpression.Infinity(expr.system);

        return new WeightedExpression.Add(left,right, expr.system);
    }

    private static boolean isZero(WeightedExpression expression) {
        return expression instanceof WeightedExpression.Natural nleft && nleft.n.getVal() == 0;
    }

    private static boolean isInfinity(WeightedExpression expression) {
        return expression instanceof WeightedExpression.Infinity;
    }

    @Override
    public WeightedExpression visitBound(WeightedExpression.Bound expr) {
        WeightedExpression bound = visit(expr.bound);
        WeightedExpression subexpr = visit(expr.expr);

        if(isInfinity(bound))
            return visit(expr.expr);
        if(isZero(bound) || isZero(subexpr))
            return new WeightedExpression.Natural(0, expr.system);

        return new WeightedExpression.Bound(bound, subexpr, expr.system);
    }

    @Override
    public WeightedExpression visitMax(WeightedExpression.Max expr) {
        HashSet<WeightedExpression> subexprs = new HashSet<>();
        for (WeightedExpression subexp : expr.subExpr) {
            subexprs.add(visit(subexp));
        }
        return new WeightedExpression.Max(subexprs,expr.system);
    }

    @Override
    public WeightedExpression visitMin(WeightedExpression.Min expr) {
        HashSet<WeightedExpression> subexprs = new HashSet<>();
        for (WeightedExpression subexp : expr.subExpr) {
            subexp = visit(subexp);
            if(isZero(subexp))
                return new WeightedExpression.Natural(0, expr.system);
            if(! isInfinity(subexp))
                subexprs.add(subexp);
        }
        return new WeightedExpression.Min(subexprs,expr.system);
    }
}
