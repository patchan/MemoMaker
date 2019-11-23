package ui;

import model.Chord;
import model.MusicalObject;
import model.Note;
import model.Rest;
import model.exceptions.DegreeException;
import model.exceptions.NameException;
import model.exceptions.OctaveException;
import model.exceptions.QualityException;

import java.util.Scanner;

import static model.Note.isValidOctave;

public class CreatorTool {
    private transient Scanner scanner = new Scanner(System.in);

    public MusicalObject makeNewObject() {
        MusicalObject object = makeMusicalObject(getObjectType());
        return object;
    }

    // EFFECTS: returns the appropriate object type to be created
    public MusicalObject makeMusicalObject(int type) {
        MusicalObject mo;
        double noteDur = getDuration();
        if (type == 1) {
            mo = new Note(getValidNoteName(), getValidOctave(), getValidDegree(), noteDur);
        } else if (type == 2) {
            mo = new Chord(getValidChordName(), getValidQuality(), getChordExtensions(), noteDur);
            addChordNotes((Chord) mo, noteDur);
        } else if (type == 3) {
            mo = new Rest(noteDur);
        } else {
            mo = null;
        }
        return mo;
    }

    public void addChordNotes(Chord chord, double noteDur) {
        int chordNotes = getChordNotes();
        for (int i = 0; i < chordNotes; i++) {
            Note mo = new Note(getValidNoteName(), getValidOctave(), getValidDegree(), noteDur);
            chord.addNotes(mo);
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the note name to a valid note name
    public String getValidNoteName() {
        String name;
        while (true) {
            try {
                name = getNoteName();
                break;
            } catch (NameException e) {
                System.out.println("Invalid note name. Please try again.");
            }
        }
        return name;
    }

    // MODIFIES: this
    // EFFECTS: sets the note name to a valid note name
    public String getValidChordName() {
        String name;
        while (true) {
            try {
                name = getChordName();
                break;
            } catch (NameException e) {
                System.out.println("Invalid note name. Please try again.");
            }
        }
        return name;
    }

    // MODIFIES: this
    // EFFECTS: sets the note octave to a valid note octave
    public int getValidOctave() {
        int octave;
        while (true) {
            try {
                octave = getNoteOctave();
                break;
            } catch (OctaveException e) {
                System.out.println("Invalid octave range. Please try again.");
            }
        }
        return octave;
    }

    // MODIFIES: this
    // EFFECTS: sets the note degree to a valid note degree
    public int getValidDegree() {
        int degree;
        while (true) {
            try {
                degree = getNoteDegree();
                break;
            } catch (DegreeException e) {
                System.out.println("Invalid note degree. Please try again.");
            }
        }
        return degree;
    }

    // MODIFIES: this
    // EFFECTS: sets the chord quality to a valid chord quality
    public String getValidQuality() {
        String quality;
        while (true) {
            try {
                quality = getChordQuality();
                break;
            } catch (QualityException e) {
                System.out.println("Invalid chord quality. Please try again.");
            }
        }
        return quality;
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
    //          throws NameException if name is not valid
    public String getNoteName() throws NameException {
        System.out.println("Enter a note name:");
        return getName();
    }

    // EFFECTS: gets user input for the note name
    //          throws NameException if name is not valid
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
    public double getDuration() {
        System.out.println("Enter a note duration:");
        System.out.println("Use 1 for quarter, 0.5 for 8th, 0.25 for 16th, 2 for half, 4 for whole");
        double noteDur = scanner.nextDouble();
        scanner.nextLine();
        return noteDur;
    }

    // REQUIRES: input is single character string
    // EFFECTS: returns user input for note name as string
    //          throws OctaveException if octave input is not valid
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
    //          throws DegreeException if degree input is not valid
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
    //          throws NameException if name is not valid
    public String getChordName() throws NameException {
        System.out.println("Enter a chord root:");
        return getName();
    }

    // REQUIRES: input must be one of "maj" "min" "aug" or "dim"
    // EFFECTS: returns user input for chord quality as string
    //          throws QualityException if chord quality is not valid
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
}
