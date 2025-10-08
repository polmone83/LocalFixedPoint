package bddRelations.booleanSystemsVisitors;

import bddRelations.BDDRelEquationSystem;
import bddRelations.BDDRelStaticEquationSystem;
import bddRelations.BDDRelUniverse;
import domains.boolDomain.parser.BooleanSystemsBaseVisitor;
import domains.boolDomain.parser.BooleanSystemsLexer;
import domains.boolDomain.parser.BooleanSystemsParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class ParsedBooleanSystem extends BDDRelStaticEquationSystem<Boolean> {

    @Override
    protected Boolean getBottomElement() {
        return false;
    }

    private class EquationsScanner extends  BooleanSystemsBaseVisitor<Boolean>{
        int nextVariable;
        @Override
        public Boolean visitSystem(BooleanSystemsParser.SystemContext ctx) {
            nextVariable = 0;
            // scan all equations
            for(BooleanSystemsParser.EquationContext eq : ctx.equation()){
                visit(eq);
                nextVariable++;
            }
            return true;
        }

        @Override
        public Boolean visitEquation(BooleanSystemsParser.EquationContext ctx) {
            String varName = ctx.ID().getText();
            addEquation(varName, nextVariable, new BooleanTerm(ctx.expr(),ParsedBooleanSystem.this));
            return true;
        }
    }

    public ParsedBooleanSystem(String code){
        this(code, false);
    }

    public ParsedBooleanSystem(String code, boolean fileFlag){
        try {
            /* Take parse the file and */
            CharStream input;
            if (fileFlag) {
                input = CharStreams.fromFileName(code);
            } else {
                input = CharStreams.fromString(code);
            }
            BooleanSystemsLexer lexer = new BooleanSystemsLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            BooleanSystemsParser parser = new BooleanSystemsParser(tokens);
            ParseTree tree = parser.system();
            EquationsScanner eqp = new EquationsScanner();
            tree.accept(eqp);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
