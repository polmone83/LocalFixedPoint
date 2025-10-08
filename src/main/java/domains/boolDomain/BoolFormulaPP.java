package domains.boolDomain;

public class BoolFormulaPP implements BoolFormulaVisitor<StringBuilder> {
    private StringBuilder pp;

    public BoolFormulaPP() {
        pp = new StringBuilder();
    }

    @Override
    public StringBuilder visitAnd(BoolFormula.And phi) {
        if (phi.subformulas.isEmpty()) {
            pp.append("tt");
        } else {
            pp.append("(");
            for (BoolFormula subformula : phi.subformulas) {
                visit(subformula);
                pp.append(" & ");
            }
            pp.delete(pp.length()-3, pp.length());
            pp.append(")");
        }
        return pp;
    }

    @Override
    public StringBuilder visitOr(BoolFormula.Or phi) {
        if (phi.subformulas.isEmpty()) {
            pp.append("ff");
        } else {
            //pp.append("(");
            for (BoolFormula subformula : phi.subformulas) {
                visit(subformula);
                pp.append(" | ");
            }
            pp.delete(pp.length()-3, pp.length());
            //pp.append(")");
        }
        return pp;
    }

    @Override
    public StringBuilder visitTrue(BoolFormula.True phi) {
        pp.append("tt");
        return pp;
    }

    @Override
    public StringBuilder visitFalse(BoolFormula.False phi) {
        pp.append("ff");
        return pp;
    }

    @Override
    public StringBuilder visitVar(BoolFormula.Var phi) {
        pp.append("x"+phi.var);
        return pp;
    }
}
