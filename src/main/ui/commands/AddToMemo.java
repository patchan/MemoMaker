package ui.commands;

import model.Bar;
import model.Library;
import model.Memo;

import java.util.Scanner;

public class AddToMemo extends CreateNewMemo {

    private transient Scanner scanner = new Scanner(System.in);

    public AddToMemo() {}

    // REQUIRES: memo is not empty
    // MODIFIES: memo
    // EFFECT: adds a new bar to memo
    @Override
    public void executeCommand(Library library) {
        library.printLibraryMemos();
        String name = getMemoToEdit();
        Memo memo = library.getMemo(name);
        if (!(memo == null)) {
            int numBars = getScoreLength();
            int i = 0;
            while (i < numBars) {
                Bar newBar = new Bar(memo.barCount() + 1);
                newBar.makeBar(getBarLength());
                memo.addToMemo(newBar);
                i++;
            }
            System.out.println("Your memo was updated. It contains:");
            library.printMemo(name);
        } else {
            System.out.println("A memo of that name cannot be found.");
        }
    }

    // EFFECTS: returns user input for memo name to edit
    public String getMemoToEdit() {
        System.out.println("Enter the memo name you would like to add to:");
        return scanner.nextLine();
    }

}
