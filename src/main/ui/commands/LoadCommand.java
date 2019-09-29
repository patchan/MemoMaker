package ui.commands;

import model.Memo;

import java.io.IOException;

public class LoadCommand implements Command {

    public LoadCommand() {}

    // REQUIRES: an existing file containing a memo to load
    // EFFECTS: loads a memo
    @Override
    public void executeCommand(Memo memo) throws IOException, ClassNotFoundException {
        memo.load();
    }

    @Override
    public boolean equals(Object command) {
        return command instanceof LoadCommand;
    }
}
