package ui.commands;

import model.Bar;
import model.Memo;

public class AddCommand extends CreateNewCommand {

    public AddCommand() {}

    // REQUIRES: memo is not empty
    // MODIFIES: memo
    // EFFECT: adds a new bar to memo
    @Override
    public void executeCommand(Memo memo) {
        int numBars = getScoreLength();
        int i = 0;
        while (i < numBars) {
            Bar newBar = new Bar();
            makeBar(newBar);
            memo.addToMemo(newBar);
            i++;
        }
        memo.printMemo();
        System.out.println("ADD");
    }

    @Override
    public boolean equals(Object command) {
        return command instanceof AddCommand;
    }
}
