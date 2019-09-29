package ui.commands;

import model.Memo;

public class NewMemoCommand implements Command {

    public NewMemoCommand() {}

    @Override
    public void executeCommand(Memo memo) {
        memo.makeMemo();
        memo.printMemo();
    }
}
