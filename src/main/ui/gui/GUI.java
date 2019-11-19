package ui.gui;

import model.Bar;
import model.Library;
import model.Memo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private JPanel panel1;
    private JPanel editorPanel;
    private JPanel topPanel;
    private JPanel mainPanel;
    private JMenuBar menuBar;
    private JFrame mainFrame = new JFrame("MemoMaker");
    private JFrame memoEditor = new JFrame("MemoMaker");
    private Library library = new Library();
    private Memo activeMemo;
    private int barNum;

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

    private void initializePanelOne() {
        panel1 = new JPanel();
        JButton addToMemo = new JButton("Add to Memo");
        panel1.add(addToMemo);
        mainFrame.getContentPane().add(BorderLayout.SOUTH, panel1);
    }

//    private void initializeMainPanel() {
//        mainPanel = new JPanel();
//        mainFrame.getContentPane().add(BorderLayout.CENTER, mainPanel);
//    }

    private void initializeEditorPanel() {
        editorPanel = new JPanel();
        topPanel = new JPanel();
        mainPanel = new JPanel();
        editorPanel.add(new JButton("Note"));
        editorPanel.add(new JButton("Chord"));
        editorPanel.add(new JButton("Rest"));
        JButton addBar = new JButton("Add Bar");
        addBar.addActionListener(new AddBarHandler());
        topPanel.add(addBar);
        memoEditor.getContentPane().add(BorderLayout.PAGE_END, editorPanel);
        memoEditor.getContentPane().add(BorderLayout.PAGE_START, topPanel);
        memoEditor.getContentPane().add(BorderLayout.CENTER, mainPanel);
    }

    private void initializeMainFrame() {
        initializeMenuBar();
        initializePanelOne();
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
            initializeEditorPanel();
            initializeFrame(memoEditor);
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
            initializeMemoEditor();
            memoEditor.setTitle("MemoMaker - " + name);
        }

    }

    private class AddBarHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            activeMemo.addToMemo(new Bar(barNum));
            mainPanel.add(new JLabel("Bar" + barNum));
            barNum++;
            memoEditor.revalidate();
            memoEditor.repaint();
        }

    }

}

