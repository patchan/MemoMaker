package ui.commands;

import model.Memo;

import java.io.IOException;

public class LoadMemoCommand implements Command {

    public LoadMemoCommand() {}

    @Override
    public void executeCommand(Memo memo) throws IOException, ClassNotFoundException {
        memo.loadMemo();
    }
}
