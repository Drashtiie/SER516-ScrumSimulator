package se.bettercode.scrum;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class NewUserStory {
    private HBox toolBar;
    private Button addUserStory;
    private TextField UserStoryPoints;
    private TextField userStory;
    private Label userStoryAlert;
    private Label storyPoints;
    private String[] st = {"FEATURE", "BUG", "ISSUE", "DEBT"};
    private ChoiceBox<String> userTaskType;

    public NewUserStory(){
        this.toolBar = new HBox();
        this.addUserStory = new Button();
        this.UserStoryPoints = new TextField();
        this.userStory = new TextField ();
        this.userStoryAlert = new Label("");
        this.storyPoints = new Label("");
        this.userTaskType = new ChoiceBox<>(FXCollections.observableArrayList(st));
    }

    public void show() {
        toolBarSetup();
        addUserStory.setPrefSize(100, 20);
        addUserStory.setText("Add User Story");
        UserStoryPoints.setText("Story Points");
        addUserStory.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if ((userStory.getText() != null && !userStory.getText().isEmpty())) {
                    userStoryAlert.setText(userStory.getText() +  "- named user story added");
                    storyPoints.setText(userStory.getText() +  "has" + UserStoryPoints.getText()+ "points.");
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
                    userStoryAlert.setText("You have not named the user story");
                }
            }
        });

        Stage primaryStage = new Stage();
        StackPane secondaryLayout = new StackPane();
        BorderPane borderPane = new BorderPane();
        secondaryLayout.prefWidthProperty().bind(primaryStage.widthProperty());

        secondaryLayout.getChildren().addAll(storyPoints, userStoryAlert);

        StackPane.setAlignment(storyPoints, Pos.CENTER_RIGHT);

        StackPane.setAlignment(userStoryAlert, Pos.CENTER_LEFT);


        borderPane.setCenter(secondaryLayout);
        borderPane.setTop(toolBar);
        primaryStage.setScene(new Scene(borderPane, 800, 600));
        primaryStage.show();
    }

    public Story getStory(){
        int sp = Integer.parseInt(storyPoints.getText());
        return new Story( sp, userStory.getText());
    }

    public void setAddButtonAction(EventHandler<ActionEvent> eventHandler) {
        addUserStory.setOnAction(eventHandler);
    }

    public void toolBarSetup() {
        toolBar.setPadding(new Insets(15, 12, 15, 12));
        toolBar.setSpacing(10);
        toolBar.setStyle("-fx-background-color: #336699;");
        toolBar.getChildren().addAll(userStory, addUserStory, UserStoryPoints, userTaskType);
    }

    public void displaySelected(File newValue) {
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
