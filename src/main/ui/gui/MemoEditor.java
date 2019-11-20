package ui.gui;

import model.Bar;
import model.Memo;
import model.Note;
import model.exceptions.BarLengthException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Integer.parseInt;

public class MemoEditor extends JFrame {
    private JPanel editorPanel;
    private JPanel mainPanel = new JPanel();
    private JPanel barPanel;
//    private JPanel notePanel;
    private JPanel input;
    private JMenuBar menuBar;
    private Memo activeMemo;
    private Bar activeBar;
    private int barNum;
    private JTextField noteName = new JTextField(1);
    private JTextField octave = new JTextField(1);
    private JTextField degree = new JTextField(2);
    private JRadioButton sharp = new JRadioButton("#");
    private JRadioButton natural = new JRadioButton("natural");
    private JRadioButton flat = new JRadioButton("b");
    private JRadioButton noteDuration = new JRadioButton();

    public MemoEditor(Memo memo) {
        super("MemoMaker");
//        initializeFields();
        initializeMenuBar();
        this.activeMemo = memo;
        barNum = 1;
        editorPanel = new JPanel();
        barPanel = new JPanel();
//        notePanel = new JPanel();
        barPanel.setLayout(new BoxLayout(barPanel, BoxLayout.LINE_AXIS));
//        notePanel.setLayout(new BoxLayout(notePanel, BoxLayout.LINE_AXIS));
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
        this.getContentPane().add(BorderLayout.CENTER, mainPanel);
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
//            barPanel = new JPanel();
//            mainPanel.add(barPanel);
            barPanel.add(new JLabel("| Bar" + barNum));
//            barPanel.add(new JLabel("\t"));
//            barPanel.add(new JLabel("\t"));
//            barPanel.add(new JLabel("\t"));
            barNum++;
            editorPanel.revalidate();
            editorPanel.repaint();
        }
    }

    private class NoteHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            initializeInputFields();
            JOptionPane.showConfirmDialog(null, input, "Add a note:", JOptionPane.OK_CANCEL_OPTION);
            Note newNote = new Note(noteName.getText(), parseInt(octave.getText()), parseInt(degree.getText()), 1);
            try {
                activeBar.addToBar(newNote);
//                JOptionPane.showMessageDialog(null,
//                        "Added " + newNote.getCompositeName() + " to bar.");
                barPanel.add(new JLabel("\t" + newNote.getCompositeName() + "\t"));
            } catch (BarLengthException ex) {
                JOptionPane.showMessageDialog(null,
                        "Could not add note to the bar because the bar is full.");
            }
            editorPanel.revalidate();
            editorPanel.repaint();
        }

        public void initializeInputFields() {
            input = new JPanel();
            input.add(new JLabel("Note Name:"));
            input.add(noteName);
            input.add(new JLabel("Octave:"));
            input.add(octave);
            input.add(new JLabel("Degree:"));
            input.add(degree);
//            input.add(sharp);
//            input.add(natural);
//            input.add(flat);
            input.add(new JLabel("Note Length:"));
            input.add(noteDuration);
        }

    }
}
