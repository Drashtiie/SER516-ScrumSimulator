package se.bettercode.scrum;


import java.util.ArrayList;

public class Story {

    private StoryDays storyDays = new StoryDays();

    public enum StoryState {TODO, STARTED, FINISHED;}
    private StoryPointSet storyPointSet;

    private StoryStateProperty status = new StoryStateProperty();
    private String title = "";

    private String taskType = "";
    private String userName = "";
    private ArrayList<Task> tasks;

    private String comments;

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public String getComments() {
        return comments;
    }
    public Story(int points) {
        this(points, "", "","","");
    }

    public Story(int points, String title, String taskType, String comments, String userName) {

        if (points < 0) {
            throw new IllegalArgumentException("Points must not be negative.");
        }
        this.title = title;
        storyPointSet = new StoryPointSet(points);
        this.userName = userName;
        this.tasks = new ArrayList<>();
        this.comments = new String();
        this.taskType = taskType;
    }

    public String getTaskType() {
        return taskType;
    }
    public String getUserName(){return userName;}

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }
    public StoryStateProperty statusProperty() {
        return status;
    }

    public StoryPoint getTotalPoints() {
        return storyPointSet.getTotal();
    }

    public int getTotalPointsAsInt() {
        return getTotalPoints().getPoints();
    }

    public StoryPoint getPointsDone() {
        return storyPointSet.getDone();
    }

    public int getPointsDoneAsInt() {
        return getPointsDone().getPoints();
    }

    public StoryState getStatus() {
        return status.getState();
    }
    public String getTitle() {
        return title;
    }

    public String getTasktype() {
        return taskType;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTasktype(String Tasktype) {
        this.taskType = Tasktype;
    }

    public double getLeadTime() {
        return storyDays.getLeadTime();
    }

    /**
     *
     * @param points
     * @return any leftover points
     */
    public int workOnStory(int points, int day) {
        if (status.getState() == StoryState.TODO) {
            status.setState(StoryState.STARTED);
            storyDays.setStartedDay(day);
        }

        int leftover = 0;
        int pointsToApply;

        if (points >= getRemainingPoints()) {
            pointsToApply = getRemainingPoints();
            storyPointSet.apply(pointsToApply);
            leftover = points - pointsToApply;
            status.setState(StoryState.FINISHED);
            storyDays.setDoneDay(day);
        } else { // points < getRemainingPoints()
            storyPointSet.apply(points);
        }

        return leftover;
    }

    public int getRemainingPoints() {
        return storyPointSet.getRemaining().getPoints();
    }

    @Override
    public String toString() {
        return "Story{" +
                "points=" + getTotalPoints().getPoints() +
                ", pointsDone=" + getPointsDone().getPoints() +
                ", status=" + status.getValue() +
                '}';
    }
}