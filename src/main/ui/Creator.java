package ui;

import model.Chord;
import model.MusicalObject;
import model.Note;
import model.Rest;
import model.exceptions.DegreeException;
import model.exceptions.NameException;
import model.exceptions.OctaveException;
import model.exceptions.QualityException;
import ui.commands.CreateNewMemo;

public class Creator {
    CreateNewMemo command = new CreateNewMemo();

    public MusicalObject makeNewObject() {
//        CreateNewMemo c = new CreateNewMemo();
        MusicalObject object = makeMusicalObject(command.getObjectType());
        return object;
    }

    // EFFECTS: returns the appropriate object type to be created
    public MusicalObject makeMusicalObject(int type) {
        MusicalObject mo;
        double noteDur = getDuration();
        if (type == 1) {
            mo = new Note(getNoteName(), getValidOctave(), getValidDegree(), noteDur);
        } else if (type == 2) {
            mo = new Chord(getChordName(), getValidQuality(), getExtension(), noteDur);
        } else if (type == 3) {
            mo = new Rest(noteDur);
        } else {
            mo = null;
        }
        return mo;
    }

    // MODIFIES: this
    // EFFECTS: sets the note name to a valid note name
    public String getNoteName() {
//        CreateNewMemo c = new CreateNewMemo();
        String name;
        while (true) {
            try {
                name = command.getNoteName();
                break;
            } catch (NameException e) {
                System.out.println("Invalid note name. Please try again.");
            }
        }
        return name;
    }

    // MODIFIES: this
    // EFFECTS: sets the note name to a valid note name
    public String getChordName() {
//        CreateNewMemo c = new CreateNewMemo();
        String name;
        while (true) {
            try {
                name = command.getChordName();
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
//        CreateNewMemo c = new CreateNewMemo();
        int octave;
        while (true) {
            try {
                octave = command.getNoteOctave();
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
//        CreateNewMemo c = new CreateNewMemo();
        int degree;
        while (true) {
            try {
                degree = command.getNoteDegree();
                break;
            } catch (DegreeException e) {
                System.out.println("Invalid note degree. Please try again.");
//                newDegree = c.getNoteDegree();
            }
        }
        return degree;
    }

    public double getDuration() {
        return command.getObjectDuration();
    }

    // MODIFIES: this
    // EFFECTS: sets the chord quality to a valid chord quality
    public String getValidQuality() {
//        CreateNewMemo c = new CreateNewMemo();
        String quality;
        while (true) {
            try {
                quality = command.getChordQuality();
                break;
            } catch (QualityException e) {
                System.out.println("Invalid chord quality. Please try again.");
//                newQuality = c.getChordQuality();
            }
        }
        return quality;
    }

    public String getExtension() {
        return command.getChordExtensions();
    }
}
