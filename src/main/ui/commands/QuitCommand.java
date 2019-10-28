package ui.commands;


import model.Library;

import java.io.IOException;
import java.util.Scanner;

public class QuitCommand implements Command {
    // the implementation of the Scanner to receive user input was adapted from the B04 SimpleCalculator
    private transient Scanner scanner = new Scanner(System.in);

    public QuitCommand() {}

    // EFFECTS: exits from the program, saving before quitting based on user command
    @Override
    public void executeCommand(Library library) throws IOException {
        System.out.println("Are you sure you want to quit? You may lose any unsaved progress.");
        System.out.println("Type '1' to quit or '2' to save before quitting.");
        int command = scanner.nextInt();
        if (command == 1) {
            System.exit(0);
        } else if (command == 2) {
            library.save();
            System.out.println("Your memo has been saved.");
            System.exit(0);
        }
    }

}
