package gui;

import bddRelations.BDDRelEquationSystem;
import bddRelations.BDDRelStaticEquationSystem;
import bddRelations.locallySoundOracles.*;
import bddRelations.soundOracles.BDDRelOracle;
import bddRelations.soundOracles.MaxOracle;
import bddRelations.soundOracles.trigStatic;
import bddRelations.termExtensionOracles.BDDRelExtensionOracle;
import bddRelations.booleanSystemsVisitors.ParsedBooleanSystem;
import domains.weightDomain.WValue;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import labeled_transitions_systems.CCS_HML_ModelChecking;
import weighted_transitions_systems.WKS_CTL_EquationSystem;

import java.util.StringTokenizer;

import static gui.EditorView.font;

public class WCCSTab extends DomainTab {
    TextArea wccsTextArea;
    TextArea wctlTextArea;
    boolean onthefly;

    public WCCSTab(String name, TextField targetVarTxt, TextArea logConsole) {
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

        wccsTextArea = new TextArea("Write CCS here");
        wccsTextArea.setFont(font);
        wccsTextArea.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        wctlTextArea = new TextArea("Write HML here");
        wctlTextArea.setFont(font);
        wctlTextArea.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);

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
        CCSHMLArea.add(wccsTextArea,0,1);
        CCSHMLArea.add(wctlTextArea,1,1);


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
                //execStatic(modelVar,specVar);
            }
        } catch (Exception e) {
            //todo: refine exception handling
            e.printStackTrace();
        }
    }

    private BDDRelOracle<WValue> getOnTheFlyOracle(BDDRelEquationSystem<WValue> system) {
        // construct the oracle
        BDDOracleComp<WValue> oracle = new BDDOracleComp<>();
        //oracle.addOracle(new MaxPrimeOracle(system, true));
        oracle.addOracle(new SMax<>(system, WValue.zero))
                .addOracle(new BDDRelExtensionOracle<>(system));
                //.addOracle(new trigStatic<>(system));

        return oracle;
    }

    private BDDRelOracle<WValue> getStaticOracle(BDDRelStaticEquationSystem<WValue> system) {
        // construct the oracle
        BDDOracleComp<WValue> oracle = new BDDOracleComp<>();
        //oracle.addOracle(new MaxPrimeOracle(system, true));
        oracle.addOracle(new MaxOracle<>(system, WValue.zero))
                .addOracle(new BDDRelExtensionOracle<>(system))
                .addOracle(new trigStatic<>(system));

        return oracle;
    }

    private void execStatic(String modelVar,String specVar){
        /*long startTime = System.nanoTime();
        // get the system
        CCS_HML_ModelChecking mc = new CCS_HML_ModelChecking(wctlTextArea.getText(), wccsTextArea.getText());
        String code = mc.getEquationSystem(modelVar,specVar);
        //System.out.println(code);
        ParsedBooleanSystem system = new ParsedBooleanSystem(code);
        long endTime = System.nanoTime();
        // Calculate the compile time in milliseconds
        long translationTime = (endTime - startTime) / 1000000;

        // construct the oracle
        BDDRelOracle<WValue> oracle = getStaticOracle(system);

        // solve the system
        WValue value = system.localSolve("x0", WValue.inifinity, oracle);
        // append the log to the editor's console
        logConsole.appendText(system.getLog());
        //logConsole.appendText("Result x0 = " + value);
        logConsole.appendText("Compile time: " + translationTime + "ms");*/

    }

    private void execOnTheFly(String modelVar,String specVar){
        // get the system
        WKS_CTL_EquationSystem system =
                new WKS_CTL_EquationSystem(wctlTextArea.getText(), wccsTextArea.getText());

        // construct the oracle
        BDDRelOracle<WValue> oracle = getOnTheFlyOracle(system);

        // solve the system
        system.localSolve(modelVar,specVar, oracle);
        // append the log to the editor's console
        logConsole.appendText(system.getLog());
    }



}
