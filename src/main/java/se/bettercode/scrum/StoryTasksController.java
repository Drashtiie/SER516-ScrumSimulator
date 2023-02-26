package se.bettercode.scrum;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class StoryTasksController extends BorderPane {
    private Story story;

    private Stage stage;

    @FXML
    private VBox doneStatusBox;

    @FXML
    private VBox progressStatusBox;

    @FXML
    private VBox newStatusBox;

    @FXML
    private Text storyTitle;

    @FXML
    private BorderPane borderPane;

    public StoryTasksController() {
    }

    public VBox getTaskCard(Task task){
        VBox taskCard = new VBox();

        Text titleNode = new Text();
        titleNode.setText("# " + task.getTitle());
        taskCard.getChildren().add(0, titleNode);

        Text descNode = new Text();
        descNode.setText(task.getDescription());
        taskCard.getChildren().add(1, descNode);

        taskCard.setPrefWidth(10);
        taskCard.setMaxWidth(10);
        taskCard.setStyle("-fx-hgap: 20px;\n" +
                "    -fx-padding: 10px;\n" +
                "\n" +
                "    -fx-background-color: #abf08b;\n" +
                "    -fx-background-radius: 10px;\n" +
                "\n" +
                "    -fx-border-radius: 10px;\n" +
                "    -fx-border-width: 2px;\n" +
                "    -fx-border-color: black;\n" +
                "    -fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.8), 10, 0, 0, 0);");

        taskCard.setPrefWidth(300);
        taskCard.setMaxWidth(300);
        taskCard.setMinWidth(300);

        VBox.setMargin(taskCard, new Insets(20, 0, 0, 0));

        return taskCard;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        this.story = (Story) stage.getUserData();
        this.storyTitle.setText("# " + this.story.getTitle());

        newStatusBox.setStyle(
                "    -fx-border-width: 2px;\n" +
                "    -fx-border-color: black;\n"
        );

        doneStatusBox.setStyle(
                "    -fx-border-width: 2px;\n" +
                "    -fx-border-color: black;\n"
        );

        progressStatusBox.setStyle(
                "    -fx-border-width: 2px;\n" +
                "    -fx-border-color: black;\n"
        );

        storyTitle.setStyle(
                "    -fx-background-color: #3d91bf;\n" +
                "    -fx-border-width: 2px;\n" +
                "    -fx-border-color: black;\n"
        );

        ArrayList<Task> tasks = this.story.getTasks();

        for (Task task : tasks) {
            if (task.getStatus().equals("NEW")){
                newStatusBox.getChildren().add(this.getTaskCard(task));
                newStatusBox.setAlignment(Pos.TOP_CENTER);
            } else if(task.getStatus().equals("DONE")){
                doneStatusBox.getChildren().add(this.getTaskCard(task));
                doneStatusBox.setAlignment(Pos.TOP_CENTER);
            } else{
                progressStatusBox.getChildren().add(this.getTaskCard(task));
                progressStatusBox.setAlignment(Pos.TOP_CENTER);
            }
        }
    }
}
