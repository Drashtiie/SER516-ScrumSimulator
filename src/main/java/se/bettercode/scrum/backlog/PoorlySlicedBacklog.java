package se.bettercode.scrum.backlog;

import se.bettercode.scrum.RandomStoryTitleGenerator;
import se.bettercode.scrum.Story;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class PoorlySlicedBacklog extends Backlog {

    final static int STORY_COUNT = 4;

    public PoorlySlicedBacklog() {
        super("Poorly sliced");
        /* 
        ArrayList<String> storyTitles = (new RandomStoryTitleGenerator()).generate(STORY_COUNT);

        addStory(new Story(8, storyTitles.get(0),""));
        addStory(new Story(8, storyTitles.get(1),""));
        addStory(new Story(8, storyTitles.get(2),""));
        addStory(new Story(8, storyTitles.get(3),""));
        */
        File file = new File("src/main/java/se/bettercode/scrum/gui/UserStory");
        Scanner sc;
        try {
            sc = new Scanner(file);
            while (sc.hasNextLine()){
                addStory(new Story(Integer.valueOf(sc.nextLine()), sc.nextLine(),sc.nextLine()));
            }
            } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    
        
                        
        
    }
}
