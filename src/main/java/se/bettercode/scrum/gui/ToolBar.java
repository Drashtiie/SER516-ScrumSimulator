package se.bettercode.scrum.gui;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
//=======
import javafx.scene.control.*;
//>>>>>>> main
import javafx.scene.layout.HBox;
import javafx.scene.control.*;
import java.nio.file.Path;
import java.nio.file.Files;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
public class ToolBar extends HBox {

    

    private final Button startButton = new Button("Start Sprint");
    private final Button viewDocs = new Button("Documents");
    private ChoiceBox<String> teamChoiceBox = new ChoiceBox<>();
    private ChoiceBox<String> backlogChoiceBox = new ChoiceBox<>();

//<<<<<<< manantpu

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


        backlogChoiceBox.setItems(FXCollections.observableArrayList(backlogs));
        backlogChoiceBox.setTooltip(new Tooltip("Select backlog"));

        startButton.setPrefSize(100, 20);
        viewDocs.setPrefSize(100, 20);

//<<<<<<< manantpu
        getChildren().addAll(teamChoiceBox, backlogChoiceBox, addUserStory, userStory,userstoryalert, startButton, teamNameField, addNewTeamButton, teamAddedAlert, viewDocs);

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
                    } else {
                    userstoryalert.setText("You have not left a comment.");
                }
                }


//=======
//<<<<<<< drashti
        //getChildren().addAll(teamChoiceBox, backlogChoiceBox, startButton,teamNameField, addNewTeamButton, teamAddedAlert);

        addNewTeamButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if ((teamNameField.getText() != null && !teamNameField.getText().isEmpty())) {
                    teamAddedAlert.setText(teamNameField.getText() +  "added as team");
                    try
                    {
                        String filename= "C:\\ser516\\ser516public\\project1\\ScrumBoardSimulator\\src\\main\\java\\se\\bettercode\\scrum\\gui\\Teams";
//                        FileWriter fw = new FileWriter(filename,true); //the true will append the new data
//                        fw.write("add a line\n");//appends the string to the file
//                        fw.close();

                        FileWriter fw = new FileWriter(filename, true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(teamNameField.getText());
                        bw.newLine();
                        bw.close();

//                        String text
//                                = "Welcome to geekforgeeks\nHappy Learning!";
//
//                        // Defining the file name of the file
//                        Path fileName = Path.of(
//                                "C:\\ser516\\ser516public\\project1\\ScrumBoardSimulator\\src\\main\\java\\se\\bettercode\\scrum\\gui\\Teams");
//
//                        // Writing into the file
//                        Files.write(fileName, text);

                        // Reading the content of the file
//                        String file_content = Files.readString(fileName);
//
//                        // Printing the content inside the file
//                        System.out.println(file_content);
                        System.out.println("Added ");


                        teamChoiceBox.setItems(FXCollections.observableArrayList(teams));
                        teamChoiceBox.setTooltip(new Tooltip("Select team"));
//>>>>>>> main
                    }
                    catch(IOException ioe)
                    {
                        System.err.println("IOException: " + ioe.getMessage());
                    }
                } else {
//<<<<<<< manantpu
//=======
                    teamAddedAlert.setText("You have not left a comment.");
//>>>>>>> main
                }
            }
        });


//<<<<<<< manantpu
//}

//=======
        teamChoiceBox.setItems(FXCollections.observableArrayList(teams));
        teamChoiceBox.setTooltip(new Tooltip("Select team"));

        //getChildren().addAll(teamChoiceBox, backlogChoiceBox, startButton, viewDocs);
//>>>>>>> main
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
    public void bindRunningProperty(BooleanProperty booleanProperty) {
        backlogChoiceBox.disableProperty().bind(booleanProperty);
        startButton.disableProperty().bind(booleanProperty);
//<<<<<<< drashti
        addNewTeamButton.disableProperty().bind(booleanProperty);
        teamChoiceBox.disableProperty().bind(booleanProperty);
//=======
        viewDocs.disableProperty().bind(booleanProperty);
//>>>>>>> main
    }

    public void setTeamChoiceBoxListener(ChangeListener<String> changeListener) {
        teamChoiceBox.getSelectionModel().selectedItemProperty().addListener(changeListener);
    }

    public void setBacklogChoiceBoxListener(ChangeListener<String> changeListener) {
        backlogChoiceBox.getSelectionModel().selectedItemProperty().addListener(changeListener);
    }


}