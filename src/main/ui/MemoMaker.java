package ui;

import ui.UserInterface.UserCommand;

import java.io.IOException;

public class MemoMaker {
    private UserInterface ui = new UserInterface();

    public MemoMaker() throws IOException, ClassNotFoundException {
        while (true) {
            ui.askForCommand();
            UserCommand command = ui.getUserCommand();
            ui.executeCommand(command);
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new MemoMaker();
    }
}
