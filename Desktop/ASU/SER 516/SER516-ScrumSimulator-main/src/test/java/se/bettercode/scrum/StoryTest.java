package se.bettercode.scrum;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StoryTest {

    Story story;

    @Before
    public void setUp() throws Exception {
        story = new Story(5);
    }

    @Test
    public void testGetTotalPoints() throws Exception {
        assertEquals(5, story.getTotalPoints().getPoints());
    }

    @Test
    public void testGetPointsDone() throws Exception {
        assertEquals(0, story.getPointsDone().getPoints());
    }

    @Test
    public void testGetStatus() throws Exception {
        assertEquals(Story.StoryState.TODO, story.getStatus());
    }

    @Test
    public void testWorkOnStoryWhenOnlyPartiallyCompleted() throws Exception {
        int remainingPoints = story.workOnStory(3, 1);
        assertEquals(0, remainingPoints);
        assertEquals(Story.StoryState.STARTED, story.getStatus());
        assertEquals(3, story.getPointsDone().getPoints());
        assertEquals(2, story.getRemainingPoints());
    }

    @Test
    public void testWorkOnStoryUntilExactCompletion() throws Exception {
        int remainingPoints = story.workOnStory(5, 1);
        assertEquals(0, remainingPoints);
        assertEquals(Story.StoryState.FINISHED, story.getStatus());
        assertEquals(5, story.getPointsDone().getPoints());
        assertEquals(story.getTotalPoints(), story.getPointsDone());
        assertEquals(0, story.getRemainingPoints());
    }

    @Test
    public void testWorkOnStoryUntilOverCompleted() throws Exception {
        int remainingPoints = story.workOnStory(7, 1);
        assertEquals(Story.StoryState.FINISHED, story.getStatus());
        assertEquals(5, story.getPointsDone().getPoints());
        assertEquals(story.getTotalPoints(), story.getPointsDone());
        assertEquals(0, story.getRemainingPoints());
        assertEquals(2, remainingPoints);
    }

    @Test
    public void testWorkOnStorySeveralTimes() throws Exception {
        story.workOnStory(3, 1);
        story.workOnStory(1, 2);
        assertEquals(Story.StoryState.STARTED, story.getStatus());
        assertEquals(4, story.getPointsDone().getPoints());
        assertEquals(1, story.getRemainingPoints());
    }

    @Test
    public void testLeadTime() {
        final int POINTS_PER_DAY = 2;
        for (int day=1; day<=3; day++) {
            story.workOnStory(POINTS_PER_DAY, day);
        }
        assertEquals(Story.StoryState.FINISHED, story.getStatus());
        assertEquals(2.0, story.getLeadTime(), 0.0);
    }

    @Test
    public void testGetRemainingPoints() throws Exception {
        assertEquals(5, story.getRemainingPoints());
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("Story{points=5, pointsDone=0, status=TODO}", story.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStoryWithNegativePoints() {
        new Story(-1);
    }

}
