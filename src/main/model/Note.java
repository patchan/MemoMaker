package model;

import java.io.Serializable;

public class Note extends MusicalObject implements Serializable {
    private static final int NATURAL = 0;
    private static final int SHARP = 1;
    private static final int FLAT = -1;

    private int degree;
    private int octave;

    // EFFECTS: constructs a Note with name, octave, degree, and duration
    public Note(String name, int octave, int degree, double duration) {
        this.name = name;
        this.octave = octave;
        this.degree = degree;
        this.duration = duration;
    }

    // MODIFIES: this
    // EFFECTS: sets the note octave of this note
    public void setOctave(int octave) {
        this.octave = octave;
    }

    public static boolean isValidOctave(int octave) {
        return octave >= 0 && octave <= 8;
    }

    // MODIFIES: this
    // EFFECTS: sets the note degree of this note
    public void setDegree(int degree) {
        this.degree = degree;
    }

    public static boolean isValidDegree(int degree) {
        return !(degree < -1 || degree > 1);
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

    // EFFECTS: produces a composite name with the note name and degree symbol
    @Override
    public String getCompositeName() {
        return this.name + getDegreeSymbol() + this.octave;
    }

}
