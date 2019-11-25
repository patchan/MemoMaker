package ui.gui;

import model.Library;
import model.Memo;
import network.WebReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MemoMaker {
    private JPanel welcomePanel;
    private JMenuBar menuBar;
    private JFrame mainFrame = new JFrame("MemoMaker");
    private JPanel memoEditor;
    private Library library = new Library();

    public MemoMaker() {
        initializeMainFrame();
    }

    // MODIFIES: this
    // EFFECTS: initializes the menu bar with the appropriate items
    private void initializeMenuBar() {
        menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem newMemo = new JMenuItem("Create New Memo");
        newMemo.addActionListener(new CreateNewMemoHandler());
        JMenuItem load = new JMenuItem("Load");
        load.addActionListener(new LoadListener());
        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(new SaveListener());
        JMenuItem quit = new JMenuItem("Quit");
        quit.addActionListener(new QuitListener());
        menuBar.add(file);
        file.add(newMemo);
        file.add(load);
        file.add(save);
        file.add(quit);
    }

    // MODIFIES: this
    // EFFECTS: initializes the welcome panel with text message and parsed web information
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

    // EFFECTS: produces JTextArea containing welcome message
    private JTextArea makeWelcomeMessage() {
        JTextArea welcome = new JTextArea();
        welcome.setText("Welcome to MemoMaker!");
        welcome.setAlignmentX(0.5f);
        welcome.setSize(400, 100);
        welcome.setEditable(false);
        welcome.setFont(welcome.getFont().deriveFont(24f));
        return welcome;
    }

    // MODIFIES: panel
    // EFFECTS: sets the parameters for the centering panel
    private void setupCenteringPanel(JPanel panel) {
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(255, 255, 255));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    // MODIFIES: this
    // EFFECTS: initializes all parts of mainFrame
    private void initializeMainFrame() {
        initializeMenuBar();
        initializeWelcomePanel();
        initializeFrame(mainFrame);
        mainFrame.setVisible(true);
    }

    // MODIFIES: frame
    // EFFECTS: initializes default parameters for frame
    private void initializeFrame(JFrame frame) {
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setJMenuBar(menuBar);
    }

    // MODIFIES: this
    // EFFECTS: sets memoEditor to the center panel of mainFrame
    private void initializeMemoEditor() {
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().add(BorderLayout.CENTER, memoEditor);
        mainFrame.validate();
        mainFrame.repaint();
    }

    public static void main(String[] args) {
        new MemoMaker();
    }

    private class CreateNewMemoHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = JOptionPane.showInputDialog(mainFrame, "Enter the memo name", null);
            library.addNewMemo(name);
            Memo activeMemo = library.getMemo(name);
            memoEditor = new MemoEditor(activeMemo);
            initializeMemoEditor();
            mainFrame.setTitle("MemoMaker - " + name);
        }
    }

    private class QuitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int quit = JOptionPane.showConfirmDialog(mainFrame,
                    "Are you sure you want to quit?\nAny unsaved progress will be lost.");
            if (quit == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }

    private class LoadListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                library.load();
                JOptionPane.showMessageDialog(null, "Load successful.");
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }


    private class SaveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                library.save();
                JOptionPane.showMessageDialog(null, "Save successful.");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}

