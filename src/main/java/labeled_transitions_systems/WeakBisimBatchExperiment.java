package labeled_transitions_systems;

import Experiments.Experiments;
import Experiments.ExecBatch;
import bddRelations.locallySoundOracles.*;
import bddRelations.soundOracles.BDDRelOracle;
import bddRelations.termExtensionOracles.BDDRelExtensionOracle;

import java.time.Duration;

public class WeakBisimBatchExperiment implements Experiments {
    private static String path = "";
    private static String adg = "";

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
        //outcome.append(testADG(args[0], args[1],args[2]));
        //outcome.append(", ");
        // run LOCAL Algorithm
        outcome.append(testFIX(args[0], args[1],args[2]));
        return outcome.toString();
    }

    private String testFIX(String model_fileName,
                                String p1, String p2){
        long startTime = System.nanoTime();
        // solve the system
        CCS_Bisim_EquationSystem system =
                new CCS_Bisim_EquationSystem(path + model_fileName,false,true);
        boolean result = system.localSolve(p1,p2,getOracle(system));
        // --------------
        long exectime = Duration.ofNanos(System.nanoTime() - startTime).toMillis();
        //long exectime = (System.nanoTime() - startTime) / magnitude; // runtime in ms
        return "" + exectime + ", " + system.getIterationCount() + ", " + system.discoveredVariables().size() + ", " + result; //Long.toString(exectime);
    }

    private String testADG(String model_fileName,
                           String p1, String p2) {
        String[] arguments = new String[] {adg,path+model_fileName, p1, p2};
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

    private static BDDRelOracle<Boolean> getOracle(CCS_Bisim_EquationSystem system){
//        BDDOracleComp<Boolean> oracle = new BDDOracleComp<>();
//        oracle.addOracle(new ArgsLocal<>(system));
//        oracle.addOracle(new trigLocalv2<>(system));
        //oracle.addOracle(new SMax<>(system,true));
        //oracle.addOracle(new BDDRelExtensionOracle<>(system));
        //oracle.addOracle(new Dep<>(system));
        return new Trivial<>();
    }

    public static void main(String[] args) {
//        // LINUX Batch
//        setOS("linux");
//        //String path = "abp/Batch-ABP_ok.txt";
//        //String path = "abp/Batch-ABP_bad.txt";
//        //String path = "RingElection/election_ok/Batch-RingElection_ok.txt";
//        String path = "RingElection/election_bad/Batch-RingElection_bad.txt";
//        //-----------------------------

        // MAC Batch
        setOS("mac");
        //String batch = "Batch-ABP_bad.txt"
        String batch = "Batch-ABP_ok.txt";

        ExecBatch eb = new ExecBatch();
        WeakBisimBatchExperiment runner = new WeakBisimBatchExperiment();
        eb.runBatch(path+batch, runner);
    }

    private static void setOS(String os){
        if(os.equals("linux")){
            adg = "/home/bacci/Tools/LocalFixpoint/adg-tool/bisim.sh";
            path = "/home/bacci/Tools/LocalFixpoint/Experiments/CCS/";
        }else if(os.equals("mac")){
            adg = "/Users/giovbacci/Library/CloudStorage/OneDrive-AalborgUniversitet/AAU/Research/Tools/AbstractDependencyGraphs/adg-tool/bisim.sh";
            path = "/Users/giovbacci/Library/CloudStorage/OneDrive-AalborgUniversitet/AAU/Research/Tools/Experiments/CCS/";
        }else{
            throw new RuntimeException("No OS selected");
        }
    }
}
