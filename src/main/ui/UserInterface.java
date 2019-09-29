package ui;

import model.Bar;
import model.Memo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserInterface {

    public Memo memo = new Memo();
    private Bar bar;
    // the implementation of the Scanner to receive user input was adapted from the B04 SimpleCalculator
    private transient Scanner scanner = new Scanner(System.in);

//    private Map<Integer, UserCommand> commandMap = new HashMap<Integer, UserCommand>();
//    commandMap

    public enum UserCommand {
        NEW, ADD, LOAD, SAVE, QUIT
    }

    public UserInterface() {

    }

    public void askForCommand() {
        System.out.println("What would you like to do?");
        System.out.println("Enter '1' to create a new memo");
        System.out.println("Enter '2' to add to the current memo");
        System.out.println("Enter '3' to load a memo");
        System.out.println("Enter '4' to save the memo");
        System.out.println("Enter '5' to quit");
    }

    public UserCommand getUserCommand() throws IOException, ClassNotFoundException {
        int command = scanner.nextInt();
        UserCommand userCommand = null;
        if (command == 1) {
            executeCommand(UserCommand.NEW);
        } else if (command == 2) {
            executeCommand(UserCommand.ADD);
        } else if (command == 3) {
            executeCommand(UserCommand.LOAD);
        } else if (command == 4) {
            executeCommand(UserCommand.SAVE);
        } else if (command == 5) {
            userCommand = UserCommand.QUIT;
        }
        return userCommand;
    }

    public void executeCommand(UserCommand c) throws IOException, ClassNotFoundException {
        if (c == UserCommand.NEW) {
            memo.makeMemo();
            memo.printMemo();
        } else if (c == UserCommand.ADD) {
            bar = new Bar();
            bar.makeBar();
            memo.addToMemo(bar);
            memo.printMemo();
        } else if (c == UserCommand.LOAD) {
            memo.loadMemo();
        } else if (c == UserCommand.SAVE) {
            memo.saveMemo();
        } else if (c == UserCommand.QUIT) {
            quit();
        }
    }

    private void quit() throws IOException {
        System.out.println("Are you sure you want to quit? You may lose any unsaved progress.");
        System.out.println("Type '1' to quit or '2' to save before quitting.");
        int command = scanner.nextInt();
        if (command == 1) {
            System.exit(0);
        } else if (command == 2) {
            memo.saveMemo();
            System.exit(0);
        }
    }

}
