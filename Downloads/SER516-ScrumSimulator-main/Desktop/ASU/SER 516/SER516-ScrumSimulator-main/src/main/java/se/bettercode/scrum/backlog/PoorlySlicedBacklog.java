package se.bettercode.scrum.backlog;

import se.bettercode.scrum.RandomStoryTitleGenerator;
import se.bettercode.scrum.Story;

import java.util.ArrayList;

public class PoorlySlicedBacklog extends Backlog {

    final static int STORY_COUNT = 4;

    public PoorlySlicedBacklog() {
        super("Poorly sliced");

        ArrayList<String> storyTitles = (new RandomStoryTitleGenerator()).generate(STORY_COUNT);

        addStory(new Story(8, storyTitles.get(0)));
        addStory(new Story(8, storyTitles.get(1)));
        addStory(new Story(8, storyTitles.get(2)));
        addStory(new Story(8, storyTitles.get(3)));

    }
}
