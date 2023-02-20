package se.bettercode.scrum;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import se.bettercode.scrum.backlog.Backlog;
import se.bettercode.scrum.backlog.SelectableBacklogs;
import se.bettercode.scrum.gui.*;
import se.bettercode.scrum.prefs.StageUserPrefs;
import se.bettercode.scrum.team.SelectableTeams;
import se.bettercode.scrum.team.Team;


import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;



public class ScrumGameApplication extends Application {

    private static final int SPRINT_LENGTH_IN_DAYS = 10;

    private Board board = new Board();
    private Sprint sprint;
    private Documents documents = new Documents();
    
    private UserWindow addUserWindow = new UserWindow();
    private TeamWindow addTeamWindow = new TeamWindow();
    private Team team;

    private NewUserStory nws = new NewUserStory();
    private Backlog backlog;
    private StatusBar statusBar = new StatusBar();
    private SelectableBacklogs backlogs = new SelectableBacklogs();
    private SelectableTeams teams = new SelectableTeams();
    private ToolBar toolBar = new ToolBar(teams.getKeys(), backlogs.getKeys());
    private BurnupChart burnupChart = getNewBurnupChart();
    private Stage primaryStage;
    private StageUserPrefs prefs;

    public static void main(String[] args) {
        System.out.println("Launching JavaFX application.");
        launch(args);
    }

    @Override
    public void init() {
        System.out.println("Inside init()");
    }

    @Override
    public void start(Stage primaryStage) {
        System.out.println("Inside start()");
        this.primaryStage = primaryStage;
        prefs = new StageUserPrefs(primaryStage);
        prefs.load();
        setStage();
        bindActionsToToolBar();
        primaryStage.show();
    }

    private void setStage() {
        primaryStage.setTitle("Scrum Game");
        BorderPane borderPane = new BorderPane();
        board.prefWidthProperty().bind(primaryStage.widthProperty());
        borderPane.setCenter(board);
        borderPane.setRight(burnupChart);
        borderPane.setTop(toolBar);
        borderPane.setBottom(statusBar);
        primaryStage.setScene(new Scene(borderPane, 800, 600));
    }

    private boolean initSprint() {
        if (team != null && backlog != null) {
            sprint = new Sprint("First sprint", SPRINT_LENGTH_IN_DAYS, team, backlog);
            board.bindBacklog(backlog);
            burnupChart.removeAllData();
            //burnupChart = getNewBurnupChart();
            ToggleButton toggleButton = new ToggleButton("Hide Burnup Chart");
            ToggleButton toggleButton2 = new ToggleButton("Show Burnup Chart2");


            ToggleButton viewtime = new ToggleButton("Enter time");

            viewtime.setOnAction(e -> {

                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Enter Time");
                dialog.setHeaderText("Enter time for user stories:");
                Optional<String> result = dialog.showAndWait();
                result.ifPresent(time -> {
                    try {
                        FileWriter writer = new FileWriter("time.txt");
                        writer.write(time);
                        writer.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
            });

            toolBar.setToggleButtonAction(event -> {
                if (toggleButton.isSelected()) {
                    burnupChart.setVisible(true);
                } else {
                    burnupChart.setVisible(false);
                }
            });
            toolBar.setToggleButton2Action(event -> {
                if (toggleButton2.isSelected()) {
                    burnupChart.setVisible(false);
                } else {
                    burnupChart.setVisible(true);
                }
            });

            toolBar.setViewtimeAction(event -> {
                if(viewtime.isSelected())
                {TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Enter Time");
                dialog.setHeaderText("Enter time for user stories:");
                Optional<String> result = dialog.showAndWait();
                result.ifPresent(time -> {
                    try {
                        FileWriter writer = new FileWriter("time.txt");
                        writer.write(time);
                        writer.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
            }
            });
            burnupChart.bindBurnupDaysProperty(backlog.getBurnup().burnupDaysProperty());
            toolBar.bindRunningProperty(sprint.runningProperty());
            return true;
        }
        return false;
    }
    
    private void bindSprintDataToStatusBar() {
        statusBar.bindTeamName(team.nameProperty());
        statusBar.bindTeamVelocity(team.velocityProperty());
        statusBar.bindStoryPointsDone(backlog.donePointsProperty());
        statusBar.bindDaysInSprint(sprint.lengthInDaysProperty());
        statusBar.bindCurrentDay(sprint.currentDayProperty());
        statusBar.bindLeadTime(backlog.averageLeadTimeProperty());
    }

    private void bindActionsToToolBar() {
        ChangeListener backlogChoiceBoxListener = new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                backlog = backlogs.get(newValue.toString());
                loadData();
            }
        };

        ChangeListener teamChoiceBoxListener = new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                team = teams.get(newValue.toString());
                loadData();
            }
        };


        toolBar.setTeamChoiceBoxListener(teamChoiceBoxListener);
        toolBar.setBacklogChoiceBoxListener(backlogChoiceBoxListener);
        toolBar.setStartButtonAction((event) -> sprint.runSprint());




        toolBar.setViewDocsButtonAction((event) -> documents.show());
//<<<<<<< alok
        toolBar.setUserStoryButtonAction((event -> nws.show()));
//=======
        toolBar.setAddUsedrsButtonAction((event) -> addUserWindow.show());

        toolBar.setAddTeamButtonAction((event) -> addTeamWindow.show());
        toolBar.setUserStoryButtonAction((event) -> nws.show());

    }

    private void loadData() {
        if (initSprint()) {
            bindSprintDataToStatusBar();
        }
    }

    public void stop() {
        System.out.println("Inside stop()");
       // prefs.save();
    }

    private BurnupChart getNewBurnupChart() {
        return new BurnupChart(SPRINT_LENGTH_IN_DAYS);
    }
}

