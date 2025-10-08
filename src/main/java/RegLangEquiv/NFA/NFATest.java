package RegLangEquiv.NFA;

import java.util.HashSet;

public class NFATest {
    private static final String path = "/Users/giovbacci/Library/CloudStorage/OneDrive-AalborgUniversitet/AAU/Research/Tools/Dependecies/Experiments/NFA/";


    public static void main(String[] args) {

        NFA_Interpreter interpreter =
                new NFA_Interpreter(path+"test.nfa", true);

        System.out.println(interpreter.isFinal("y"));
        System.out.println(interpreter.isFinal("z"));

        HashSet<String> states = new HashSet<>();
        states.add("y");
        states.add("y");
        System.out.println(interpreter.getSuccessors(states, "a"));
        System.out.println(interpreter.isFinal(states));

        HashSet<String> states1 = new HashSet<>();
        states1.add("u");
        states1.add("y");

        System.out.println(states1.hashCode() );
        System.out.println(states.hashCode() );

        System.out.println(states.equals(states1));
    }


}
