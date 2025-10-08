package domains.weightDomain;

public interface WeightedExpressionVisitor<T> {

    default T visit(WeightedExpression expr) {
        return expr.accept(this);
    }

    //todo add the other visit methods
    T visitNatural(WeightedExpression.Natural expr);
    T visitInfinity(WeightedExpression.Infinity expr);
    T visitVar(WeightedExpression.Var expr);
    T visitAdd(WeightedExpression.Add expr);
    T visitBound(WeightedExpression.Bound expr);
    T visitMax(WeightedExpression.Max expr);
    T visitMin(WeightedExpression.Min expr);

}
