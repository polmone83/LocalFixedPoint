package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
// parser


public class EditorView extends BorderPane {

    private Button execBtn;
    private TabPane editorArea;
    private TextArea log;
    private TextField targetvarTxt;

    static Font font = Font.font("Courier New",18);

    public EditorView(){

        log = new TextArea();
        log.setMinHeight(300);
        log.setFont(font);

        targetvarTxt = new TextField("");
        targetvarTxt.setFont(font);
        Label label = new Label("Target variable");
        label.setFont(font);

        Button execBtn = new Button("Exec");
        execBtn.setFont(font);

        HBox crtlPanel = new HBox();
        crtlPanel.getChildren().add(label);
        crtlPanel.getChildren().add(targetvarTxt);
        crtlPanel.getChildren().add(execBtn);
        crtlPanel.setSpacing(5);
        crtlPanel.setAlignment(Pos.BASELINE_RIGHT);

        BorderPane.setMargin(crtlPanel, new Insets(10,10,10,10));

        //DomainTab boolTab = new BooleanOldTab("Boolean Old",targetvarTxt,log);
        //DomainTab boololdTab = new BooleanTab("Boolean New",targetvarTxt,log);
        DomainTab boolnewTab = new BooleanTabOnTheFly("Boolean Systems",targetvarTxt,log);
        //DomainTab weightTab = new WeightsTab("Weighted Systems", targetvarTxt,log);
        DomainTab groundnessTab = new GroundnessTab("Groundness",targetvarTxt,log);
        DomainTab ccshmlTab = new CCSHMLTab("CCS",targetvarTxt,log);
        DomainTab wccsTab = new WCCSTab("WCCS",targetvarTxt,log);
        DomainTab nfaTab = new NFATab("NFA",targetvarTxt,log);
        this.editorArea = new TabPane(boolnewTab,groundnessTab,ccshmlTab, wccsTab,nfaTab);

        execBtn.setOnAction(event ->
                {
                    DomainTab selectedTab = (DomainTab) editorArea.
                            getSelectionModel().getSelectedItem();
                    selectedTab.exec();
                }
        );

        setCenter(editorArea);
        setTop(crtlPanel);
        setBottom(log);

        //BorderPane.setMargin(execBtn, new Insets(0,10,0,10));
    }
}
