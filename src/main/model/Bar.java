package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Bar implements Serializable {
    private Bar bar;
    private ArrayList<Note> notes;
    private Note note;
    // the implementation of the Scanner to receive user input was adapted from the B04 SimpleCalculator
    private transient Scanner scanner = new Scanner(System.in);

    // EFFECTS: constructs an empty bar
    public Bar() {
        this.notes = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: produces a bar with four quarter notes
    public void makeBar() {
        int i = 0;
        int barLength = 4;
        while (i < barLength) {
            String name = getNoteName();
            int octave = getNoteOctave();
            note = new Note(name, octave);
            int degree = getNoteDegree();
            note.setDegree(degree);
            addToBar(note);
            i++;
        }
    }

    // REQUIRES: input is single character string
    // EFFECTS: returns user input for note name as string
    private String getNoteName() {
        System.out.println("Enter a quarter note:");
        String name = scanner.nextLine();
        return name;
    }

    // REQUIRES: input is single character string
    // EFFECTS: returns user input for note name as string
    private int getNoteOctave() {
        System.out.println("Enter the octave of the note:");
        int octave = scanner.nextInt();
        return octave;
    }

    // REQUIRES: input is integer -1, 0, or 1
    // EFFECTS: returns user input for note degree as an integer
    private int getNoteDegree() {
        System.out.println("Enter an accidental (-1 for flat, 0 for natural, 1 for sharp):");
        int degree = scanner.nextInt();
        scanner.nextLine();
        return degree;
    }

    // MODIFIES: this
    // EFFECTS: adds a Note to this Memo
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
