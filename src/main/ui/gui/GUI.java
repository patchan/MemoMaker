package ui.gui;

import model.Library;
import model.Memo;
import network.WebReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GUI {
    private JPanel welcomePanel;
    private JMenuBar menuBar;
    private JFrame mainFrame = new JFrame("MemoMaker");
    private JFrame memoEditor;
    private Library library = new Library();
    private Memo activeMemo;

    public GUI() {
        initializeMainFrame();
    }

    private void initializeMenuBar() {
        menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem newMemo = new JMenuItem("Create New Memo");
        newMemo.addActionListener(new CreateNewMemoHandler());
        JMenuItem load = new JMenuItem("Load");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem quit = new JMenuItem("Quit");
        quit.addActionListener(new QuitListener());
        menuBar.add(file);
        file.add(newMemo);
        file.add(load);
        file.add(save);
        file.add(quit);
    }

    private void initializeWelcomePanel() {
        JPanel centeringPanel = new JPanel();
        setupCenteringPanel(centeringPanel);
        welcomePanel = new JPanel();
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
        welcomePanel.setBackground(new Color(255, 255, 255));
        JTextArea welcome = makeWelcomeMessage();
        welcomePanel.add(welcome);
        try {
            WebReader.main(new String[]{});
            JPanel webText = WebReader.getParsedText();
            welcomePanel.add(webText);
        } catch (IOException e) {
            e.printStackTrace();
        }
        centeringPanel.add(welcomePanel);
        mainFrame.getContentPane().add(BorderLayout.CENTER, centeringPanel);
    }

    private JTextArea makeWelcomeMessage() {
        JTextArea welcome = new JTextArea();
        welcome.setText("Welcome to MemoMaker!");
        welcome.setAlignmentX(0.5f);
        welcome.setSize(400, 100);
        welcome.setEditable(false);
        welcome.setFont(welcome.getFont().deriveFont(24f));
        return welcome;
    }

    private void setupCenteringPanel(JPanel panel) {
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(255, 255, 255));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

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
            try {
                memoEditor = new MemoEditor(activeMemo);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            initializeMemoEditor();
            memoEditor.setTitle("MemoMaker - " + name);
        }
    }

    private class QuitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int quit = JOptionPane.showConfirmDialog(mainFrame,
                    "Are you sure you want to quit? Any unsaved progress will be lost.");
            if (quit == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }
}

