package se.bettercode.scrum;

import javafx.beans.property.SimpleStringProperty;

public class StoryStateProperty extends SimpleStringProperty {

    public StoryStateProperty() {
        setState(Story.StoryState.TODO);
    }
    
    public void setState(Story.StoryState storyState) {
        setValue(storyState.name());
    }

    public Story.StoryState getState() {
        return Story.StoryState.valueOf(get());
    }
}
