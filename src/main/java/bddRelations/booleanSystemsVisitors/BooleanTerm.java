package bddRelations.booleanSystemsVisitors;

import bddRelations.BDDRel;
import bddRelations.BDDRelEquationSystem;
import bddRelations.termExtensionOracles.BoolFormulaExtension;
import core.*;
import domains.boolDomain.BoolFormula;
import domains.boolDomain.BoolTerm2BoolFormula;
import domains.boolDomain.EvalVisitor;
import domains.boolDomain.parser.BooleanSystemsParser;
import domains.boolDomain.parser.BooleanSystemsVisitor;

public class BooleanTerm implements RightHandSide<Integer,Boolean, SimpleVarSet>, TermExtension<Integer,Boolean,BDDRel,SimpleVarSet> {

    private BooleanSystemsParser.ExprContext term;
    private BDDRelEquationSystem<Boolean> system;

    public BooleanTerm(BooleanSystemsParser.ExprContext term,
                       BDDRelEquationSystem<Boolean> system){
        this.term = term;
        this.system = system;
    }

    @Override
    public Boolean eval(Assignment<Integer, Boolean> ass) {
        EvalVisitor<Integer,BDDRel,SimpleVarSet> visitor = new EvalVisitor<>(ass,system);
        return visitor.visit(term);
    }

    @Override
    public SimpleVarSet getVars() {
        BDDRelVarsVisitor visitor = new BDDRelVarsVisitor(system);
        return visitor.visit(this.term);
    }

    public <T> T accept(BooleanSystemsVisitor<T> visitor){
        return term.accept(visitor);
    }

    @Override
    public BDDRel evalExtension(EquationSystem<Integer, Boolean, BDDRel,SimpleVarSet> system, BDDRel relation, Assignment<Integer, Boolean> ass) {
        if(system instanceof BDDRelEquationSystem<Boolean>) {
            //BooleanTermExtension e = new BooleanTermExtension((BDDRelEquationSystem<Boolean>) system, relation, ass);
            BoolFormula phi = BoolTerm2BoolFormula.encode(term,(BDDRelEquationSystem<Boolean>) system);
            BoolFormulaExtension e = new BoolFormulaExtension((BDDRelEquationSystem<Boolean>) system, relation, ass);
            return e.visit(phi).toBDDRel();
        }
        return null;
    }
}
