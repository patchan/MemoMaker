package ui;

import ui.commands.*;

import java.util.Scanner;

public class UserInterface {

    // the implementation of the Scanner to receive user input was adapted from the B04 SimpleCalculator
    private Scanner scanner = new Scanner(System.in);

    public UserInterface() {}

    // EFFECTS: prints command instructions to the user
    public void askForCommand() {
        System.out.println("What would you like to do?");
        System.out.println("\t-> '1' to create a new memo");
        System.out.println("\t-> '2' to add to the current memo");
        System.out.println("\t-> '3' to load a memo");
        System.out.println("\t-> '4' to save the memo");
        System.out.println("\t-> '5' to quit");
    }

    // REQUIRES: scanner input must be Integer[1, 5]
    // EFFECTS: gets command from user
    public Command getUserCommand() {
        int userCommand = scanner.nextInt();
        scanner.nextLine();
        return parseUserCommand(userCommand);
    }

    // REQUIRES: userInput is Integer[1, 5]
    // EFFECTS: returns the appropriate Command subtype for command input
    public static Command parseUserCommand(int userInput) {
        Command userCommand = null;
        if (userInput == 1) {
            userCommand = new CreateNewMemo();
        } else if (userInput == 2) {
            userCommand = new AddToMemo();
        } else if (userInput == 3) {
            userCommand = new LoadCommand();
        } else if (userInput == 4) {
            userCommand = new SaveCommand();
        } else if (userInput == 5) {
            userCommand = new QuitCommand();
        }
        return userCommand;
    }

}
