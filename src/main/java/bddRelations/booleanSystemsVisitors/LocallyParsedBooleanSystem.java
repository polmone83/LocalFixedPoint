package bddRelations.booleanSystemsVisitors;

import bddRelations.BDDRelEquationSystem;
import core.RightHandSide;
import core.SimpleVarSet;
import domains.boolDomain.parser.BooleanSystemsBaseVisitor;
import domains.boolDomain.parser.BooleanSystemsLexer;
import domains.boolDomain.parser.BooleanSystemsParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;

public class LocallyParsedBooleanSystem extends BDDRelEquationSystem<Boolean> {

    private ArrayList<BooleanTerm> equations;
    private ArrayList<String> indices;

    @Override
    protected Boolean getBottomElement() {
        return false;
    }

    @Override
    public RightHandSide<Integer, Boolean, SimpleVarSet> getRHS(Integer x) {
        return equations.get(x);
    }

    @Override
    public Integer getIndex(String varName) {
        return indices.indexOf(varName);
    }

    @Override
    public String getLabel(Integer x) {
        return indices.get(x);
    }

    private class EquationsScanner extends  BooleanSystemsBaseVisitor<Boolean>{

        @Override
        public Boolean visitSystem(BooleanSystemsParser.SystemContext ctx) {
            // scan all equations
            for(BooleanSystemsParser.EquationContext eq : ctx.equation()){
                visit(eq);
            }
            return true;
        }

        @Override
        public Boolean visitEquation(BooleanSystemsParser.EquationContext ctx) {
            String varName = ctx.ID().getText();
            BooleanTerm tx = new BooleanTerm(ctx.expr(), LocallyParsedBooleanSystem.this);
            equations.add(tx);
            indices.add(varName);
            return true;
        }


    }

    public LocallyParsedBooleanSystem(String code){
        indices = new ArrayList<>();
        equations = new ArrayList<>();

        CharStream input = CharStreams.fromString(code);
        BooleanSystemsLexer lexer = new BooleanSystemsLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        BooleanSystemsParser parser = new BooleanSystemsParser(tokens);
        ParseTree tree = parser.system();

        EquationsScanner eqp = new EquationsScanner();
        tree.accept(eqp);
    }
}
