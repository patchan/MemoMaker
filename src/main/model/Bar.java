package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Bar implements Serializable {
    private ArrayList<Note> notes;

    // EFFECTS: constructs an empty bar
    public Bar() {
        this.notes = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a Note to this Bar
    public void addToBar(Note note) {
        notes.add(note);
        System.out.println("The note \"" + note.compositeName() + "\" has been added to the bar.");
    }

    // EFFECTS: produces a list of all the composite Note names in the bar
    public ArrayList<String> getBar() {
        ArrayList<String> notesList = new ArrayList<>();
        for (Note note : notes) {
            String noteName;
            noteName = note.compositeName();
            notesList.add(noteName);
        }
        return notesList;
    }

    // EFFECTS: prints list of names of all notes in the bar
    public void printBar() {
        ArrayList<String> notesList;
        notesList = getBar();
        System.out.println(notesList);
    }

    // EFFECTS: returns true if Note n is in the bar
    public Boolean barContains(Note n) {
        return notes.contains(n);
    }

    // EFFECTS: returns the size of the bar
    public int barSize() {
        return notes.size();
    }
}
