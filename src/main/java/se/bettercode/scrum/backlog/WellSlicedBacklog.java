package se.bettercode.scrum.backlog;

import se.bettercode.scrum.RandomStoryTitleGenerator;
import se.bettercode.scrum.Story;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class WellSlicedBacklog extends Backlog {

    final static int STORY_COUNT = 10;

    public WellSlicedBacklog() {
        super("Well sliced");
        int storycount  = 0;
        File file = new File("src/main/java/se/bettercode/scrum/gui/UserStory");
        Scanner sc;
        try {
            sc = new Scanner(file);
            while (sc.hasNextLine() && storycount<10){
<<<<<<< HEAD
                addStory(new Story(Integer.valueOf(sc.nextLine()), sc.nextLine(),sc.nextLine(),sc.nextLine()));
=======
                addStory(new Story(Integer.valueOf(sc.nextLine()), sc.nextLine(),sc.nextLine(),sc.nextLine(),sc.nextLine()));
>>>>>>> 119da7acd7ceb48f843f008914b8f204eb4a4978
                storycount += 1;
            }
            } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        /* 
        ArrayList<String> storyTitles = (new RandomStoryTitleGenerator()).generate(STORY_COUNT);


        addStory(new Story(3, storyTitles.get(0),""));
        addStory(new Story(1, storyTitles.get(1),""));
        addStory(new Story(1, storyTitles.get(2),""));
        addStory(new Story(3, storyTitles.get(3),""));
        addStory(new Story(1, storyTitles.get(4),""));
        addStory(new Story(1, storyTitles.get(5),""));
        addStory(new Story(1, storyTitles.get(6),""));
        addStory(new Story(3, storyTitles.get(7),""));
        addStory(new Story(1, storyTitles.get(8),""));
        addStory(new Story(1, storyTitles.get(9),""));
        */

    }
}