package se.bettercode.scrum;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;

public class StoryTasksController extends BorderPane {
    private Story story;

    private Stage stage;

    public Story getStory() {
        return story;
    }

    public Stage getStage() {
        return stage;
    }

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
        VBox titleContainer = new VBox();
        VBox descContainer = new VBox();
        HBox buttonContainer = new HBox();
        HBox typeContainer = new HBox();

        Text titleNode = new Text();
        titleNode.setText("# " + task.getTitle());
        titleNode.setStyle("-fx-font-weight: bold;");
        titleContainer.getChildren().add(0, titleNode);

        Text descNode = new Text();
        descNode.setText(task.getDescription());
        descContainer.getChildren().add(0, descNode);

        Text typeNode = new Text();
        typeNode.setText(task.getTaskType());
        typeContainer.getChildren().add(0, typeNode);
        descContainer.getChildren().add(1, typeContainer);

        Button deleteButton = new Button();
        descContainer.getChildren().add(2, buttonContainer);
        buttonContainer.getChildren().add(0, deleteButton);
        buttonContainer.setAlignment(Pos.CENTER_RIGHT);
        deleteButton.setStyle(
                "    -fx-background-color: #3d91bf;\n" +
                "    -fx-background-radius: 50px;\n" +
                "\n" +
                "    -fx-border-radius: 50px;\n" +
                "    -fx-border-width: 1px;\n" +
                "    -fx-border-color: black;\n" +
                "    -fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.8), 5, 0, 0, 0);"
        );

        Image img = new Image("https://cdn-icons-png.flaticon.com/512/3334/3334328.png");
        ImageView view = new ImageView(img);
        view.setPreserveRatio(true);
        view.setFitWidth(20);
        view.setFitHeight(20);

        deleteButton.setPrefSize(30, 30);
        deleteButton.setMinSize(30, 30);
        deleteButton.setMaxSize(30, 30);
        deleteButton.setGraphic(view);
        deleteButton.setAlignment(Pos.CENTER);
        deleteButton.getStyleClass().add("delete_button");
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                  ArrayList<Task> updatedStoryTasks = story.getTasks();
                  updatedStoryTasks.remove(task);
                  story.setTasks(updatedStoryTasks);
                  stage.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        taskCard.getChildren().add(0, titleContainer);
        taskCard.getChildren().add(1, descContainer);

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
        VBox.setMargin(descNode, new Insets(10, 0, 0, 0));
        HBox.setMargin(typeNode, new Insets(10, 0, 0, 0));

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
