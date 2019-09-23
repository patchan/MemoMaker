package model;

import java.util.ArrayList;
import java.util.Scanner;

public class Memo {
    private ArrayList<Bar> memo;
    private Bar bar;
    // the implementation of the Scanner to receive user input was adapted from the B04 SimpleCalculator
    private Scanner scanner = new Scanner(System.in);

    // EFFECTS: constructs an empty memo
    public Memo() {
        memo = new ArrayList<>();
    }

    // REQUIRES: scoreLength > 0
    // MODIFIES: this
    // EFFECTS: produces a memo with a number of bars given by scoreLength
    public void makeMemo() {
        int i = 0;
        int scoreLength;
        scoreLength = getScoreLength();
        while (i < scoreLength) {
            bar = new Bar();
            bar.makeBar();
            addToMemo(bar);
            i++;
        }
    }

    // EFFECTS: returns user input for the number of bars to create in the memo
    public int getScoreLength() {
        System.out.println("How many bars would you like in the memo?");
        int numBars;
        numBars = scanner.nextInt();
        return numBars;
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
