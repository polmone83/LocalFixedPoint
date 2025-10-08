package domains.boolDomain;

public interface BoolFormulaVisitor<T> {

    default T visit(BoolFormula phi) {
        return phi.accept(this);
    }

    T visitAnd(BoolFormula.And phi);
    T visitOr(BoolFormula.Or phi);
    T visitTrue(BoolFormula.True phi);
    T visitFalse(BoolFormula.False phi);
    T visitVar(BoolFormula.Var phi);
}
