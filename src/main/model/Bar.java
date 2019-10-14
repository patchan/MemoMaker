package model;

import model.exceptions.BarLengthException;
import ui.commands.CreateNewMemo;

import java.io.Serializable;
import java.util.ArrayList;

public class Bar implements Serializable {
    private ArrayList<MusicalObject> musicalObjects;
    private int barline;

    // EFFECTS: constructs an empty bar
    public Bar() {
        this.musicalObjects = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: creates a new bar with musical objects
    public void makeBar() {
        CreateNewMemo c = new CreateNewMemo();
        double i = 0;
        barline = c.getBarLength();
        while (i < barline) {
            MusicalObject newObject = setObjectType(c.getObjectType());
            double objectLength = c.getObjectDuration();
            newObject.makeMusicalObject(objectLength);
            try {
                addToBar(newObject);
                i = i + newObject.getDuration();
            } catch (BarLengthException e) {
                System.out.println("That note doesn't fit in the bar. \nThe bar is currently "
                        + totalObjectLength() + " quarter notes long. \nPlease try again.");
            } finally {
                printBar();
            }
        }
    }

    // EFFECTS: produce true if the object fits within the bar
    public boolean withinBarLength(double objectLength) {
        return barline >= objectLength + totalObjectLength();
    }

    // EFFECTS: returns the total length of the current objects in the bar
    public double totalObjectLength() {
        double totLength = 0;
        for (MusicalObject mo : musicalObjects) {
            totLength = totLength + mo.getDuration();
        }
        return totLength;
    }

    // MODIFIES: this
    // EFFECTS: adds a MusicalObject to this Bar if it fits in the bar
    public void addToBar(MusicalObject mo) throws BarLengthException {
        if (withinBarLength(mo.getDuration())) {
            musicalObjects.add(mo);
            System.out.println(mo.printName() + " has been added to the bar.");
        } else {
            throw new BarLengthException();
        }
    }

    // MODIFIES: this
    // EFFECTS: this method is for creating bars for testing
    //          inserts a MusicalObject to the Bar
    public void insertObject(MusicalObject mo) {
        musicalObjects.add(mo);
    }

    // EFFECTS: returns the appropriate object type to be created
    public MusicalObject setObjectType(int type) {
        MusicalObject mo;
        if (type == 1) {
            mo = new Note();
        } else if (type == 2) {
            mo = new Chord();
        } else if (type == 3) {
            mo = new Rest();
        } else {
            mo = null;
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

    // EFFECTS: produces a list of all the musical objects in the bar
    public void setBarLength(int length) {
        this.barline = length;
    }

    // EFFECTS: prints the composite names of all the musical objects in the bar as a list
    public void printBar() {
        ArrayList<String> objectList;
        objectList = getBar();
        System.out.println(objectList);
    }

    // MODIFIES: this
    // EFFECTS: removes the musical object at the 1-based position i of the bar
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
