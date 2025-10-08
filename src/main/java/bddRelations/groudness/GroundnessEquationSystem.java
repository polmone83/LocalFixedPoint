package bddRelations.groudness;

import bddRelations.BDDRel;
import bddRelations.BDDRelEquationSystem;
import bddRelations.BDDRelStaticEquationSystem;
import com.github.javabdd.BDD;
import com.github.javabdd.BDDFactory;
import core.RightHandSide;
import domains.posDomain.groudness.groundSystemBaseVisitor;
import domains.posDomain.groudness.groundSystemParser;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GroundnessEquationSystem extends BDDRelStaticEquationSystem<BDD> {

    /**
     * Counter for Pos variables
     */
    int varCounter;
    /**
     * Symbol table for Pos variables
     */
    private final Map<String, Integer> symbolTable;
    /**
     * Factory for the Pos formulas
     */
    private BDDFactory factory;

    private Set<Integer> varSet;

    public GroundnessEquationSystem() {
        this.varCounter = 0;
        this.symbolTable = new HashMap<>();
        factory = BDDFactory.init(1000, 1000);
        varSet = new HashSet<>();
    }

    @Override
    protected BDD getBottomElement() {
        return factory.zero();
    }

    private void addSymbol(String varName) {
        if (!symbolTable.containsKey(varName)) {
            symbolTable.put(varName, varCounter);
            varSet.add(varCounter);
            varCounter++;
        }
    }

    public BDD getPOSVar(String varName) {
        if (symbolTable.containsKey(varName)) {
            BDD var = factory.ithVar(symbolTable.get(varName));
            return var;
        } else {
            throw new RuntimeException("The variable" + varName + "does not appear in the system of equations.");
        }
    }

    public int getPOSVarIndex(String varName){
        if (symbolTable.containsKey(varName)) {
            return symbolTable.get(varName);
        } else {
            throw new RuntimeException("The variable" + varName + "does not appear in the system of equations.");
        }
    }

    public Set<Integer> getPosVarSet(){
        return varSet;
    }

    public void initBDD() {

        // initialise the BDDFactory for the interval variables
        PosVariableScanner visitor = new PosVariableScanner();
        varCounter = 0; // reset the counter
        for (int varIndex = 0; varIndex < this.varsCount(); varIndex++) {
            // rhs is a GroundessTerm
            GroundessTerm rhs = (GroundessTerm) getRHS(varIndex);
            rhs.accept(visitor); // collect the variables of this rhs in the symbol table
        }
        // at this point all variables have been added to the symbol table

        // add variables to the BDDFactory
        this.factory.extVarNum(symbolTable.size());
    }

    public BDDFactory getFactory() {
        return factory;
    }

    /**
     * This visitor scans the variables present in an expression and
     * collect them into the symbol table. Upon collection each
     * variable is assigned to a unique Integer identifier which will later
     * refer to its corresponding BDD.
     */
    private class PosVariableScanner extends groundSystemBaseVisitor<Boolean> {

        @Override
        public Boolean visitAnd(groundSystemParser.AndContext ctx) {
            visit(ctx.left);
            visit(ctx.right);
            return null;
        }

        @Override
        public Boolean visitOr(groundSystemParser.OrContext ctx) {
            visit(ctx.left);
            visit(ctx.right);
            return null;
        }

        @Override
        public Boolean visitIff(groundSystemParser.IffContext ctx) {
            visit(ctx.left);
            visit(ctx.right);
            return null;
        }

        @Override
        public Boolean visitVar(groundSystemParser.VarContext ctx) {
            addSymbol(ctx.VAR().getText());
            return null;
        }

        @Override
        public Boolean visitTrue(groundSystemParser.TrueContext ctx) {
            return null;
        }

        @Override
        public Boolean visitFalse(groundSystemParser.FalseContext ctx) {
            return null;
        }

        @Override
        public Boolean visitPredicate(groundSystemParser.PredicateContext ctx) {
            for (TerminalNode var : ctx.VAR()) {
                addSymbol(var.getText());
            }
            return null;
        }

        @Override
        public Boolean visitParens(groundSystemParser.ParensContext ctx) {
            visit(ctx.expr());
            return null;
        }

        @Override
        public Boolean visitCall(groundSystemParser.CallContext ctx) {
            visit(ctx.predicate());
            return null;
        }

        @Override
        public Boolean visitRestriction(groundSystemParser.RestrictionContext ctx) {
            visit(ctx.expr());
            for (TerminalNode var : ctx.VAR()) {
                addSymbol(var.getText());
            }
            return null;
        }
    }
}
