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
import javafx.stage.Stage;

import java.io.*;

public class NewUserStory {
    private HBox toolBar = new HBox();
    private Button addUserStory = new Button();

    private TextField UserStoryPoints = new TextField();
    private TextField userStory = new TextField ();
  
    private TextField comments = new TextField();
    private Label userstoryalert = new Label("");
    private Label storypoints = new Label("");
    String st[] = { "Feature", "Bug", "Release", "Other" };

    private String usersInfo = ReadFromTxt();
    private ChoiceBox<String> usertasktype = new ChoiceBox<>(FXCollections.observableArrayList(st));

    private ChoiceBox<String> userAssign = new ChoiceBox<>(FXCollections.observableArrayList(usersInfo));

    public String ReadFromTxt() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/java/se/bettercode/scrum/team/usersInfo"));
        StringBuffer stringBuffer = new StringBuffer();

        // Read file line by line
        String line;
        while ((line = reader.readLine()) != null) {
            // Split line at ":"
            String[] parts = line.split(":");

            // Append each part to string buffer on a new line
            for (String part : parts) {
                stringBuffer.append(part + "\n");
            }
        }
        reader.close();

        // Get the final string
        String usersInfo = stringBuffer.toString();
        return usersInfo;
    }
    public NewUserStory() throws IOException {

    }
    public void show() {
        toolBarSetup();
        addUserStory.setPrefSize(100, 20);
        addUserStory.setText("Add User Story");
        UserStoryPoints.setText("Story Points");
      
        comments.setPromptText("Add Comment");
        addUserStory.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if ((userStory.getText() != null && !userStory.getText().isEmpty())) {
                    userstoryalert.setText(userStory.getText() +  "- named user story added");
                    storypoints.setText(userStory.getText() +  "has" + UserStoryPoints.getText()+ "points.");
                    try
                    {
                        String filename= "src/main/java/se/bettercode/scrum/gui/UserStory";
    
                        FileWriter fw = new FileWriter(filename, true);
                        BufferedWriter bw = new BufferedWriter(fw);   

                        bw.write(UserStoryPoints.getText());
                        bw.newLine();
                        bw.write(userStory.getText());
                        bw.newLine();
                        bw.write(usertasktype.getValue());
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
                    userstoryalert.setText("You have not named the user story");
                }
                }
            });
        
        Stage primaryStage = new Stage();
        StackPane secondaryLayout = new StackPane();
        BorderPane borderPane = new BorderPane();
        secondaryLayout.prefWidthProperty().bind(primaryStage.widthProperty());

        /* 
        String path = "src/main/java/se/bettercode/scrum/resources";
        File repo = new File (path);
        File[] fileList = repo.listFiles();
        ListView<File> list = new ListView<File>();
        ObservableList<File> data = FXCollections.observableArrayList(fileList);
        list.setItems(data);
        list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<File>() {
            @Override
            public void changed(ObservableValue<? extends File> observable, File oldValue, File newValue) {
                // Your action here
                //System.out.println("Selected item: " + newValue);
                //displaySelected(newValue);
            }
        });
        */

//        addButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override public void handle(ActionEvent event) {
//                addDocFunc();
//            }
//        });

        secondaryLayout.getChildren().addAll(storypoints,userstoryalert);

        secondaryLayout.setAlignment(storypoints, Pos.CENTER_RIGHT);

        secondaryLayout.setAlignment(userstoryalert, Pos.CENTER_LEFT);


        borderPane.setCenter(secondaryLayout);
        borderPane.setTop(toolBar);
        primaryStage.setScene(new Scene(borderPane, 800, 600));
        primaryStage.show();
    }

    public Story getStory(){
        Integer sp = Integer.valueOf(storypoints.getText());
        Story story = new Story( sp, userStory.getText(), usertasktype.getValue());
        return story;
    }
      
    public void setAddButtonAction(EventHandler<ActionEvent> eventHandler) {
        addUserStory.setOnAction(eventHandler);
        

    }

    private void toolBarSetup() {
        toolBar.setPadding(new Insets(15, 12, 15, 12));
        toolBar.setSpacing(10);
        toolBar.setStyle("-fx-background-color: #336699;");
        toolBar.getChildren().addAll(userStory,addUserStory,UserStoryPoints,usertasktype,userAssign ,comments);
    }

   /*
    private void displaySelected(File newValue) {
        Stage newWindow = new Stage();
        BorderPane root = new BorderPane();
//        TextArea fileTextArea = new TextArea();
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



}*/
}