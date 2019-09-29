package ui.commands;

import model.Bar;
import model.Memo;

public class AddToMemoCommand implements Command {

    public AddToMemoCommand() {}

    @Override
    public void executeCommand(Memo memo) {
        Bar bar = new Bar();
        bar.makeBar();
        memo.addToMemo(bar);
        memo.printMemo();
    }
}
