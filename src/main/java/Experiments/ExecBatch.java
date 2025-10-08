package Experiments;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import Experiments.ExperimentBatchParser.ExperimentContext;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class exposes the method runchBatch to execute a batch of experiments
 * using the given ExperimentRunner
 */
public class ExecBatch {

    private String path;
    private String outFile;
    private BufferedWriter writer;

    /** ExperimentRunner */
    private Experiments runner;

    /**
     * execute a batch of experiments using the given ExperimentRunner
     * @param batch file with the batch specification
     * @param runner ExperimentRunner used on each experiment
     */
    public void runBatch(String batch, Experiments runner){
        this.runner = runner;
        try {
            /* Parse the batch specification */
            CharStream input = CharStreams.fromFileName(batch);
            ExperimentBatchLexer lexer = new ExperimentBatchLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ExperimentBatchParser parser = new ExperimentBatchParser(tokens);
            ParseTree parseTree = parser.start();

            // visit the parse tree and run all experiments therein
            BatchVisitor visitor = new BatchVisitor();
            visitor.visit(parseTree);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private class BatchVisitor extends ExperimentBatchBaseVisitor<Boolean>{

        @Override
        public Boolean visitStart(ExperimentBatchParser.StartContext ctx) {
            path = ctx.path().ID().getText();
            outFile = path + ctx.output().ID().getText();
            for (ExperimentContext experiment : ctx.experiment()) {
                visit(experiment);
            }
            return true;
        }

        @Override
        public Boolean visitPath(ExperimentBatchParser.PathContext ctx) {
            path = ctx.ID().getText();
            return true;
        }

        @Override
        public Boolean visitExperiment(ExperimentContext ctx) {
            String[] args = new String[ctx.ID().size()];
            // prepare the inputs
            for(int i = 0; i < args.length; i++){
                args[i] = ctx.ID(i).getText();
            }
            // run the experiment
            String outcome = runner.runSingle(args, path);
            // store the result
            try {
                writer = new BufferedWriter(new FileWriter(outFile,true));
                writer.append(outcome + "\n"); // write a new line with the outcome
                writer.close();
            }catch (IOException e){
                System.out.println("The results of the experiment could not be saved");
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
    }

}
