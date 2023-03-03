package se.bettercode.scrum.gui;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AllProjects{

    private HBox toolBar = new HBox();
    private Button addButton = new Button();
    public void show() {
        toolBarSetup();

        addButton.setPrefSize(300, 1);
        addButton.setText("List of All Project");

        Stage primaryStage = new Stage();
        StackPane secondaryLayout = new StackPane();
        BorderPane borderPane = new BorderPane();
        secondaryLayout.prefWidthProperty().bind(primaryStage.widthProperty());

        borderPane.setCenter(secondaryLayout);
        borderPane.setTop(toolBar);
        ObservableList<String> projects = FXCollections.observableArrayList();
        projects.add("STARRED PROJECTS");
        ObservableList<String> projectns = FXCollections.observableArrayList();
        projectns.add("NOT STARRED PROJECTS");
        try {
            File myObj = new File("src/main/java/se/bettercode/scrum/gui/Projects");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] data2 = data.split(" - ");
                //System.out.println(data2[1] + data2[1].length());
                System.out.println(data2[1].compareTo("true" ));
                String b = "true";
                if(data2[1].compareTo("true") == 0){
                    projects.add(data2[0]);
                    System.out.println("stared");
                }
                else{
                    projectns.add(data2[0]);
                }

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        ListView<String> listView = new ListView<String>(projects);
        listView.setMaxSize(700, 350);
        ListView<String> listView2 = new ListView<String>(projectns);
        listView.setMaxSize(700, 350);

        HBox h1 = new HBox();
        h1.getChildren().addAll(listView);
        HBox h2 = new HBox();
        h1.getChildren().addAll(listView2);
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(5, 50, 5, 50));
        layout.getChildren().addAll(listView2,listView);
        secondaryLayout.getChildren().add(layout);
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