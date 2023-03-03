package se.bettercode.scrum;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.Test;

import static org.junit.Assert.*;

public class StoryTasksControllerTest {

    @Test
    public void getTaskCard() {
        /*
        Test Cases:
        1. Check task card title value
        2. Check task card title format / UI
        3. Check task card description value
        4. Check task card description format / UI (especially testing the new line case i.e. new line character should be retained)
        5. Check task type value on the task card
        6. Task card not empty or not null
         */

        StoryTasksController controller = new StoryTasksController();
        Task task = new Task("123", "New Desc\n new line", "NEW", "BUG");
        VBox taskCard = controller.getTaskCard(task);
        ObservableList<Node> nodes = taskCard.getChildren();

        VBox titleContainer = (VBox) nodes.get(0);
        VBox descContainer = (VBox) nodes.get(1);

        Text title = (Text) titleContainer.getChildren().get(0);
        assertNotEquals(title.getText(), null);
        assertEquals("# 123" , title.getText());

        Text description = (Text) descContainer.getChildren().get(0);
        assertNotEquals(description.getText(), null);
        assertEquals("New Desc\n new line" , description.getText());

        HBox typeContainer = (HBox) descContainer.getChildren().get(1);
        Text type = (Text) typeContainer.getChildren().get(0);
        assertNotEquals(type.getText(), null);
        assertEquals("NEW" , type.getText());

        assertNotEquals(taskCard, null);
    }

    @Test
    public void setStage() {
         /*
         Test Cases:
        1. Current user story and task list not null
          */

        Stage stage = new Stage();
        StoryTasksController controller = new StoryTasksController();
        controller.setStage(stage);
        Story userStory = controller.getStory();

        assertNotEquals(userStory, null);
        assertNotEquals(userStory.getTasks(), null);
    }
}