package se.bettercode.scrum.gui;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import se.bettercode.scrum.Project;
public class ProjectWindow {

    private AllProjects allProjectWindow = new AllProjects();

    private HBox toolBar = new HBox();
    private Button addButton = new Button();
    String tagList[] = {"Internal", "External"};
    ComboBox comboBox = new ComboBox(FXCollections.observableArrayList(tagList));

    TextField teamNameField = new TextField ();

    Label teamAddedAlert = new Label("");

    private Button viewButton = new Button();
    private CheckBox starred = new CheckBox("Star");


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
        secondaryLayout.getChildren().add(layout);
        primaryStage.setScene(new Scene(borderPane, 800, 600));
        primaryStage.show();

    }


    private void toolBarSetup() {
        toolBar.setPadding(new Insets(15, 12, 15, 12));
        toolBar.setSpacing(10);
        toolBar.setStyle("-fx-background-color: #336699;");
        comboBox.getSelectionModel().selectFirst();
        toolBar.getChildren().addAll( teamNameField, addButton, viewButton, starred, comboBox);
        addButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if ((teamNameField.getText() != null && !teamNameField.getText().isEmpty())) {
                    if(starred.isSelected()){
                        teamAddedAlert.setText(teamNameField.getText() +  " added as project and it is starred." + "It is "+ comboBox.getValue().toString() + " project.");
                    }
                    else {
                        teamAddedAlert.setText(teamNameField.getText() + " added as project" + "It is "+ comboBox.getValue().toString() + " project.");
                    }
                    Project p = new Project();
                    p.setProject(teamNameField.getText(), starred.isSelected(), comboBox.getValue().toString());
                    System.out.println("Added ");
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
