package model;

import java.io.Serializable;

public abstract class MusicalObject implements Serializable {

    protected String name;
    protected double duration;

    public MusicalObject() {
    }

    // REQUIRES: name is single character string
    // MODIFIES: this
    // EFFECTS: sets the name of this musical object
    public void setName(String name) {
        this.name = name;
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
