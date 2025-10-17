package labeled_transitions_systems;

import Experiments.Experiments;
import Experiments.ExecBatch;
import bddRelations.BDDRelEquationSystem;
import bddRelations.booleanSystemsVisitors.ParsedBooleanSystem;
import bddRelations.locallySoundOracles.*;
import bddRelations.soundOracles.BDDRelOracle;
import bddRelations.soundOracles.trigStatic;
import bddRelations.termExtensionOracles.BDDRelExtensionOracle;
import bddRelations.termExtensionOracles.BDDRelExtensionOracleBetter;

import java.time.Duration;
import java.util.GregorianCalendar;
import java.util.HashSet;

public class WeakBisimBatchExperiment implements Experiments {
    private static String path = "";
    private static String adg = "";

    private static final HashSet<Method> methods = new HashSet<>();
    enum Method {
        ADG,
        GLOBAL,
        LOCAL
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

        StringBuilder outcome = new StringBuilder();
        outcome.append(args[0]); // model
        outcome.append(", ");
        // query
        outcome.append(args[1]);
        outcome.append(" ~ ");
        outcome.append(args[2]);

        if(methods.contains(Method.ADG)) {
            // run ADG
            outcome.append(", ");
            outcome.append(testADG(args[0], args[1],args[2]));
        }

        if(methods.contains(Method.LOCAL)) {
            // run LOCAL Algorithm
            outcome.append(", ");
            outcome.append(testLocalFIX(args[0], args[1], args[2]));
        }

        if(methods.contains(Method.GLOBAL)) {
            // run GLOBAL Algorithm
            outcome.append(", ");
            outcome.append(testGlobalFIX(args[0], args[1],args[2]));
        }
        return outcome.toString();
    }


    private String testGlobalFIX(String model_fileName,
                                 String p1, String p2){

        CCS_Bisim_StaticEquationSystemUpTo system =
                new CCS_Bisim_StaticEquationSystemUpTo(path+model_fileName,false,true);
        // ORACLE
//        BDDOracleComp<Boolean> oracle = new BDDOracleComp<>();
        //oracle.addOracle(new MaxPrimeOracle(system, true));
        //oracle.addOracle(new SMax<>(system, true));
        //oracle.addOracle(new ArgsOracle<>(system));
//        oracle.addOracle(new FixPoint<>());
//        oracle.addOracle(new BDDRelExtensionOracle<>(system));
//        oracle.addOracle(new FixPoint<>());
        //oracle.addOracle(new trigStatic<>(system));

        BDDRelOracle<Boolean> oracle = new BDDRelExtensionOracle<>(system);

        boolean result = system.localSolve(p1,p2,oracle);


        return system.getExecTime() + ", " + system.getCompileTime() +", " + system.getRHSEvalCount() + ", " + system.getIterationCount() + ", " + system.varsCount() + ", " + result;

    }

    private String testLocalFIX(String model_fileName,
                                String p1, String p2){
        //long startTime = System.nanoTime();
        // solve the system
        CCS_Bisim_EquationSystemUpTo system =
                new CCS_Bisim_EquationSystemUpTo(path + model_fileName,false,true);
        boolean result = system.localSolve(p1,p2,getOracle(system));
        // --------------
        //long exectime = Duration.ofNanos(System.nanoTime() - startTime).toMillis();
        //long exectime = (System.nanoTime() - startTime) / magnitude; // runtime in ms
        return system.getExecTime() + ", " + system.getRHSEvalCount() + ", " + system.getIterationCount() + ", " + system.varsCount() + ", " + result; //Long.toString(exectime);
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

    private static BDDRelOracle<Boolean> getOracle(BDDRelEquationSystem<Boolean> system){
//        BDDOracleComp<Boolean> oracle = new BDDOracleComp<>();
//        oracle.addOracle(new FixPoint<>());
//        oracle.addOracle(new BDDRelExtensionOracle<>(system));
//        oracle.addOracle(new FixPoint<>());
        //oracle.addOracle(new ArgsLocal<>(system));
        //oracle.addOracle(new trigLocalv2<>(system));
        //oracle.addOracle(new SMax<>(system,true));
        //oracle.addOracle(new BDDRelExtensionOracle<>(system));
        //oracle.addOracle(new Dep<>(system));
        return new BDDRelExtensionOracle<>(system);
    }

    public static void main(String[] args) {
        // LINUX Batch
//        setOS("linux");
//        //String batch = "abp/Batch-ABP_ok.txt";
//        //String batch = "abp/Batch-ABP_bad.txt";
//        String batch = "RingElection/election_ok/Batch-RingElection_ok.txt";
//        //String batch = "RingElection/election_bad/Batch-RingElection_bad.txt";
//        batch = "BatchLocGlob.txt";
//        //-----------------------------

        // MAC Batch
        setOS("mac");
        String batch = "Batch-ABP_bad.txt";
        //batch = "Batch-ABP_ok.txt";
        //batch = "Batch-RingElection_bad.txt";
        //batch = "Batch-RingElection_ok.txt";
        batch = "LocalGlobal/BatchLocGlob.txt";

        //methods.add(Method.ADG);
        methods.add(Method.LOCAL);
        methods.add(Method.GLOBAL);

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
