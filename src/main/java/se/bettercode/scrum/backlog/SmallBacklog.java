package se.bettercode.scrum.backlog;


import se.bettercode.scrum.RandomStoryTitleGenerator;
import se.bettercode.scrum.Story;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SmallBacklog extends Backlog {

    final static int STORY_COUNT = 5;

    public SmallBacklog() {
        super("Small");
        int storycount  = 0;
        File file = new File("src/main/java/se/bettercode/scrum/gui/UserStory");
        Scanner sc;
        try {
            sc = new Scanner(file);
            while (sc.hasNextLine() && storycount<5){
                addStory(new Story((Integer.valueOf(sc.nextLine())), sc.nextLine(),sc.nextLine(),sc.nextLine(),sc.nextLine(),sc.nextLine()));
                storycount += 1;
            }
            } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        /* 
        ArrayList<String> storyTitles = (new RandomStoryTitleGenerator()).generate(STORY_COUNT);

        addStory(new Story(3, storyTitles.get(0),""));
        addStory(new Story(5, storyTitles.get(1),""));
        addStory(new Story(8, storyTitles.get(2),""));
        addStory(new Story(5, storyTitles.get(3),""));
        addStory(new Story(1, storyTitles.get(4),""));
        */

    }
}