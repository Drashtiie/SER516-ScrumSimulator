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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AllProjects{

    private HBox toolBar = new HBox();
    private Button addButton = new Button();


    TextField teamNameField = new TextField ();

    Label teamAddedAlert = new Label("");



    public void show() {
        toolBarSetup();

        addButton.setPrefSize(300, 20);
        addButton.setText("List of All Project");

        Stage primaryStage = new Stage();
        StackPane secondaryLayout = new StackPane();
        BorderPane borderPane = new BorderPane();
        secondaryLayout.prefWidthProperty().bind(primaryStage.widthProperty());

        borderPane.setCenter(secondaryLayout);
        borderPane.setTop(toolBar);
        ObservableList<String> projects = FXCollections.observableArrayList();
        try {
            File myObj = new File("src/main/java/se/bettercode/scrum/gui/Projects");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();


                projects.add(data);
               }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        ListView<String> listView = new ListView<String>(projects);
        listView.setMaxSize(700, 700);

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(5, 50, 5, 50));
        layout.getChildren().addAll(listView);
        // layout.setStyle("-fx-background-color: BEIGE");


        secondaryLayout.getChildren().add(layout);

        //Setting the stage
        primaryStage.setScene(new Scene(borderPane, 800, 600));
        primaryStage.show();

    }




    private void toolBarSetup() {
        toolBar.setPadding(new Insets(15, 12, 15, 12));
        toolBar.setSpacing(10);
        toolBar.setStyle("-fx-background-color: #336699;");
        toolBar.getChildren().addAll(  addButton);

    }




}
