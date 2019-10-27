package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RestTest {
    private Rest blank;
    private Rest quarter;
    private Rest half;
    private Rest eighth;
    private Rest sixteenth;
    private Rest whole;

    @BeforeEach
    public void setup() {
        blank = new Rest();
        quarter = new Rest(1);
        half = new Rest(2);
        eighth = new Rest(0.5);
        sixteenth = new Rest(0.25);
        whole = new Rest(4);
    }

    @Test
    public void testRestConstructor() {
        Rest r = new Rest(1);
        assertEquals(1, r.getDuration());
    }

    @Test
    public void testMakeMusicalObject() {
        blank.makeMusicalObject(1);
        assertEquals(1, blank.getDuration());
    }

    @Test
    public void testGetCompositeNameQuarter() {
        assertEquals("Q-rest", quarter.getCompositeName());
    }

    @Test
    public void testGetCompositeNameHalf() {
        assertEquals("H-rest", half.getCompositeName());
    }

    @Test
    public void testGetCompositeNameSixteenth() {
        assertEquals("16-rest", sixteenth.getCompositeName());
    }

    @Test
    public void testGetCompositeNameEighth() {
        assertEquals("8-rest", eighth.getCompositeName());
    }

    @Test
    public void testGetCompositeNameWhole() {
        assertEquals("W-rest", whole.getCompositeName());
    }

    @Test
    public void testGetCompositeNameNull() {
        whole.setDuration(5);
        assertNull(whole.getCompositeName());
    }

    @Test
    public void testGetType() {
        assertEquals("Rest", whole.getType());
    }

}
