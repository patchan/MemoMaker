package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// unit tests for the Memo class
public class MemoTest {
    private Note a, b, c, d, e, f;
    private Bar bar1, bar2;
    private Memo emptyMemo, memo1, memo2;
    private Section sec1, sec2;

    @BeforeEach
    public void setup() {
        a = new Note("A", 4, 0, 1);
        b = new Note("B", 4, 1, 1);
        c = new Note("C", 4, 0, 1);
        d = new Note("D", 4, 0, 1);
        e = new Note("E", 4, 0, 1);
        f = new Note("F", 4, 0, 1);
        bar1 = new Bar(1);
        bar1.insertObject(a);
        bar1.insertObject(b);
        bar1.insertObject(c);
        bar1.insertObject(d);
        bar2 = new Bar(2);
        bar2.insertObject(e);
        bar2.insertObject(d);
        emptyMemo = new Memo();
        memo1 = new Memo();
        memo2 = null;
        memo1.addToMemo(bar1);
        sec1 = new Section("1");
        sec2 = new Section( "2");
    }

    @Test
    public void testAddSection() {
        memo1.addSection(sec1);
        assertTrue(memo1.getSections().contains(sec1));
        assertEquals(1, memo1.countSections());
    }

    @Test
    public void testAddSectionDuplicate() {
        memo1.addSection(sec1);
        memo1.addSection(sec1);
        assertTrue(memo1.getSections().contains(sec1));
        assertEquals(1, memo1.countSections());
    }

    @Test
    public void testRemoveSection() {
        memo1.addSection(sec1);
        memo1.removeSection(sec1);
        assertFalse(memo1.getSections().contains(sec1));
        assertEquals(0, memo1.countSections());
    }

    @Test
    public void testRemoveSectionDoNothing() {
        assertEquals(0, memo1.countSections());
        memo1.removeSection(sec1);
        assertFalse(memo1.getSections().contains(sec1));
        assertEquals(0, memo1.countSections());
    }

    @Test
    public void testListSectionNames() {
        memo1.addSection(sec1);
        List<String> result = new ArrayList<>();
        result.add(sec1.getName());
        assertEquals(result, memo1.listSectionNames());
    }

    @Test
    public void testGetBars() {
        ArrayList<Bar> result = memo1.getBars();
        assertTrue(result.contains(bar1));
    }

    @Test
    public void testBarCountEmpty() {
        assertEquals(0, emptyMemo.barCount());
    }

    @Test
    public void testBarCountOne() {
        assertEquals(1, memo1.barCount());
    }

    @Test
    public void testNoteCountEmpty() {
        assertEquals(0, emptyMemo.noteCount());
    }

    @Test
    public void testNoteCount() {
        assertEquals(4, memo1.noteCount());
    }

    @Test
    public void testMemoContains() {
        assertTrue(memo1.memoContains(a));
        assertFalse(memo1.memoContains(e));
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
        memo1.addToMemo(bar2);
        assertTrue(memo1.memoContains(e));
        assertTrue(memo1.memoContains(d));
        assertFalse(memo1.memoContains(f));
    }

    @Test
    public void testEqualsTrue() {
        memo2 = new Memo();
        memo2.addToMemo(bar1);
        assertTrue(memo1.equals(memo2));
        assertTrue(memo1.hashCode() == memo2.hashCode());
    }

    @Test
    public void testEqualsSame() {
        assertTrue(memo1.equals(memo1));
    }

    @Test
    public void testEqualsNull() {
        assertFalse(memo1.equals(memo2));
    }

    @Test
    public void testEqualsDifferentType() {
        assertFalse(memo1.equals(bar1));
    }

    @Test
    public void testEqualsFalse() {
        memo2 = new Memo();
        assertFalse(memo1.equals(memo2));
        assertFalse(memo1.hashCode() == memo2.hashCode());
    }

}
