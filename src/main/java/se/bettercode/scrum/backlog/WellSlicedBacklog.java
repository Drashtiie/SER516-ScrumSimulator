package se.bettercode.scrum.backlog;

import se.bettercode.scrum.RandomStoryTitleGenerator;
import se.bettercode.scrum.Story;

import java.util.ArrayList;

public class WellSlicedBacklog extends Backlog {

    final static int STORY_COUNT = 10;

    public WellSlicedBacklog() {
        super("Well sliced");

        ArrayList<String> storyTitles = (new RandomStoryTitleGenerator()).generate(STORY_COUNT);

        addStory(new Story(3, storyTitles.get(0)));
        addStory(new Story(1, storyTitles.get(1)));
        addStory(new Story(1, storyTitles.get(2)));
        addStory(new Story(3, storyTitles.get(3)));
        addStory(new Story(1, storyTitles.get(4)));
        addStory(new Story(1, storyTitles.get(5)));
        addStory(new Story(1, storyTitles.get(6)));
        addStory(new Story(3, storyTitles.get(7)));
        addStory(new Story(1, storyTitles.get(8)));
        addStory(new Story(1, storyTitles.get(9)));
    }
}
