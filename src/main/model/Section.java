package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Section implements Serializable {
    private String name;
    private ArrayList<Bar> bars;

    public Section(String name) {
        this.name = name;
        bars = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds bar to section
    public void addBar(Bar b) {
        if (!bars.contains(b)) {
            bars.add(b);
            b.setSection(this);
        }
    }

    // MODIFIES: this
    // EFFECTS: return true if bar was removed from section, else return false
    public boolean removeBar(Bar b) {
        if (bars.contains(b)) {
            bars.remove(b);
            b.removeSection(this);
            return true;
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: sets section name
    public void setName(String name) {
        this.name = name;
    }

    // EFFECTS: returns section name
    public String getName() {
        return name;
    }

    // EFFECTS: returns list of bars in the section
    public ArrayList<Bar> getBars() {
        return bars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Section section = (Section) o;
        return Objects.equals(name, section.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
