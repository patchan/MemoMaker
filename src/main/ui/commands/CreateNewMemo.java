package ui.commands;

import model.*;

import java.util.Scanner;

public class CreateNewMemo implements Command {
    // the implementation of the Scanner to receive user input was adapted from the B04 SimpleCalculator
    private transient Scanner scanner = new Scanner(System.in);

    public CreateNewMemo() {}

    // EFFECTS: creates a new memo
    @Override
    public void executeCommand(Memo memo) {
        int numBars = getScoreLength();
        int i = 0;
        memo.clearMemo();
        while (i < numBars) {
            Bar newBar = new Bar();
            makeBar(newBar);
            memo.addToMemo(newBar);
            i++;
        }
        System.out.println("A new memo was created.");
        memo.printMemo();
    }

    // EFFECTS: creates a new bar with musical objects
    protected void makeBar(Bar bar) {
        double i = 0;
        int barLength = getBarLength();
        while (i < barLength) {
            MusicalObject newObject = makeMusicalObject(getObjectType());
            bar.addToBar(newObject);
            i = i + newObject.getDuration();
        }
    }

    // REQUIRES: input must be integer 1, 2, or 3
    // EFFECTS: creates a new musical object (either Note, Chord, or Rest) based on user input
    protected MusicalObject makeMusicalObject(int input) {
        MusicalObject mo = null;
        if (input == 1) {
            mo = makeNote(getObjectDuration());
        } else if (input == 2) {
            mo = makeChord(getObjectDuration(), getChordNotes());
        } else if (input == 3) {
            mo = makeRest(getObjectDuration());
        }
        return mo;
    }

    // EFFECTS: creates a new chord
    protected Chord makeChord(double noteDur, int chordNotes) {
        Chord mo = new Chord(getChordName(), getChordQuality(), getChordExtensions());
        mo.setDuration(noteDur);
        int i = 0;
        while (i < chordNotes) {
            mo.addNotes(makeNote(noteDur));
            i++;
        }
        return mo;
    }

    // EFFECTS: creates a new note
    protected Note makeNote(double noteDur) {
        Note mo = new Note(getNoteName(), getNoteOctave(), getNoteDegree());
        mo.setDuration(noteDur);
        return mo;
    }

    // EFFECTS: creates a new rest
    protected Rest makeRest(double restDur) {
        Rest mo = new Rest(restDur);
        return mo;
    }

    // REQUIRES: input is integer 1, 2, or 3
    // EFFECTS: returns user input for the object type to be created
    private int getObjectType() {
        System.out.println("What do you want to add to the bar? (1 for note, 2 for chord, 3 for rest)");
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    // REQUIRES: input is single character string
    // EFFECTS: returns user input for note name as string
    protected String getNoteName() {
        System.out.println("Enter a note name:");
        String name = scanner.nextLine();
        return name;
    }

    // REQUIRES: input is single character string
    // EFFECTS: returns user input for note name as string
    protected double getObjectDuration() {
        System.out.println("Enter a note duration:");
        System.out.println("Use 1 for quarter, 0.5 for 8th, 0.25 for 16th, 2 for half, 4 for whole");
        double noteDur = scanner.nextDouble();
        scanner.nextLine();
        return noteDur;
    }

    // REQUIRES: input is single character string
    // EFFECTS: returns user input for note name as string
    protected int getNoteOctave() {
        System.out.println("Enter the octave of the note:");
        int octave = scanner.nextInt();
        scanner.nextLine();
        return octave;
    }

    // REQUIRES: input is integer -1, 0, or 1
    // EFFECTS: returns user input for note degree as an integer
    protected int getNoteDegree() {
        System.out.println("Enter an accidental (-1 for flat, 0 for natural, 1 for sharp):");
        int degree = scanner.nextInt();
        scanner.nextLine();
        return degree;
    }

    // REQUIRES: input is single character string
    // EFFECTS: returns user input for chord name as string
    protected String getChordName() {
        System.out.println("Enter a chord root:");
        String name = scanner.nextLine();
        return name;
    }

    // REQUIRES: input must be one of "maj" "min" "aug" or "dim"
    // EFFECTS: returns user input for chord quality as string
    protected String getChordQuality() {
        System.out.println("Enter (maj, min, aug, or dim) as the chord quality:");
        String name = scanner.nextLine();
        return name;
    }

    // EFFECTS: returns user input for chord extensions as string
    protected String getChordExtensions() {
        System.out.println("Enter chord extensions:");
        String name = scanner.nextLine();
        return name;
    }

    // EFFECTS: returns user input for chord extensions as string
    protected int getChordNotes() {
        System.out.println("How many notes are in your chord?");
        int notes = scanner.nextInt();
        scanner.nextLine();
        return notes;
    }

    // EFFECTS: returns user input for the number of bars to create in the memo
    protected int getScoreLength() {
        System.out.println("How many bars would you like to add?");
        int numBars;
        numBars = scanner.nextInt();
        scanner.nextLine();
        return numBars;
    }

    // EFFECTS: returns user input for the number of bars to create in the memo
    protected int getBarLength() {
        System.out.println("How many quarter notes are in your bar?");
        int barLength;
        barLength = scanner.nextInt();
        scanner.nextLine();
        return barLength;
    }

    @Override
    public boolean equals(Object command) {
        return command instanceof CreateNewMemo;
    }

}
