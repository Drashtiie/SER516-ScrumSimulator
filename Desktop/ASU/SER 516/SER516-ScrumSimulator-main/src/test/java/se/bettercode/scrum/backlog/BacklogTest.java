package se.bettercode.scrum.backlog;

import org.junit.Before;
import org.junit.Test;
import se.bettercode.scrum.Story;

import java.util.NoSuchElementException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BacklogTest {

    Backlog backlog;

    @Before
    public void setUp() {
        backlog = new Backlog("Test");
        backlog.addStory(new Story(3));
    }

    @Test
    public void testGetName() {
        assertEquals("Test", backlog.getName());
    }

    @Test
    public void testAddStory() {
        assertEquals(1, backlog.stories.size());
        backlog.addStory(new Story(5));
        assertEquals(2, backlog.stories.size());
    }

    @Test
    public void testGetStory() {
        Story story = backlog.getStory();
        assertNotNull(story);
    }

    @Test
    public void testGetStoryWhenAllAreFinished() {
        Story story = backlog.getStory();
        story.workOnStory(3, 1);
        boolean exceptionHit = false;
        try {
            story = backlog.getStory();
        } catch (NoSuchElementException e) {
            exceptionHit = true;
        }
        assertTrue(exceptionHit);
    }

    @Test
    public void testGetFinishedPoints() {
        backlog.addStory(new Story(5));
        backlog.addStory(new Story(7));
        Story story = backlog.getStory();
        int work = story.getTotalPoints().getPoints();
        story.workOnStory(work, 1);
        assertEquals(work, backlog.getFinishedPoints());
    }

    @Test
    public void testGetFinishedStoriesCount() {
        assertEquals(0, backlog.getFinishedStoriesCount());
        Story story = backlog.getStory();
        story.workOnStory(3, 1);
        assertEquals(1, backlog.getFinishedStoriesCount());
    }

    @Test
    public void testGetTotalPoints() {
        backlog.addStory(new Story(5));
        backlog.addStory(new Story(7));
        assertEquals(15, backlog.getTotalPoints());
    }

    @Test
    public void testGetWorkInProgressPoints() {
        backlog.addStory(new Story(5));
        backlog.addStory(new Story(7));
        Story story = backlog.getStory();
        int work = story.getTotalPoints().getPoints() - 1;
        story.workOnStory(work, 1);
        assertEquals(work, backlog.getWorkInProgressPoints());
        assertEquals(0, backlog.getFinishedPoints());
    }

    @Test
    public void testIsFinished() {
        backlog = new Backlog("Mah backlog");
        Story story1 = new Story(1);
        Story story2 = new Story(2);
        Story story3 = new Story(3);
        backlog.addStory(story1);
        backlog.addStory(story2);
        backlog.addStory(story3);
        story1.workOnStory(1, 1);
        story2.workOnStory(2, 1);
        assertEquals(false, backlog.isFinished());
        story3.workOnStory(3, 1);
        assertEquals(true, backlog.isFinished());
    }

    //TODO: Cant use runDay in test because of IllegalStateException: Toolkit not initialized
    /*
    public void testAverageLeadTime() {
        backlog.addStory(new Story(8));
        backlog.addStory(new Story(10));
        backlog.addStory(new Story(5));
        int dailyBurn = 2;
        for (int day=1; day<=20; day++) {
            backlog.runDay(dailyBurn, day);
        }
        assertEquals(2.6, backlog.getAverageLeadTime());
    }
    */

    @Test
    public void testToString() {
        backlog.addStory(new Story(1));
        assertEquals("Backlog{stories=[Story{points=3, pointsDone=0, status=TODO}, Story{points=1, pointsDone=0, status=TODO}], average lead time=0.0}", backlog.toString());
    }
}