package se.bettercode.scrum.prefs;


import javafx.stage.Stage;

import java.util.prefs.Preferences;

public class StageUserPrefs {

    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 600;
    private static final int DEFAULT_X = 100;
    private static final int DEFAULT_Y = 100;
    private final Stage stage;
    private final Preferences userPrefs = Preferences.userNodeForPackage(getClass());

    public StageUserPrefs(Stage stage) {
        this.stage = stage;
    }

    public void load() {
        stage.setX(userPrefs.getDouble("stage.x", DEFAULT_X));
        stage.setY(userPrefs.getDouble("stage.y", DEFAULT_Y));
        stage.setWidth(userPrefs.getDouble("stage.width", DEFAULT_WIDTH));
        stage.setHeight(userPrefs.getDouble("stage.height", DEFAULT_HEIGHT));
    }

    public void save() {
        userPrefs.putDouble("stage.x", stage.getX());
        userPrefs.putDouble("stage.y", stage.getY());
        userPrefs.putDouble("stage.width", stage.getWidth());
        userPrefs.putDouble("stage.height", stage.getHeight());
    }
}
