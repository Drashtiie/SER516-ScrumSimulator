package se.bettercode.scrum;

import org.junit.Test;

import static org.junit.Assert.*;

public class TaskTest {

    @Test
    public void addToStory() {
        /*
        Test Cases:
        1. Story and list of tasks is not null
        2. check add task functionality
         */
        Task task = new Task("123", "New Desc", "NEW", "BUG");
        Story userStory = new Story(9, "lets go");
        Story resultingStory = task.addToStory(userStory);

        assertNotEquals(resultingStory, null);
        assertNotEquals(resultingStory.getTasks(), null);

        assertEquals(resultingStory.getTasks().get(0), task);
    }

    @Test
    public void deleteFromStory() {
        /*
        Test Cases:
        1. Story and list of tasks is not null
        2. check delete task functionality
         */
        Task task = new Task("456", "Old Desc", "DONE", "DEBT");
        Story userStory = new Story(6, "go alone");
        Story resultingStory1 = task.addToStory(userStory);
        Story resultingStory2 = task.deleteFromStory(resultingStory1);

        assertNotEquals(resultingStory1, null);
        assertNotEquals(resultingStory1.getTasks(), null);
        assertNotEquals(resultingStory2, null);
        assertNotEquals(resultingStory2.getTasks(), null);

        assertNotEquals(resultingStory1.getTasks(), resultingStory2.getTasks());
    }
}