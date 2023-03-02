package se.bettercode.scrum;

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
import javafx.stage.Stage;
import se.bettercode.scrum.gui.Board;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Scanner;

public class Documents {
    private HBox toolBar = new HBox();
    private Button addButton = new Button();
    public Documents(){
    }
    public void show() {
        toolBarSetup();
        addButton.setPrefSize(100, 20);
        addButton.setText("Add Doc");
        Stage primaryStage = new Stage();
        StackPane secondaryLayout = new StackPane();
        BorderPane borderPane = new BorderPane();
        secondaryLayout.prefWidthProperty().bind(primaryStage.widthProperty());

        String path = "src/main/java/se/bettercode/scrum/resources";
        File repo = new File (path);
        File[] fileList = repo.listFiles();
        ListView<File> list = new ListView<File>();
        ObservableList<File> data = FXCollections.observableArrayList(fileList);
        list.setItems(data);
        list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<File>() {

            @Override
            public void changed(ObservableValue<? extends File> observable, File oldValue, File newValue) {
                System.out.println("Selected item: " + newValue);
                displaySelected(newValue);
            }
        });

        secondaryLayout.getChildren().add(list);
        borderPane.setCenter(secondaryLayout);
        borderPane.setTop(toolBar);
        primaryStage.setScene(new Scene(borderPane, 800, 600));
        primaryStage.show();
    }

    private void addDocFunc(Stage stage) {

    }

    public void setAddButtonAction(EventHandler<ActionEvent> eventHandler) {
        addButton.setOnAction(eventHandler);
    }

    private void toolBarSetup() {
        toolBar.setPadding(new Insets(15, 12, 15, 12));
        toolBar.setSpacing(10);
        toolBar.setStyle("-fx-background-color: #336699;");
        toolBar.getChildren().addAll(addButton);
    }

    private void displaySelected(File newValue) {
        Stage newWindow = new Stage();
        BorderPane root = new BorderPane();
        Text textArea = new Text();
        VBox centerBox = new VBox();
        System.out.println("Inside displaySelected Func");

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
            System.out.println(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        centerBox.getChildren().add(textArea);
        root.setCenter(centerBox);
        newWindow.setScene(new Scene(root, 800, 600));
        newWindow.show();
    }
}
