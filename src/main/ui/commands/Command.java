package ui.commands;

import model.Memo;

import java.io.IOException;

public interface Command {

    // REQUIRES: user command input must be Integer[1, 5]
    // MODIFIES: memo
    // EFFECTS: executes user command based on user command input
    public void executeCommand(Memo memo) throws IOException, ClassNotFoundException;
}
