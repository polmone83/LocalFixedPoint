package bddRelations.groudness;
import bddRelations.BDDRelUniverse;
import domains.posDomain.groudness.groundSystemBaseVisitor;
import domains.posDomain.groudness.groundSystemLexer;
import domains.posDomain.groudness.groundSystemParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class GroundSystemBuilder {

    GroundnessEquationSystem system;

    private class EquationScanner extends groundSystemBaseVisitor<Integer>{
        /**
         * Equations counter
         */
        int nextVar;

        @Override
        public Integer visitSystem(groundSystemParser.SystemContext ctx) {
            nextVar = 0;
            for (groundSystemParser.EquationContext equation : ctx.equation()) {
                visit(equation);
                nextVar++;
            }
            return nextVar;
        }

        @Override
        public Integer visitEquation(groundSystemParser.EquationContext ctx) {
            PrologPredicate p =
                    new PrologPredicate(
                            ctx.predicate().ID().getText(),
                            ctx.predicate().VAR().size());
            system.addEquation(p.getSignature(),nextVar,new GroundessTerm(ctx.expr(), system));
            return nextVar;
        }
    }

    public GroundSystemBuilder(String source, boolean isFilepath){
        // construct the equation system
        this.system = new GroundnessEquationSystem();
        // parse the equation system
        CharStream input;
        if (isFilepath) { // source refers to a filepath
            try {
                input = CharStreams.fromFileName(source);
            } catch (Exception e) {
                throw new RuntimeException("file not found");
            }
        } else { // code is the prolog program
            input = CharStreams.fromString(source);
        }
        groundSystemLexer lexer = new groundSystemLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        groundSystemParser parser = new groundSystemParser(tokens);
        ParseTree tree = parser.system();

        // populate the equation system
        EquationScanner scanner = new EquationScanner();
        scanner.visit(tree);

        // initialize system
        init();
        system.initBDD();
    }

    /**
     * Initialize the universe to accommodate relations included in V x V
     * where |V| = this.varsCount() (see {@link this.varsCount})
     */
    private void init() {
        int varsCount = system.varsCount();
        //system.initUniverse(new BDDRelUniverse(varsCount,varsCount));
    }

    public GroundnessEquationSystem getSystem() {
        return system;
    }
}
