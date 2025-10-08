package labeled_transitions_systems.CCS;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.*;

public class CCSInterpreter {

    private HashMap<String,CCSParser.ProcessContext> procesDecl;
    private HashMap<String,CCSProcess> procesDef;
    private HashMap<String, CCSParser.ChannelSetContext> channelSetDecl;
    private HashMap<String, Set<String>> channelSet;

    public CCSInterpreter(String code,Boolean fileFlag){
        try {
            /* Take parse the file and */
            CharStream input;
            if(fileFlag){
                input = CharStreams.fromFileName(code);
            }else{
                input = CharStreams.fromString(code);
            }

            CCSLexer lexer = new CCSLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            CCSParser parser = new CCSParser(tokens);
            ParseTree ccs_system = parser.system();
            init(ccs_system);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public CCSInterpreter(ParseTree ccs_system) {
        init(ccs_system);
    }

    private void init(ParseTree ccs_system){
        this.procesDecl = new HashMap<>();
        this.procesDef = new HashMap<>();
        this.channelSetDecl = new HashMap<>();
        this.channelSet = new HashMap<>();

        // visit the declarations and populate the maps
        DeclVisitor declVisitor = new DeclVisitor();
        declVisitor.visit(ccs_system);
    }

    public CCSProcess getProcess(String pName){
        CCSProcess p = procesDef.get(pName);
        if(p == null) {
            CCSProcessBuilder astBuilder = new CCSProcessBuilder(this);
            p = astBuilder.visit(procesDecl.get(pName));
            CCSProcessNormalForm2 nf2 = new CCSProcessNormalForm2();
            CCSProcessNormalForm1 nf = new CCSProcessNormalForm1();
            p = nf2.visit(p);
            p = nf.visit(p); // put p in normal form
            procesDef.put(pName,p); // store the process definition
        }
        return p;
    }

    public Set<String> getChannelSet(String setID){
        Set<String> chanSet = channelSet.get(setID);
        if(chanSet == null){
            chanSet = new TreeSet<>();
            for (TerminalNode chan : channelSetDecl.get(setID).CHANNEL()) {
                chanSet.add(chan.getText());
            }
            channelSet.put(setID,chanSet);
        }
        return chanSet;
    }

    private class DeclVisitor extends CCSBaseVisitor<Boolean>{

        @Override
        public Boolean visitSystem(CCSParser.SystemContext ctx) {
            for (CCSParser.PDeclContext pDeclContext : ctx.pDecl()) {
                visit(pDeclContext);
            }

            for (CCSParser.SetDeclContext setDeclContext : ctx.setDecl()) {
                visit(setDeclContext);
            }
            return true;
        }

        @Override
        public Boolean visitSetDecl(CCSParser.SetDeclContext ctx) {
            channelSetDecl.put(ctx.ID().getText(),ctx.channelSet());
            return true;
        }

        @Override
        public Boolean visitPDecl(CCSParser.PDeclContext ctx) {
            procesDecl.put(ctx.ID().getText(), ctx.process());
            return true;
        }
    }

}
