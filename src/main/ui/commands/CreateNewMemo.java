package ui.commands;

import model.*;
import model.exceptions.DegreeException;
import model.exceptions.NameException;
import model.exceptions.OctaveException;
import model.exceptions.QualityException;

import java.util.Scanner;

import static model.Note.*;


public class CreateNewMemo implements Command {
    // the implementation of the Scanner to receive user input was adapted from the B04 SimpleCalculator
    private transient Scanner scanner = new Scanner(System.in);

    public CreateNewMemo() {}

    // EFFECTS: creates a new memo
    @Override
    public void executeCommand(Library library) {
        String name = getMemoName();
        library.addNewMemo(name);
        Memo memo = library.getMemo(name);
        int numBars = getScoreLength();
        int i = 0;
        while (i < numBars) {
            Bar newBar = new Bar(i + 1);
            newBar.makeBar(getBarLength());
            memo.addToMemo(newBar);
            i++;
            System.out.println("Bar " + newBar.getBarNum() + " added.");
        }
        System.out.println("A new memo was created. It contains:");
        library.printMemo(name);
    }

    // EFFECTS: returns user input for the name of the memo to create
    public String getMemoName() {
        System.out.println("Enter a memo name:");
        return scanner.nextLine();
    }

    // EFFECTS: returns user input for the object type to be created
    public int getObjectType() {
        System.out.println("What do you want to add to the bar? (1 for note, 2 for chord, 3 for rest)");
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    // REQUIRES: input is single character string
    // EFFECTS: returns user input for note name as string
    public String getNoteName() throws NameException {
        System.out.println("Enter a note name:");
        return getName();
    }

    public String getName() throws NameException {
        String result = scanner.nextLine();
        if (MusicalObject.isValidName(result)) {
            return result;
        } else {
            throw new NameException();
        }
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
    public int getNoteOctave() throws OctaveException {
        System.out.println("Enter the octave of the note:");
        int octave = scanner.nextInt();
        scanner.nextLine();
        if (isValidOctave(octave)) {
            return octave;
        } else {
            throw new OctaveException();
        }
    }

    // REQUIRES: input is integer -1, 0, or 1
    // EFFECTS: returns user input for note degree as an integer
    public int getNoteDegree() throws DegreeException {
        System.out.println("Enter an accidental (-1 for flat, 0 for natural, 1 for sharp):");
        int degree = scanner.nextInt();
        scanner.nextLine();
        if (Note.isValidDegree(degree)) {
            return degree;
        } else {
            throw new DegreeException();
        }
    }

    // REQUIRES: input is single character string
    // EFFECTS: returns user input for chord name as string
    public String getChordName() throws NameException {
        System.out.println("Enter a chord root:");
        return getName();
    }

    // REQUIRES: input must be one of "maj" "min" "aug" or "dim"
    // EFFECTS: returns user input for chord quality as string
    public String getChordQuality() throws QualityException {
        System.out.println("Enter (maj, min, aug, or dim) as the chord quality:");
        String result = scanner.nextLine();
        if (Chord.isValidQuality(result)) {
            return result;
        } else {
            throw new QualityException();
        }
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
        System.out.println("How many bars would you like to add to this memo?");
        int numBars;
        numBars = scanner.nextInt();
        scanner.nextLine();
        return numBars;
    }

    // EFFECTS: returns user input for the number of bars to create in the memo
    public int getBarLength() {
        System.out.println("How many quarter notes are in this bar?");
        int barLength;
        barLength = scanner.nextInt();
        scanner.nextLine();
        return barLength;
    }

}
