package ui.commands;

import model.Library;

import java.io.IOException;

public interface Command {

    // MODIFIES: memo
    // EFFECTS: executes user command based on user command input
    public void executeCommand(Library library) throws IOException, ClassNotFoundException;
}
