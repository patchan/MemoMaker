package ui.commands;

import model.Memo;

import java.io.IOException;

public class SaveMemoCommand implements Command {

    public SaveMemoCommand() {}

    @Override
    public void executeCommand(Memo memo) throws IOException {
        memo.saveMemo();
    }
}
