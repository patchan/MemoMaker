package model;

import exceptions.DegreeException;
import exceptions.NameException;
import exceptions.OctaveException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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
    public void testSetNote() {
        try {
            blank.setName("B");
        } catch (NameException e) {
            fail();
        }
        assertEquals("B", blank.getName());
    }

    @Test
    public void testSetNoteInvalid() {
        try {
            a.setName("Z");
            fail();
        } catch (NameException e) {
            System.out.println("Invalid note name");
        }
        assertEquals("A", a.getName());
    }

    @Test
    public void testSetOctaveMin() {
        try {
            a.setOctave(0);
        } catch (OctaveException e) {
            fail();
        }
        assertEquals(0, a.getOctave());
    }

    @Test
    public void testSetOctaveMax() {
        try {
            a.setOctave(8);
        } catch (OctaveException e) {
            fail();
        }
        assertEquals(8, a.getOctave());
    }

    @Test
    public void testSetOctaveInvalidOver() {
        try {
            a.setOctave(10);
            fail();
        } catch (OctaveException e) {
            System.out.println("Invalid octave entry");
        }
        assertEquals(4, a.getOctave());
    }

    @Test
    public void testSetOctaveInvalidUnder() {
        try {
            a.setOctave(-1);
            fail();
        } catch (OctaveException e) {
            System.out.println("Invalid octave entry");
        }
        assertEquals(4, a.getOctave());
    }

    @Test
    public void testSetDegreeNatural() {
        try {
            a.setDegree(0);
        } catch (DegreeException e) {
            fail();
        }
        assertEquals(0, a.getDegree());
    }

    @Test
    public void testSetDegreeSharp() {
        try {
            a.setDegree(1);
        } catch (DegreeException e) {
            fail();
        }
        assertEquals(1, a.getDegree());
    }

    @Test
    public void testSetDegreeFlat() {
        try {
            a.setDegree(-1);
        } catch (DegreeException e) {
            fail();
        }
        assertEquals(-1, a.getDegree());
    }

    @Test
    public void testSetDegreeInvalidOver() {
        try {
            a.setDegree(2);
            fail();
        } catch (DegreeException e) {
            System.out.println("Could not set to invalid degree");
        }
        assertEquals(0, a.getDegree());
    }

    @Test
    public void testSetDegreeInvalidUnder() {
        try {
            a.setDegree(-2);
            fail();
        } catch (DegreeException e) {
            System.out.println("Could not set to invalid degree");
        }
        assertEquals(0, a.getDegree());
    }

    @Test
    public void testSetDuration() {
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
    public void testPrintName() {
        assertEquals("Note: D4#", dsharp.printName());
    }

}