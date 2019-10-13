package model;

import exceptions.NameException;

import java.io.Serializable;

public abstract class MusicalObject implements Serializable {

    protected String name;
    protected double duration;

    public MusicalObject() {
    }

    // REQUIRES: name is single character string
    // MODIFIES: this
    // EFFECTS: sets the name of this musical object
    public void setName(String name) throws NameException {
        if (isValidName(name)) {
            this.name = name;
        } else {
            throw new NameException();
        }
    }

    // EFFECTS: produces true if the string is a valid note name, false otherwise
    private boolean isValidName(String name) {
        for (NoteName n : NoteName.values()) {
            if (n.getNoteName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    // REQUIRES: duration is double 0.25, 0.5, 1, or 2
    // MODIFIES: this
    // EFFECTS: sets the duration of this musical object
    public void setDuration(double duration) {
        this.duration = duration;
    }

    // EFFECTS: produces the name of this musical object
    public String getName() {
        return this.name;
    }

    // EFFECTS: produces the duration of this musical object
    public double getDuration() {
        return this.duration;
    }

    // EFFECTS: produces the composite name of this musical object
    abstract String getCompositeName();

    // EFFECTS: prints the name of this musical object
    abstract String printName();

    // MODIFIES: this
    // EFFECTS: creates a musical object of a given type
    abstract void makeMusicalObject(double duration);
}
