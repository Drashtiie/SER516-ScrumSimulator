package se.bettercode.scrum;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StoryDaysTest {

    StoryDays storyDays;

    @Before
    public void setUp() {
        storyDays = new StoryDays();
    }

    @Test(expected = IllegalArgumentException.class)
    public void startedDayMustNotBeNegative() {
        storyDays.setStartedDay(-1);
    }

    @Test
    public void startedDayCanBeSet() {
        storyDays.setStartedDay(1);
        assertEquals(1, storyDays.getStartedDay());
    }

    @Test(expected = IllegalArgumentException.class)
    public void doneDayMustNotBeBeforeStartedDay() {
        storyDays.setStartedDay(3);
        storyDays.setDoneDay(2);
    }

    @Test
    public void leadTime() {
        storyDays.setStartedDay(3);
        storyDays.setDoneDay(5);
        assertEquals(2.0, storyDays.getLeadTime(), 0.0);
    }

}
