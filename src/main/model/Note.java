package model;

import java.io.Serializable;

public abstract class Note implements Serializable {
    private static final int NATURAL = 0;
    private static final int SHARP = 1;
    private static final int FLAT = -1;

    private String name;
    private int degree;
    private int octave;
    protected double duration;

    // EFFECTS: constructs a Note with the parameter name, octave, and the degree set to natural
    public Note(String name, int octave, int degree) {
        this.name = name;
        this.octave = octave;
        this.degree = degree;
    }

    // REQUIRES: name is single character string
    // MODIFIES: this
    // EFFECTS: sets the note name of this note
    protected void setNoteName(String name) {
        this.name = name;
    }

    // REQUIRES: octave is a non-negative integer
    // MODIFIES: this
    // EFFECTS: sets the note octave of this note
    protected void setOctave(int octave) {
        this.octave = octave;
    }

    // REQUIRES: degree is integer -1, 0, or 1
    // MODIFIES: this
    // EFFECTS: sets the note degree of this note
    protected void setDegree(int degree) {
        this.degree = degree;
    }

    // EFFECTS: produces the note name
    protected String getNoteName() {
        return this.name;
    }

    // EFFECTS: produces the note octave
    protected int getOctave() {
        return this.octave;
    }

    // EFFECTS: produces the note degree
    protected int getDegree() {
        return this.degree;
    }

    // REQUIRES: degree is integer -1, 0, or -1
    // EFFECTS: produces symbolized note degree where
    //          FLAT = "b", NATURAL = "", and SHARP = "#"
    protected String getDegreeSymbol() {
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
    protected String compositeName() {
        String compositeName;
        compositeName = this.name + this.octave + this.getDegreeSymbol();
        return compositeName;
    }

}
