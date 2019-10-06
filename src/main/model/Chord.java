package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Chord extends MusicalObject implements Serializable {
    private static final String MAJOR = "maj";
    private static final String MINOR = "min";
    private static final String AUGMENTED = "aug";
    private static final String DIMINISHED = "dim";

    protected String quality;
    protected String extensions;
    protected ArrayList<Note> notes;

    public Chord(String name, String quality, String extensions) {
        this.name = name;
        setQuality(quality);
        this.extensions = extensions;
        notes = new ArrayList<>();
    }

    public void setQuality(String s) {
        if (s.equalsIgnoreCase("maj")) {
            quality = MAJOR;
        } else if (s.equalsIgnoreCase("min")) {
            quality = MINOR;
        } else if (s.equalsIgnoreCase("aug")) {
            quality = AUGMENTED;
        } else if (s.equalsIgnoreCase("dim")) {
            quality = DIMINISHED;
        } else {
            quality = null;
        }
    }

    public void addNotes(Note n) {
        notes.add(n);
    }

    public void removeNotes(int i) {
        notes.remove(i - 1);
    }

    public String getQuality() {
        return this.quality;
    }

    public String getExtensions() {
        return this.extensions;
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public int getChordSize() {
        return notes.size();
    }

    public boolean contains(Note n) {
        return notes.contains(n);
    }

    // EFFECTS: produces a composite name with the chord name and quality
    @Override
    protected String getCompositeName() {
        String compositeName = this.name + this.quality + this.extensions;
        return compositeName;
    }

    @Override
    protected String printName() {
        String compositeName = getCompositeName();
        System.out.println("Chord: " + compositeName);
        return "Chord: " + compositeName;
    }
}
