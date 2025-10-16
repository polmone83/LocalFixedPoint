package gui;

import bddRelations.BDDRelEquationSystem;
import bddRelations.BDDRelStaticEquationSystem;
import bddRelations.locallySoundOracles.*;
import bddRelations.soundOracles.ArgsOracle;
import bddRelations.soundOracles.BDDRelOracle;
import bddRelations.soundOracles.trigStatic;
import bddRelations.termExtensionOracles.BDDRelExtensionOracle;
import bddRelations.booleanSystemsVisitors.ParsedBooleanSystem;
import core.EquationSystem;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import labeled_transitions_systems.CCS_HML_EquationSystem;
import labeled_transitions_systems.CCS_HML_ModelChecking;

import java.time.Duration;
import java.util.StringTokenizer;

import static gui.EditorView.font;

public class CCSHMLTab extends DomainTab {
    TextArea ccsTextArea;
    TextArea hmlTextArea;
    boolean onthefly;

    public CCSHMLTab(String name, TextField targetVarTxt, TextArea logConsole) {
        super(name, targetVarTxt, logConsole);

        onthefly = false;
        CheckBox ontheflyCB = new CheckBox("on-the-fly");
        ontheflyCB.setSelected(onthefly);
        BorderPane.setMargin(ontheflyCB,new Insets(5));

        ontheflyCB.setOnAction(
                (ActionEvent e)->{
                    onthefly = ontheflyCB.isSelected();
                }
        );

        ccsTextArea = new TextArea("Write CCS here");
        ccsTextArea.setFont(font);
        ccsTextArea.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        hmlTextArea = new TextArea("Write HML here");
        hmlTextArea.setFont(font);
        hmlTextArea.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);

        GridPane CCSHMLArea = new GridPane();
        CCSHMLArea.setHgap(3);

        /*ColumnConstraints leftCol = new ColumnConstraints();
        leftCol.setPercentWidth(50);
        leftCol.setHalignment(HPos.LEFT);*/

        ColumnConstraints col = new ColumnConstraints();
        col.setPercentWidth(50);
        col.setHalignment(HPos.RIGHT);

        RowConstraints editorsRow = new RowConstraints();
        editorsRow.setPercentHeight(100);
        editorsRow.setValignment(VPos.CENTER);

        RowConstraints optionsRow = new RowConstraints();
        optionsRow.setMaxHeight(25);
        optionsRow.setValignment(VPos.CENTER);

        CCSHMLArea.getColumnConstraints().add(col);
        CCSHMLArea.getColumnConstraints().add(col);


        CCSHMLArea.getRowConstraints().add(optionsRow);
        CCSHMLArea.add(ontheflyCB,1,0);

        CCSHMLArea.getRowConstraints().add(editorsRow);
        CCSHMLArea.add(ccsTextArea,0,1);
        CCSHMLArea.add(hmlTextArea,1,1);



        /*CCSHMLArea.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                //"-fx-border-radius: 5;" +
                "-fx-border-color: blue;");*/
        //CCSHMLArea.setPadding(new Insets(10, 10, 10, 10));
        //CCSHMLArea.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);


        this.setContent(CCSHMLArea);
    }

    @Override
    public void exec() {
        try {
            // retrieve the target variable
            String targetVar = targetVarTxt.getText().trim();
            StringTokenizer tokenizer = new StringTokenizer(targetVar,",");
            String modelVar = tokenizer.nextToken().trim();
            String specVar = tokenizer.nextToken().trim();

            if(onthefly){
                execOnTheFly(modelVar,specVar);
            }else{
                execStatic(modelVar,specVar);
            }
        } catch (Exception e) {
            //todo: refine exception handling
            e.printStackTrace();
        }
    }

    private BDDRelOracle<Boolean> getOnTheFlyOracle(BDDRelEquationSystem<Boolean> system) {
        // construct the oracle
        BDDOracleComp<Boolean> oracle = new BDDOracleComp<>();
        //oracle.addOracle(new MaxPrimeOracle(system, true));
        oracle.addOracle(new SMax<>(system, true));
        //oracle.addOracle(new ArgsLocal<>(system));
        oracle.addOracle(new BDDRelExtensionOracle<>(system));
        oracle.addOracle(new trigLocalv2<>(system));

        return oracle;
    }

    private BDDRelOracle<Boolean> getStaticOracle(BDDRelStaticEquationSystem<Boolean> system) {
        // construct the oracle
        BDDOracleComp<Boolean> oracle = new BDDOracleComp<>();
        //oracle.addOracle(new MaxPrimeOracle(system, true));
        //oracle.addOracle(new SMax<>(system, true));
        //oracle.addOracle(new ArgsOracle<>(system));
        //oracle.addOracle(new BDDRelExtensionOracle<>(system));
        oracle.addOracle(new trigStatic<>(system));

        return oracle;
    }

    private void execStatic(String modelVar,String specVar){
        long startTime = System.nanoTime();
        // get the system
        CCS_HML_ModelChecking mc = new CCS_HML_ModelChecking(hmlTextArea.getText(),ccsTextArea.getText());
        String code = mc.getEquationSystem(modelVar,specVar);
        //System.out.println(code);
        ParsedBooleanSystem system = new ParsedBooleanSystem(code);
        // Calculate the compile time in milliseconds
        long translationTime = Duration.ofNanos(System.nanoTime() - startTime).toMillis();

        // construct the oracle
        BDDRelOracle<Boolean> oracle = getStaticOracle(system);

        // solve the system
        Boolean value = system.localSolve("x0", false, oracle);
        // show the result
        logConsole.appendText("Global Algorithm \n");
        logConsole.appendText("Solution " + value + "\n");
        logConsole.appendText("Compile time: " + translationTime + "ms\n");
        printStats(system);
        logConsole.appendText("-------------------\n");
    }

    private void execOnTheFly(String modelVar,String specVar){
        // get the system
        CCS_HML_EquationSystem system =
                new CCS_HML_EquationSystem(hmlTextArea.getText(),ccsTextArea.getText());

        // construct the oracle
        BDDRelOracle<Boolean> oracle = getOnTheFlyOracle(system);

        // solve the system
        Boolean value = system.localSolve(modelVar,specVar, false, oracle);
        // show the result
        logConsole.appendText("Local Algorithm \n");
        logConsole.appendText("Solution " + value + "\n");
        printStats(system);
        logConsole.appendText("-------------------\n");
    }

    private void printStats(EquationSystem eq){
        logConsole.appendText("Exec Time: " + eq.getExecTime() + "ms\n");
        logConsole.appendText("RHS Eval Count: " + eq.getRHSEvalCount() + "\n");
        logConsole.appendText("Iteration Count: " + eq.getIterationCount() + "\n");
        logConsole.appendText("# Visited: " + eq.visited.cardinality() + "\n");
    }



}
