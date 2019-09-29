package ui.commands;

import model.Bar;
import model.Memo;

public class AddCommand implements Command {

    public AddCommand() {}

    // REQUIRES: memo is not empty
    // MODIFIES: memo
    // EFFECT: adds a new bar to memo
    @Override
    public void executeCommand(Memo memo) {
        Bar bar = new Bar();
        bar.makeBar();
        memo.addToMemo(bar);
        memo.printMemo();
    }

    @Override
    public boolean equals(Object command) {
        return command instanceof AddCommand;
    }
}
