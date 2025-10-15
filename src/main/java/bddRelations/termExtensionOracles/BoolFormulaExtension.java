package bddRelations.termExtensionOracles;

import bddRelations.BDDRel;
import bddRelations.BDDRelEquationSystem;
import bddRelations.BDDRelUniverse;
import bddRelations.BDDSet;
import core.Assignment;
import domains.boolDomain.BoolFormula;
import domains.boolDomain.BoolFormulaVisitor;

public class BoolFormulaExtension implements BoolFormulaVisitor<BDDSet>{

    private final BDDRel relation;
    private final Assignment<Integer, Boolean> ass;
    private final BDDRelEquationSystem<Boolean> system;
    private final BDDRelUniverse u;

    public BoolFormulaExtension(BDDRelEquationSystem<Boolean> system,
                                BDDRel relation, Assignment<Integer,Boolean> ass) {
        this.ass = ass;
        this.relation = relation;
        this.system = system;
        this.u = system.getUniverse();
    }

    @Override
    public BDDSet visitAnd(BoolFormula.And phi) {
        if(phi.eval(ass)) {
            System.out.println("trigger");
            return new BDDSet(u);
        }

        BDDSet ret = new BDDSet(u); // empty set
        for (BoolFormula subformula : phi.subformulas) {
            BDDSet subret = visit(subformula);
            if(subret.isEmpty() && !subformula.eval(ass)){
                System.out.println("trigger");
                return new BDDSet(u); // empty set
            }
            ret.unionWith(subret);
        }
        return ret;
    }

    @Override
    public BDDSet visitOr(BoolFormula.Or phi) {
        if(phi.eval(ass)) {
            System.out.println("trigger");
            return new BDDSet(u); // empty set
        }

        BDDSet ret = new BDDSet(u); // empty set
        for (BoolFormula subformula : phi.subformulas) {
            ret.unionWith(visit(subformula));
        }
        return ret;
    }

    @Override
    public BDDSet visitTrue(BoolFormula.True phi) {
        return new BDDSet(u); // empty set
    }

    @Override
    public BDDSet visitFalse(BoolFormula.False phi) {
        return new BDDSet(u); // empty set
    }

    @Override
    public BDDSet visitVar(BoolFormula.Var phi) {
        if(phi.eval(ass)){
            System.out.println("trigger");
            return new BDDSet(u);
        }else{
            return BDDSet.toBDDSet(relation.sliceLeftAt(phi.var));
        }
    }

}
