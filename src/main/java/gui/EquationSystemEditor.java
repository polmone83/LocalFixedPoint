package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EquationSystemEditor extends Application {
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(new EditorView(), 1000, 800);
        primaryStage.setTitle("Equation System Editor");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
