package model;

import java.io.Serializable;

public class Rest extends MusicalObject implements Serializable {

    // EFFECTS: default constructor for Rest
    public Rest() {}

    // EFFECTS: constructs a Rest with duration
    public Rest(double duration) {
        this.duration = duration;
    }

    // EFFECTS: creates a new rest
    @Override
    protected void makeMusicalObject(double restDur) {
        setDuration(restDur);
    }

    // EFFECTS: returns "Rest"
    @Override
    protected String getType() {
        return "Rest";
    }

    // EFFECTS: returns the composite rest name
    @Override
    protected String getCompositeName() {
        String compositeName;
        if (duration == 1) {
            compositeName = "Q-rest";
        } else if (duration == 0.25) {
            compositeName = "16-rest";
        } else if (duration == 0.5) {
            compositeName = "8-rest";
        } else if (duration == 2) {
            compositeName = "H-rest";
        } else if (duration == 4) {
            compositeName = "W-rest";
        } else {
            compositeName = null;
        }
        return compositeName;
    }

}
