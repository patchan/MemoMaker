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
    private Bar bar1, bar2, bar3;

    @BeforeEach
    public void setup() {
        a = new QuarterNote("A", 4, 4);
        b = new QuarterNote("B", 4, 4);
        c = new QuarterNote("C", 4, 4);
        d = new QuarterNote("D", 4, 4);
        e = new QuarterNote("E", 4, 4);
        f = new QuarterNote("F", 4, 4);
        bar1 = new Bar();
        bar2 = new Bar();
        bar3 = new Bar();
        bar2.addToBar(a);
        bar2.addToBar(b);
        bar2.addToBar(c);
        bar3.addToBar(d);
        bar3.addToBar(e);
        bar3.addToBar(f);
        bar3.addToBar(a);
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
}
