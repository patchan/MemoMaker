package model;

import ui.commands.CreateNewMemo;

import java.io.Serializable;
import java.util.ArrayList;

public class Bar implements Serializable {
    private ArrayList<MusicalObject> musicalObjects;

    // EFFECTS: constructs an empty bar
    public Bar() {
        this.musicalObjects = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: creates a new bar with musical objects
    public void makeBar() {
        CreateNewMemo c = new CreateNewMemo();
        double i = 0;
        int barLength = c.getBarLength();
        while (i < barLength) {
            MusicalObject newObject = setObjectType(c.getObjectType());
            newObject.makeMusicalObject(c.getObjectDuration());
            addToBar(newObject);
            i = i + newObject.getDuration();
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a Note to this Bar
    public void addToBar(MusicalObject mo) {
        musicalObjects.add(mo);
        System.out.println(mo.printName() + " has been added to the bar.");
    }

    public MusicalObject setObjectType(int type) {
        MusicalObject mo = null;
        if (type == 1) {
            mo = new Note();
        } else if (type == 2) {
            mo = new Chord();
        } else if (type == 3) {
            mo = new Rest();
        }
        return mo;
    }

    // EFFECTS: produces a list of all the musical objects in the bar
    public ArrayList<String> getBar() {
        ArrayList<String> objectList = new ArrayList<>();
        for (MusicalObject mo : musicalObjects) {
            String objectName;
            objectName = mo.getCompositeName();
            objectList.add(objectName);
        }
        return objectList;
    }

    // EFFECTS: prints the composite names of all the musical objects in the bar as a list
    public void printBar() {
        ArrayList<String> objectList;
        objectList = getBar();
        System.out.println(objectList);
    }

    // REQUIRES: int i is the 1-based position in the bar
    // MODIFIES: this
    // EFFECTS: removes this musical object from the bar
    public void removeObject(int i) {
        musicalObjects.remove(i - 1);
    }

    // EFFECTS: returns true if MusicalObject mo is in the bar
    public Boolean barContains(MusicalObject mo) {
        return musicalObjects.contains(mo);
    }

    // EFFECTS: returns the size of the bar
    public int barSize() {
        return musicalObjects.size();
    }
}
