package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import static gui.EditorView.font;

public abstract class DomainTab extends Tab {
    protected final TextField targetVarTxt;
    protected final TextArea logConsole, editorArea;

    /**
     * Construct a DomainTab object with main content a new editorArea
     * @param name
     * @param targetVarTxt
     * @param logConsole
     */
    public DomainTab(String name, TextField targetVarTxt,TextArea logConsole){
        super(name);
        this.logConsole = logConsole;
        this.targetVarTxt = targetVarTxt;
        editorArea = new TextArea();
        editorArea.setFont(font);
        this.setContent(editorArea);
    }

    /**
     * Construct a DomainTab object. The content of the tab is left unset.
     */
    public DomainTab(String name,TextField targetVarTxt,TextArea logConsole, TextArea editorArea){
        super(name);
        this.logConsole = logConsole;
        this.targetVarTxt = targetVarTxt;
        this.editorArea = editorArea;
    }

    public abstract void exec();

}
