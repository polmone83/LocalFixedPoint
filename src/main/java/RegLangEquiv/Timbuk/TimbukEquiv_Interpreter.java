package RegLangEquiv.Timbuk;

import RegLangEquiv.NFAEquiv_Interpreter;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class TimbukEquiv_Interpreter implements NFAEquiv_Interpreter {

    protected final HashMap<String,HashMap<String,HashSet<String>>> t;
    protected final HashSet<String> finalstates; // final states
    protected final HashSet<String> checkLeft; // left dfa state
    protected final HashSet<String> checkRight; // right dfa state
    protected final HashSet<String> alphabet;

    public TimbukEquiv_Interpreter(){
        // Construct the NFA and the query
        t = new HashMap();
        finalstates = new HashSet();
        alphabet = new HashSet();
        checkLeft = new HashSet<>();
        checkRight = new HashSet<>();
    }

    public void construct(String leftNFA, String rightNFA, Boolean fileFlag) {
        // Construct the NFA and the query
        NFABuilder nfaBuilder = new NFABuilder();

        try {
            ParseTree parseTree = getTimbukParseTree(leftNFA, fileFlag);
            nfaBuilder.prefix = "l";
            checkLeft.add(nfaBuilder.getInitState());
            nfaBuilder.visit(parseTree);

            parseTree = getTimbukParseTree(rightNFA, fileFlag);
            nfaBuilder.prefix = "r";
            checkRight.add(nfaBuilder.getInitState());
            nfaBuilder.visit(parseTree);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    protected ParseTree getTimbukParseTree(String code, Boolean fileFlag) throws Exception{
        /* Parse the source code */
        CharStream input;
        if (fileFlag) {
            input = CharStreams.fromFileName(code);
        } else {
            input = CharStreams.fromString(code);
        }

        TimbukLexer lexer = new TimbukLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        TimbukParser parser = new TimbukParser(tokens);

        return parser.ops();
    }

    /* Methods to extend the transition function and the observer */

    public HashSet<String> getSuccessors(String source, String label){
        HashMap<String, HashSet<String>> trans = t.get(source);
        if(trans != null){
            return trans.getOrDefault(label, new HashSet());
        }
        return new HashSet();
    }

    @Override
    public HashSet<String> getSuccessors(HashSet<String> sources, String label){
        HashSet<String> successors = new HashSet<>();
        for (String source : sources) {
            successors.addAll(getSuccessors(source, label));
        }
        return successors;
    }

    @Override
    public Iterator<String> alphabetIterator() {
        return alphabet.iterator();
    }

    public Boolean isFinal(String state){
        return finalstates.contains(state);
    }

    @Override
    public Boolean isFinal(HashSet<String> states){
        for (String state : states) {
            if(isFinal(state)){
                return true;
            }
        }
        return false;
    }

    @Override
    public HashSet<String> getCheckLeft() {
        return checkLeft;
    }

    @Override
    public HashSet<String> getCheckRight() {
        return checkRight;
    }

    protected class NFABuilder extends TimbukBaseVisitor<Boolean>{

        String prefix;

        @Override
        public Boolean visitLabeldecl(TimbukParser.LabeldeclContext ctx) {
            alphabet.add(ctx.ID().getText());
            return true;
        }

        @Override
        public Boolean visitFinalstates(TimbukParser.FinalstatesContext ctx) {
            for (TerminalNode state : ctx.ID()) {
                finalstates.add(prefix + state.getText());
            }
            return true;
        }

        @Override
        public Boolean visitTransition(TimbukParser.TransitionContext ctx) {
            // check the format
            if(ctx.ID().size() > 3)
                throw new RuntimeException("The transition system is not in the expected format");

            String currentState;
            if(ctx.ID().size() == 2){
                /* transition --label--> nextState */
                currentState = getInitState();
            }else{
                // transition: currentState --label--> nextState
                currentState = prefix + ctx.ID(1).getText();
            }

            // retrieve transitions from currentState
            HashMap<String,HashSet<String>> stateTransitions =
                    t.getOrDefault(currentState, new HashMap<>());

            String label = ctx.label.getText(); // the label for the transition
            String nextState = prefix + ctx.state.getText(); // the next state

            /* retrieve the next states collected so far for
                the transition currentState --label-->
             */
            HashSet<String> nextStates =
                    stateTransitions.getOrDefault(label,new HashSet<>());
            nextStates.add(nextState);
            stateTransitions.put(label,nextStates); // add the nextState

            t.put(currentState, stateTransitions); // update the transitions for current state

            return true;
        }

        public String getInitState() {
            return this.prefix;
        }
    }
}
