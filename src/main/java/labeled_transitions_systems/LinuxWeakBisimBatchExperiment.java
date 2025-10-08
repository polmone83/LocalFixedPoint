package labeled_transitions_systems;

import Experiments.Experiments;
import Experiments.ExecBatch;
import bddRelations.locallySoundOracles.BDDOracleComp;
import bddRelations.locallySoundOracles.SMax;
import bddRelations.soundOracles.BDDRelOracle;
import bddRelations.termExtensionOracles.BDDRelExtensionOracle;

import java.time.Duration;

public class LinuxWeakBisimBatchExperiment implements Experiments {
    private String path = "";
    private static final String adg = "/home/bacci/Tools/LocalFixpoint/adg-tool/bisim.sh";
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
        return "" + exectime; //Long.toString(exectime);
        //System.out.println(system.getLog());
    }

    private String testADG(String model_fileName,
                           String p1, String p2) {
        String[] arguments = new String[] {adg,path+model_fileName, p1, p2};
        //File outFile = new File("/home/bacci/Tools/LocalFixpoint/Experiments/CCS/abp/output.txt");
        try {
            ProcessBuilder pb = new ProcessBuilder(arguments);
                    //.redirectOutput(outFile);
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
        oracle.addOracle(new BDDRelExtensionOracle<>(system));
        //oracle.addOracle(new trigLocalv2<>(system));
        //oracle.addOracle(new Dep<>(system));
        return oracle;
    }

    public static void main(String[] args) {
        //String path = "/home/bacci/Tools/LocalFixpoint/Experiments/CCS/abp/Batch-ABP_ok.txt";
        //String path = "/home/bacci/Tools/LocalFixpoint/Experiments/CCS/abp/Batch-ABP_bad.txt";
        //String path = "/home/bacci/Tools/LocalFixpoint/Experiments/CCS/RingElection/election_ok/Batch-RingElection_ok.txt";
        String path = "/home/bacci/Tools/LocalFixpoint/Experiments/CCS/RingElection/election_bad/Batch-RingElection_bad.txt";
        ExecBatch eb = new ExecBatch();
        LinuxWeakBisimBatchExperiment runner = new LinuxWeakBisimBatchExperiment();
        eb.runBatch(path, runner);
    }
}
