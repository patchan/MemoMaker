package ui;

import model.Library;
import ui.commands.Command;

import java.io.IOException;

public class MemoMaker {
    private UserInterface ui = new UserInterface();
    private Library library;


    public MemoMaker() throws IOException, ClassNotFoundException {
        library = new Library();
        while (true) {
            ui.askForCommand();
            Command command = ui.returnUserCommand();
            command.executeCommand(library);
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new MemoMaker();
    }
}
