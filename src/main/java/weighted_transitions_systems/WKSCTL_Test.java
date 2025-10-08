package weighted_transitions_systems;

import bddRelations.BDDRelEquationSystem;
import bddRelations.locallySoundOracles.*;
import bddRelations.soundOracles.BDDRelOracle;
import bddRelations.termExtensionOracles.BDDRelExtensionOracle;
import domains.weightDomain.WValue;

import java.util.ArrayList;

public class WKSCTL_Test {
    private static final String path = "/Users/giovbacci/Library/CloudStorage/OneDrive-AalborgUniversitet/AAU/Research/Tools/Dependecies/Experiments/WCCS/";

    private interface OracleBuilder{
        BDDRelOracle<WValue> build(BDDRelEquationSystem<WValue> system);
    }

    public static void main(String[] args) {
        //LeaderElection();
        //ClientServer();
        BitProtocol();
    }

    private static void ClientServer() {
        ArrayList<OracleBuilder> oracles = new ArrayList<>();
        //oracles.add(s -> oracle1(s));
        oracles.add(s -> oracle1(s));

        for (OracleBuilder oracle : oracles) {
            runTest("ClientServer.wccs",
                    "ClientServer.wctl",
                    "System", "F4",
                    oracle);
        }
    }

    private static void LeaderElection() {
        ArrayList<OracleBuilder> oracles = new ArrayList<>();
        //oracles.add(s -> oracle1(s));
        oracles.add(s -> oracle1(s));

        for (OracleBuilder oracle : oracles) {
            runTest("LeaderElection8.wccs",
                    "LeaderElection.wctl",
                    "Ring", "G1",
                    oracle);
        }
    }

    private static void BitProtocol() {
        ArrayList<OracleBuilder> oracles = new ArrayList<>();
        //oracles.add(s -> oracle1(s));
        oracles.add(s -> oracle1(s));

        for (OracleBuilder oracle : oracles) {
            runTest("BitProtocol(B5M7).wccs",
                    "BitProtocol.wctl",
                    "System", "F1",
                    oracle); //
        }
    }

    private static void runTest(String model_fileName, String spec_fileName,
                                String pName, String fName, OracleBuilder builder){
        String modelPath = path + model_fileName;
        String specPath = path + spec_fileName;
        WKS_CTL_EquationSystem system =
                new WKS_CTL_EquationSystem(specPath, modelPath,true);

        // construct the oracle
        BDDRelOracle<WValue> oracle = builder.build(system);
        // solve the system
        system.localSolve(pName,fName, oracle);
        System.out.println(system.getLog());
    }

    private static BDDRelOracle<WValue> oracle1(BDDRelEquationSystem<WValue> system) {
        // construct the oracle
        BDDOracleComp<WValue> oracle = new BDDOracleComp<>();
        //oracle.addOracle(new MaxPrimeOracle(system, true));
        oracle.addOracle(new SMax<>(system, WValue.zero));
        //oracle.addOracle(new BDDRelExtensionOracle<>(system));

        return oracle;
    }

    private static BDDRelOracle<WValue> oracle2(BDDRelEquationSystem<WValue> system) {
        // construct the oracle
        BDDOracleComp<WValue> oracle = new BDDOracleComp<>();
        //oracle.addOracle(new Trivial<>());
        oracle.addOracle(new SMax<>(system, WValue.zero));
        oracle.addOracle(new BDDRelExtensionOracle<>(system));
        oracle.addOracle(new Dep<>(system));

        return oracle;
    }
}
