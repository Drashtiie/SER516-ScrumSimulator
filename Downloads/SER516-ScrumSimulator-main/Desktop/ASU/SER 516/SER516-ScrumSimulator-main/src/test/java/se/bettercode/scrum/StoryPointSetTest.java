package se.bettercode.scrum;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StoryPointSetTest {

    StoryPointSet storyPointSet;

    @Before
    public void setUp() {
    }

    @Test
    public void applySinglePoint() {
        storyPointSet = new StoryPointSet(5);
        assertEquals(5, storyPointSet.getTotal().getPoints());
        assertEquals(5, storyPointSet.getRemaining().getPoints());
        assertEquals(0, storyPointSet.getDone().getPoints());

        storyPointSet.apply(1);
        assertEquals(5, storyPointSet.getTotal().getPoints());
        assertEquals(4, storyPointSet.getRemaining().getPoints());
        assertEquals(1, storyPointSet.getDone().getPoints());
    }

    @Test
    public void applyAllPointsAtOnce() {
        storyPointSet = new StoryPointSet(5);
        storyPointSet.apply(5);
        assertEquals(5, storyPointSet.getTotal().getPoints());
        assertEquals(0, storyPointSet.getRemaining().getPoints());
        assertEquals(5, storyPointSet.getDone().getPoints());
    }

    @Test(expected = IllegalArgumentException.class)
    public void applyTooManyPoints() {
        storyPointSet = new StoryPointSet(5);
        storyPointSet.apply(6);
    }
}
