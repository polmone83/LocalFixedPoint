package gui;

import bddRelations.BDDRelEquationSystem;
import bddRelations.BDDRelStaticEquationSystem;
import bddRelations.locallySoundOracles.*;
import bddRelations.soundOracles.BDDRelOracle;
import bddRelations.soundOracles.MaxPrimeOracle;
import bddRelations.soundOracles.NsOracle;
import bddRelations.soundOracles.trigStatic;
import bddRelations.termExtensionOracles.BDDRelExtensionOracle;
import bddRelations.termExtensionOracles.BDDRelStaticExtensionOracle;
import bddRelations.booleanSystemsVisitors.LocallyParsedBooleanSystem;
import bddRelations.booleanSystemsVisitors.ParsedBooleanSystem;
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

import java.util.ArrayList;

public class BooleanTabOnTheFly extends DomainTab {
    boolean onthefly;

    public BooleanTabOnTheFly(String name, TextField targetVarTxt, TextArea logConsole) {
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

        editorArea.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);

        GridPane CCSHMLArea = new GridPane();
        CCSHMLArea.setHgap(3);

        ColumnConstraints col = new ColumnConstraints();
        col.setPercentWidth(100);
        col.setHalignment(HPos.RIGHT);

        RowConstraints editorsRow = new RowConstraints();
        editorsRow.setPercentHeight(100);
        editorsRow.setValignment(VPos.CENTER);

        RowConstraints optionsRow = new RowConstraints();
        optionsRow.setMaxHeight(25);
        optionsRow.setValignment(VPos.CENTER);

        CCSHMLArea.getColumnConstraints().add(col);

        CCSHMLArea.getRowConstraints().add(optionsRow);
        CCSHMLArea.add(ontheflyCB,0,0);

        CCSHMLArea.getRowConstraints().add(editorsRow);
        CCSHMLArea.add(editorArea,0,1);

        this.setContent(CCSHMLArea);
    }

    @Override
    public void exec() {
        try {
            // retrieve the target variable
            String targetVar = targetVarTxt.getText().trim();

            if(onthefly){
                execOnTheFly(targetVar);
            }else{
                execStatic(targetVar);
            }
        } catch (Exception e) {
            //todo: refine exception handling
            e.printStackTrace();
        }
    }

    private void execOnTheFly(String targetVar) {
        // compile the code
        LocallyParsedBooleanSystem system = new LocallyParsedBooleanSystem(editorArea.getText());

        // construct the oracle
        BDDRelOracle<Boolean> oracle = getOnTheFlyOracle(system);
        // solve the system
        system.localSolve(targetVar, false, oracle);
        // append the log to the editor's console
        logConsole.appendText(system.getLog());
    }

    private BDDRelOracle<Boolean> getOnTheFlyOracle(BDDRelEquationSystem<Boolean> system) {

        // construct the oracle
        BDDOracleComp<Boolean> oracle = new BDDOracleComp<>();
        //oracle.addOracle(new MaxPrimeOracle(system, true));
        oracle.addOracle(new SMax<>(system, true));
        oracle.addOracle(new BDDRelExtensionOracle<>(system));
        //oracle.addOracle(new Dep<>(system));
        return oracle;
    }

    public void execStatic(String targetVar) {
            // compile the code
            ParsedBooleanSystem system = new ParsedBooleanSystem(editorArea.getText());

            // construct the oracle
            BDDRelOracle<Boolean> oracle = getStaticOracle(system);
            // solve the system
            system.localSolve(targetVar, false, oracle);
            // append the log to the editor's console
            logConsole.appendText(system.getLog());
    }

    private BDDRelOracle<Boolean> getStaticOracle(BDDRelStaticEquationSystem<Boolean> system) {

        BDDOracleComp<Boolean> oracle = new BDDOracleComp<>();
        // oracle.addOracle(new MaxPrimeOracle(system, true));
        oracle.addOracle(new SMax<>(system, true));
        oracle.addOracle(new BDDRelExtensionOracle<>(system));
        /* TODO: when using the trig oracle the solution of the system
                x = x | y;
                y = tt;
                z = ff;
            returns the wrong answer */
        //oracle.addOracle(new trigStatic<>(system));
                //.addOracle(new Dep<>(system));
        return oracle;
    }

}
