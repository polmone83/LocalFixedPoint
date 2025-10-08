package labeled_transitions_systems;

import bddRelations.BDDRelEquationSystem;
import bddRelations.BDDRelStaticEquationSystem;
import bddRelations.locallySoundOracles.BDDOracleComp;
import bddRelations.locallySoundOracles.Dep;
import bddRelations.locallySoundOracles.SMax;
import bddRelations.soundOracles.BDDRelOracle;
import bddRelations.soundOracles.trigStatic;
import bddRelations.termExtensionOracles.BDDRelExtensionOracle;

import java.util.ArrayList;

public class CCSBisim_Test {
    private static String path = "/Users/giovbacci/Library/CloudStorage/OneDrive-AalborgUniversitet/AAU/Research/Tools/Dependecies/Experiments/CCS/";

    private interface OracleBuilder<D>{
        BDDRelOracle<D> build(BDDRelEquationSystem<D> system);
    }

    public static void main(String[] args) {
        //BasicBuffer();
        //BasicBuffer_Static();

        //Orchard();
        //Peterson();
        ABP();
        //ABP_Static();
        //ABP_bad();
        //ABP_bad_Static();
        //RingElection();
        RingElection_bad();
    }

    private static void RingElection_bad(){
        String oldpath = path;
        path = "/Users/giovbacci/Library/CloudStorage/OneDrive-AalborgUniversitet/AAU/Research/Tools/pardg/ccs_examples/election_bad/";
        ArrayList<OracleBuilder<Boolean>> oracles = new ArrayList<>();
        //oracles.add(s -> oracle1(s));
        oracles.add(s -> oracle1(s));

        for (OracleBuilder<Boolean> oracle : oracles) {
            runTest("election_9.ccs",
                    "Ring",
                    "Spec",oracle);
        }

        path = oldpath;
    }

    private static void RingElection(){
        String oldpath = path;
        path = "/Users/giovbacci/Library/CloudStorage/OneDrive-AalborgUniversitet/AAU/Research/Tools/pardg/ccs_examples/election_ok/";
        ArrayList<OracleBuilder<Boolean>> oracles = new ArrayList<>();
        //oracles.add(s -> oracle1(s));
        oracles.add(s -> oracle1(s));

        for (OracleBuilder<Boolean> oracle : oracles) {
            runTest("election_9.ccs",
                    "Ring",
                    "Spec",oracle);
        }

        path = oldpath;
    }

    private static void ABP_bad() {
        ArrayList<OracleBuilder<Boolean>> oracles = new ArrayList<>();
        //oracles.add(s -> oracle1(s));
        oracles.add(s -> oracle1(s));

        for (OracleBuilder<Boolean> oracle : oracles) {
            runTest("abp_generic_bad.ccs",
                    "ABPl_6",
                    "SPEC",oracle);
        }
    }

    private static void ABP_bad_Static() {
        runStaticTest("abp_generic_bad.ccs",
                    "ABPl_6",
                    "SPEC");
    }

    private static void ABP() {
        ArrayList<OracleBuilder<Boolean>> oracles = new ArrayList<>();
        //oracles.add(s -> oracle1(s));
        oracles.add(s -> oracle1(s));

        for (OracleBuilder<Boolean> oracle : oracles) {
            runTest("abp_generic.ccs",
                    "ABPl_3",
                    "SPEC",oracle);
        }
    }

    private static void ABP_Static() {
        runStaticTest("abp_generic.ccs",
                    "ABPl_3",
                    "SPEC");
    }

    private static void BasicBuffer_Static() {
        runStaticTest("BasicBuffer.ccs","Buff3", "Spec");
    }

    private static void BasicBuffer() {
        ArrayList<OracleBuilder<Boolean>> oracles = new ArrayList<>();
        //oracles.add(s -> oracle1(s));
        oracles.add(s -> oracle1(s));

        for (OracleBuilder<Boolean> oracle : oracles) {
            runTest("BasicBuffer.ccs",
                    "Buff3",
                    "Spec",oracle);
        }
    }

    private static void Peterson() {
        ArrayList<OracleBuilder<Boolean>> oracles = new ArrayList<>();
        //oracles.add(s -> oracle1(s));
        oracles.add(s -> oracle1(s));

        for (OracleBuilder<Boolean> oracle : oracles) {
            runTest("Peterson.ccs",
                    "Peterson",
                    "Spec",oracle);
        }
    }

    private static void Orchard() {
        ArrayList<OracleBuilder<Boolean>> oracles = new ArrayList<>();
        //oracles.add(s -> oracle1(s));
        oracles.add(s -> oracle1(s));

        for (OracleBuilder<Boolean> oracle : oracles) {
            runTest("Orchard.ccs",
                    "Orchard",
                    "Spec",oracle);
        }
    }


    private static void runTest(String model_fileName,
                                String p1, String p2, OracleBuilder builder){
        String modelPath = path + model_fileName;
        CCS_Bisim_EquationSystem system =
                new CCS_Bisim_EquationSystem(modelPath,false,true);

        // construct the oracle
        BDDRelOracle<Boolean> oracle = builder.build(system);
        // solve the system
        system.localSolve(p1,p2,oracle);
        System.out.println(system.getLog());
    }

    private static void runStaticTest(String model_fileName,
                                String p1, String p2){
        String modelPath = path + model_fileName;
        CCS_Bisim_Checker bsimChecker = new CCS_Bisim_Checker();
        BDDRelStaticEquationSystem<Boolean> system =
                bsimChecker.getSystem(modelPath, false, true,p1, p2);

        BDDOracleComp<Boolean> oracle = new BDDOracleComp<>();
        oracle.addOracle(new SMax<>(system, true));
        oracle.addOracle(new BDDRelExtensionOracle<>(system));
        oracle.addOracle(new trigStatic<>(system));

        // solve the system
        system.localSolve("x0",false,oracle);
        System.out.println(system.getLog());
    }

    private static BDDRelOracle<Boolean> oracle1(BDDRelEquationSystem<Boolean> system) {
        // construct the oracle
        BDDOracleComp<Boolean> oracle = new BDDOracleComp<>();
        oracle.addOracle(new SMax<>(system, true));
        //oracle.addOracle(new BDDRelExtensionOracle<>(system));
        //oracle.addOracle(new Dep<>(system));

        return oracle;
    }
}
