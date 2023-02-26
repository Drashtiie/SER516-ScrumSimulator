package se.bettercode.scrum;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public class StoryCardController extends BorderPane {

    public static final int MEDIUM_STORY_POINTS = 5;

    @FXML
    private Text storyPoints;

    @FXML
    private Button addTaskButton;

    @FXML
    private Button viewTaskButton;

    @FXML
    private Text storyTitle;

    @FXML
    private BorderPane storyCard;

    private Story story;

    public StoryCardController(Story story) {
        this.story = story;
        URL location = getClass().getResource("StoryCard.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        storyTitle.setText(story.getTitle());
        storyPoints.setText(Integer.toString(story.getPointsDone().getPoints()) +
                "/" + Integer.toString(story.getTotalPoints().getPoints()));
        setPrefHeight(getHeightBasedOnStoryPoints());

        addTaskButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TaskCard.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setTitle("Task Card");
                    stage.setScene(new Scene(root1));
                    stage.setUserData(story);
                    stage.show();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        viewTaskButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StoryTasks.fxml"));
                    Parent root2 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setTitle("Story Tasks");
                    stage.setScene(new Scene(root2));
                    stage.setUserData(story);
                    StoryTasksController tasksController = fxmlLoader.getController();
                    tasksController.setStage(stage);
                    stage.show();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void bindStoryTitle(StringProperty title) {
        storyTitle.textProperty().bind(title);
    }

    public void bindStoryPoints(IntegerProperty points) {
        storyPoints.textProperty().bind(Bindings.convert(points));
    }

    private double getHeightBasedOnStoryPoints() {
        if (story.getTotalPoints().getPoints() > MEDIUM_STORY_POINTS) {
            return 110;
        } else if (story.getTotalPoints().getPoints() < MEDIUM_STORY_POINTS) {
            return 45;
        } else {
            return 80;
        }
    }
}
