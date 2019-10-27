package model;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

public class Memo implements Readable, Writeable, Serializable {
    private ArrayList<Bar> bars;
    private HashSet<Section> sections;

    // EFFECTS: constructs an empty memo
    public Memo() {
        this.bars = new ArrayList<>();
        this.sections = new HashSet<>();
    }

    // MODIFIES: this
    // EFFECTS: clears the entire memo
    public void clearMemo() {
        bars.clear();
    }

    // MODIFIES: this
    // EFFECTS: adds a Bar to the Memo
    public void addToMemo(Bar bar) {
        bars.add(bar);
//        System.out.println("The bar " + bar.getBar() + " has been added to your memo.");
    }

    // MODIFIES: this
    // EFFECTS: adds a section the memo if it is not already in the memo
    public void addSection(Section s) {
        if (!sections.contains(s)) {
            sections.add(s);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes a section from the memo
    //          removes the section from all bars assigned to the section
    public void removeSection(Section s) {
        if (sections.contains(s)) {
            for (Bar b : bars) {
                b.removeSection(s);
            }
            sections.remove(s);
        }
    }

    // EFFECTS: returns the set of sections in the memo
    public HashSet<Section> getSections() {
        return sections;
    }

    // EFFECTS: returns how many sections are in the memo
    public int countSections() {
        return sections.size();
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
    public boolean memoContains(MusicalObject mo) {
        for (Bar b : bars) {
            if (b.barContains(mo)) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns a list of bars in the memo
    public ArrayList<Bar> getBars() {
        return bars;
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
