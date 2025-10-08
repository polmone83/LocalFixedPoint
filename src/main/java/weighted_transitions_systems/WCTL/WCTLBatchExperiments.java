package weighted_transitions_systems.WCTL;

import Experiments.Experiments;
import Experiments.ExecBatch;
import bddRelations.BDDRel;
import bddRelations.locallySoundOracles.BDDOracleComp;
import bddRelations.locallySoundOracles.SMax;
import bddRelations.soundOracles.BDDRelOracle;
import bddRelations.termExtensionOracles.BDDRelExtensionOracle;
import core.LocalOracle;
import core.SimpleVarSet;
import domains.weightDomain.WValue;
import weighted_transitions_systems.WKS_CTL_EquationSystem;

import java.time.Duration;

public class WCTLBatchExperiments implements Experiments {
    private String path = "";

    /**
     * Run a single experiment on a set of arguments (to be found at the given path)
     *
     * @param args input file names
     * @param path path where the input files are (empty if there is no common path)
     * @return the outcome of the experiment
     */
    @Override
    public String runSingle(String[] args, String path) {
        this.path = path;
        
        // ABP-B5M5.txt queries.txt System F0
        StringBuilder outcome = new StringBuilder();
        outcome.append(args[0]); // model e.g. ABP-B5M5.txt
        outcome.append(", ");
        // query
        outcome.append(args[2]); // state e.g., System
        outcome.append(" |= ");
        outcome.append(args[3]); // wctl formula, e.g., F0
        outcome.append(", ");
        
        // run LOCAL Algorithm
        outcome.append(testFIX(args[0], args[1],args[2],args[3]));
        return outcome.toString();
    }

    private String testFIX(String model_fileName, String spec_fileName,
                           String pName, String fName) {

        long startTime = System.nanoTime(); // start the timer

        String modelPath = path + model_fileName;
        String specPath = path + spec_fileName;
        WKS_CTL_EquationSystem system =
                new WKS_CTL_EquationSystem(specPath, modelPath,true);

        // solve the system
        WValue solution = system.localSolve(pName,fName, getOracle(system));

        long exectime = Duration.ofNanos(System.nanoTime() - startTime).toMillis();
        return "" + exectime + ", " + system.getRHSEvalCount() + ", " + solution; //Long.toString(exectime);

    }

    private LocalOracle<Integer, WValue, BDDRel, SimpleVarSet> getOracle(WKS_CTL_EquationSystem system) {
        BDDOracleComp<WValue> oracle = new BDDOracleComp<>();
        oracle.addOracle(new SMax<>(system,WValue.zero));
        //oracle.addOracle(new BDDRelExtensionOracle<>(system));
        //oracle.addOracle(new trigLocalv2<>(system));
        //oracle.addOracle(new Dep<>(system));
        return oracle;
    }

    public static void main(String[] args) {
        String path = "/Users/giovbacci/Library/CloudStorage/OneDrive-AalborgUniversitet/AAU/Research/Tools/Experiments/WCTL/ABP/";
        String batch = "Batch-ABP-wctl.txt";
        //String batch = "Batch-wctl test.txt";
        ExecBatch eb = new ExecBatch();
        WCTLBatchExperiments runner = new WCTLBatchExperiments();
        eb.runBatch(path+batch, runner);
    }
}
