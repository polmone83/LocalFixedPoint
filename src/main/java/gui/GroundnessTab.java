package gui;

import bddRelations.BDDRelEquationSystem;
import bddRelations.locallySoundOracles.BDDOracleComp;
import bddRelations.locallySoundOracles.SMax;
import bddRelations.soundOracles.*;
import bddRelations.groudness.GroundSystemBuilder;
import bddRelations.groudness.GroundnessEquationSystem;
import bddRelations.groudness.PrologTranslator;
import bddRelations.termExtensionOracles.BDDRelExtensionOracle;
import com.github.javabdd.BDD;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class GroundnessTab extends DomainTab {

    TextArea prologSourceCode;
    public GroundnessTab(String name, TextField targetVarTxt, TextArea logConsole) {
        super(name, targetVarTxt, logConsole, new TextArea());
        this.prologSourceCode = new TextArea();
        // set the TextArea dedicated to the equation system as not editable.
        this.editorArea.setEditable(false);

        HBox code = new HBox();
        //code.setPrefHeight(Double.MAX_VALUE);
        code.getChildren().addAll(prologSourceCode,editorArea);
        code.setSpacing(1);
        //code.setAlignment(Pos.BASELINE_RIGHT);
        this.setContent(code);
    }

    @Override
    public void exec() {
        try {
            // compile the code
            PrologTranslator translator = new PrologTranslator(prologSourceCode.getText(),false);
            editorArea.setText(translator.getEqs()); // the compiled equation system
            GroundSystemBuilder eqb = new GroundSystemBuilder(translator.getEqs(),false);
            GroundnessEquationSystem system = eqb.getSystem();

            // retrieve the target variable
            String targetVar = targetVarTxt.getText().trim();

            // construct the oracle
            BDDRelOracle<BDD> oracle = getDefaultOracle(system);
            //DOracle<Integer,BDD, BDDRel> oracle = new NaiveOracle(system);

            // solve the system
            system.localSolve(targetVar, system.getFactory().zero(), oracle);
            // append the log to the editor's console
            logConsole.appendText(system.getLog());

        } catch (Exception e) {
            //todo: refine exception handling
            e.printStackTrace();
        }
    }

    private BDDRelOracle<BDD> getDefaultOracle(GroundnessEquationSystem system) {
        // construct the oracle
        BDDOracleComp<BDD> oracle = new BDDOracleComp<>();
        //oracle.addOracle(new MaxPrimeOracle(system, true));
        oracle.addOracle(new Args2Oracle<>(system));
                //.addOracle(new trigStatic<>(system));

        return oracle;
    }

}
