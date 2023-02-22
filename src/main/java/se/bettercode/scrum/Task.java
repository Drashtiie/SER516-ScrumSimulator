package se.bettercode.scrum;

import java.util.ArrayList;

public class Task {
    private int id;

    private static int IDCounter = 0;

    private String title = "";

    private String status;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    private String description = "";

    private ArrayList<Comment> comments;

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public static void setIDCounter(int IDCounter) {
        Task.IDCounter = IDCounter;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Task(String title, String description, String status) {
        this.id = IDCounter++;
        this.title = title;
        this.description = description;
        this.comments = new ArrayList<>();
        this.status = status;
    }

    public Story addToStory(Story userStory){
        ArrayList<Task> storyTasks = userStory.getTasks();
        storyTasks.add(this);
        userStory.setTasks(storyTasks);
        return userStory;
    }

    public Story deleteFromStory(Story userStory){
        ArrayList<Task> storyTasks = userStory.getTasks();
        storyTasks.remove(this);
        userStory.setTasks(storyTasks);
        return userStory;
    }

    public void moveTo(String status){
        this.setStatus(status);
        //reload UI now after this status change
    }
}
