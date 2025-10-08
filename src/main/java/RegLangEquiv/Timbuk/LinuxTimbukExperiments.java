package RegLangEquiv.Timbuk;
import Experiments.Experiments;
import RegLangEquiv.NFAEquivContext_EquationSystem;
import RegLangEquiv.NFAEquiv_Interpreter;
import bddRelations.locallySoundOracles.BDDOracleComp;
import bddRelations.locallySoundOracles.SMax;
import bddRelations.soundOracles.BDDRelOracle;

public class LinuxTimbukExperiments {
    private static final String path = "/home/bacci/Tools/LocalFixpoint/Experiments/Timbuk/random_difficult_cases/";
    private static final String hkc = "/home/bacci/Tools/LocalFixpoint/hknt-1.0/hkc";
    public static void main(String[] args) {
        testInclusion("NFA-4000-2-1.25-10.1", "NFA-4000-2-1.25-10.2");
        testHKCInclusion("NFA-4000-2-1.25-10.1","NFA-4000-2-1.25-10.2");
    }

    private static void testHKCEquiv(String nfaL, String nfaR) {
        String[] arguments = new String[] {hkc, "-equiv", path+nfaL, path+nfaR};
        try {
            ProcessBuilder pb = new ProcessBuilder(arguments);
            //pb.redirectOutput(ProcessBuilder.Redirect.PIPE);
            // Start measuring execution time
            long startTime = System.nanoTime();

            Process proc = pb.start();
            proc.waitFor();

            // Start measuring execution time
            long exectime = (System.nanoTime() - startTime) / 1000000;
            System.out.println(exectime + " ms");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void testHKCInclusion(String nfaL, String nfaR) {
        String[] arguments = new String[] {hkc, "-incl", path+nfaL, path+nfaR};
        try {
            ProcessBuilder pb = new ProcessBuilder(arguments);
            //pb.redirectOutput(ProcessBuilder.Redirect.PIPE);
            // Start measuring execution time
            long startTime = System.nanoTime();

            Process proc = pb.start();
            proc.waitFor();

            long exectime = (System.nanoTime() - startTime) / 1000000;
            System.out.println("HKC: " + exectime + " ms");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void testEquiv(String nfaL, String nfaR){
        TimbukEquiv_Interpreter interpreter = new TimbukEquiv_Interpreter();
        interpreter.construct(path+nfaL,path+nfaR,true);

        long startTime = System.nanoTime();
        solve(interpreter);
        long exectime = (System.nanoTime() - startTime) / 1000000;
        System.out.println("FIX: " + exectime + " ms");
    }

    private static void testInclusion(String nfaL, String nfaR){
        TimbukIncl_Interpreter interpreter = new TimbukIncl_Interpreter();
        interpreter.construct(path+nfaL,path+nfaR,true);

        long startTime = System.nanoTime();
        solve(interpreter);
        long exectime = (System.nanoTime() - startTime) / 1000000;
        System.out.println("FIX: " + exectime + " ms");
    }

    private static void solve(NFAEquiv_Interpreter interpreter){
        NFAEquivContext_EquationSystem system = new NFAEquivContext_EquationSystem();
        //NFAEquiv_Interpreter interpreter = new NFA_Interpreter(editorArea.getText(),false);
        system.setInterpreter(interpreter);
        system.localSolve(getOracle(system)); //
        // append the log to the console
        System.out.println(system.getLog());
    }

    private static BDDRelOracle<Boolean> getOracle(NFAEquivContext_EquationSystem system){
        BDDOracleComp<Boolean> oracle = new BDDOracleComp<>();
        oracle.addOracle(new SMax<>(system,true));
        //oracle.addOracle(new BDDRelExtensionOracle<>(system));
        //oracle.addOracle(new Dep<>(system));
        return oracle;
    }
}
