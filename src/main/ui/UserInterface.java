package ui;

import ui.commands.*;

import java.util.Scanner;

public class UserInterface {

    // the implementation of the Scanner to receive user input was adapted from the B04 SimpleCalculator
    private Scanner scanner = new Scanner(System.in);

    public UserInterface() {}

    public void askForCommand() {
        System.out.println("What would you like to do?");
        System.out.println("Enter '1' to create a new memo");
        System.out.println("Enter '2' to add to the current memo");
        System.out.println("Enter '3' to load a memo");
        System.out.println("Enter '4' to save the memo");
        System.out.println("Enter '5' to quit");
    }

    public Command getUserCommand() {
        int command = scanner.nextInt();
        Command userCommand = null;
        if (command == 1) {
            userCommand = new NewMemoCommand();
        } else if (command == 2) {
            userCommand = new AddToMemoCommand();
        } else if (command == 3) {
            userCommand = new LoadMemoCommand();
        } else if (command == 4) {
            userCommand = new SaveMemoCommand();
        } else if (command == 5) {
            userCommand = new QuitCommand();
        }
        return userCommand;
    }

}
