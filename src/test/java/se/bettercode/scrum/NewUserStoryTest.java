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

    @Test
    public void usersListTest() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/java/se/bettercode/scrum/team/usersInfo"));
        String line = reader.readLine();
        String[] token;
        int commaLoc;
        String name;
        ObservableList<String> temp = FXCollections.observableArrayList();
        while (line != null) {
            token = line.split(":");
            commaLoc = token[1].indexOf(',');
            name = token[1].substring(2,commaLoc);
            temp.add(name);
            line = reader.readLine();
        }
        ArrayList<ArrayList<String>> userDetailsList = user.getUsers();
        ObservableList<String> userNamesList = FXCollections.observableArrayList();
        for (int i = 0; i < userDetailsList.size(); i++){
            userNamesList.add(userDetailsList.get(i).get(1));
        }
        assertEquals(temp, userNamesList);
    }
}
