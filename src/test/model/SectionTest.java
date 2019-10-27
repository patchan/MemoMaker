package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class SectionTest {
    private Section sec1;
    private Section sec2;
    private Section sec3;
    private Bar bar1;
    private Note a, b, c, d;

    @BeforeEach
    public void setup() {
        sec1 = new Section("1");
        sec2 = new Section("2");
        sec3 = null;
        bar1 = new Bar();
        a = new Note("A", 4, 0, 1);
        b = new Note("B", 4, 0, 1);
        c = new Note("C", 4, 0, 1);
        d = new Note("D", 4, 0, 1);
        bar1.insertObject(a);
        bar1.insertObject(b);
        bar1.insertObject(c);
        bar1.insertObject(d);
    }

    @Test
    public void testAddBar() {
        sec1.addBar(bar1);
        ArrayList<Bar> result = sec1.getBars();
        assertTrue(result.contains(bar1));
    }

    @Test
    public void testAddBarDuplicate() {
        sec1.addBar(bar1);
        sec1.addBar(bar1);
        ArrayList<Bar> result = sec1.getBars();
        assertTrue(result.contains(bar1));
        assertEquals(1, result.size());
    }

    @Test
    public void testRemoveBar() {
        sec1.addBar(bar1);
        assertTrue(sec1.removeBar(bar1));
        ArrayList<Bar> result = sec1.getBars();
        assertFalse(result.contains(bar1));
    }

    @Test
    public void testRemoveBarNotContains() {
        assertFalse(sec1.removeBar(bar1));
    }

    @Test
    public void testSetGetName() {
        sec1.setName("2");
        assertEquals("2", sec1.getName());
    }

    @Test
    public void testEqualsTrue() {
        sec2.setName("1");
        assertTrue(sec1.equals(sec2));
        assertTrue(sec1.hashCode()==sec2.hashCode());
    }

    @Test
    public void testEqualsSame() {
        assertTrue(sec1.equals(sec1));
    }

    @Test
    public void testEqualsNull() {
        assertFalse(sec1.equals(sec3));
    }

    @Test
    public void testEqualsDifferentType() {
        assertFalse(sec1.equals(bar1));
    }

    @Test
    public void testEqualsFalse() {
        assertFalse(sec1.equals(sec2));
        assertFalse(sec1.hashCode()==sec2.hashCode());
    }

}
