package ui.commands;


import model.Memo;

import java.io.IOException;
import java.util.Scanner;

public class QuitCommand implements Command {

    public QuitCommand() {}

    // the implementation of the Scanner to receive user input was adapted from the B04 SimpleCalculator
    private transient Scanner scanner = new Scanner(System.in);

    @Override
    public void executeCommand(Memo memo) throws IOException {
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
