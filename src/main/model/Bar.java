package model;

import java.util.ArrayList;
import java.util.Scanner;

public class Bar {
    public ArrayList<Note> bar;
    public Note note;
    // the implementation of the Scanner to receive user input was adapted from the B04 SimpleCalculator
    private Scanner scanner = new Scanner(System.in);

    public Bar() {
        bar = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: produces a bar with four quarter notes
    public void makeBar() {
        int i = 0;
        int barLength = 4;
        while (i < barLength) {
            String name = getNoteInput();
            note = new Note(name);
            int degree = getDegreeInput();
            note.setDegree(degree);
            addToBar(note);
            i++;
        }
    }

    // REQUIRES: input is single character string
    // EFFECTS: returns user input for note name as string
    public String getNoteInput() {
        System.out.println("Enter a quarter note:");
        String name = scanner.nextLine();
        System.out.println("You have entered an: " + name);
        return name;
    }

    // REQUIRES: input is integer -1, 0, or 1
    // EFFECTS: returns user input for note degree as an integer
    public int getDegreeInput() {
        System.out.println("Enter an accidental (-1 for flat, 0 for natural, 1 for sharp):");
        int degree = scanner.nextInt();
        scanner.nextLine();
        return degree;
    }

    // MODIFIES: this
    // EFFECTS: adds a Note to this Memo
    public void addToBar(Note note) {
        bar.add(note);
        System.out.println("The note \"" + note.compositeName() + "\" has been added to the bar.");
    }

    // EFFECTS: produces a list of all the composite Note names in the bar
    public ArrayList<String> getBar() {
        ArrayList<String> notesList = new ArrayList<>();
        for (Note note : bar) {
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
        for (String s : notesList) {
            System.out.println(s);
        }
    }

    // EFFECTS: returns true if Note n is in the bar
    public Boolean barContains(Note n) {
        return bar.contains(n);
    }

    // EFFECTS: returns the size of the bar
    public int barSize() {
        return bar.size();
    }
}
