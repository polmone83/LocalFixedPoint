package bddRelations.groudness;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TestProlog {
    private static final String path = "/Users/giovbacci/Library/CloudStorage/OneDrive-AalborgUniversitet/AAU/Research/Tools/Dependecies/Experiments/prolog/";
    private static String outfile  = "queens";

    public static void main(String[] args) {
        String[] files = {"add", "queens", "big","lambdas","mperms","perms","sud4x"};
        for (String file : files) {
            makeEqs(file);
        }
    }
    public static void makeEqs(String outfile) {
        PrologTranslator builder = new PrologTranslator(path + outfile + ".pl",true);

        try {
            File myObj = new File(path + outfile + ".eqs");
            myObj.createNewFile(); // create the file if it doesn't exist

            FileWriter myWriter = new FileWriter(path + outfile + ".eqs");
            myWriter.write(builder.getEqs());
            myWriter.close();
            System.out.println("Successfully compiled " + outfile);

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
