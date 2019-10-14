package model;

import model.exceptions.DegreeException;
import model.exceptions.NameException;
import model.exceptions.OctaveException;
import ui.commands.CreateNewMemo;

import java.io.Serializable;

public class Note extends MusicalObject implements Serializable {
    private static final int NATURAL = 0;
    private static final int SHARP = 1;
    private static final int FLAT = -1;

    private int degree;
    private int octave;

    // EFFECTS: default constructor for Note
    public Note() {}

    // EFFECTS: constructs a Note with name, octave, degree, and duration
    public Note(String name, int octave, int degree, double duration) {
        this.name = name;
        this.octave = octave;
        this.degree = degree;
        this.duration = duration;
    }

    // MODIFIES: this
    // EFFECTS: sets the note octave of this note
    public void setOctave(int octave) throws OctaveException {
        if (octave >= 0 && octave <= 8) {
            this.octave = octave;
        } else {
            throw new OctaveException();
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the note degree of this note
    public void setDegree(int degree) throws DegreeException {
        if (degree < -1 || degree > 1) {
            throw new DegreeException();
        } else {
            this.degree = degree;
        }
    }

    // EFFECTS: produces the note octave
    public int getOctave() {
        return this.octave;
    }

    // EFFECTS: produces the note degree
    public int getDegree() {
        return this.degree;
    }

    // REQUIRES: degree is integer -1, 0, or -1
    // EFFECTS: produces symbolized note degree where
    //          FLAT = "b", NATURAL = "", and SHARP = "#"
    public String getDegreeSymbol() {
        String accidental;
        if (degree == FLAT) {
            accidental = "b";
        } else if (degree == NATURAL) {
            accidental = "";
        } else if (degree == SHARP) {
            accidental = "#";
        } else {
            accidental = null;
        }
        return accidental;
    }

    // EFFECTS: creates a new note
    @Override
    protected void makeMusicalObject(double noteDur) {
        CreateNewMemo c = new CreateNewMemo();
        setValidName(c.getNoteName());
        setValidOctave(c.getNoteOctave());
        setValidDegree(c.getNoteDegree());
        setDuration(noteDur);
    }

    // MODIFIES: this
    // EFFECTS: sets the note name to a valid note name
    public void setValidName(String name) {
        CreateNewMemo c = new CreateNewMemo();
        String newName = name;
        while (true) {
            try {
                setName(newName);
                break;
            } catch (NameException e) {
                System.out.println("Invalid note name. Please try again.");
                newName = c.getNoteName();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the note octave to a valid note octave
    public void setValidOctave(int octave) {
        CreateNewMemo c = new CreateNewMemo();
        int newOctave = octave;
        while (true) {
            try {
                setOctave(newOctave);
                break;
            } catch (OctaveException e) {
                System.out.println("Invalid octave range. Please try again.");
                newOctave = c.getNoteOctave();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the note degree to a valid note degree
    public void setValidDegree(int degree) {
        CreateNewMemo c = new CreateNewMemo();
        int newDegree = degree;
        while (true) {
            try {
                setDegree(newDegree);
                break;
            } catch (DegreeException e) {
                System.out.println("Invalid note degree. Please try again.");
                newDegree = c.getNoteDegree();
            }
        }
    }

    // EFFECTS: returns "Note"
    @Override
    protected String getType() {
        return "Note";
    }

    // EFFECTS: produces a composite name with the note name and degree symbol
    @Override
    protected String getCompositeName() {
        return this.name + this.octave + getDegreeSymbol();
    }

}
