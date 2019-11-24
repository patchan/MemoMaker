/**
 * This is deprecated after the implementation of a GUI
 */
//package ui;
//
//import model.Library;
//import network.WebReader;
//import ui.commands.Command;
//
//import java.io.IOException;
//
//public class MemoMaker {
//    private UserInterface ui = new UserInterface();
//    private Library library;
//
//
//    public MemoMaker() throws IOException, ClassNotFoundException {
//        System.out.println("Welcome to MemoMaker!");
//        WebReader.main(new String[]{});
//        library = new Library();
//        while (true) {
//            ui.askForCommand();
//            Command command = ui.returnUserCommand();
//            command.executeCommand(library);
//        }
//    }
//
//    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        new MemoMaker();
//    }
//}
