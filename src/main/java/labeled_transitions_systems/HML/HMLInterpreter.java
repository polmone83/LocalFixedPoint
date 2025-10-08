package labeled_transitions_systems.HML;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.HashMap;

/**
 * HMLInterpreter stores and serves the declarations of a system
 * of recursive HML formulas.
 */
public class HMLInterpreter {
    private HashMap<String, HMLParser.FormulaDeclContext> decls;
    private HashMap<String, HMLParser.ActionsContext> channelSetDecl;

    /**
     * Construct an interpreter for a system of recursive HML formulas from a file
     * @param path the file path
     */
    public HMLInterpreter(String path, boolean fileFlag){
        try {
            /* Parse the file */
            CharStream input = fileFlag? CharStreams.fromFileName(path) : CharStreams.fromString(path);
            HMLLexer lexer = new HMLLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            HMLParser parser = new HMLParser(tokens);
            ParseTree hml_decls = parser.decls();
            init(hml_decls);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void init(ParseTree hml_decls){
        this.decls = new HashMap<>();
        this.channelSetDecl = new HashMap<>();

        // visit the declarations and populate the maps
        HMLDeclsVisitor declVisitor = new HMLDeclsVisitor();
        declVisitor.visit(hml_decls);
    }

    /**
     * Returns the HML formula definition associated with the given name
     * @param name the name of a formula in the system
     * @return the formula associated to the given name
     */
    public HMLParser.FormulaDeclContext getFormulaDef(String name){
        return decls.get(name);
    }

    /**
     * Returns the actions associated with the given name
     * @param name the name of a set of actions in the system
     * @return the set of actions associated to the given name
     */
    public HMLParser.ActionsContext getActionsDef(String name){
        return channelSetDecl.get(name);
    }

    private class HMLDeclsVisitor extends HMLBaseVisitor<Boolean>{
        @Override
        public Boolean visitDecls(HMLParser.DeclsContext ctx) {
            for (HMLParser.SetDeclContext setDeclContext : ctx.setDecl()) {
                visit(setDeclContext);
            }
            for (HMLParser.FormulaDeclContext formulaDeclContext : ctx.formulaDecl()) {
                visit(formulaDeclContext);
            }
            return true;
        }

        @Override
        public Boolean visitMinDecl(HMLParser.MinDeclContext ctx) {
            decls.put(ctx.ID().getText(), ctx);
            return true;
        }

        @Override
        public Boolean visitMaxDecl(HMLParser.MaxDeclContext ctx) {
            decls.put(ctx.ID().getText(), ctx);
            return true;
        }

        @Override
        public Boolean visitSetDecl(HMLParser.SetDeclContext ctx) {
            channelSetDecl.put(ctx.ID().getText(),ctx.actions());
            return true;
        }
    }
}
