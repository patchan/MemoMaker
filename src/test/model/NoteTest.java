package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// unit tests for the Note class
public class NoteTest {
    private Note a;
    private Note b;
    private Note c;
    private Note d;

    @BeforeEach
    public void setup() {
        a = new Note("A", 4, 4);
        b = new Note("B", 4, 4);
        c = new Note("C", 4, 4);
        d = new Note("D", 4, 4);
    }

    @Test
    public void testSetNote() {
        a.setName("B");
        assertEquals("B", a.getName());
    }

    @Test
    public void testSetDegreeNatural() {
        a.setDegree(0);
        assertEquals(0, a.getDegree());
    }

    @Test
    public void testSetOctave() {
        a.setOctave(1);
        assertEquals(1, a.getOctave());
    }

    @Test
    public void testSetDegreeSharp() {
        a.setDegree(1);
        assertEquals(1, a.getDegree());
    }

    @Test
    public void testSetDegreeFlat() {
        a.setDegree(-1);
        assertEquals(-1, a.getDegree());
    }

    @Test
    public void testSetDuration() {
        a.setDuration(2);
        assertEquals(2, a.getDuration());
    }

    @Test
    public void testGetDegreeSymbolNatural() {
        a.setDegree(0);
        assertEquals("", a.getDegreeSymbol());
    }

    @Test
    public void testGetDegreeSymbolSharp() {
        b.setDegree(1);
        assertEquals("#", b.getDegreeSymbol());
    }

    @Test
    public void testGetDegreeSymbolFlat() {
        c.setDegree(-1);
        assertEquals("b", c.getDegreeSymbol());
    }

    @Test
    public void testCompositeNameNatural() {
        d.setDegree(0);
        assertEquals("D4", d.getCompositeName());
    }

    @Test
    public void testCompositeNameFlat() {
        d.setDegree(-1);
        assertEquals("D4b", d.getCompositeName());
    }

    @Test
    public void testCompositeNameSharp() {
        d.setDegree(1);
        assertEquals("D4#", d.getCompositeName());
    }

    @Test
    public void testPrintName() {
        d.setDegree(1);
        assertEquals("Note: D4#", d.printName());
    }

}