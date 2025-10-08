package domains.boolDomain;

import java.util.HashSet;

public class BoolFormulaSimplify implements BoolFormulaVisitor<BoolFormula>{

    public static BoolFormula simplify(BoolFormula phi){
        BoolFormulaSimplify s = new BoolFormulaSimplify();
        return s.visit(phi);
    }

    private BoolFormulaSimplify(){
    }

    @Override
    public BoolFormula visitAnd(BoolFormula.And phi) {
        if(phi.subformulas.isEmpty()){
            return new BoolFormula.True(phi.system);
        }
        HashSet<BoolFormula> subFormulas = new HashSet<>();
        for (BoolFormula subformula : phi.subformulas) {
            BoolFormula psi = visit(subformula);
            if(psi instanceof BoolFormula.True){
                // skip
            }else if(psi instanceof BoolFormula.False){
                return psi; // return false
            }else if(psi instanceof BoolFormula.And){
                // flatten
                subFormulas.addAll(((BoolFormula.And) psi).subformulas);
            }else{
                subFormulas.add(psi);
            }
        }

        if(subFormulas.isEmpty()){
            return new BoolFormula.True(phi.system);
        }else if(subFormulas.size() == 1){
            return subFormulas.iterator().next();
        }
        phi.subformulas = subFormulas;
        return phi;
    }

    @Override
    public BoolFormula visitOr(BoolFormula.Or phi) {
        if(phi.subformulas.isEmpty()){
            return new BoolFormula.False(phi.system);
        }
        HashSet<BoolFormula> subFormulas = new HashSet<>();
        for (BoolFormula subformula : phi.subformulas) {
            BoolFormula psi = visit(subformula);
            if(psi instanceof BoolFormula.False){
                // skip
            }else if(psi instanceof BoolFormula.True){
                return psi; // return true
            }else if(psi instanceof BoolFormula.Or){
                // flatten
                subFormulas.addAll(((BoolFormula.Or) psi).subformulas);
            }else{
                subFormulas.add(psi);
            }
        }

        if(subFormulas.isEmpty()){
            return new BoolFormula.False(phi.system);
        }else if(subFormulas.size() == 1){
            return subFormulas.iterator().next();
        }
        phi.subformulas = subFormulas;
        return phi;
    }

    @Override
    public BoolFormula visitTrue(BoolFormula.True phi) {
        return phi;
    }

    @Override
    public BoolFormula visitFalse(BoolFormula.False phi) {
        return phi;
    }

    @Override
    public BoolFormula visitVar(BoolFormula.Var phi) {
        return phi;
    }
}
