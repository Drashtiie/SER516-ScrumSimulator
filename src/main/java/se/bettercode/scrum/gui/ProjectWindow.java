package se.bettercode.scrum.gui;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import se.bettercode.scrum.gui.Board;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Scanner;
//import org.apache.commons.io.FileUtils;

public class ProjectWindow {
    private HBox toolBar = new HBox();
    private final Button addButton = new Button();
    private final Button delButton = new Button();
    private File[] fileList;
    private ObservableList<File> data;
    private ListView<File> list = new ListView<File>();
    private final String path = "src/main/java/se/bettercode/scrum/resources";
    private File repo = new File (path);
    private boolean delButtonClicked = false;
    public ProjectWindow(){
    }
    public void show() {
        toolBarSetup();
        addButton.setPrefSize(100, 20);
        addButton.setText("Add Doc");
        delButton.setPrefSize(100, 20);
        delButton.setText("Delete");
        Stage primaryStage = new Stage();
        StackPane secondaryLayout = new StackPane();
        BorderPane borderPane = new BorderPane();
        secondaryLayout.prefWidthProperty().bind(primaryStage.widthProperty());
        listFiles();

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                addDocFunc(primaryStage);
            }
        });
        delButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                delDocFunc();
//                listFiles();
            }
        });
        secondaryLayout.getChildren().add(list);
        borderPane.setCenter(secondaryLayout);
        borderPane.setTop(toolBar);
        primaryStage.setScene(new Scene(borderPane, 800, 600));
        primaryStage.show();
    }

    public void listFiles(){
        fileList = repo.listFiles();
        data = FXCollections.observableArrayList(fileList);
        list.setItems(data);
        if(!delButtonClicked){
            System.out.println("inside if statement - button not clicked");
            list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<File>() {

                @Override
                public void changed(ObservableValue<? extends File> observable, File oldValue, File newValue) {
                    // Your action here
                    displaySelected(newValue);
                }
            });
        }
    }

    private void delDocFunc(){
        delButtonClicked = true;
        System.out.println("Delete mode on");
        toolBar.setDisable(true);
        list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<File>() {
            @Override
            public void changed(ObservableValue<? extends File> observable, File oldValue, File newValue) {
                newValue.delete();
                System.out.println(newValue + " deleted successfully");
                toolBar.setDisable(false);
                System.out.println("Delete button clicked is : " + delButtonClicked);
                listFiles();
                delButtonClicked = false;
            }
        });
    }
    private void addDocFunc(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        System.out.println("Selected file is : " + selectedFile);
        String fileNameOnly = selectedFile.getName();
        File newFile = new File (path + "/" + fileNameOnly);
        try {
            Files.copy(selectedFile.toPath(),newFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        data.add(selectedFile);
        listFiles();
    }

//    public void setAddButtonAction(EventHandler<ActionEvent> eventHandler) {
//        addButton.setOnAction(eventHandler);
//    }

    private void toolBarSetup() {
        toolBar.setPadding(new Insets(15, 12, 15, 12));
        toolBar.setSpacing(10);
        toolBar.setStyle("-fx-background-color: #336699;");
        toolBar.getChildren().addAll(addButton, delButton);
    }

    private void displaySelected(File newValue) {
        Stage newWindow = new Stage();
        BorderPane root = new BorderPane();
//        TextArea fileTextArea = new TextArea();
        Text textArea = new Text();
        VBox centerBox = new VBox();

        BufferedReader reader = null;
        try {
            Charset inputCharset = StandardCharsets.ISO_8859_1;
            reader = new BufferedReader(new InputStreamReader(Files.newInputStream(newValue.toPath()), inputCharset));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            String ls = System.getProperty("line.separator");
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            reader.close();

            String content = stringBuilder.toString();
            textArea.setText(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        centerBox.getChildren().add(textArea);
        root.setCenter(centerBox);
        newWindow.setScene(new Scene(root, 800, 600));
        newWindow.show();
    }
}