package weighted_transitions_systems.WCTL;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.HashMap;

public class WCTLInterpreter {
    private HashMap<String,WCTLParser.FormulaContext> decls;

    public WCTLInterpreter(String path, boolean fileFlag){
        try {
            /* Parse the file */
            CharStream input = fileFlag? CharStreams.fromFileName(path) : CharStreams.fromString(path);
            WCTLLexer lexer = new WCTLLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            WCTLParser parser = new WCTLParser(tokens);
            ParseTree hml_decls = parser.decls();
            init(hml_decls);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void init(ParseTree wctl_decls){
        this.decls = new HashMap<>();
        WCTLDeclsVisitor visitor = new WCTLDeclsVisitor();
        visitor.visit(wctl_decls);
    }

    public WCTLParser.FormulaContext getFormula(String fName) {
        return decls.get(fName);
    }

    private class WCTLDeclsVisitor extends WCTLBaseVisitor<Boolean>{
        @Override
        public Boolean visitFormuladecl(WCTLParser.FormuladeclContext ctx) {
            decls.put(ctx.FID().getText(),ctx.formula());
            return true;
        }
    }
}
