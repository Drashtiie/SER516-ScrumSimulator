package se.bettercode.scrum.gui;

import javafx.collections.ObservableList;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class UserWindowTest extends TestCase {
    private UserWindow userWindow;

    @Before
    public void setUp() {
        userWindow = new UserWindow();
    }
    @Test
    public void testReadLinesFromFile() {
        String[] expected = {"", "team 1", "team 2", "team 3",""};
        String[] actual = UserWindow.readLinesFromFile("src/main/java/se/bettercode/scrum/gui/Teams");
        assertArrayEquals(expected, actual);
    }

    public void testUserTeamField() {
        ObservableList<String> teams = userWindow.userTeamField.getItems();
        assertEquals(Arrays.asList("Team 1", "Team 2", "Team 3"), teams);
    }

    public void testShow() {


    }

    public void testSetAddUsersButtonAction() {
    }
}