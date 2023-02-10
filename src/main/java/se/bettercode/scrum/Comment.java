package se.bettercode.scrum;

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

    public void addToParent(String parentType){

    }

    public void deleteFromParent(String parentType){

    }
}

