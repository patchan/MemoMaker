package model;

import model.exceptions.BarLengthException;
import ui.Creator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Bar implements Serializable {
    private ArrayList<MusicalObject> musicalObjects;
    private Section section;
    private double barline;
    private int barNum;

    // EFFECTS: constructs an empty bar
    public Bar(int barNum) {
        this.barNum = barNum;
        initializeSection();
        this.musicalObjects = new ArrayList<>();
    }

    public void initializeSection() {
        section = new Section("None");
    }

    // MODIFIES: this
    // EFFECTS: returns true if section was set, else false
    public boolean setSection(Section s) {
        if (!s.equals(section)) {
            this.section = s;
            return true;
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: removes Section s from the bar
    public void removeSection(Section s) {
        if (section.equals(s)) {
            initializeSection();
        }
    }

    // EFFECTS: returns the section the bar is in
    public Section getSection() {
        return section;
    }

    // MODIFIES: this
    // EFFECTS: creates a full bar with musical objects
    public void makeBar(int length) {
        double i = 0;
        setBarLength(length);
        while (i < length) {
            MusicalObject newObject = new Creator().makeNewObject();
            try {
                addToBar(newObject);
                i = i + newObject.getDuration();
            } catch (BarLengthException e) {
                System.out.println("That note doesn't fit in the bar (length: " + barline + ")."
                        + "\nThe bar current holds " + totalObjectLength()
                        + " quarter notes. \nPlease try again.");
            } finally {
                printBar();
            }
        }
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

    // MODIFIES: this
    // EFFECTS: sets the bar length
    public void setBarLength(double length) {
        this.barline = length;
    }

    // EFFECTS: returns the bar length
    public double getBarLength() {
        return this.barline;
    }

    // MODIFIES: this
    // EFFECTS: sets the bar number
    public void setBarNum(int i) {
        this.barNum = i;
    }

    // EFFECTS: produces the bar number
    public int getBarNum() {
        return this.barNum;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Bar bar = (Bar) o;
        return Objects.equals(musicalObjects, bar.musicalObjects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(musicalObjects);
    }
}
