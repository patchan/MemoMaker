package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Memo implements Readable, Writeable, Serializable {
    private Memo memo;
    private ArrayList<Bar> bars;
    // the implementation of the Scanner to receive user input was adapted from the B04 SimpleCalculator
    private transient Scanner scanner = new Scanner(System.in);

    // EFFECTS: constructs an empty memo
    public Memo() {
        this.bars = new ArrayList<>();
    }

    // REQUIRES: scoreLength > 0
    // MODIFIES: this
    // EFFECTS: produces a memo with a number of bars given by scoreLength
    public void makeMemo() {
        int i = 0;
        int scoreLength;
        scoreLength = getScoreLength();
        while (i < scoreLength) {
            Bar newBar = new Bar();
            newBar.makeBar();
            addToMemo(newBar);
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
        bars.add(bar);
        System.out.println("The bar " + bar.getBar() + " has been added to your memo.");
    }

    // EFFECTS: prints list of each Bar in the memo
    public void printMemo() {
        System.out.println("Your memo contains " + barCount() + " bars:");
        for (Bar b : bars) {
            b.printBar();
        }
    }

    // EFFECTS: returns an ArrayList of Bars in the memo
    //          this method is for testing the loadMemo method
    public Memo returnMemo() {
        ArrayList<ArrayList<String>> listOfBars = new ArrayList<>();
        for (Bar b : bars) {
            ArrayList<String> listOfNotes;
            listOfNotes = b.getBar();
            listOfBars.add(listOfNotes);
        }
        return memo;
    }

    // EFFECTS: returns true if Note n is in the memo
    public Boolean memoContains(Note n) {
        for (Bar b : bars) {
            if (b.barContains(n)) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns the number of bars in the memo i.e. the size of the memo
    public int barCount() {
        return bars.size();
    }

    // EFFECTS: returns the total number of notes in the memo
    public int noteCount() {
        int notes = 0;
        int noteCount = 0;
        for (Bar b : bars) {
            noteCount = notes + b.barSize();
        }
        return noteCount;
    }

    // this implementation of loadMemo() and saveMemo() was implemented based on the tutorial found here:
    // url = https://www.mkyong.com/java/how-to-read-and-write-java-object-to-a-file/
    // REQUIRES: file containing memo to load
    // EFFECTS: loads a memo object
    @Override
    public void loadMemo() throws IOException, ClassNotFoundException {
        FileInputStream loadedFile = new FileInputStream(new File("my_memo"));
        ObjectInputStream loadedObjects = new ObjectInputStream(loadedFile);

        ArrayList<Bar> loadedBars = (ArrayList<Bar>) loadedObjects.readObject();
        Memo loadedMemo = new Memo();
        for (Bar b : loadedBars) {
            loadedMemo.addToMemo(b);
        }

        loadedObjects.close();
        loadedFile.close();

        System.out.println("A memo has been loaded.");
        loadedMemo.printMemo();
        memo = loadedMemo;
    }

    // REQUIRES: non-empty memo
    // MODIFIES: overwrites file being saved to
    // EFFECTS: saves the memo as a java object to a json file
    @Override
    public void saveMemo() throws IOException {
        FileOutputStream saveFile = new FileOutputStream(new File("my_memo"));
        ObjectOutputStream saveObjects = new ObjectOutputStream(saveFile);

        saveObjects.writeObject(bars);

        saveObjects.close();
        saveFile.close();
    }
}
