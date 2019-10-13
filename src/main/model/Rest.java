package model;

import java.io.Serializable;

public class Rest extends MusicalObject implements Serializable {

    public Rest() {}

    public Rest(double restDur) {
        this.duration = restDur;
    }

    // EFFECTS: creates a new rest
    @Override
    protected void makeMusicalObject(double restDur) {
        setDuration(restDur);
    }

    // EFFECTS: returns the composite rest name
    @Override
    protected String getCompositeName() {
        String compositeName = null;
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
        }
        return compositeName;
    }

    // EFFECTS: prints the rest name and returns "Rest: compositeName"
    @Override
    protected String printName() {
        String compositeName = getCompositeName();
        System.out.println("Rest: " + compositeName);
        return "Rest: " + compositeName;
    }
}
