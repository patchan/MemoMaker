package ui.gui;

import model.*;
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
    private JPanel editorTools = new JPanel();
    private JPanel mainPanel = new JPanel();
    private JPanel barPanel;
    private JPanel input;
    private JMenuBar menuBar;
    private Memo activeMemo;
    private Bar activeBar;
    private JTextField noteName = new JTextField(1);
    private JTextField octave = new JTextField(1);
    private JRadioButton sharp = new JRadioButton("#");
    private JRadioButton natural = new JRadioButton("natural");
    private JRadioButton flat = new JRadioButton("b");
    private ButtonGroup degree = new ButtonGroup();
    private ButtonGroup noteDuration = new ButtonGroup();
    private JRadioButton quarterNote = new JRadioButton("Quarter");
    private JRadioButton eighthNote = new JRadioButton("Eighth");
    private ButtonGroup chordQuality = new ButtonGroup();
    private JRadioButton maj = new JRadioButton("maj");
    private JRadioButton min = new JRadioButton("min");
    private JRadioButton aug = new JRadioButton("aug");
    private JRadioButton dim = new JRadioButton("dim");
    private JTextField extensions = new JTextField(3);
    private ImageIcon noteIcon;
    private ImageIcon restIcon;

    public MemoEditor(Memo memo) {
        super("MemoMaker");
        initializeMenuBar();
        try {
            loadIcons();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.activeMemo = memo;
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        initializeButtons(editorTools);
        this.add(editorTools);
        initializeFrame();
    }

    // EFFECTS: initializes all buttons, action listeners and button groups
    private void initializeButtons(JPanel panel) {
        JButton addNote = new JButton("Note");
        JButton addChord = new JButton("Chord");
        JButton addRest = new JButton("Rest");
        JButton addBar = new JButton("Add Bar");
        addBar.addActionListener(new AddBarHandler());
        addNote.addActionListener(new NoteHandler());
        addRest.addActionListener(new RestHandler());
        addChord.addActionListener(new ChordHandler());
        panel.add(addBar);
        panel.add(addNote);
        panel.add(addChord);
        panel.add(addRest);
        initializeButtonGroups();
    }

    // EFFECTS: initializes button groups
    private void initializeButtonGroups() {
        degree.add(sharp);
        degree.add(natural);
        degree.add(flat);
        noteDuration.add(quarterNote);
        noteDuration.add(eighthNote);
        chordQuality.add(maj);
        chordQuality.add(min);
        chordQuality.add(dim);
        chordQuality.add(aug);
    }

    private void loadIcons() throws IOException {
        Image noteImage = ImageIO.read(new File("data/music-note.jpg"));
        noteIcon = new ImageIcon(noteImage);
        Image restImage = ImageIO.read(new File("data/music-rest.png"));
        restIcon = new ImageIcon(restImage);
    }

    private void initializeFrame() {
        this.setTitle("MemoMaker");
        this.setSize(500, 500);
        this.getContentPane().add(BorderLayout.CENTER, mainPanel);
        this.getContentPane().add(BorderLayout.PAGE_END, editorTools);
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
        setJMenuBar(menuBar);
    }

    private class AddBarHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            barPanel = new JPanel();
            barPanel.setLayout(new BoxLayout(barPanel, BoxLayout.LINE_AXIS));
            activeMemo.addToMemo(new Bar(activeMemo.barCount() + 1));
            activeBar = activeMemo.getBar(activeMemo.barCount());
            activeBar.setBarLength(4);
            mainPanel.add(barPanel);
            barPanel.add(new JLabel("(Bar" + activeBar.getBarNum() + ")  "));
            mainPanel.revalidate();
            mainPanel.repaint();
        }
    }

    private abstract class CreatorTool {
        protected void addNamePanel() {
            JPanel namePanel = new JPanel();
            namePanel.add(new JLabel("Root:"));
            namePanel.add(noteName);
            input.add(namePanel);
        }

        protected void addLengthPanel() {
            JPanel lengthPanel = new JPanel();
            lengthPanel.add(new JLabel("Length:"));
            lengthPanel.add(quarterNote);
            lengthPanel.add(eighthNote);
            input.add(lengthPanel);
        }

        protected double returnDuration() {
            if (quarterNote.isSelected()) {
                return 1.0;
            } else if (eighthNote.isSelected()) {
                return 0.5;
            }
            return 0;
        }
    }

    private class RestHandler extends CreatorTool implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            initializeInputFields();
            JOptionPane.showConfirmDialog(editorTools, input, "Add a rest:",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, restIcon);
            Rest newRest = new Rest(returnDuration());
            try {
                activeBar.addToBar(newRest);
                barPanel.add(new JLabel("\t" + newRest.getCompositeName() + "\t"));
            } catch (BarLengthException ex) {
                JOptionPane.showMessageDialog(null,
                        "Could not add that rest because the bar is full.");
            }
            mainPanel.revalidate();
            mainPanel.repaint();
        }

        public void initializeInputFields() {
            input = new JPanel();
            input.setLayout(new BoxLayout(input, BoxLayout.Y_AXIS));
            addLengthPanel();
        }

    }

    private class NoteHandler extends CreatorTool implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            initializeInputFields();
            JOptionPane.showConfirmDialog(editorTools, input, "Add a note:",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, noteIcon);
            Note newNote = new Note(noteName.getText(), parseInt(octave.getText()), returnDegree(), returnDuration());
            try {
                activeBar.addToBar(newNote);
                barPanel.add(new JLabel("\t" + newNote.getCompositeName() + "\t"));
            } catch (BarLengthException ex) {
                JOptionPane.showMessageDialog(null,
                        "Could not add that note because the bar is full.");
            }
            mainPanel.revalidate();
            mainPanel.repaint();
        }

        public void initializeInputFields() {
            input = new JPanel();
            input.setLayout(new BoxLayout(input, BoxLayout.Y_AXIS));
            addNamePanel();
            addOctavePanel();
            addDegreePanel();
            addLengthPanel();
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

        private int returnDegree() {
            if (sharp.isSelected()) {
                return 1;
            } else if (flat.isSelected()) {
                return -1;
            }
            return 0;
        }

    }

    private class ChordHandler extends CreatorTool implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            initializeInputFields();
            JOptionPane.showConfirmDialog(editorTools, input, "Add a chord:",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, noteIcon);
            Chord newChord = new Chord(noteName.getText(), returnQuality(), extensions.getText(), returnDuration());
            try {
                activeBar.addToBar(newChord);
                barPanel.add(new JLabel("\t" + newChord.getCompositeName() + "\t"));
            } catch (BarLengthException ex) {
                JOptionPane.showMessageDialog(null,
                        "Could not add that note because the bar is full.");
            }
            mainPanel.revalidate();
            mainPanel.repaint();
        }

        private void initializeInputFields() {
            input = new JPanel();
            input.setLayout(new BoxLayout(input, BoxLayout.Y_AXIS));
            addNamePanel();
            addQualityPanel();
            addExtensionPanel();
            addLengthPanel();
        }

        private void addQualityPanel() {
            JPanel qualityPanel = new JPanel();
            qualityPanel.add(new JLabel("Quality"));
            qualityPanel.add(maj);
            qualityPanel.add(min);
            qualityPanel.add(dim);
            qualityPanel.add(aug);
            input.add(qualityPanel);
        }

        private void addExtensionPanel() {
            JPanel extensionPanel = new JPanel();
            extensionPanel.add(new JLabel("Extensions"));
            extensionPanel.add(extensions);
            input.add(extensionPanel);
        }

        private String returnQuality() {
            if (min.isSelected()) {
                return ChordQuality.MIN.getValue();
            } else if (dim.isSelected()) {
                return ChordQuality.DIM.getValue();
            } else if (aug.isSelected()) {
                return ChordQuality.AUG.getValue();
            } else {
                return ChordQuality.MAJ.getValue();
            }
        }
    }
}
