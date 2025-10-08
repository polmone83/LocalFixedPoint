package RegLangEquiv.NFA;

import RegLangEquiv.NFAEquiv_Interpreter;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class NFA_Interpreter implements NFAEquiv_Interpreter {

    public enum Query { EQUIVALENCE, INCLUSION, CONTAINMENT }

    private HashMap<String,HashMap<String,HashSet<String>>> t;
    private HashSet<String> o; // final states
    private HashSet<String> checkLeft; // left dfa state
    private HashSet<String> checkRight; // right dfa state
    private Query query;
    private HashSet<String> alphabet;

    public NFA_Interpreter(String code,Boolean fileFlag){

        try {
            /* Parse the source code */
            CharStream input;
            if(fileFlag){
                input = CharStreams.fromFileName(code);
            }else{
                input = CharStreams.fromString(code);
            }
            NFALexer lexer = new NFALexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            NFAParser parser = new NFAParser(tokens);
            ParseTree parseTree = parser.automaton();

            // Construct the RegLangEquiv.NFA and the query
            t = new HashMap();
            o = new HashSet();
            alphabet = new HashSet();

            NFABuilder nfaBuilder = new NFABuilder();
            nfaBuilder.visit(parseTree);

        }catch (Exception e){
            e.printStackTrace();
        }
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
        return o.contains(state);
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

    public Query getQuery() {
        return query;
    }

    private class NFABuilder extends NFABaseVisitor<Boolean>{

        @Override
        public Boolean visitAccept(NFAParser.AcceptContext ctx) {
            // add the final states
            for (TerminalNode stateID : ctx.states().ID()) {
                o.add(stateID.getText());
            }
            return true;
        }

        @Override
        public Boolean visitTransition(NFAParser.TransitionContext ctx) {
            // construct the target set
            HashSet<String> targetStates = getStateSet(ctx.to);

            // populate the transition function
            for (TerminalNode fromStateID : ctx.from.ID()) {
                String fromState = fromStateID.getText();
                HashMap<String, HashSet<String>> fromT =
                        t.getOrDefault(fromState, new HashMap<>());

                for (TerminalNode labelID : ctx.labels().ID()) {
                    String label = labelID.getText();
                    alphabet.add(label);
                    HashSet<String> toLabel = fromT.getOrDefault(label,new HashSet());
                    toLabel.addAll(targetStates);
                    fromT.put(label,toLabel);
                }

                t.put(fromState, fromT);
            }
            return true;
        }

        @Override
        public Boolean visitEquivalence(NFAParser.EquivalenceContext ctx) {
            query = Query.EQUIVALENCE;
            checkLeft = getStateSet(ctx.states(0));
            checkRight = getStateSet(ctx.states(1));
            return true;
        }

        @Override
        public Boolean visitInclusion(NFAParser.InclusionContext ctx) {
            query = Query.INCLUSION;
            // X <= Y iff X+Y = Y
            checkRight = getStateSet(ctx.states(1));
            checkLeft = getStateSet(ctx.states(0));
            checkLeft.addAll(checkRight);
            return true;
        }

        @Override
        public Boolean visitContainment(NFAParser.ContainmentContext ctx) {
            query = Query.CONTAINMENT;
            // X => Y iff X = X+Y
            checkLeft = getStateSet(ctx.states(0));
            checkRight = getStateSet(ctx.states(1));
            checkRight.addAll(checkLeft);
            return true;
        }

        private HashSet<String> getStateSet(NFAParser.StatesContext states) {
            HashSet<String> ret = new HashSet<>();
            for (TerminalNode stateID : states.ID()) {
                ret.add(stateID.getText());
            }
            return ret;
        }
    }

}
