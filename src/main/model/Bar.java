package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Bar implements Serializable {
    private ArrayList<MusicalObject> musicalObjects;

    // EFFECTS: constructs an empty bar
    public Bar() {
        this.musicalObjects = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a Note to this Bar
    public void addToBar(MusicalObject mo) {
        musicalObjects.add(mo);
        System.out.println(mo.printName() + " has been added to the bar.");
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
