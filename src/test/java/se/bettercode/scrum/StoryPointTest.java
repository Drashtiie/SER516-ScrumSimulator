package se.bettercode.scrum;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StoryPointTest {

    @Test(expected = IllegalArgumentException.class)
    public void cannotCreateNegativeStoryPoint() {
        new StoryPoint(-1);
    }

    @Test
    public void add() {
        StoryPoint p = new StoryPoint(5);
        assertEquals(5, p.getPoints());
        p.add(3);
        assertEquals(8, p.getPoints());
    }

    @Test
    public void addOtherStoryPointObject() {
        StoryPoint p = new StoryPoint(5);
        StoryPoint q = new StoryPoint(7);
        p.add(q);
        assertEquals(12, p.getPoints());
    }

    @Test
    public void differentPointsAreNotEqual() {
        StoryPoint p = new StoryPoint(5);
        StoryPoint q = new StoryPoint(7);
        assertFalse(p.equals(q));
    }

    @Test
    public void samePointsAreEqual() {
        StoryPoint p = new StoryPoint(3);
        StoryPoint q = new StoryPoint(3);
        assertTrue(p.equals(q));
    }

    @Test
    public void subtract() {
        StoryPoint p = new StoryPoint(5);
        p.subtract(3);
        assertEquals(2, p.getPoints());
    }

    @Test
    public void set() {
        StoryPoint p = new StoryPoint(5);
        p.setPoints(1);
        assertEquals(1, p.getPoints());
    }

}
