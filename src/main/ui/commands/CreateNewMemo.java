package ui.commands;

import model.*;

import java.util.Scanner;

public class CreateNewMemo implements Command {
    // the implementation of the Scanner to receive user input was adapted from the B04 SimpleCalculator
    private transient Scanner scanner = new Scanner(System.in);

    public CreateNewMemo() {}

    // EFFECTS: creates a new memo
    @Override
    public void executeCommand(Library library) {
        String name = getMemoName();
        library.addNewMemo(name);
        Memo memo = library.getMemo(name);
        int numBars = getScoreLength();
        int i = 0;
        while (i < numBars) {
            Bar newBar = new Bar(i + 1);
            newBar.makeBar(getBarLength());
            memo.addToMemo(newBar);
            i++;
            System.out.println("Bar " + newBar.getBarNum() + " added.");
        }
        System.out.println("A new memo was created. It contains:");
        library.printMemo(name);
    }

    // EFFECTS: returns user input for the name of the memo to create
    public String getMemoName() {
        System.out.println("Enter a memo name:");
        return scanner.nextLine();
    }

    // EFFECTS: returns user input for the number of bars to create in the memo
    public int getScoreLength() {
        System.out.println("How many bars would you like to add to this memo?");
        int numBars;
        numBars = scanner.nextInt();
        scanner.nextLine();
        return numBars;
    }

    // EFFECTS: returns user input for the number of bars to create in the memo
    public int getBarLength() {
        System.out.println("How many quarter notes are in this bar?");
        int barLength;
        barLength = scanner.nextInt();
        scanner.nextLine();
        return barLength;
    }

}
