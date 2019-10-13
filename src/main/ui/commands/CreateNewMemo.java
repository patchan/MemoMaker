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
            newBar.makeBar();
            memo.addToMemo(newBar);
            i++;
        }
        System.out.println("A new memo was created.");
        memo.printMemo();
    }

    // REQUIRES: input is integer 1, 2, or 3
    // EFFECTS: returns user input for the object type to be created
    public int getObjectType() {
        System.out.println("What do you want to add to the bar? (1 for note, 2 for chord, 3 for rest)");
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    // REQUIRES: input is single character string
    // EFFECTS: returns user input for note name as string
    public String getNoteName() {
        System.out.println("Enter a note name:");
        return scanner.nextLine();
    }

    // REQUIRES: input is single character string
    // EFFECTS: returns user input for note name as string
    public double getObjectDuration() {
        System.out.println("Enter a note duration:");
        System.out.println("Use 1 for quarter, 0.5 for 8th, 0.25 for 16th, 2 for half, 4 for whole");
        double noteDur = scanner.nextDouble();
        scanner.nextLine();
        return noteDur;
    }

    // REQUIRES: input is single character string
    // EFFECTS: returns user input for note name as string
    public int getNoteOctave() {
        System.out.println("Enter the octave of the note:");
        int octave = scanner.nextInt();
        scanner.nextLine();
        return octave;
    }

    // REQUIRES: input is integer -1, 0, or 1
    // EFFECTS: returns user input for note degree as an integer
    public int getNoteDegree() {
        System.out.println("Enter an accidental (-1 for flat, 0 for natural, 1 for sharp):");
        int degree = scanner.nextInt();
        scanner.nextLine();
        return degree;
    }

    // REQUIRES: input is single character string
    // EFFECTS: returns user input for chord name as string
    public String getChordName() {
        System.out.println("Enter a chord root:");
        return scanner.nextLine();
    }

    // REQUIRES: input must be one of "maj" "min" "aug" or "dim"
    // EFFECTS: returns user input for chord quality as string
    public String getChordQuality() {
        System.out.println("Enter (maj, min, aug, or dim) as the chord quality:");
        return scanner.nextLine();
    }

    // EFFECTS: returns user input for chord extensions as string
    public String getChordExtensions() {
        System.out.println("Enter chord extensions:");
        return scanner.nextLine();
    }

    // EFFECTS: returns user input for chord extensions as string
    public int getChordNotes() {
        System.out.println("How many notes are in your chord?");
        int notes = scanner.nextInt();
        scanner.nextLine();
        return notes;
    }

    // EFFECTS: returns user input for the number of bars to create in the memo
    public int getScoreLength() {
        System.out.println("How many bars would you like to add?");
        int numBars;
        numBars = scanner.nextInt();
        scanner.nextLine();
        return numBars;
    }

    // EFFECTS: returns user input for the number of bars to create in the memo
    public int getBarLength() {
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
