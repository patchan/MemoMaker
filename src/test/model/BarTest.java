package model;

import model.exceptions.BarLengthException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// unit tests for the Bar class
public class BarTest {
    private Note a, b, c, d, e, f;
    private Bar bar1, bar2, bar3, bar4;
    private MusicalObject nullObject;
    private Section sec1, sec2, def;

    @BeforeEach
    public void setup() {
        a = new Note("A", 4, 0, 1);
        b = new Note("B", 4, 0, 1);
        c = new Note("C", 4, 0, 1);
        d = new Note("D", 4, 0, 1);
        e = new Note("E", 4, 0, 1);
        f = new Note("F", 4, 0, 1);
        bar1 = new Bar();
        bar2 = new Bar();
        bar3 = new Bar();
        bar4 = null;
        bar2.insertObject(a);
        bar2.insertObject(b);
        bar2.insertObject(c);
        bar3.insertObject(d);
        bar3.insertObject(e);
        bar3.insertObject(f);
        bar3.insertObject(a);
        sec1 = new Section("A");
        sec2 = new Section("B");
        def = new Section("Default");
    }

    @Test
    public void testSetSection() {
        bar1.setSection(sec1);
        assertTrue(sec1.equals(bar1.getSection()));
    }

    @Test
    public void testSetSameSection() {
        bar1.setSection(sec1);
        assertFalse(bar1.setSection(sec1));
    }

    @Test
    public void testRemoveSection() {
        bar1.setSection(sec1);
        bar1.removeSection(sec1);
        assertEquals(def, bar1.getSection());
    }

    @Test
    public void testRemoveBarSectionDoNothing() {
        bar1.removeSection(sec1);
        assertEquals(def, bar1.getSection());
    }

    @Test
    public void testRemoveWrongSection() {
        bar1.setSection(sec1);
        bar1.removeSection(sec2);
        assertEquals(sec1, bar1.getSection());
    }

    @Test
    public void testAddToBarExpectSuccess() {
        bar1.setBarLength(2);
        try {
            bar1.addToBar(a);
            bar1.addToBar(b);
        } catch (BarLengthException e) {
            fail();
        }
        assertEquals(2, bar1.barSize());
        assertTrue(bar1.barContains(a));
        assertTrue(bar1.barContains(b));
    }

    @Test
    public void testAddToBarExpectBarLengthException() {
        bar1.setBarLength(1);
        try {
            bar1.addToBar(a);
            bar1.addToBar(b);
            fail();
        } catch (BarLengthException e) {
            System.out.println("Caught BarLengthException");
        }
        assertEquals(1, bar1.barSize());
        assertTrue(bar1.barContains(a));
        assertFalse(bar1.barContains(b));
    }

    @Test
    public void testInsertObject() {
        bar1.insertObject(a);
        bar1.insertObject(b);
        assertEquals(2, bar1.barSize());
        assertTrue(bar1.barContains(a));
        assertTrue(bar1.barContains(b));
    }

    @Test
    public void testGetBar() {
        ArrayList<String> expectedResult = new ArrayList<>();
        expectedResult.add(a.getCompositeName());
        expectedResult.add(b.getCompositeName());
        expectedResult.add(c.getCompositeName());
        assertEquals(expectedResult, bar2.getBar());
    }

    @Test
    public void testBarContains() {
        assertFalse(bar1.barContains(a));
        assertTrue(bar2.barContains(a));
    }

    @Test
    public void testRemoveObject() {
        bar2.removeObject(1);
        assertFalse(bar2.barContains(a));
    }

    @Test
    public void testBarSizeEmpty() {
        assertEquals(0, bar1.barSize());
    }

    @Test
    public void testBarSizeSome() {
        assertEquals(3, bar2.barSize());
    }

    @Test
    public void testBarSizeFull() {
        assertEquals(4, bar3.barSize());
    }

    @Test
    public void testWithinBarLengthTrue() {
        bar1.setBarLength(2);
        assertTrue(bar1.withinBarLength(a.getDuration()));
    }

    @Test
    public void testWithinBarLengthFalse() {
        bar2.setBarLength(3);
        assertFalse(bar2.withinBarLength(a.getDuration()));
    }

    @Test
    public void testSetGetBarLength() {
        bar1.setBarLength(2);
        assertEquals(2, bar1.getBarLength());
    }

    @Test
    public void testSetObjectTypeNote() {
        MusicalObject note = new Note();
        MusicalObject nullObject = bar1.setObjectType(1);
        assertEquals(note.getType(), nullObject.getType());
    }

    @Test
    public void testSetObjectTypeChord() {
        MusicalObject chord = new Chord();
        MusicalObject nullObject = bar1.setObjectType(2);
        assertEquals(chord.getType(), nullObject.getType());
    }

    @Test
    public void testSetObjectTypeRest() {
        MusicalObject rest = new Rest();
        MusicalObject nullObject = bar1.setObjectType(3);
        assertEquals(rest.getType(), nullObject.getType());
    }

    @Test
    public void testSetObjectTypeNull() {
        MusicalObject nullObject = bar1.setObjectType(4);
        assertEquals(null, nullObject);
    }

    @Test
    public void testEqualsTrue() {
        bar1.insertObject(a);
        bar1.insertObject(b);
        bar1.insertObject(c);
        assertTrue(bar2.equals(bar1));
        assertTrue(bar2.hashCode()==bar1.hashCode());
    }

    @Test
    public void testEqualsDifferentType() {
        assertFalse(bar2.equals(a));
    }

    @Test
    public void testEqualsNull() {
        assertFalse(bar2.equals(bar4));
    }

    @Test
    public void testEqualsFalse() {
        assertFalse(bar2.equals(bar1));
        assertFalse(bar2.hashCode()==bar1.hashCode());
    }
}
