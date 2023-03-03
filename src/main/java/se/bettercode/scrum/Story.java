package se.bettercode.scrum;


import java.util.ArrayList;

public class Story {

    private StoryDays storyDays = new StoryDays();

    public enum StoryState {TODO, STARTED, READYTOTEST, FINISHED;}
    private StoryPointSet storyPointSet;

    private StoryStateProperty status = new StoryStateProperty();
    private String title = "";

    private String taskType = "";
    private String userName = "";
    private ArrayList<Task> tasks;

    private String comments;

    public String status_set_by_user;

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
        this(points, "", "","","","");
    }

    public Story(int points, String title, String taskType, String newstat,String comments, String userName) {

        if (points < 0) {
            throw new IllegalArgumentException("Points must not be negative.");
        }
        this.title = title;
        storyPointSet = new StoryPointSet(points);
        this.userName = userName;
        this.tasks = new ArrayList<>();
        this.comments = new String();
        this.taskType = taskType;
        this.status_set_by_user = newstat;
        if(newstat.equals("TODO")){
            System.out.println("Hello");
            status.setState(StoryState.TODO);
        }
        else if(newstat.equals("STARTED")){
            status.setState(StoryState.STARTED);
        }
        else if(newstat.equals("READYTOTEST")){
            status.setState(StoryState.READYTOTEST);
        }
        else{
            status.setState(StoryState.FINISHED);
        }
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
        if(status_set_by_user.equals("STARTED")){
            int x = getTotalPointsAsInt();
            int y = 1;
            if(x==y){
                System.out.println("Integer wala case");
                return 1;
            }
            else{

            return (getTotalPointsAsInt()/2);
            }
        }
        else if(status_set_by_user.equals("FINISHED")){
            return (getTotalPointsAsInt());
        }
        else{
            return getPointsDone().getPoints();
        }
    }
/* 
    public StoryState getStat(){
        System.out.println("Status set by the user is " + newstat);
        if(user == "TODO"){
            status.setState(StoryState.TODO);
        }
        else if(newstat == "STARTED"){
            status.setState(StoryState.STARTED);
        }
        else if(newstat == "FINISHED"){
            status.setState(StoryState.FINISHED);
        }
    }
*/
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