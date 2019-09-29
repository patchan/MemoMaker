package ui.commands;

import model.Memo;

import java.util.Scanner;

public class CreateNewCommand implements Command {
    // the implementation of the Scanner to receive user input was adapted from the B04 SimpleCalculator
    private transient Scanner scanner = new Scanner(System.in);

    public CreateNewCommand() {}

    // EFFECTS: creates a new memo
    @Override
    public void executeCommand(Memo memo) {
        int numBars = getScoreLength();
        memo.makeMemo(numBars);
        memo.printMemo();
    }

    // EFFECTS: returns user input for the number of bars to create in the memo
    public int getScoreLength() {
        System.out.println("How many bars would you like in the memo?");
        int numBars;
        numBars = scanner.nextInt();
        return numBars;
    }

    @Override
    public boolean equals(Object command) {
        return command instanceof CreateNewCommand;
    }

}
