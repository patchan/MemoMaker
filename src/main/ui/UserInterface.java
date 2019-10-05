package ui;

import ui.commands.*;

import java.util.Scanner;

public class UserInterface {

    // the implementation of the Scanner to receive user input was adapted from the B04 SimpleCalculator
    private Scanner scanner = new Scanner(System.in);

    public UserInterface() {}

    // EFFECTS: prints instructions to user
    public void askForCommand() {
        System.out.println("What would you like to do?");
        System.out.println("Enter '1' to create a new memo");
        System.out.println("Enter '2' to add to the current memo");
        System.out.println("Enter '3' to load a memo");
        System.out.println("Enter '4' to save the memo");
        System.out.println("Enter '5' to quit");
    }

    // REQUIRES: scanner input must be Integer[1, 5]
    // EFFECTS: gets command from user
    public Command getUserCommand() {
        int command = scanner.nextInt();
        scanner.nextLine();
        return parseUserCommand(command);
    }

    // REQUIRES: commandInput is Integer[1, 5]
    // EFFECTS: returns the appropriate Command subtype for command input
    public static Command parseUserCommand(int commandInput) {
        Command userCommand = null;
        if (commandInput == 1) {
            userCommand = new CreateNewCommand();
        } else if (commandInput == 2) {
            userCommand = new AddCommand();
        } else if (commandInput == 3) {
            userCommand = new LoadCommand();
        } else if (commandInput == 4) {
            userCommand = new SaveCommand();
        } else if (commandInput == 5) {
            userCommand = new QuitCommand();
        }
        return userCommand;
    }

}
