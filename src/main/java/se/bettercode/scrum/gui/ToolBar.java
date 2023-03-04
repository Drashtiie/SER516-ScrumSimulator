package se.bettercode.scrum.gui;
//Added comment for testing integration
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import se.bettercode.scrum.backlog.Backlog;
import se.bettercode.scrum.backlog.SelectableBacklogs;
import se.bettercode.scrum.team.SelectableTeams;

public class ToolBar extends HBox {

    private Backlog backlog;
    private StatusBar statusBar = new StatusBar();
    private SelectableBacklogs backlogs = new SelectableBacklogs();
    private SelectableTeams teams = new SelectableTeams();
    //private ToolBar toolBar = new ToolBar(teams.getKeys(), backlogs.getKeys());


    private final Button toggleButton = new Button("Hide Burnup");

    private final Button toggleButton2 = new Button("Unhide Burnup");
    private final Button startButton = new Button("Start Sprint");
    private final Button viewDocs = new Button("Documents");
    private ChoiceBox<String> teamChoiceBox = new ChoiceBox<>();
    private ChoiceBox<String> backlogChoiceBox = new ChoiceBox<>();
    private final Button addUsers = new Button("Users + ");
    private final Button addTeam = new Button("Team + ");
    private final Button addProject = new Button("Project + ");


    Button addUserStory = new Button("Add user story ");
    TextField userStory = new TextField ();
    Label userstoryalert = new Label("");
public ToolBar(String[] teams, String[] backlogs) {
        setPadding(new Insets(15, 12, 15, 12));
        setSpacing(10);
        setStyle("-fx-background-color: #336699;");

        teamChoiceBox.setItems(FXCollections.observableArrayList(teams));
        teamChoiceBox.setTooltip(new Tooltip("Select team"));

        backlogChoiceBox.setItems(FXCollections.observableArrayList(backlogs));
        backlogChoiceBox.setTooltip(new Tooltip("Select backlog"));

        startButton.setPrefSize(100, 20);
        viewDocs.setPrefSize(100, 20);
        toggleButton.setPrefSize(120,20);
        toggleButton2.setPrefSize(120,20);
        addUsers.setPrefSize(120,20);
        addProject.setPrefSize(120,20);
        addTeam.setPrefSize(120,20);

        getChildren().addAll(teamChoiceBox, backlogChoiceBox, addUserStory, startButton, viewDocs, toggleButton, toggleButton2, addUsers, addTeam, addProject);

        addUserStory.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if ((userStory.getText() != null && !userStory.getText().isEmpty())) {
                    userstoryalert.setText(userStory.getText() +  "- named user story added");
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
                    userstoryalert.setText("You have not left a comment.");
                }
                }
            });
        



        teamChoiceBox.setItems(FXCollections.observableArrayList(teams));
        teamChoiceBox.setTooltip(new Tooltip("Select team"));

    }



    public void setStartButtonAction(EventHandler<ActionEvent> eventHandler) {
        startButton.setOnAction(eventHandler);
    }


//    private void reload(){
//        this.toolBar = new ToolBar(teams.getKeys(), backlogs.getKeys() );
//    }

    public void setViewDocsButtonAction(EventHandler<ActionEvent> eventHandler) {
        viewDocs.setOnAction(eventHandler);
    }
    public void setAddUsedrsButtonAction(EventHandler<ActionEvent> eventHandler) {
        addUsers.setOnAction(eventHandler);
    }
    public void setAddTeamButtonAction(EventHandler<ActionEvent> eventHandler) {
        addTeam.setOnAction(eventHandler);
        //toolBar.reload();
    }
    public void setAddProjectButtonAction(EventHandler<ActionEvent> eventHandler) {
        addProject.setOnAction(eventHandler);
    }

    public void setToggleButtonAction(EventHandler<ActionEvent> eventHandler) {
        toggleButton.setOnAction(eventHandler);
    }

    public void setToggleButton2Action(EventHandler<ActionEvent> eventHandler) {
        toggleButton2.setOnAction(eventHandler);
    }
    public void bindRunningProperty(BooleanProperty booleanProperty) {
        backlogChoiceBox.disableProperty().bind(booleanProperty);
        startButton.disableProperty().bind(booleanProperty);
        teamChoiceBox.disableProperty().bind(booleanProperty);
        viewDocs.disableProperty().bind(booleanProperty);
        toggleButton.disableProperty().bind(booleanProperty);
        toggleButton2.disableProperty().bind(booleanProperty);
        addProject.disableProperty().bind(booleanProperty);
    }

    public void setTeamChoiceBoxListener(ChangeListener<String> changeListener) {
        teamChoiceBox.getSelectionModel().selectedItemProperty().addListener(changeListener);
    }

    public void setBacklogChoiceBoxListener(ChangeListener<String> changeListener) {
        backlogChoiceBox.getSelectionModel().selectedItemProperty().addListener(changeListener);
    }
    public void setUserStoryButtonAction(EventHandler<ActionEvent> eventHandler) {
        addUserStory.setOnAction(eventHandler);
    }
}