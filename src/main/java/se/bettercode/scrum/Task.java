package se.bettercode.scrum;

import java.util.ArrayList;

public class Task {
    private int id;

    private String title = "";

    private String description = "";

    public void setStoryID(int storyID) {
        this.StoryID = storyID;
    }

    public int getStoryID() {
        return StoryID;
    }

    private int StoryID;

    private ArrayList<Comment> comments;

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

    public Task(int id, String title, String description, int storyID) {
        this.id = id;
        this.title = title;
        this.description = description;
        StoryID = storyID;
        this.comments = new ArrayList<>();
    }

    public void addToStory(){

    }

    public void delete(){

    }

    public void moveTo(String status){

    }
}
