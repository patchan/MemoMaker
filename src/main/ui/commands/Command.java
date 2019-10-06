package ui.commands;

import model.Memo;

import java.io.IOException;

public interface Command {

    // MODIFIES: memo
    // EFFECTS: executes user command based on user command input
    public void executeCommand(Memo memo) throws IOException, ClassNotFoundException;
}
