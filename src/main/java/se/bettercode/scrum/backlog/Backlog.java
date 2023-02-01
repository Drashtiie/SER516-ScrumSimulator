package se.bettercode.scrum.backlog;

import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import se.bettercode.scrum.Story;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Backlog {

    private final String name;
    List<Story> stories = new ArrayList<>();
    IntegerProperty donePoints = new SimpleIntegerProperty(0);
    private DoubleProperty averageLeadTime = new SimpleDoubleProperty();

    private BacklogBurnup burnup = new BacklogBurnup();

    protected Backlog(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addStory(Story story) {
        stories.add(story);
    }

    protected Story getStory() {
        return stories.stream().filter(p -> p.getStatus() != Story.StoryState.FINISHED).findFirst().get();
    }

    public List<Story> getStories() {
        return stories;
    }

    public List<Story> getStories(Story.StoryState filter) {
        return stories.stream().filter(p -> p.getStatus() == filter).collect(Collectors.toList());
    }

    public int getDonePoints() {
        return donePoints.get();
    }

    public IntegerProperty donePointsProperty() {
        return donePoints;
    }

    public DoubleProperty averageLeadTimeProperty() {
        return averageLeadTime;
    }

    public double getAverageLeadTime() {
        return averageLeadTime.get();
    }

    public int getFinishedStoriesCount() {
        return getStories(Story.StoryState.FINISHED).size();
    }

    public int getTotalPoints() {
        return stories.stream().mapToInt(Story::getTotalPointsAsInt).sum();
    }

    public int getWorkInProgressPoints() {
        return getPointsPerState(Story.StoryState.STARTED);
    }

    public int getFinishedPoints() {
        return getPointsPerState(Story.StoryState.FINISHED);
    }

    private int getPointsPerState(Story.StoryState filter) {
        return getStories(filter).stream().mapToInt(Story::getPointsDoneAsInt).sum();
    }

    public boolean isFinished() {
        return getTotalPoints() == getFinishedPoints();
    }

    @Override
    public String toString() {
        return "Backlog{" +
                "stories=" + stories +
                ", average lead time=" + getAverageLeadTime() +
                '}';
    }

    public void runDay(int dailyBurn, int day) {
        Story story;
        storyLoop:
        while (dailyBurn > 0) {
            try {
                story = getStory();
            } catch (NoSuchElementException e) {
                story = null;
                System.out.println("Sprint fully completed before running out of days!");
                break storyLoop;
            }
            dailyBurn = story.workOnStory(dailyBurn, day);
        }

        setFinishedPoints();
        setAverageLeadTime();
        addBurnupDay(day);
    }

    private BurnupDay makeBurnupDay(int day) {
        return new BurnupDay(day, getTotalPoints(), getDonePoints());
    }

    private void setAverageLeadTime() {
        Platform.runLater(() -> averageLeadTime.set(calculatedAverageLeadTime()));
    }

    private double calculatedAverageLeadTime() {

        List<Story> finishedStories = getStories(Story.StoryState.FINISHED);
        double totalLeadTime = finishedStories.stream().mapToDouble(Story::getLeadTime).sum();

        if (finishedStories.size() == 0) {
            return 0.0;
        } else {
            return totalLeadTime / finishedStories.size();
        }
    }

    private void setFinishedPoints() {
        Platform.runLater(() -> donePoints.set(getFinishedPoints()));
    }

    private void addBurnupDay(int day) {
        Platform.runLater(() -> burnup.addDay(makeBurnupDay(day)));
    }

    public BacklogBurnup getBurnup() {
        return burnup;
    }
}
