package se.bettercode.scrum;

import de.saxsys.javafx.test.JfxRunner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import org.junit.Test;
import org.junit.runner.RunWith;
import se.bettercode.scrum.team.User;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(JfxRunner.class)
public class NewUserStoryTest {
//    JFXPanel fxPanel = new JFXPanel();
    static User user = new User();
//    static ScrumGameApplication sga = new ScrumGameApplication();
//Check if it is going in File
    @Test
    public void statusTest() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/java/se/bettercode/scrum/team/UserStoryStatus"));
        String line = reader.readLine();
        assertEquals(line, "Ready to Test");
    }
}