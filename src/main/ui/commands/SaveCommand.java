package ui.commands;

import model.Memo;

import java.io.IOException;

public class SaveCommand implements Command {

    public SaveCommand() {}

    // EFFECTS: saves the memo
    @Override
    public void executeCommand(Memo memo) throws IOException {
        memo.save();
    }

    @Override
    public boolean equals(Object command) {
        return command instanceof SaveCommand;
    }
}
