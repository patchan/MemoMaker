package ui.commands;

import model.Bar;
import model.Memo;

public class AddToMemo extends CreateNewMemo {

    public AddToMemo() {}

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
    }

    @Override
    public boolean equals(Object command) {
        return command instanceof AddToMemo;
    }
}
