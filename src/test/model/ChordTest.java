package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// unit tests for the Chord class
public class ChordTest {
    private Chord cmaj;
    private Chord fmin;
    private Chord gmaj;
    private Note a;
    private Note b;
    private Note c;
    private Note d;
    private ArrayList<Note> notes;

    @BeforeEach
    public void setup() {
        cmaj = new Chord("C", "maj", "6", 1);
        fmin = new Chord("F", "min", "9", 1);
        gmaj = new Chord("G", "maj", "7", 1);
        a = new Note("A", 4, 0, 1);
        b = new Note("B", 4, 1, 1);
        c = new Note("C", 4, 0, 1);
        d = new Note("D", 4, 0, 1);
        notes = new ArrayList<>();
        notes.add(c);
    }

    @Test
    public void testSetQuality() {
        cmaj.setQuality("min");
        assertEquals("min", cmaj.getQuality());
    }

    @Test
    public void testGetQuality() {
        assertEquals("maj", cmaj.getQuality());
    }

    @Test
    public void testIsValidQualityTrueMaj() {
        assertTrue(cmaj.isValidQuality("maj"));
    }

    @Test
    public void testIsValidQualityTrueMin() {
        assertTrue(cmaj.isValidQuality("min"));
    }

    @Test
    public void testIsValidQualityTrueAug() {
        assertTrue(cmaj.isValidQuality("aug"));
    }

    @Test
    public void testIsValidQualityTrueDim() {
        assertTrue(cmaj.isValidQuality("dim"));
    }

    @Test
    public void testIsValidQualityFalse() {
        assertFalse(cmaj.isValidQuality("none"));
    }

    @Test
    public void testSetExtensions() {
        cmaj.setExtensions("11");
        assertEquals("11", cmaj.getExtensions());
    }

    @Test
    public void testAddNotes() {
        cmaj.addNotes(a);
        cmaj.addNotes(b);
        cmaj.addNotes(c);
        assertEquals(3, cmaj.getChordSize());
        assertTrue(cmaj.contains(a));
        assertTrue(cmaj.contains(b));
        assertTrue(cmaj.contains(c));
        assertFalse(cmaj.contains(d));
    }

    @Test
    public void testRemoveNotes() {
        fmin.addNotes(a);
        fmin.removeNotes(1);
        assertEquals(0, fmin.getChordSize());
        assertFalse(fmin.contains(a));
    }

    @Test
    public void testGetExtensions() {
        assertEquals("6", cmaj.getExtensions());
    }

    @Test
    public void testGetNotes() {
        cmaj.addNotes(c);
        assertEquals(notes, cmaj.getChordNotes());
    }

    @Test
    public void testSetDuration() {
        cmaj.setDuration(2);
        assertEquals(2, cmaj.getDuration());
    }

    @Test
    public void testCompositeName() {
        assertEquals("Cmaj6", cmaj.getCompositeName());
    }

    @Test
    public void testGetType() {
        assertEquals("Chord", cmaj.getType());
    }

}