package RegLangEquiv.Timbuk;

import Experiments.Experiments;
import Experiments.ExecBatch;
import RegLangEquiv.NFAEquivContext_EquationSystem;
import RegLangEquiv.NFAEquiv_Interpreter;
import bddRelations.locallySoundOracles.BDDOracleComp;
import bddRelations.locallySoundOracles.SMax;
import bddRelations.soundOracles.BDDRelOracle;

import java.time.Duration;

public class LinuxTimbukBatchExperiments implements Experiments {
    private String path = "";
    private static final String hkc = "/home/bacci/Tools/LocalFixpoint/hknt-1.0/hkc";
    private enum QUERY {
        EQUIVALENCE,
        INCLUSION
    }

    private String testHKC(QUERY q, String nfaL, String nfaR) {
        String option = q.equals(QUERY.EQUIVALENCE) ? "-equiv" : "-incl";
        String[] arguments = new String[] {hkc, option, path+nfaL, path+nfaR};
        try {
            ProcessBuilder pb = new ProcessBuilder(arguments);
            // Start measuring execution time
            long startTime = System.nanoTime();

            Process proc = pb.start();
            proc.waitFor();

            long exectime = Duration.ofNanos(System.nanoTime() - startTime).toMillis();
            return "" + exectime; //Long.toString(exectime);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Process Exception");
        }
    }

    private String testFIX(QUERY q, String nfaL, String nfaR){
        TimbukEquiv_Interpreter interpreter = q.equals(QUERY.EQUIVALENCE)
                ? new TimbukEquiv_Interpreter()
                : new TimbukIncl_Interpreter();
        interpreter.construct(path+nfaL,path+nfaR,true);

        long startTime = System.nanoTime();
        solve(interpreter);
        long exectime = Duration.ofNanos(System.nanoTime() - startTime).toMillis();
        return "" + exectime; //Long.toString(exectime);
        //System.out.println("FIX: " + exectime + " ms");
    }

    private Boolean solve(NFAEquiv_Interpreter interpreter){
        NFAEquivContext_EquationSystem system = new NFAEquivContext_EquationSystem();
        system.setInterpreter(interpreter);
        return system.localSolve(getOracle(system));
    }

    private static BDDRelOracle<Boolean> getOracle(NFAEquivContext_EquationSystem system){
        BDDOracleComp<Boolean> oracle = new BDDOracleComp<>();
        oracle.addOracle(new SMax<>(system,true));
        //oracle.addOracle(new BDDRelExtensionOracle<>(system));
        //oracle.addOracle(new Dep<>(system));
        return oracle;
    }

    /**
     * Run a single experiment on a set of arguments (to be found at the given path)
     *
     * @param args input file names
     * @param path path where the input files are (empty if there is no common path)
     * @return the outcome of the experiment
     */
    @Override
    public String runSingle(String[] args, String path) {
        this.path = path; // set the path where the input files are
        QUERY q = QUERY.EQUIVALENCE ;

        StringBuilder outcome = new StringBuilder();
        outcome.append(args[0]);
        outcome.append(q.equals(QUERY.EQUIVALENCE)? "=" : "<=");
        outcome.append(args[1]);
        outcome.append(", ");

        // run HKC
        outcome.append(testHKC(q, args[0], args[1]));
        outcome.append(", ");
        // run LOCAL Algorithm
        outcome.append(testFIX(q, args[0], args[1]));
        return outcome.toString();
    }

    public static void main(String[] args) {
        //String path = "/home/bacci/Tools/LocalFixpoint/Experiments/Timbuk/fa_timbuk_armc/Batch-armcNFA.txt";
        String path = "/home/bacci/Tools/LocalFixpoint/Experiments/Timbuk/random_difficult_cases/Batch-NFA.txt";

        ExecBatch eb = new ExecBatch();
        LinuxTimbukBatchExperiments runner = new LinuxTimbukBatchExperiments();
        eb.runBatch(path, runner);
    }
}
