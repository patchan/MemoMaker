package model;

import java.io.Serializable;

public class Note extends MusicalObject implements Serializable {
    private static final int NATURAL = 0;
    private static final int SHARP = 1;
    private static final int FLAT = -1;

    private int degree;
    private int octave;

    // EFFECTS: constructs a Note with the parameter name, octave, and the degree set to natural
    public Note(String name, int octave, int degree) {
        this.name = name;
        this.octave = octave;
        this.degree = degree;
    }

    // REQUIRES: octave is a non-negative integer
    // MODIFIES: this
    // EFFECTS: sets the note octave of this note
    public void setOctave(int octave) {
        this.octave = octave;
    }

    // REQUIRES: degree is integer -1, 0, or 1
    // MODIFIES: this
    // EFFECTS: sets the note degree of this note
    public void setDegree(int degree) {
        this.degree = degree;
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
        String accidental = null;
        if (degree == FLAT) {
            accidental = "b";
        } else if (degree == NATURAL) {
            accidental = "";
        } else if (degree == SHARP) {
            accidental = "#";
        }
        return accidental;
    }

    // EFFECTS: produces a composite name with the note name and degree symbol
    @Override
    protected String getCompositeName() {
        String compositeName;
        compositeName = this.name + this.octave + getDegreeSymbol();
        return compositeName;
    }

    // EFFECTS: prints the note composite name and returns "Note: compositeName"
    @Override
    protected String printName() {
        String compositeName = getCompositeName();
        System.out.println("Note: " + compositeName);
        return "Note: " + compositeName;
    }

}
