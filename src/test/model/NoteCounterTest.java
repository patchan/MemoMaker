package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// unit tests for NoteCounter
public class NoteCounterTest {
    private NoteCounter noteCounter, noteCounter2;
    private Bar b;

    @BeforeEach
    public void setup() {
        noteCounter = new NoteCounter();
        noteCounter2 = new NoteCounter();
        b = new Bar(1);
    }

    @Test
    public void updateTestOneCounter() {
        b.addObserver(noteCounter);
        b.notifyObservers();
        assertEquals(1, noteCounter.getCount());
    }

    @Test
    public void updateTestTwoCounter() {
        b.addObserver(noteCounter);
        b.addObserver(noteCounter2);
        b.notifyObservers();
        assertEquals(1, noteCounter.getCount());
        assertEquals(1, noteCounter2.getCount());
    }
}
