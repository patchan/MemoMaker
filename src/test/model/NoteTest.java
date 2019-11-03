package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

// unit tests for the Note class
public class NoteTest {
    private Note blank;
    private Note a;
    private Note d;
    private Note dsharp;
    private Note dflat;

    @BeforeEach
    public void setup() {
        blank = new Note();
        a = new Note("A", 4, 0, 1);
        d = new Note("D", 4, 0, 1);
        dsharp = new Note("D", 4, 1, 1);
        dflat = new Note("D", 4, -1, 1);
    }

    @Test
    public void testSetGetName() {
        a.setName("B");
        assertEquals("B",a.getName());
    }

    @Test
    public void testIsValidNameTrue() {
        assertTrue(a.isValidName("A"));
    }

    @Test
    public void testIsValidNameFalse() {
        assertFalse(a.isValidName("T"));
    }

    @Test
    public void testIsValidOctaveTrueLowBoundary() {
        assertTrue(a.isValidOctave(0));
    }

    @Test
    public void testIsValidOctaveTrue() {
        assertTrue(a.isValidOctave(4));
    }
    @Test
    public void testIsValidOctaveTrueHighBoundary() {
        assertTrue(a.isValidOctave(8));
    }

    @Test
    public void testIsValidOctaveFalseLow() {
        assertFalse(a.isValidOctave(-1));
    }

    @Test
    public void testIsValidOctaveFalseHigh() {
        assertFalse(a.isValidOctave(10));
    }

    @Test
    public void testIsValidQDegreeTrueFlat() {
        assertTrue(a.isValidDegree(-1));
    }

    @Test
    public void testIsValidQDegreeTrueNatural() {
        assertTrue(a.isValidDegree(0));
    }

    @Test
    public void testIsValidQDegreeTrueSharp() {
        assertTrue(a.isValidDegree(1));
    }

    @Test
    public void testIsValidQDegreeFalseLow() {
        assertFalse(a.isValidDegree(-2));
    }

    @Test
    public void testIsValidQDegreeFalseHigh() {
        assertFalse(a.isValidDegree(2));
    }

    @Test
    public void testSetOctave() {
        a.setOctave(2);
        assertEquals(2, a.getOctave());
    }

    @Test
    public void testGetOctave() {
        assertEquals(4, a.getOctave());
    }

    @Test
    public void testSetDegree() {
        a.setDegree(1);
        assertEquals(1, a.getDegree());
    }

    @Test
    public void testGetDegree() {
        assertEquals(0, a.getDegree());
    }

    @Test
    public void testSetGetDuration() {
        a.setDuration(2);
        assertEquals(2, a.getDuration());
    }

    @Test
    public void testGetDegreeSymbolNatural() {
        assertEquals("", a.getDegreeSymbol());
    }

    @Test
    public void testGetDegreeSymbolSharp() {
        assertEquals("#", dsharp.getDegreeSymbol());
    }

    @Test
    public void testGetDegreeSymbolFlat() {
        assertEquals("b", dflat.getDegreeSymbol());
    }

    @Test
    public void testGetDegreeSymbolNull() {
        Note note = new Note("A", 4, 2, 1);
        assertNull(note.getDegreeSymbol());
    }

    @Test
    public void testCompositeNameNatural() {
        assertEquals("D4", d.getCompositeName());
    }

    @Test
    public void testCompositeNameFlat() {
        assertEquals("D4b", dflat.getCompositeName());
    }

    @Test
    public void testCompositeNameSharp() {
        assertEquals("D4#", dsharp.getCompositeName());
    }

    @Test
    public void testGetType() {
        assertEquals("Note", dsharp.getType());
    }

    @Test
    public void testPrintName() {
        assertEquals("Note: D4#", dsharp.printName());
    }

}