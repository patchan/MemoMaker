package ui.commands;

import model.Library;

import java.io.IOException;

public class SaveCommand implements Command {

    public SaveCommand() {}

    // EFFECTS: saves the memo
    @Override
    public void executeCommand(Library library) throws IOException {
        library.save();
        System.out.println("Library successfully saved.");
    }
    
}
