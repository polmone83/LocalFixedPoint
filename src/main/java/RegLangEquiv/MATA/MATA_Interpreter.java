package RegLangEquiv.MATA;

import com.github.javabdd.BDD;
import com.github.javabdd.BDDFactory;
import com.github.javabdd.BDDVarSet;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class MATA_Interpreter {

    private HashMap<String,HashMap<String,HashSet<String>>> t;
    private HashSet<String> finalStates; // final states
    private HashSet<String> initialStates;

    private HashSet<String> stateSpace;

    /* Maps the names to represent bits to the corresponding bdd variable */
    private HashMap<String, Integer> bits;
    private BDDFactory universe;
    private BDDVarSet varSet;

    public MATA_Interpreter(String code, Boolean fileFlag){

        try {
            /* Parse the source code */
            CharStream input;
            if(fileFlag){
                input = CharStreams.fromFileName(code);
            }else{
                input = CharStreams.fromString(code);
            }
            MATALexer lexer = new MATALexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            MATAParser parser = new MATAParser(tokens);
            ParseTree parseTree = parser.automaton();

            universe = BDDFactory.init(1000,1000);
            bits = new HashMap();
            stateSpace = new HashSet();
            finalStates = new HashSet();
            initialStates = new HashSet<>();
            BitScanner bs = new BitScanner();
            bs.visit(parseTree);
            int[] vars = new int[universe.varNum()];
            for(int v = 0; v < universe.varNum(); v ++){
                vars[v] = v;
            }
            varSet = universe.makeSet(vars);

            // Construct the RegLangEquiv.NFA and the query
            t = new HashMap();

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

    public HashSet<String> getSuccessors(HashSet<String> sources, String label){
        HashSet<String> successors = new HashSet<>();
        for (String source : sources) {
            successors.addAll(getSuccessors(source, label));
        }
        return successors;
    }

    public int getStatesNumber() {
        return stateSpace.size();
    }

    public Boolean isFinal(String state){
        return finalStates.contains(state);
    }

    public Boolean isFinal(HashSet<String> states){
        for (String state : states) {
            if(isFinal(state)) return true;
        }
        return false;
    }

    public HashSet<String> getInitialStates() {
        return initialStates;
    }

    /**
     * This visitor scan all transitions to determine how many bits
     * are used to represent the alphabet.
     */
    private class BitScanner extends MATABaseVisitor<Boolean>{
        private boolean visitedFinal = false;
        private boolean visitedInitial = false;

        @Override
        public Boolean visitFinal(MATAParser.FinalContext ctx) {
            if(visitedFinal)
                throw new RuntimeException("Multiple occurrences of the %Final");

            visit(ctx.states());
            for (TerminalNode state : ctx.states().STATE()) {
                String stateEnc = state.getText();
                finalStates.add(stateEnc);
            }
            visitedFinal = true;
            return true;
        }

        @Override
        public Boolean visitInitital(MATAParser.InititalContext ctx) {
            if(visitedInitial)
                throw new RuntimeException("Multiple occurrences of %Intial");

            visit(ctx.states());
            for (TerminalNode state : ctx.states().STATE()) {
                String stateEnc = state.getText();
                initialStates.add(stateEnc);
            }
            visitedInitial = true;
            return true;
        }


        /*@Override
        public Boolean visitTransition(MATAParser.TransitionContext ctx) {
            visit(ctx.expr());
            return null;
        }*/

        @Override
        public Boolean visitStates(MATAParser.StatesContext ctx) {
            for (TerminalNode state : ctx.STATE()) {
                stateSpace.add(state.getText());
            }
            return true;
        }

        @Override
        public Boolean visitVar(MATAParser.VarContext ctx) {
            String bit = ctx.SYMBOL().getText();
            Integer bitnum = bits.get(bit);
            if(bitnum == null){
                bitnum = universe.extVarNum(1);
                bits.put(bit,bitnum);
            }
            return null;
        }
    }


    /**
     * This visitor construct a BDD representing the language
     * of bitvectors encoded by a transition expression
     */
    private class SymbolsSet extends MATABaseVisitor<BDD>{

        @Override
        public BDD visitParens(MATAParser.ParensContext ctx) {
            return visit(ctx.expr());
        }

        @Override
        public BDD visitAnd(MATAParser.AndContext ctx) {
            return visit(ctx.left).andWith(visit(ctx.right));
        }

        @Override
        public BDD visitOr(MATAParser.OrContext ctx) {
            return visit(ctx.left).orWith(visit(ctx.right));
        }

        @Override
        public BDD visitNeg(MATAParser.NegContext ctx) {
            return visit(ctx.expr()).not();
        }

        @Override
        public BDD visitVar(MATAParser.VarContext ctx) {
            String bit = ctx.SYMBOL().getText();
            Integer bitnum = bits.get(bit);
            return universe.ithVar(bitnum);
        }
    }

    private class NFABuilder extends MATABaseVisitor<Boolean>{

        @Override
        public Boolean visitTransition(MATAParser.TransitionContext ctx) {
            HashSet<String> targetStates = getStateSet(ctx.to);;

            // construct the target set
            SymbolsSet ss = new SymbolsSet();
            BDD symbols = ss.visit(ctx.expr());

            // populate the transition function
            for (TerminalNode fromStateID : ctx.from.STATE()) {
                String fromState = fromStateID.getText();
                HashMap<String, HashSet<String>> fromT =
                        t.getOrDefault(fromState, new HashMap<>());

                BDD.BDDIterator iter = symbols.iterator(varSet);
                while (iter.hasNext()){
                    String bitvecLabel = bitvec2string(iter.nextSat());
                    HashSet<String> toLabel = fromT.getOrDefault(bitvecLabel,new HashSet());
                    toLabel.addAll(targetStates);
                    fromT.put(bitvecLabel,toLabel);
                }
                t.put(fromState, fromT);
            }
            return true;
        }

        private HashSet<String> getStateSet(MATAParser.StatesContext states) {
            HashSet<String> ret = new HashSet<>();
            for (TerminalNode stateID : states.STATE()) {
                ret.add(stateID.getText());
            }
            return ret;
        }
    }

    private static String bitvec2string(boolean[] bitvecLabel){
        StringBuilder s = new StringBuilder();
        for(int bit = 0; bit < bitvecLabel.length; bit++){
            s.append(bitvecLabel[bit] ? "1" : "0");
        }
        return s.toString();
    }

    public Iterator<String> alphabet(){
        BDD.BDDIterator iter = universe.one().iterator(varSet);
        return new Iterator<String>() {
            @Override
            public boolean hasNext() {
                return iter.hasNext();
            }

            @Override
            public String next() {
                return bitvec2string(iter.nextSat());
            }
        };
    }

}
