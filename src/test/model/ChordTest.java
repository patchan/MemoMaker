package model;

import model.exceptions.NameException;
import model.exceptions.QualityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertNull;

// unit tests for the Chord class
public class ChordTest {
    private Chord empty;
    private Chord cmaj;
    private Chord fmin;
    private Chord gmaj;
    private Note a;
    private Note b;
    private Note c;
    private Note d;

    @BeforeEach
    public void setup() {
        empty = new Chord();
        cmaj = new Chord("C", "maj", "6", 1);
        fmin = new Chord("F", "min", "9", 1);
        gmaj = new Chord("G", "maj", "7", 1);
        a = new Note("A", 4, 0, 1);
        b = new Note("B", 4, 1, 1);
        c = new Note("C", 4, 0, 1);
        d = new Note("D", 4, 0, 1);
    }

    @Test
    public void testSetNameValid() {
        try {
            empty.setName("C");
        } catch (NameException e) {
            fail("Did not set name to C");
        }
        assertEquals("C", empty.getName());
    }

    @Test
    public void testSetNameInvalid() {
        try {
            empty.setName("Z");
            fail("Set name to Z");
        } catch (NameException e) {
            System.out.println("Did not set name to Z");
        }
        assertNull(empty.getName());
    }

    @Test
    public void testSetValidNameInvalid() {
        empty.setValidName("C");
        assertEquals("C", empty.getName());
    }

    @Test
    public void testSetQualityMaj() {
        try {
            empty.setQuality("maj");
        } catch (QualityException e) {
            fail("Did not set to major");
        }
        assertEquals("maj", empty.getQuality());
    }

    @Test
    public void testSetQualityMin() {
        try {
            cmaj.setQuality("min");
        } catch (QualityException e) {
            fail("Did not set to minor");
        }
        assertEquals("min", cmaj.getQuality());
    }

    @Test
    public void testSetQualityAug() {
        try {
            cmaj.setQuality("aug");
        } catch (QualityException e) {
            fail("Did not set to augmented");
        }
        assertEquals("aug", cmaj.getQuality());
    }

    @Test
    public void testSetQualityDim() {
        try {
            cmaj.setQuality("dim");
        } catch (QualityException e) {
            fail("Did not set to diminished");
        }
        assertEquals("dim", cmaj.getQuality());
    }

    @Test
    public void testSetQualityNull() {
        try {
            cmaj.setQuality("");
            fail("Did not expect to set quality to null");
        } catch (QualityException e) {
            System.out.println("Caught QualityException");
        }
    }

    @Test
    public void testSetValidQualityValid() {
        cmaj.setValidQuality("min");
        assertEquals("min", cmaj.getQuality());
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
        assertEquals(cmaj.notes, cmaj.getChordNotes());
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