package labeled_transitions_systems;

import Experiments.Experiments;
import Experiments.ExecBatch;
import bddRelations.locallySoundOracles.BDDOracleComp;
import bddRelations.locallySoundOracles.SMax;
import bddRelations.soundOracles.BDDRelOracle;
import bddRelations.termExtensionOracles.BDDRelExtensionOracle;

import java.time.Duration;

public class WeakBisimBatchExperiment implements Experiments {
    private String path = "/Users/giovbacci/Library/CloudStorage/OneDrive-AalborgUniversitet/AAU/Research/Tools/Dependecies/Experiments/Timbuk/random_difficult_cases/";
    private static final long magnitude = 10000; // 10^-1 ms
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

        StringBuilder outcome = new StringBuilder();
        outcome.append(args[0]); // model
        outcome.append(", ");
        // query
        outcome.append(args[1]);
        outcome.append(" ~ ");
        outcome.append(args[2]);
        outcome.append(", ");

        // run ADG
        outcome.append(testADG(args[0], args[1],args[2]));
        outcome.append(", ");
        // run LOCAL Algorithm
        outcome.append(testFIX(args[0], args[1],args[2]));
        return outcome.toString();
    }

    private String testFIX(String model_fileName,
                                String p1, String p2){
        long startTime = System.nanoTime();
        // solve the system
        solve(model_fileName,p1,p2);
        long exectime = Duration.ofNanos(System.nanoTime() - startTime).toMillis();
        //long exectime = (System.nanoTime() - startTime) / magnitude; // runtime in ms
        return "" + exectime; //Long.toString(exectime);
        //System.out.println(system.getLog());
    }

    private String testADG(String model_fileName,
                           String p1, String p2) {
        String[] arguments = new String[] {"/Users/giovbacci/Library/CloudStorage/OneDrive-AalborgUniversitet/AAU/Research/Tools/AbstractDependencyGraphs/adg-tool/bisim.sh",
                path+model_fileName, p1, p2};
        try {
            ProcessBuilder pb = new ProcessBuilder(arguments);
            // Start measuring execution time
            long startTime = System.nanoTime();

            Process proc = pb.start();
            proc.waitFor();
            long exectime = Duration.ofNanos(System.nanoTime() - startTime).toMillis();
            //long exectime = (System.nanoTime() - startTime) / magnitude ; // runtime in ms
            return "" + exectime; //Long.toString(exectime);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Process Exception");
        }
    }

    private Boolean solve(String model_fileName,
                          String p1, String p2){
        String modelPath = path + model_fileName;
        CCS_Bisim_EquationSystem system =
                new CCS_Bisim_EquationSystem(modelPath,false,true);
        return system.localSolve(p1,p2,getOracle(system));
    }

    private static BDDRelOracle<Boolean> getOracle(CCS_Bisim_EquationSystem system){
        BDDOracleComp<Boolean> oracle = new BDDOracleComp<>();
        oracle.addOracle(new SMax<>(system,true));
        //oracle.addOracle(new BDDRelExtensionOracle<>(system));
        //oracle.addOracle(new Dep<>(system));
        return oracle;
    }

    public static void main(String[] args) {
        String path = "/Users/giovbacci/Library/CloudStorage/OneDrive-AalborgUniversitet/AAU/Research/Tools/Experiments/CCS/Batch-ABP_ok.txt";

        ExecBatch eb = new ExecBatch();
        WeakBisimBatchExperiment runner = new WeakBisimBatchExperiment();
        eb.runBatch(path, runner);
    }
}
