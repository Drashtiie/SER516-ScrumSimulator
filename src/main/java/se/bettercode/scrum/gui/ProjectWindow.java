package se.bettercode.scrum.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import se.bettercode.scrum.team.User;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
public class ProjectWindow {

    private AllProjects allProjectWindow = new AllProjects();

    private HBox toolBar = new HBox();
    private Button addButton = new Button();


    TextField teamNameField = new TextField ();

    Label teamAddedAlert = new Label("");

    private Button viewButton = new Button();


    public void show() {
        toolBarSetup();

        addButton.setPrefSize(100, 20);
        addButton.setText("Add Project");

        viewButton.setPrefSize(100, 20);
        viewButton.setText("View Projects");
        Stage primaryStage = new Stage();
        StackPane secondaryLayout = new StackPane();
        BorderPane borderPane = new BorderPane();
        secondaryLayout.prefWidthProperty().bind(primaryStage.widthProperty());

        borderPane.setCenter(secondaryLayout);
        borderPane.setTop(toolBar);


        VBox layout = new VBox(10);
        layout.setPadding(new Insets(5, 50, 5, 50));
        layout.getChildren().addAll(teamAddedAlert);
        // layout.setStyle("-fx-background-color: BEIGE");


        secondaryLayout.getChildren().add(layout);

        //Setting the stage
        primaryStage.setScene(new Scene(borderPane, 800, 600));
        primaryStage.show();

    }




//    public void setAddUsersButtonAction(EventHandler<ActionEvent> eventHandler) {
//        addButton.setOnAction(eventHandler);
//    }

    private void toolBarSetup() {
        toolBar.setPadding(new Insets(15, 12, 15, 12));
        toolBar.setSpacing(10);
        toolBar.setStyle("-fx-background-color: #336699;");
        toolBar.getChildren().addAll( teamNameField, addButton, viewButton);
//
//        toolBar.setViewProjectButtonAction((event) -> allProjectWindow.show());
//        public void setViewProjectButtonAction(EventHandler<ActionEvent> eventHandler) {
//            viewButton.setOnAction(eventHandler);
//        eventHandler}

        addButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if ((teamNameField.getText() != null && !teamNameField.getText().isEmpty())) {
                    teamAddedAlert.setText(teamNameField.getText() +  " added as project");
                    try
                    {
                        String filename= "src/main/java/se/bettercode/scrum/gui/Projects";
                        FileWriter fw = new FileWriter(filename, true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(teamNameField.getText());
                        bw.newLine();
                        bw.close();
                        System.out.println("Added ");


                    }
                    catch(IOException ioe)
                    {
                        System.err.println("IOException: " + ioe.getMessage());
                    }
                } else {

                    teamAddedAlert.setText("You have not left a project name.");

                }
            }

        });
        viewButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                allProjectWindow.show();
            }

        });




    }




}
