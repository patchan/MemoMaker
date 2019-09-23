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
        a = new Note("A");
        b = new Note("B");
        c = new Note("C");
        d = new Note("D");
    }

    @Test
    public void testSetNote() {
        a.setNote("B");
        assertEquals("B", a.getNote());
    }

    @Test
    public void testSetDegree() {
        a.setDegree(-1);
        b.setDegree(0);
        c.setDegree(-1);
        assertEquals(-1, a.getDegree());
        assertEquals(0, b.getDegree());
        assertEquals(-1, c.getDegree());
        a.setDegree(0);
        assertEquals(0, a.getDegree());
    }

    @Test
    public void testGetDegreeSymbol() {
        a.setDegree(0);
        b.setDegree(1);
        c.setDegree(-1);
        assertEquals("", a.getDegreeSymbol());
        assertEquals("#", b.getDegreeSymbol());
        assertEquals("b", c.getDegreeSymbol());
    }

    @Test
    public void testCompositeNameNatural() {
        d.setDegree(0);
        assertEquals("D", d.compositeName());
    }

    @Test
    public void testCompositeNameFlat() {
        d.setDegree(-1);
        assertEquals("Db", d.compositeName());
    }

    @Test
    public void testCompositeNameSharp() {
        d.setDegree(1);
        assertEquals("D#", d.compositeName());
    }


}