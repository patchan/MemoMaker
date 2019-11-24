/**
 * This is deprecated after the implementation of a GUI
 */

//package ui;
//
//import ui.commands.*;
//
//import java.util.HashMap;
//import java.util.Scanner;
//
//public class UserInterface {
//
//    // the implementation of the Scanner to receive user input was adapted from the B04 SimpleCalculator
//    private Scanner scanner = new Scanner(System.in);
//    private static HashMap<Integer, Command> command;
//
//    public UserInterface() {
//        command = new HashMap<>();
//        initializeCommands();
//    }
//
//    // MODIFIES: this
//    // EFFECTS: initializes commands in a HashMap table
//    public void initializeCommands() {
//        command.put(1, new CreateNewMemo());
//        command.put(2, new AddToMemo());
//        command.put(3, new AssignSections());
//        command.put(4, new LoadCommand());
//        command.put(5, new SaveCommand());
//        command.put(6, new QuitCommand());
//    }
//
//    // EFFECTS: prints command instructions to the user
//    public void askForCommand() {
//        System.out.println("\n-----------------------------------------");
//        System.out.println("What would you like to do?");
//        System.out.println("\t-> '1' to create a new memo");
//        System.out.println("\t-> '2' to add a current memo");
//        System.out.println("\t-> '3' to assign sections to a memo");
//        System.out.println("\t-> '4' to load a library");
//        System.out.println("\t-> '5' to save the library");
//        System.out.println("\t-> '6' to quit");
//    }
//
//    // REQUIRES: scanner input must be Integer[1, 6]
//    // EFFECTS: gets command from user
//    public Command returnUserCommand() {
//        int userCommand = scanner.nextInt();
//        scanner.nextLine();
//        return parseUserCommand(userCommand);
//    }
//
//    // REQUIRES: userInput is Integer[1, 6]
//    // EFFECTS: returns the appropriate Command subtype for command input
//    private static Command parseUserCommand(int userInput) {
//        return command.get(userInput);
//    }
//
//}
