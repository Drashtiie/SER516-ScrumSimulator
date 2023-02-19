package se.bettercode.scrum.backlog;


import se.bettercode.scrum.RandomStoryTitleGenerator;
import se.bettercode.scrum.Story;

import java.util.ArrayList;

public class SmallBacklog extends Backlog {

    final static int STORY_COUNT = 5;

    public SmallBacklog() {
        super("Small");

        ArrayList<String> storyTitles = (new RandomStoryTitleGenerator()).generate(STORY_COUNT);

        addStory(new Story(3, storyTitles.get(0)));
        addStory(new Story(5, storyTitles.get(1)));
        addStory(new Story(8, storyTitles.get(2)));
        addStory(new Story(5, storyTitles.get(3)));
        addStory(new Story(1, storyTitles.get(4)));
    }
}
