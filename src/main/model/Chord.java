package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Chord extends MusicalObject implements Serializable {

    private String quality;
    private String extensions;
    private ArrayList<Note> notes;

    // EFFECTS: constructs a Chord with name, quality, extensions, and duration
    public Chord(String name, String quality, String extensions, double duration) {
        this.name = name;
        this.quality = quality;
        this.extensions = extensions;
        this.duration = duration;
        notes = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: sets the quality of this chord
    public void setQuality(String quality) {
        this.quality = quality;
    }

    public static boolean isValidQuality(String quality) {
        for (ChordQuality q : ChordQuality.values()) {
            if (q.getValue().equalsIgnoreCase(quality)) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: sets the extension of this chord
    public void setExtensions(String ext) {
        extensions = ext;
    }

    // MODIFIES: this
    // EFFECTS: adds Note to the list of notes in this chord
    public void addNotes(Note n) {
        notes.add(n);
    }

    // MODIFIES: this
    // EFFECTS: removes the Note at the 1-based position of this chord
    public void removeNotes(int i) {
        notes.remove(i - 1);
    }

    // EFFECTS: produces the quality of this chord
    public String getQuality() {
        return this.quality;
    }

    // EFFECTS: produces the extension of this chord
    public String getExtensions() {
        return this.extensions;
    }

    // EFFECTS: produces a list of notes in this chord
    public ArrayList<Note> getChordNotes() {
        return notes;
    }

    // EFFECTS: produces the number of notes in this chord
    public int getChordSize() {
        return notes.size();
    }

    // EFFECTS: produces true if this chord contains Note n
    public boolean contains(Note n) {
        return notes.contains(n);
    }

    // EFFECTS: produces a composite name with the chord name and quality
    @Override
    protected String getCompositeName() {
        return this.name + this.quality + this.extensions;
    }

}
