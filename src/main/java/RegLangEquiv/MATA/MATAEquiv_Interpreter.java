package RegLangEquiv.MATA;

import RegLangEquiv.NFAEquiv_Interpreter;

import java.util.HashSet;
import java.util.Iterator;

public class MATAEquiv_Interpreter implements NFAEquiv_Interpreter {
    private MATA_Interpreter interpreter1;
    private MATA_Interpreter interpreter2;

    private static final String LEFT = "a";
    private static final String RIGHT = "b";

    public MATAEquiv_Interpreter(String code1, String code2, boolean fileFlag){
        interpreter1 = new MATA_Interpreter(code1, fileFlag);
        interpreter2 = new MATA_Interpreter(code2, fileFlag);
    }


    @Override
    public HashSet<String> getCheckLeft() {
        HashSet<String> initStates = new HashSet();
        for (String state : interpreter1.getInitialStates()) {
            initStates.add(LEFT + state);
        }
        return initStates;
    }

    @Override
    public HashSet<String> getCheckRight() {
        HashSet<String> initStates = new HashSet();
        for (String state : interpreter2.getInitialStates()) {
            initStates.add(RIGHT + state);
        }
        return initStates;
    }

    @Override
    public Boolean isFinal(HashSet<String> states) {
        MATA_Interpreter interpreter;
        for (String state : states) {
            interpreter = state.startsWith(LEFT) ? interpreter1 : interpreter2;
            if(interpreter.isFinal(state.substring(1)))
                return true;
        }
        return false;
    }

    @Override
    public HashSet<String> getSuccessors(HashSet<String> states, String symbol) {
        // TODO: successors should be cached
        HashSet<String> successors = new HashSet<>();
        MATA_Interpreter interpreter;
        String prefix;
        for (String state : states) {
            prefix = state.startsWith(LEFT) ? LEFT : RIGHT;
            interpreter = prefix.equals(LEFT) ? interpreter1 : interpreter2;
            for (String successor : interpreter.getSuccessors(state.substring(1), symbol)) {
                successors.add(prefix + successor);
            }
        }
        return successors;
    }

    @Override
    public Iterator<String> alphabetIterator() {
        // Remark: it is assumed interpreter1.alphabet() = interpreter2.alphabet()
        return interpreter1.alphabet();
    }
}
