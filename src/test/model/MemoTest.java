package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// unit tests for the Memo class
public class MemoTest {
    private Note a, b, c, d, e, f;
    private Bar bar1, bar2;
    private Memo emptyMemo, memo;

    @BeforeEach
    public void setup() {
        a = new Note("A", 4, 0, 1);
        b = new Note("B", 4, 1, 1);
        c = new Note("C", 4, 0, 1);
        d = new Note("D", 4, 0, 1);
        e = new Note("E", 4, 0, 1);
        f = new Note("F", 4, 0, 1);
        bar1 = new Bar();
        bar1.insertObject(a);
        bar1.insertObject(b);
        bar1.insertObject(c);
        bar1.insertObject(d);
        bar2 = new Bar();
        bar2.insertObject(e);
        bar2.insertObject(d);
        emptyMemo = new Memo();
        memo = new Memo();
        memo.addToMemo(bar1);
    }

    @Test
    public void testBarCountEmpty() {
        assertEquals(0, emptyMemo.barCount());
    }

    @Test
    public void testBarCountOne() {
        assertEquals(1, memo.barCount());
    }

    @Test
    public void testNoteCountEmpty() {
        assertEquals(0, emptyMemo.noteCount());
    }

    @Test
    public void testNoteCount() {
        assertEquals(4, memo.noteCount());
    }

    @Test
    public void testMemoContains() {
        assertTrue(memo.memoContains(a));
        assertFalse(memo.memoContains(e));
    }

    @Test
    public void testAddToMemoEmpty() {
        emptyMemo.addToMemo(bar1);
        assertEquals(1, emptyMemo.barCount());
        assertTrue(emptyMemo.memoContains(a));
        assertTrue(emptyMemo.memoContains(b));
        assertTrue(emptyMemo.memoContains(c));
        assertTrue(emptyMemo.memoContains(d));
    }

    @Test
    public void testAddToMemo() {
        memo.addToMemo(bar2);
        assertTrue(memo.memoContains(e));
        assertTrue(memo.memoContains(d));
        assertFalse(memo.memoContains(f));
    }

    @Test
    public void testLoadAndSave() throws IOException, ClassNotFoundException {
        memo.save();
        Memo savedMemo = memo;
        emptyMemo.load();
        Memo loadedMemo = memo;
        assertEquals(loadedMemo.returnMemo(), savedMemo.returnMemo());
    }

}
