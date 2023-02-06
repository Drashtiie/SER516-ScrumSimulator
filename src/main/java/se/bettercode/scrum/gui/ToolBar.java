package se.bettercode.scrum.gui;

import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;

public class ToolBar extends HBox {

    private final Button toggleButton = new Button("Hide Burnup");

    private final Button toggleButton2 = new Button("Unhide Burnup");
    private final Button startButton = new Button("Start Sprint");
    private final Button viewDocs = new Button("Documents");
    private ChoiceBox<String> teamChoiceBox = new ChoiceBox<>();
    private ChoiceBox<String> backlogChoiceBox = new ChoiceBox<>();

    public ToolBar(String[] teams, String[] backlogs) {
        setPadding(new Insets(15, 12, 15, 12));
        setSpacing(10);
        setStyle("-fx-background-color: #336699;");

        teamChoiceBox.setItems(FXCollections.observableArrayList(teams));
        teamChoiceBox.setTooltip(new Tooltip("Select team"));

        backlogChoiceBox.setItems(FXCollections.observableArrayList(backlogs));
        backlogChoiceBox.setTooltip(new Tooltip("Select backlog"));

        startButton.setPrefSize(100, 20);
        viewDocs.setPrefSize(100, 20);
        toggleButton.setPrefSize(120,20);
        toggleButton2.setPrefSize(120,20);

        getChildren().addAll(teamChoiceBox, backlogChoiceBox, startButton, viewDocs, toggleButton, toggleButton2);
    }

    public void setStartButtonAction(EventHandler<ActionEvent> eventHandler) {
        startButton.setOnAction(eventHandler);
    }

    public void setViewDocsButtonAction(EventHandler<ActionEvent> eventHandler) {
        viewDocs.setOnAction(eventHandler);
    }

    public void setToggleButtonAction(EventHandler<ActionEvent> eventHandler) {
        toggleButton.setOnAction(eventHandler);
    }

    public void setToggleButton2Action(EventHandler<ActionEvent> eventHandler) {
        toggleButton2.setOnAction(eventHandler);
    }
    public void bindRunningProperty(BooleanProperty booleanProperty) {
        teamChoiceBox.disableProperty().bind(booleanProperty);
        backlogChoiceBox.disableProperty().bind(booleanProperty);
        startButton.disableProperty().bind(booleanProperty);
        viewDocs.disableProperty().bind(booleanProperty);
        toggleButton.disableProperty().bind(booleanProperty);
        toggleButton2.disableProperty().bind(booleanProperty);
    }

    public void setTeamChoiceBoxListener(ChangeListener<String> changeListener) {
        teamChoiceBox.getSelectionModel().selectedItemProperty().addListener(changeListener);
    }

    public void setBacklogChoiceBoxListener(ChangeListener<String> changeListener) {
        backlogChoiceBox.getSelectionModel().selectedItemProperty().addListener(changeListener);
    }


//    public void setToggleButtonAction(EventHandler<ActionEvent> eventHandler) {toggleButton.setOnAction(event -> {
//        BurnupChart.this.setIsVisible(toggleButton.isSelected());
//    });
//    }
}
