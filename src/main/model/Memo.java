package model;

import java.util.ArrayList;
import java.util.Scanner;

public class Memo {
    private ArrayList<Bar> memo;
    private Bar bar;
    // the implementation of the Scanner to receive user input was adapted from the B04 SimpleCalculator
    private Scanner scanner = new Scanner(System.in);

    public Memo() {
        memo = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: produces a memo with an arbitrary number of bars
    // the while true loop was implemented based on the B04 SimpleCalculator
    public void makeMemo() {
        String operation = "";

        while (true) {
            System.out.println("What would you like to do?"
                    + "(\'add\' to add more bars or \'quit\')");
            operation = scanner.nextLine();

            if (operation.equals("quit")) {
                break;
            }

            bar = new Bar();
            bar.makeBar();
            addToMemo(bar);
        }
    }
    
    // MODIFIES: this
    // EFFECTS: adds a Bar to the Memo
    public void addToMemo(Bar bar) {
        memo.add(bar);
        System.out.println("The bar " + bar.getBar() + " has been added to your memo.");
    }

    // EFFECTS: prints list of each Bar in the memo
    public void printMemo() {
        System.out.println("Your memo contains " + barCount() + " bars:");
        for (Bar b : memo) {
            b.printBar();
        }
    }

    // EFFECTS: returns true if Note n is in the memo
    public Boolean memoContains(Note n) {
        for (Bar b: memo) {
            if (b.barContains(n)) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns the number of bars in the memo i.e. the size of the memo
    public int barCount() {
        return memo.size();
    }

    // EFFECTS: returns the total number of notes in the memo
    public int noteCount() {
        int notes = 0;
        int noteCount = 0;
        for (Bar b : memo) {
            noteCount = notes + b.barSize();
        }
        return noteCount;
    }

}
