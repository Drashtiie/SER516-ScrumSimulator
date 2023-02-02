package se.bettercode.scrum;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Box;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Observable;

public class Documents {
    public Documents(){
        System.out.println(" Docs");
    }
    public void show() {
        String path = "C:\\Users\\ljain3\\Desktop\\Files";
        File repo = new File (path);
        File[] fileList = repo.listFiles();
        ListView<File> list = new ListView<File>();
        ObservableList<File> data = FXCollections.observableArrayList(fileList);
        list.setItems(data);
        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(list);
        Scene secondScene = new Scene(secondaryLayout, 700, 400);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setScene(secondScene);

        newWindow.show();
        System.out.println("Showing Docs");
    }
}
