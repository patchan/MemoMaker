package ui.gui;

import model.Bar;
import model.Memo;
import model.Note;
import model.exceptions.BarLengthException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import static java.lang.Integer.parseInt;

public class MemoEditor extends JFrame {
    private JPanel editorPanel;
    private JPanel mainPanel = new JPanel();
    private JPanel barPanel;
    private JPanel input;
    private JMenuBar menuBar;
    private Memo activeMemo;
    private Bar activeBar;
    private int barNum;
    private JTextField noteName = new JTextField(1);
    private JTextField octave = new JTextField(1);
    private JRadioButton sharp = new JRadioButton("#");
    private JRadioButton natural = new JRadioButton("natural");
    private JRadioButton flat = new JRadioButton("b");
    private ButtonGroup degree = new ButtonGroup();
    private ButtonGroup noteDuration = new ButtonGroup();
    private JRadioButton quarterNote = new JRadioButton("Quarter");
    private JRadioButton eighthNote = new JRadioButton("Eighth");
    private Image noteImage = ImageIO.read(new File("data/music-note.jpg"));
    private ImageIcon noteIcon = new ImageIcon(noteImage);

    public MemoEditor(Memo memo) throws IOException {
        super("MemoMaker");
        initializeMenuBar();
        this.activeMemo = memo;
        barNum = 1;
        editorPanel = new JPanel();
        barPanel = new JPanel();
        barPanel.setLayout(new BoxLayout(barPanel, BoxLayout.LINE_AXIS));
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
        mainPanel.add(barPanel);
        this.add(editorPanel);
        initializeFrame();
    }

    private void initializeFrame() {
        this.setTitle("MemoMaker");
        this.setSize(500, 500);
        this.getContentPane().add(BorderLayout.CENTER, mainPanel);
        this.getContentPane().add(BorderLayout.PAGE_END, editorPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        degree.add(sharp);
        degree.add(natural);
        degree.add(flat);
        noteDuration.add(quarterNote);
        noteDuration.add(eighthNote);
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
            barPanel.add(new JLabel(" | Bar" + barNum));
            barNum++;
            mainPanel.revalidate();
            mainPanel.repaint();
        }
    }

    private class NoteHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            initializeInputFields();
            JOptionPane.showConfirmDialog(null, input, "Add a note:",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.OK_CANCEL_OPTION, noteIcon);
            Note newNote = new Note(noteName.getText(), parseInt(octave.getText()), returnDegree(), returnDuration());
            try {
                activeBar.addToBar(newNote);
                barPanel.add(new JLabel("\t" + newNote.getCompositeName() + "\t"));
            } catch (BarLengthException ex) {
                JOptionPane.showMessageDialog(null,
                        "Could not add note to the bar because the bar is full.");
            }
            mainPanel.revalidate();
            mainPanel.repaint();
        }

        public void initializeInputFields() {
            input = new JPanel();
            input.setLayout(new BoxLayout(input, BoxLayout.Y_AXIS));
//            JPanel namePanel = new JPanel();
//            namePanel.add(new JLabel("Note Name:"));
//            namePanel.add(noteName);
//            input.add(namePanel);
//            JPanel octavePanel = new JPanel();
//            octavePanel.add(new JLabel("Octave:"));
//            octavePanel.add(octave);
//            input.add(octavePanel);
//            JPanel degreePanel = new JPanel();
//            degreePanel.add(new JLabel("Degree:"));
//            degreePanel.add(sharp);
//            degreePanel.add(natural);
//            degreePanel.add(flat);
//            input.add(degreePanel);
//            JPanel lengthPanel = new JPanel();
//            lengthPanel.add(new JLabel("Note Length:"));
//            lengthPanel.add(quarterNote);
//            lengthPanel.add(eighthNote);
//            input.add(lengthPanel);
            addNamePanel();
            addOctavePanel();
            addDegreePanel();
            addLengthPanel();
        }

        private void addNamePanel() {
            JPanel namePanel = new JPanel();
            namePanel.add(new JLabel("Note Name:"));
            namePanel.add(noteName);
            input.add(namePanel);
        }

        private void addOctavePanel() {
            JPanel octavePanel = new JPanel();
            octavePanel.add(new JLabel("Octave:"));
            octavePanel.add(octave);
            input.add(octavePanel);
        }

        private void addDegreePanel() {
            JPanel degreePanel = new JPanel();
            degreePanel.add(new JLabel("Degree:"));
            degreePanel.add(sharp);
            degreePanel.add(natural);
            degreePanel.add(flat);
            input.add(degreePanel);
        }

        private void addLengthPanel() {
            JPanel lengthPanel = new JPanel();
            lengthPanel.add(new JLabel("Note Length:"));
            lengthPanel.add(quarterNote);
            lengthPanel.add(eighthNote);
            input.add(lengthPanel);
        }

        public int returnDegree() {
            if (sharp.isSelected()) {
                return 1;
            } else if (flat.isSelected()) {
                return -1;
            }
            return 0;
        }

        public double returnDuration() {
            if (quarterNote.isSelected()) {
                return 1.0;
            } else if (eighthNote.isSelected()) {
                return 0.5;
            }
            return 0;
        }

    }
}
