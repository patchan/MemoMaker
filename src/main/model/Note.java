package model;

public class Note {
    private static final int NATURAL = 0;
    private static final int SHARP = 1;
    private static final int FLAT = -1;

    private String name;
    private int degree;

    // EFFECTS: constructs a Note with the parameter name and the degree set to natural
    public Note(String name) {
        this.name = name;
        this.degree = NATURAL;
    }

    // REQUIRES: name is single character string
    // MODIFIES: this
    // EFFECTS: sets the note name of this note
    public void setNote(String name) {
        this.name = name;
    }

    // REQUIRES: degree is integer -1, 0, or 1
    // MODIFIES: this
    // EFFECTS: sets the note degree of this note
    public void setDegree(int degree) {
        this.degree = degree;
    }

    // EFFECTS: produces the note name
    public String getNote() {
        return this.name;
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
    public String compositeName() {
        String compositeName;
        compositeName = this.name + this.getDegreeSymbol();
        return compositeName;
    }

}
