//package ui.gui;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.IOException;
//
//public class MenuBar extends JMenuBar {
//    private JMenu file;
//    private JMenuItem newMemo;
//    private JMenuItem load;
//    private JMenuItem save;
//    private JMenuItem quit;
//
//    public MenuBar() {
//        file = new JMenu("File");
//        newMemo = new JMenuItem("Create New Memo");
//        load = new JMenuItem("Load");
//        save = new JMenuItem("Save");
//        quit = new JMenuItem("Quit");
//        newMemo.addActionListener(new CreateNewMemoHandler());
//        quit.addActionListener(new QuitListener());
//        file.add(newMemo);
//        file.add(load);
//        file.add(save);
//        file.add(quit);
//        this.add(file);
//    }
//
//    private class CreateNewMemoHandler implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            String name = JOptionPane.showInputDialog(mainFrame, "Enter the memo name", null);
//            library.addNewMemo(name);
//            activeMemo = library.getMemo(name);
//            try {
//                memoEditor = new MemoEditor(activeMemo);
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//            initializeMemoEditor();
//            memoEditor.setTitle("MemoMaker - " + name);
//        }
//    }
//
//    private class QuitListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            int quit = JOptionPane.showConfirmDialog(mainFrame,
//                    "Are you sure you want to quit? Any unsaved progress will be lost.");
//            if (quit == JOptionPane.YES_OPTION) {
//                System.exit(0);
//            }
//        }
//    }
//}
