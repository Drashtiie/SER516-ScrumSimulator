package se.bettercode.scrum;

import junit.framework.TestCase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ProjectTest extends TestCase {

    public String projectTag;
    ArrayList<String> tagList= new ArrayList<String>(
            Arrays.asList("Internal", "External"));
    public void testGetProjectList() {


    }

    public void testSetProject() throws IOException {

        File myObj = new File("src/main/java/se/bettercode/scrum/gui/Projects");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String[] data2 = data.split(" - ",2);
            this.projectTag = data2[2];
            boolean x= this.tagList.contains(this.projectTag);
            assertTrue(x);

        }
        myReader.close();

    }

}