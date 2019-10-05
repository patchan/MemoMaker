package model;

import java.io.*;
import java.util.ArrayList;

public class Memo implements Readable, Writeable, Serializable {
    private ArrayList<Bar> bars;

    // EFFECTS: constructs an empty memo
    public Memo() {
        this.bars = new ArrayList<>();
    }

    public void clearMemo() {
        bars.clear();
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
    public ArrayList<ArrayList<String>> returnMemo() {
        ArrayList<ArrayList<String>> listOfBars = new ArrayList<>();
        for (Bar b : bars) {
            ArrayList<String> listOfNotes;
            listOfNotes = b.getBar();
            listOfBars.add(listOfNotes);
        }
        return listOfBars;
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
    public void load() throws IOException, ClassNotFoundException {
        FileInputStream loadedFile = new FileInputStream(new File("my_memo"));
        ObjectInputStream loadedObjects = new ObjectInputStream(loadedFile);

        clearMemo();
        bars = (ArrayList<Bar>) loadedObjects.readObject();
//        for (Bar b : bars) {
//            bars.addToMemo(b);
//        }
        System.out.println("bar count is " + barCount());

        loadedObjects.close();
        loadedFile.close();

        System.out.println("A memo has been loaded.");
        printMemo();
    }

    // REQUIRES: non-empty memo
    // MODIFIES: overwrites file being saved to
    // EFFECTS: saves the memo as a java object to a json file
    @Override
    public void save() throws IOException {
        FileOutputStream saveFile = new FileOutputStream(new File("my_memo"));
        ObjectOutputStream saveObjects = new ObjectOutputStream(saveFile);

        saveObjects.writeObject(bars);

        saveObjects.close();
        saveFile.close();
    }
}
