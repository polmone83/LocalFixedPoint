package labeled_transitions_systems.CCS;

import labeled_transitions_systems.CCS_HML_ModelChecking;
import utilities.SetIterator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class CCSTest {
    private static final String path = "/Users/giovbacci/Library/CloudStorage/OneDrive-AalborgUniversitet/AAU/Research/Tools/Dependecies/Experiments/CCS/";


    public static void main(String[] args) {
        testSucc("Ring");
        //constructSystem();
    }

    private static void testSucc(String pName){
        CCSInterpreter interpreter = new CCSInterpreter(path + "election_8.ccs",true);
        CCSProcess p = interpreter.getProcess(pName);
        System.out.println(pName + " = " + p);
        CCSProcess proc = new CCSProcess.PName(pName, interpreter);
        CCSProcessSuccessor succGen = new CCSProcessSuccessor();
        System.out.println("cached sussessors: ");
        long timer_genRhs = System.nanoTime();
        Set<CCSProcess.CCS_Step> succ = succGen.getWeakSuccessors(proc); // proc.weakSuccessors(); // new version of successors
        timer_genRhs = (System.nanoTime() - timer_genRhs)/ 1000000;
        System.out.println("time " + timer_genRhs);
        System.out.println("Size: " + succ.size());
        //System.out.println(proc.weakSuccessors()); // old version of successors
        //System.out.println(p.successors().size());

        Iterator<CCSProcess.CCS_Step> iterator =
                CCSProcessSuccIterator.getWeakSuccessors(proc); // iterators for successors
        Scanner input = new Scanner(System.in);
        HashSet<CCSProcess.CCS_Step> step = new HashSet<>();
        int size = 0;
        timer_genRhs = System.nanoTime();
        while(iterator.hasNext()){
            //System.out.println(iterator.next());
            //input.next();
            iterator.next();
            size++;
        }
        timer_genRhs = (System.nanoTime() - timer_genRhs)/ 1000000;
        System.out.println("time " + timer_genRhs);
        System.out.println("iterator steps:" + size);
        System.out.println("steps set:" + step.size());
    }

    private static void testSuccessors() {
        CCSInterpreter interpreter = new CCSInterpreter(path + "model.ccs",true
        );
        /*CCSAbstractProcess p = interpreter.getProcess("Spec");
        System.out.println(p.successors());
        System.out.println(p.weakSuccessors());*/

        CCSProcess p = interpreter.getProcess("Jobshop");
        System.out.println(p.successors());
        System.out.println(p.successors().size());
        /*HashSet<String> test = new HashSet<>();
        for (CCSAbstractProcess.CCS_Step successor : p.successors()) {
            test.add(successor.toString());
        }
        System.out.println(test);*/

        System.out.println("Weak");

        System.out.println(p.weakSuccessors());
        System.out.println(p.weakSuccessors().size());

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
