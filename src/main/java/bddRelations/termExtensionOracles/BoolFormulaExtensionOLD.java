package bddRelations.termExtensionOracles;

import bddRelations.BDDRel;
import bddRelations.BDDRelEquationSystem;
import bddRelations.BDDRelUniverse;
import core.Assignment;
import core.SimpleVarSet;
import domains.boolDomain.BoolFormula;
import domains.boolDomain.BoolFormulaVisitor;

public class BoolFormulaExtensionOLD implements BoolFormulaVisitor<SimpleVarSet>{

    private final BDDRel relation;
    private final Assignment<Integer, Boolean> ass;
    private final BDDRelEquationSystem<Boolean> system;
    private final BDDRelUniverse u;

    public BoolFormulaExtensionOLD(BDDRelEquationSystem<Boolean> system,
                                   BDDRel relation, Assignment<Integer,Boolean> ass) {
        this.ass = ass;
        this.relation = relation;
        this.system = system;
        this.u = system.getUniverse();
    }

    @Override
    public SimpleVarSet visitAnd(BoolFormula.And phi) {
        SimpleVarSet ret = new SimpleVarSet(); // empty set
        for (BoolFormula subformula : phi.subformulas) {
            SimpleVarSet subret = visit(subformula);
            if(subret.isEmpty() && subformula.eval(ass).equals(false)){
                return new SimpleVarSet(); // empty set
            }
            ret.unionWith(subret);
        }
        return ret;
    }

    @Override
    public SimpleVarSet visitOr(BoolFormula.Or phi) {
        for (BoolFormula subformula : phi.subformulas) {
            if(subformula.eval(ass).equals(true)){
                return new SimpleVarSet(); // empty set
            }
        }

        SimpleVarSet ret = new SimpleVarSet(); // empty set
        for (BoolFormula subformula : phi.subformulas) {
            ret.unionWith(visit(subformula));
        }
        return ret;
    }

    @Override
    public SimpleVarSet visitTrue(BoolFormula.True phi) {
        return new SimpleVarSet(); // empty set
    }

    @Override
    public SimpleVarSet visitFalse(BoolFormula.False phi) {
        return new SimpleVarSet(); // empty set
    }

    @Override
    public SimpleVarSet visitVar(BoolFormula.Var phi) {
        //SimpleVarSet sigleton = new SimpleVarSet(); // empty set
        //sigleton.add(phi.var);
        return relation.sliceLeft(phi.var);
    }

}
