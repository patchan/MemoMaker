package model;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Memo extends Subject implements Serializable {
    private ArrayList<Bar> bars;
    private HashSet<Section> sections;

    // EFFECTS: constructs an empty memo
    public Memo() {
        this.bars = new ArrayList<>();
        this.sections = new HashSet<>();
        addObserver(new BarCounter());
    }

    // MODIFIES: this
    // EFFECTS: adds a Bar to the Memo
    public void addToMemo(Bar bar) {
        bars.add(bar);
        notifyObservers();
    }

    // MODIFIES: this
    // EFFECTS: adds a section the memo if it is not already in the memo
    public void addSection(Section s) {
        sections.add(s);
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

    public List<String> listSectionNames() {
        List<String> result = new ArrayList<>();
        for (Section s : sections) {
            result.add(s.getName());
        }
        return result;
    }

    // EFFECTS: returns how many sections are in the memo
    public int countSections() {
        return sections.size();
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

    // EFFECTS: prints the memo with the given name
    public void printMemo() {
        for (Bar b : bars) {
            b.printBar();
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Memo memo = (Memo) o;
        return Objects.equals(bars, memo.bars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bars);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }
}
