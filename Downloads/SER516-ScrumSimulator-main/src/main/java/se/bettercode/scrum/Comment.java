package se.bettercode.scrum;

import java.util.ArrayList;

public class Comment {
    private int id;

    private String description = "";

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    public int getStoryID() {
        return parentID;
    }

    public void setParentType(String parentType) {
        this.parentType = parentType;
    }

    public String getParentType() {
        return parentType;
    }

    private int parentID;

    private String parentType;

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Comment(int id, String description, int parentID, String parentType) {
        this.id = id;
        this.description = description;
        this.parentID = parentID;
        this.parentType = parentType;
    }

//    public Story addToStory(Story userStory){
//        ArrayList<Comment> storyComments = userStory.getComments();
//        storyComments.add(this);
//        userStory.setComments(storyComments);
//        return userStory;
//    }
//
//    public Story deleteFromStory(Story userStory){
//        ArrayList<Comment> storyComments = userStory.getComments();
//        storyComments.remove(this);
//        userStory.setComments(storyComments);
//        return userStory;
//    }

    public Task addToTask(Task storyTask){
        ArrayList<Comment> storyComments = storyTask.getComments();
        storyComments.add(this);
        storyTask.setComments(storyComments);
        return storyTask;
    }

    public Task deleteFromTask(Task storyTask){
        ArrayList<Comment> storyComments = storyTask.getComments();
        storyComments.remove(this);
        storyTask.setComments(storyComments);
        return storyTask;
    }
}
