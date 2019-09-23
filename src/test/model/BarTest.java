package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// unit tests for the Bar class
public class BarTest {
    private Note a, b, c, d, e, f;
    private Bar bar1, bar2;

    @BeforeEach
    public void setup() {
        a = new Note("A");
        b = new Note("B");
        c = new Note("C");
        d = new Note("D");
        e = new Note("E");
        f = new Note("F");
        bar1 = new Bar();
        bar2 = new Bar();
        bar2.addToBar(a);
        bar2.addToBar(b);
        bar2.addToBar(c);
    }

    @Test
    public void testAddToBar() {
        bar1.addToBar(a);
        bar1.addToBar(b);
        assertEquals(2, bar1.barSize());
        assertTrue(bar1.barContains(a));
        assertTrue(bar1.barContains(b));
    }

    @Test
    public void testGetBar() {
        ArrayList<String> result = new ArrayList<>();
        result.add(a.compositeName());
        result.add(b.compositeName());
        result.add(c.compositeName());
        assertEquals(result, bar2.getBar());
    }

    @Test
    public void testBarContains() {
        assertFalse(bar1.barContains(a));
        assertTrue(bar2.barContains(a));
    }

    @Test
    public void testBarSize() {
        assertEquals(0, bar1.barSize());
        assertEquals(3, bar2.barSize());
    }
}
