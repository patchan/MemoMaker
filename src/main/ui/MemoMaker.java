package ui;

import model.Memo;
import ui.commands.Command;

import java.io.IOException;

public class MemoMaker {
    private UserInterface ui = new UserInterface();
    private Memo memo = new Memo();


    public MemoMaker() throws IOException, ClassNotFoundException {
        while (true) {
            ui.askForCommand();
            Command command = ui.getUserCommand();
            command.executeCommand(memo);
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new MemoMaker();
    }
}
