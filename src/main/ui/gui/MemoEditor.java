package ui.gui;

import model.Bar;
import model.Memo;
import model.Note;
import model.exceptions.BarLengthException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MemoEditor extends JFrame {
    private JPanel editorPanel;
    private JPanel barPanel;
    private JPanel notePanel;
    private JMenuBar menuBar;
    private Memo activeMemo;
    private Bar activeBar;
    private int barNum;

    public MemoEditor(Memo memo) {
        super("MemoMaker");
//        initializeFields();
        initializeMenuBar();
        this.activeMemo = memo;
        barNum = 1;
        editorPanel = new JPanel();
        barPanel = new JPanel();
        notePanel = new JPanel();
        barPanel.setLayout(new BoxLayout(barPanel, BoxLayout.Y_AXIS));
        notePanel.setLayout(new BoxLayout(notePanel, BoxLayout.Y_AXIS));
        JButton addNote = new JButton("Note");
        JButton addChord = new JButton("Chord");
        JButton addRest = new JButton("Rest");
        JButton addBar = new JButton("Add Bar");
        addBar.addActionListener(new AddBarHandler());
        addNote.addActionListener(new NoteHandler());
        editorPanel.add(addBar);
        editorPanel.add(addNote);
        editorPanel.add(addChord);
        editorPanel.add(addRest);
        this.add(editorPanel);
        initializeFrame();
    }

    private void initializeFrame() {
        this.setTitle("MemoMaker");
        this.setSize(500, 500);
        this.getContentPane().add(BorderLayout.LINE_START, barPanel);
        this.getContentPane().add(BorderLayout.CENTER, notePanel);
        this.getContentPane().add(BorderLayout.PAGE_END, editorPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initializeMenuBar() {
        menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem newMemo = new JMenuItem("Create New Memo");
        JMenuItem load = new JMenuItem("Load");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem quit = new JMenuItem("Quit");
        menuBar.add(file);
        file.add(newMemo);
        file.add(load);
        file.add(save);
        file.add(quit);
        JPanel topPanel = new JPanel();
        topPanel.add(menuBar);
        setJMenuBar(menuBar);
    }

    private class AddBarHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            activeMemo.addToMemo(new Bar(barNum));
            activeBar = activeMemo.getBar(barNum);
            activeBar.setBarLength(4);
            barPanel.add(new JLabel("Bar" + barNum));
            barPanel.add(new JLabel("\t"));
            barPanel.add(new JLabel("\t"));
            barPanel.add(new JLabel("\t"));
            barNum++;
            editorPanel.revalidate();
            editorPanel.repaint();
        }
    }

    private class NoteHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
// TODO:            JDialog noteDialog = new JDialog();
            Note newNote = new Note("A", 2, 1, 1);
            try {
                activeBar.addToBar(newNote);
                JOptionPane.showMessageDialog(null,
                        "Added " + newNote.getCompositeName() + " to bar.");
                notePanel.add(new JLabel("\t" + newNote.getCompositeName() + "\t"));
            } catch (BarLengthException ex) {
                JOptionPane.showMessageDialog(null,
                        "Could not add note to the bar because the bar is full.");
            }
            editorPanel.revalidate();
            editorPanel.repaint();
        }

    }
}
