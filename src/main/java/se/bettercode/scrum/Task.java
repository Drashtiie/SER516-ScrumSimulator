package se.bettercode.scrum;

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

    public void addToStory(){

    }

    public void delete(){

    }

    public void moveTo(String status){

    }
}
