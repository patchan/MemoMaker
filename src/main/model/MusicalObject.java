package model;

import model.exceptions.NameException;

import java.io.Serializable;

public abstract class MusicalObject implements Serializable {

    protected String name;
    protected double duration;

    public MusicalObject() {
    }

    // MODIFIES: this
    // EFFECTS: sets the name of this musical object if it is a valid note name
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

    // EFFECTS: produces a string with the object type
    protected abstract String getType();

    // EFFECTS: produces the composite name of this musical object
    protected abstract String getCompositeName();

    // EFFECTS: returns "Type: CompositeName" of the MusicalObject
    public String printName() {
        return getType() + ": " + getCompositeName();
    }

    // MODIFIES: this
    // EFFECTS: creates a musical object of a given type
    protected abstract void makeMusicalObject(double duration);
}
