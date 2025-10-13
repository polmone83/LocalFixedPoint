package weighted_transitions_systems.WCCS;

import labeled_transitions_systems.CCS.CCSProcess;
import labeled_transitions_systems.CCS_HML_ModelChecking;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WCCSTest {
    private static final String path = "/Users/giovbacci/Library/CloudStorage/OneDrive-AalborgUniversitet/AAU/Research/Tools/Experiments/WCTL/ABP/";


    public static void main(String[] args) {
        testSuccessors();

        //constructSystem();


    }

    private static void testSuccessors() {
        WCCSInterpreter interpreter = new WCCSInterpreter(path + "ABP-B5M7.txt",true
        );
        /*CCSAbstractProcess p = interpreter.getProcess("Spec");
        System.out.println(p.successors());
        System.out.println(p.weakSuccessors());*/

        WCCSProcess p = new WCCSProcess.PName("System",interpreter).getDef();

        System.out.println(p);
        System.out.println(p.successors());

        //System.out.println(p.getLabels());
        //System.out.println(p.successors().size());
        /*HashSet<String> test = new HashSet<>();
        for (CCSAbstractProcess.CCS_Step successor : p.successors()) {
            test.add(successor.toString());
        }
        System.out.println(test);*/

        /*HMLInterpreter spec = new HMLInterpreter(path + "spec.hml",true);
        PrettyPrint pp = new PrettyPrint();
        StringBuilder sb = pp.visit(spec.getFormulaDef("MutualExclusion"));
        String s = sb.toString();

        System.out.println(spec.getFormulaDef("MutualExclusion"));*/
    }

    private static void constructSystem() {
        CCS_HML_ModelChecking mc =
                new CCS_HML_ModelChecking(path + "spec.hml",path + "model.ccs", true);

        try {
            File myObj = new File(path + "mc_output.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }

            FileWriter myWriter = new FileWriter(path + "mc_output.txt");
            myWriter.write(mc.getEquationSystem("Jobshop","Z"));
            myWriter.close();
            System.out.println("Successfully wrote to the file.");

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
