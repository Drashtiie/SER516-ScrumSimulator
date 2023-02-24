package se.bettercode.scrum;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Project {
    public Project(){
    }
    private final String projectsFilePath = "src/main/java/se/bettercode/scrum/projectList";
    private boolean isStarred;
    private String projectName;
    private ArrayList<ArrayList<String>> projectList = new ArrayList<ArrayList<String>>();

    public ArrayList<ArrayList<String>> getProjectList(){
        String[] token;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(projectsFilePath));
            String line = null;
            line = reader.readLine();
            while (line != null){
                ArrayList<String> temp = new ArrayList();
                token = line.split(":");
                temp.add(token[0]);
                temp.add(token[1]);
                projectList.add(temp);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Sending"  + projectList);
        return projectList;
    }

    public void setProject(String projectName, boolean isStarred){
        this.projectName = projectName;
        this.isStarred = isStarred;
        File file = new File(projectsFilePath);
        BufferedWriter bf;
        try {
            bf = new BufferedWriter(new FileWriter(file, true));
            bf.write(this.projectName + ":" + this.isStarred);
            bf.newLine();
            bf.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
