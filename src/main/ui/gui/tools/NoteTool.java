package ui.gui.tools;

import ui.gui.MemoEditor;

import javax.swing.*;

public class NoteTool extends AdderTool {

    public NoteTool(MemoEditor editor, JComponent parent) {
        super(editor, parent);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Note");
        addToParent(parent);
    }

}
