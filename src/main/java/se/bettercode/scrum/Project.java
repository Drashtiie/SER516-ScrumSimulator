package se.bettercode.scrum;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Project {
    public Project(){
    }
    private final String projectsFilePath = "src/main/java/se/bettercode/scrum/projectList";
    private String projectName;
    private ArrayList<String> projectList = new ArrayList<String>();

    public ArrayList<String> getProjectList(){
        String[] token;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(projectsFilePath));
            String line = null;
            line = reader.readLine();
            while (line != null){
//                System.out.println("this is temp:" + temp);
                projectList.add(projectName);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Sending: " + projectList);
        return projectList;
    }

    public void setProject(String projectName){
        this.projectName = projectName;
        File file = new File(projectsFilePath);
        BufferedWriter bf;
        try {
            bf = new BufferedWriter(new FileWriter(file, true));
            bf.write(this.projectName);
            bf.newLine();
            bf.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
