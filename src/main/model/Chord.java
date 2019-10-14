package model;

import model.exceptions.NameException;
import model.exceptions.QualityException;
import ui.commands.CreateNewMemo;

import java.io.Serializable;
import java.util.ArrayList;

public class Chord extends MusicalObject implements Serializable {

    protected String quality;
    protected String extensions;
    protected ArrayList<Note> notes;

    // EFFECTS: default constructor for Chord
    public Chord() {
        notes = new ArrayList<>();
    }

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
    public void setQuality(String s) throws QualityException {
        if (s.equalsIgnoreCase("maj")) {
            quality = ChordQuality.MAJ.getValue();
        } else if (s.equalsIgnoreCase("min")) {
            quality = ChordQuality.MIN.getValue();
        } else if (s.equalsIgnoreCase("aug")) {
            quality = ChordQuality.AUG.getValue();
        } else if (s.equalsIgnoreCase("dim")) {
            quality = ChordQuality.DIM.getValue();
        } else {
            throw new QualityException();
        }
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

    // EFFECTS: creates a new chord
    @Override
    protected void makeMusicalObject(double noteDur) {
        CreateNewMemo c = new CreateNewMemo();
        setValidName();
        setValidQuality();
        setExtensions(c.getChordExtensions());
        setDuration(noteDur);
        int chordNotes = c.getChordNotes();
        int i = 0;
        while (i < chordNotes) {
            Note mo = new Note();
            mo.makeMusicalObject(noteDur);
            addNotes(mo);
            i++;
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the chord name to a valid chord name
    public void setValidName() {
        CreateNewMemo c = new CreateNewMemo();
        while (true) {
            try {
                setName(c.getNoteName());
                break;
            } catch (NameException e) {
                System.out.println("Invalid chord name. Please try again.");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the chord quality to a valid chord quality
    public void setValidQuality() {
        CreateNewMemo c = new CreateNewMemo();
        while (true) {
            try {
                setQuality(c.getChordQuality());
                break;
            } catch (QualityException e) {
                System.out.println("Invalid chord quality. Please try again.");
            }
        }
    }

    // EFFECTS: returns "Chord"
    @Override
    protected String getType() {
        return "Chord";
    }

    // EFFECTS: produces a composite name with the chord name and quality
    @Override
    protected String getCompositeName() {
        return this.name + this.quality + this.extensions;
    }

}
