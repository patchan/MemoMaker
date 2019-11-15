package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BarCounterTest {
    private BarCounter barCounter, barCounter2;
    private Memo m;

    @BeforeEach
    public void setup() {
        barCounter = new BarCounter();
        barCounter2 = new BarCounter();
        m = new Memo();
    }

    @Test
    public void updateTestOneCounter() {
        m.addObserver(barCounter);
        m.notifyObservers();
        assertEquals(1, barCounter.getCount());
    }

    @Test
    public void updateTestTwoCounter() {
        m.addObserver(barCounter);
        m.addObserver(barCounter2);
        m.notifyObservers();
        assertEquals(1, barCounter.getCount());
        assertEquals(1, barCounter2.getCount());
    }
}
