package se.bettercode.scrum.gui;

import javafx.scene.layout.StackPane;
import javafx.scene.Scene;
import java.time.LocalDateTime;
import java.time.Duration;
//Added comment for testing integration
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javafx.stage.Modality;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
//<<<<<<< manantpu
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.nio.file.Path;
import java.nio.file.Files;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.Optional;


public class ToolBar extends HBox {
    
    private final Button toggleButton = new Button("Hide Burnup");
    private final Button toggleButton2 = new Button("Unhide Burnup");
    private final Button startButton = new Button("Start Sprint");
    private final Button viewDocs = new Button("Documents");
    private ChoiceBox<String> teamChoiceBox = new ChoiceBox<>();
    private ChoiceBox<String> backlogChoiceBox = new ChoiceBox<>();


    private final Button viewtime = new Button("Enter Time 1");


    private final Button addUsers = new Button("Users + ");
    private final Button addTeam = new Button("Team + ");

    Button addUserStory = new Button("Add user story ");
    TextField userStory = new TextField ();
    Label userstoryalert = new Label("");

    //=======
    Button addNewTeamButton = new Button("Add + ");
    TextField teamNameField = new TextField ();

    Label teamAddedAlert = new Label("");
    //>>>>>>> main
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


//<<<<<<< manantpu
        getChildren().addAll(teamChoiceBox, backlogChoiceBox, addUserStory, userStory,userstoryalert, startButton, teamNameField, addNewTeamButton, teamAddedAlert, viewDocs, toggleButton, toggleButton2, viewtime);
        addUsers.setPrefSize(120,20);
//        getChildren().addAll(teamChoiceBox, backlogChoiceBox, addUserStory, startButton, teamNameField, addNewTeamButton, teamAddedAlert, viewDocs);
        /* 


        addUserStory.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if ((userStory.getText() != null && !userStory.getText().isEmpty())) {
                    userstoryalert.setText(userStory.getText() +  "- named user story added");
                    try
                    {
                        String filename= "src/main/java/se/bettercode/scrum/gui/UserStory";

                        FileWriter fw = new FileWriter(filename, true);
                        BufferedWriter bw = new BufferedWriter(fw);   
                        bw.write(userStory.getText());
                        bw.newLine();
                        bw.close();

                        System.out.println("Added user story"+userStory.getText());
                    }
                    catch(IOException ioe)
                    {
                        System.err.println("IOException: " + ioe.getMessage());
                    }
                    } 
                    else {
                    userstoryalert.setText("You have not left a comment.");
                }
                }
            });

*/
        teamChoiceBox.setItems(FXCollections.observableArrayList(teams));
        teamChoiceBox.setTooltip(new Tooltip("Select team"));

    }
//>>>>>>> main





    public void setStartButtonAction(EventHandler<ActionEvent> eventHandler) {
        startButton.setOnAction(eventHandler);
    }
    public void setAddNewTeamButton(EventHandler<ActionEvent> eventHandler) {
        addNewTeamButton.setOnAction(eventHandler);
    }


    public void setViewDocsButtonAction(EventHandler<ActionEvent> eventHandler) {
        viewDocs.setOnAction(eventHandler);
    }

    public void setAddUsedrsButtonAction(EventHandler<ActionEvent> eventHandler) {
        addUsers.setOnAction(eventHandler);
    }
    public void setAddTeamButtonAction(EventHandler<ActionEvent> eventHandler) {
        addTeam.setOnAction(eventHandler);
    }

    public void setToggleButtonAction(EventHandler<ActionEvent> eventHandler) {
        toggleButton.setOnAction(eventHandler);
    }

    public void setToggleButton2Action(EventHandler<ActionEvent> eventHandler) {
        toggleButton2.setOnAction(eventHandler);
    }
    public void setViewtimeAction(EventHandler<ActionEvent> eventHandler){
            viewtime.setOnAction(eventHandler);
    }
    public void bindRunningProperty(BooleanProperty booleanProperty) {
        backlogChoiceBox.disableProperty().bind(booleanProperty);
        startButton.disableProperty().bind(booleanProperty);
        addNewTeamButton.disableProperty().bind(booleanProperty);
        teamChoiceBox.disableProperty().bind(booleanProperty);
        //=======
        viewDocs.disableProperty().bind(booleanProperty);

        addUserStory.disableProperty().bind(booleanProperty);
        toggleButton.disableProperty().bind(booleanProperty);
        toggleButton2.disableProperty().bind(booleanProperty);
        viewtime.disableProperty().bind(booleanProperty);
    }
    public void setUserStoryButtonAction(EventHandler<ActionEvent> eventHandler) {
        addUserStory.setOnAction(eventHandler);

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
public void start(final Stage primaryStage) {

    viewtime.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(primaryStage);
            StackPane dialogLayout = new StackPane();
            Scene dialogScene = new Scene(dialogLayout, 300, 250);
            dialog.setScene(dialogScene);
            dialog.show();

            // Create the countdown timer (!!!!!!! change localdatetime before final merge!!!! - Anish)
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime end = now.plusDays(2).plusHours(3).plusMinutes(30);
            Duration duration = Duration.between(now, end);

            // Add timer to window

            dialogLayout.getChildren().add(new Label(
                    String.format("Count down: %d days, %d hours, %d minutes",
                            duration.toDays(),
                            duration.toHours() - (duration.toDays() * 24),
                            duration.toMinutes() - (duration.toHours() * 60))
            ));
        }
    });

    StackPane root = new StackPane();
    root.getChildren().add(viewtime);
    Scene scene = new Scene(root, 300, 250);
    primaryStage.setScene(scene);
    primaryStage.show();
}


}
