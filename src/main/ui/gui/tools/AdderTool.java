package ui.gui.tools;

import ui.gui.MemoEditor;

import javax.swing.*;

public abstract class AdderTool {
    protected MemoEditor editor;
    protected JButton button;

    public AdderTool(MemoEditor editor, JComponent parent) {
        this.editor = editor;
        createButton(parent);
        addToParent(button);
    }

    protected abstract void createButton(JComponent parent);

    public void addToParent(JComponent parent) {
        parent.add(button);
    }
}
