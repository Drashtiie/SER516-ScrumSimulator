package se.bettercode.scrum;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TaskCardController {

    @FXML
    private ComboBox<String> taskStatus;

    @FXML
    private TextArea taskDescription;

    @FXML
    private TextField taskTitle;

    @FXML
    private Button saveButton;

    private Story story;

    public TaskCardController(){
    }

    @FXML
    public void handleSaveButton(ActionEvent event) {
        String description = taskDescription.getText().replaceAll("\n", System.getProperty("line.separator"));
        String title = taskTitle.getText();
        String status = taskStatus.getValue();
        Task newTask = new Task(title, description, status);
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        this.story = (Story) stage.getUserData();
        ArrayList<Task> tasks = this.story.getTasks();
        tasks.add(newTask);
        this.story.setTasks((tasks));
        stage.close();
    }
}
