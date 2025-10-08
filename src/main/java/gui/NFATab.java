package gui;

import RegLangEquiv.NFA.NFA_Interpreter;
import RegLangEquiv.NFAEquivContext_EquationSystem;
import RegLangEquiv.NFAEquiv_EquationSystem;
import RegLangEquiv.NFAEquiv_Interpreter;
import bddRelations.BDDRelEquationSystem;
import bddRelations.locallySoundOracles.BDDOracleComp;
import bddRelations.locallySoundOracles.trigLocal;
import bddRelations.soundOracles.BDDRelOracle;
import bddRelations.termExtensionOracles.BDDRelExtensionOracle;
import bddRelations.locallySoundOracles.SMax;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class NFATab extends DomainTab {

    public NFATab(String name, TextField targetVarTxt, TextArea logConsole) {
        super(name, targetVarTxt, logConsole);
    }

    @Override
    public void exec() {
        try {
            /*String path = "/Users/giovbacci/Library/CloudStorage/OneDrive-AalborgUniversitet/AAU/Research/Tools/Dependecies/Experiments/MATA/";

            String file1 = path+"false-Bakery4pBinEnc-FbOneOne-Nondet-Partiali-B-3-rhs.mata";
            String file2 = path+"false-Bakery4pBinEnc-FbOneOne-Nondet-Partiali-B-3-rhs.mata";
            MATAEquiv_Interpreter interpreter =
                    new MATAEquiv_Interpreter(file1, file2, true);*/

            // compile the code
            //NFAEquiv_EquationSystem system = new NFAEquiv_EquationSystem();
            NFAEquivContext_EquationSystem system = new NFAEquivContext_EquationSystem();
            NFAEquiv_Interpreter interpreter = new NFA_Interpreter(editorArea.getText(),false);
            system.setInterpreter(interpreter);


            // retrieve the target variable
            targetVarTxt.setText("Not used");

            // construct the oracle
            BDDRelOracle<Boolean> oracle = getDefaultOracle(system);
            // solve the system
            system.localSolve(oracle);
            // append the log to the editor's console
            logConsole.appendText(system.getLog());

        } catch (Exception e) {
            //todo: refine exception handling
            e.printStackTrace();
        }
    }

    private BDDRelOracle<Boolean> getDefaultOracle(BDDRelEquationSystem<Boolean> system) {
        // construct the oracle
        BDDOracleComp<Boolean> oracle = new BDDOracleComp<>();
        //oracle.addOracle(new MaxPrimeOracle(system, true));
        oracle.addOracle(new SMax<>(system, true));
        //oracle.addOracle(new BDDRelExtensionOracle<>(system));
                //.addOracle(new trigLocal<>(system));

        return oracle;
    }

}
