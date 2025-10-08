package RegLangEquiv.MATA;

import RegLangEquiv.NFA.NFA_Interpreter;
import RegLangEquiv.NFAEquivContext_EquationSystem;
import RegLangEquiv.NFAEquiv_EquationSystem;
import RegLangEquiv.NFAEquiv_Interpreter;
import bddRelations.locallySoundOracles.SMax;

import java.util.HashSet;

public class NFA_MATATest {
    private static final String path = "/Users/giovbacci/Library/CloudStorage/OneDrive-AalborgUniversitet/AAU/Research/Tools/Dependecies/Experiments/MATA/";


    public static void main(String[] args) {
        //testEquivInterpreter();
        //testSingleInterpreter();
        testEquiv("true-IBakery5PUnrEnc-Rev-FbOneOne-Nondet-Partiali-B-1");
    }

    private static void testEquiv(String nfa){
        String file1 = path+nfa+"-lhs.mata";
        String file2 = path+nfa+"-rhs.mata";
        MATAEquiv_Interpreter interpreter =
                new MATAInclusion_Interpreter(file1, file2, true);

        NFAEquivContext_EquationSystem system = new NFAEquivContext_EquationSystem();
        //NFAEquiv_Interpreter interpreter = new NFA_Interpreter(editorArea.getText(),false);
        system.setInterpreter(interpreter);
        system.localSolve(new SMax<>(system,true));
        // append the log to the console
        System.out.println(system.getLog());
    }

    private static void testEquivInterpreter(){
        String file1 = path+"aut1.mata";
        String file2 = path+"aut1.mata";
        MATAEquiv_Interpreter interpreter =
                new MATAEquiv_Interpreter(file1, file2, true);

        System.out.println(interpreter.getCheckLeft());
        System.out.println(interpreter.getCheckRight());

        HashSet<String> states = new HashSet<>();
        states.add("bq1");
        states.add("aq2");
        System.out.println(interpreter.getSuccessors(states, "01011"));
        System.out.println(interpreter.isFinal(states));

    }

    private static void testSingleInterpreter() {
        MATA_Interpreter interpreter =
                new MATA_Interpreter(path+"false-Bakery4pBinEnc-FbOneOne-Nondet-Partiali-B-3-rhs.mata", true);

        System.out.println(interpreter.isFinal("q0"));
        System.out.println(interpreter.isFinal("q1"));

        // q83 (!a1 & a2 & a3 & a4 & a5) q82

        HashSet<String> states = new HashSet<>();
        states.add("q83");
        states.add("q1");
        System.out.println(interpreter.getSuccessors(states, "01111"));
        System.out.println(interpreter.isFinal(states));

        HashSet<String> states1 = interpreter.getInitialStates();
        System.out.println(states1);

        System.out.println(interpreter.getSuccessors(states1, "111111"));
        System.out.println(interpreter.getSuccessors(states1, "101100"));
    }


}
