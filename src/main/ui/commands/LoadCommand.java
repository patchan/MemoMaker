package ui.commands;

import model.Library;

import java.io.IOException;

public class LoadCommand implements Command {

    public LoadCommand() {}

    // REQUIRES: an existing file containing a memo to load
    // EFFECTS: loads a memo
    @Override
    public void executeCommand(Library library) throws IOException, ClassNotFoundException {
        library.load();
        System.out.println("Your library has been loaded.");
        System.out.println("Memos:");
        library.printLibraryMemos();
    }

}
