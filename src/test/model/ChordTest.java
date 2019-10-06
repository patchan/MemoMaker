package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    @BeforeEach
    public void setup() {
        cmaj = new Chord("C", "maj", "6");
        fmin = new Chord("F", "min", "9");
        gmaj = new Chord("G", "maj", "7");
        a = new Note("A", 4, 4);
        b = new Note("B", 4, 4);
        c = new Note("C", 4, 4);
        d = new Note("D", 4, 4);
    }

    @Test
    public void testSetQualityMaj() {
        cmaj.setQuality("maj");
        assertEquals("maj", cmaj.getQuality());
    }

    @Test
    public void testSetQualityMin() {
        cmaj.setQuality("min");
        assertEquals("min", cmaj.getQuality());
    }

    @Test
    public void testSetQualityAug() {
        cmaj.setQuality("aug");
        assertEquals("aug", cmaj.getQuality());
    }

    @Test
    public void testSetQualityDim() {
        cmaj.setQuality("dim");
        assertEquals("dim", cmaj.getQuality());
    }

    @Test
    public void testSetQualityNull() {
        cmaj.setQuality("");
        assertEquals(null, cmaj.getQuality());
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
        assertEquals(cmaj.notes, cmaj.getNotes());
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
    public void testPrintName() {
        assertEquals("Chord: Cmaj6", cmaj.printName());
    }

}