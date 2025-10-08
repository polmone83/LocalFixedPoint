package bddRelations.groudness;

import bddRelations.BDDRel;
import com.github.javabdd.BDD;
import core.Assignment;
import core.RightHandSide;
import core.SimpleVarSet;
import domains.posDomain.groudness.groundSystemParser;
import domains.posDomain.groudness.groundSystemVisitor;

public class GroundessTerm implements RightHandSide<Integer,BDD, SimpleVarSet>{

    private groundSystemParser.ExprContext term;
    private GroundnessEquationSystem system;

    public GroundessTerm(groundSystemParser.ExprContext term,
                         GroundnessEquationSystem system){
        this.term = term;
        this.system = system;
    }

    @Override
    public BDD eval(Assignment<Integer, BDD> ass) {
        EvalGroundessExpression visitor = new EvalGroundessExpression(system,ass);
        return visitor.visit(term);
    }

    @Override
    public SimpleVarSet getVars() {
        BDDRelVarsVisitor visitor = new BDDRelVarsVisitor(system);
        return visitor.visit(this.term);
    }

    public <T> T accept(groundSystemVisitor<T> visitor){
        return term.accept(visitor);
    }
}
