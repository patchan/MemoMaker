package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    private Library library;
    private Library emptyLibrary;
    private Memo memo;
    private Memo memo1;

    @BeforeEach
    public void setup() {
        library = new Library();
        emptyLibrary = new Library();
        memo = new Memo();
        library.addNewMemo("1");
        library.addNewMemo("2");
        library.addNewMemo("2");
        memo1 = memo;
    }

    @Test
    public void testAddNewMemo() {
        library.addNewMemo("Test");
        assertTrue(library.contains("Test"));
    }

    @Test
    public void testDeleteMemo() {
        library.deleteMemo("1");
        assertFalse(library.contains("1"));
    }

    @Test
    public void testClearLibrary() {
        library.clearLibrary();
        assertEquals(0, library.size());
        assertFalse(library.contains("1"));
        assertFalse(library.contains("2"));
        assertFalse(library.contains("3"));
    }

    @Test
    public void testGetMemo() {
        assertEquals(memo1, library.getMemo("1"));
    }

    @Test
    public void testLoadAndSave() throws IOException, ClassNotFoundException {
        library.save();
        Library savedLibrary = library;
        emptyLibrary.load();
        Library loadedLibrary = library;
        assertEquals(loadedLibrary.returnLibrary(), savedLibrary.returnLibrary());
    }
}
