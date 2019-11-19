package ui.gui;

import javax.swing.*;

public class MemoEditor {
    private JFrame frame = new JFrame("MemoMaker");
    private JPanel panel;

    public MemoEditor() {
        panel = new JPanel();
        JButton addNote = new JButton("Note");
        JButton addChord = new JButton("Chord");
        JButton addRest = new JButton("Rest");
        panel.add(addNote);
        panel.add(addChord);
        panel.add(addRest);
        frame.setVisible(true);
    }
}
