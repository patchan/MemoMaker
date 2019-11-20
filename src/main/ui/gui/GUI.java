package ui.gui;

import model.Bar;
import model.Library;
import model.Memo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private JPanel welcomePanel;
    private JPanel editorPanel;
    private JPanel topPanel;
    private JPanel mainPanel;
    private JMenuBar menuBar;
    private JFrame mainFrame = new JFrame("MemoMaker");
    private JFrame memoEditor;
    private Library library = new Library();
    private Memo activeMemo;
    private int barNum;
    private Bar activeBar;

    public GUI() {
        initializeMainFrame();
        barNum = 1;
    }

    private void initializeMenuBar() {
        menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem newMemo = new JMenuItem("Create New Memo");
        newMemo.addActionListener(new CreateNewMemoHandler());
        JMenuItem load = new JMenuItem("Load");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem quit = new JMenuItem("Quit");
        menuBar.add(file);
        file.add(newMemo);
        file.add(load);
        file.add(save);
        file.add(quit);
    }

    private void initializeWelcomePanel() {
        welcomePanel = new JPanel();
        JTextArea welcome = new JTextArea("Welcome to MemoMaker!");
        welcomePanel.add(welcome);
//        WebReader webReader = new WebReader();
//        String deezerInfo = webReader.getAlbum(1);
//        JTextArea deezerInfo = new JTextArea();
//        welcomePanel.add(deezerInfo);
        mainFrame.getContentPane().add(BorderLayout.CENTER, welcomePanel);
    }

//    private void initializeEditorPanel() {
//        editorPanel = new JPanel();
//        topPanel = new JPanel();
//        mainPanel = new JPanel();
//        JButton note = new JButton("Note");
//        note.addActionListener(new NoteHandler());
//        editorPanel.add(note);
//        JButton chord = new JButton("Chord");
//        chord.addActionListener(new ChordHandler());
//        editorPanel.add(chord);
//        JButton rest = new JButton("Rest");
//        rest.addActionListener(new RestHandler());
//        editorPanel.add(rest);
//        JButton addBar = new JButton("Add Bar");
//        addBar.addActionListener(new AddBarHandler());
//        topPanel.add(addBar);
//        memoEditor.getContentPane().add(BorderLayout.PAGE_END, editorPanel);
//        memoEditor.getContentPane().add(BorderLayout.PAGE_START, topPanel);
//        memoEditor.getContentPane().add(BorderLayout.CENTER, mainPanel);
//    }

    private void initializeMainFrame() {
        initializeMenuBar();
        initializeWelcomePanel();
        initializeFrame(mainFrame);
        mainFrame.setVisible(true);
    }

    private void initializeFrame(JFrame frame) {
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(menuBar);
    }

    private void initializeMemoEditor() {
        if (!memoEditor.isVisible()) {
//            initializeEditorPanel();
//            initializeFrame(memoEditor);
            mainFrame.setVisible(false);
            memoEditor.setVisible(true);
        }
    }

    public static void main(String[] args) {
        new GUI();
    }

    private class CreateNewMemoHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = JOptionPane.showInputDialog(mainFrame, "Enter the memo name", null);
            library.addNewMemo(name);
            activeMemo = library.getMemo(name);
            memoEditor = new MemoEditor(activeMemo);
            initializeMemoEditor();
            memoEditor.setTitle("MemoMaker - " + name);
        }
    }

    private class AddBarHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            activeMemo.addToMemo(new Bar(barNum));
            activeBar = activeMemo.getBar(barNum);
            mainPanel.add(new JLabel("Bar" + barNum));
            barNum++;
            memoEditor.revalidate();
            memoEditor.repaint();
        }
    }

//    private class NoteHandler implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
////            Note newNote = new Note();
////            activeBar.addToBar(newNote);
////            mainPanel.add(new JLabel("Bar" + barNum));
////            barNum++;
////            memoEditor.revalidate();
////            memoEditor.repaint();
//        }
//    }
//
//    private class ChordHandler implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            //
//        }
//    }
//
//    private class RestHandler implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            //
//        }
//    }

}

