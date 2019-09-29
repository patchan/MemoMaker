package ui.commands;

import model.Memo;

import java.io.IOException;

public interface Command {
    public void executeCommand(Memo memo) throws IOException, ClassNotFoundException;
}
