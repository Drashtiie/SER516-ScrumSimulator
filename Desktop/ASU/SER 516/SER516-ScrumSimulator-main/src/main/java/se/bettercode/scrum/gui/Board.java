package se.bettercode.scrum.gui;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import se.bettercode.scrum.backlog.Backlog;
import se.bettercode.scrum.Story;
import se.bettercode.scrum.StoryCardController;

import java.util.Arrays;
import java.util.List;

public class Board extends GridPane {

    private Backlog backlog;
    private final VBox todoColumn = new VBox(10);
    private final VBox startedColumn = new VBox(10);
    private final VBox doneColumn = new VBox(10);
    private final DingAudioClip dingAudioClip = new DingAudioClip();

    public Board() {
        setPadding(new Insets(10));

        int i = 0;
        for (VBox columnBox : columns()) {
            ColumnConstraints constraints = new ColumnConstraints();
            constraints.setPercentWidth(100); // Will be treated as relative weight when sum is over 100.
            getColumnConstraints().add(constraints);
            add(columnBox, i++, 1);
        }

    }

    private List<VBox> columns() {
        return Arrays.asList(todoColumn, startedColumn, doneColumn);
    }


    public void bindBacklog(Backlog backlog) {
        this.backlog = backlog;
        for (Story story : backlog.getStories()) {
            story.statusProperty().addListener((observable, oldValue, newValue) -> {
                updateBoard();
                dingAudioClip.playIfDone(story);
            });
        }
        updateBoard();
    }

    private void updateBoard() {
        Platform.runLater(() -> {
            clearAllColumns();
            for (Story story : backlog.getStories()) {
                switch (story.getStatus()) {
                    case TODO:
                        todoColumn.getChildren().add(new StoryCardController(story));
                        break;
                    case STARTED:
                        startedColumn.getChildren().add(new StoryCardController(story));
                        break;
                    case FINISHED:
                        doneColumn.getChildren().add(new StoryCardController(story));
                        break;
                }
            }

        });
    }

    private void clearAllColumns() {
        todoColumn.getChildren().clear();
        startedColumn.getChildren().clear();
        doneColumn.getChildren().clear();
        addColumnHeaders();
    }

    private void addColumnHeaders() {
        todoColumn.getChildren().add(new Text("TODO"));
        startedColumn.getChildren().add(new Text("STARTED"));
        doneColumn.getChildren().add(new Text("DONE"));
    }
}
