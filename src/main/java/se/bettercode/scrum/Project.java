package se.bettercode.scrum;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Project {
    public Project(){
    }
    private final String projectsFilePath = "src/main/java/se/bettercode/scrum/projectList";
    private int projectId;
    private String projectName;
    private String projectTeamName;
    HashMap<Integer, ArrayList<String>> projectsMap = new HashMap<Integer, ArrayList<String>>();


    public void addProject(int projectId, String projectName, String projectTeamName){
        this.projectId = projectId;
        this.projectName = projectName;
        this. projectTeamName = projectTeamName;
        ArrayList<String> values = new ArrayList<>();
        values.add(projectName);
        values.add(projectTeamName);
        projectsMap.put(projectId,values);
        File file = new File(projectsFilePath);
        BufferedWriter bf;
        try {
            bf = new BufferedWriter(new FileWriter(file, true));
            for (Map.Entry<Integer, ArrayList<String>> entry : projectsMap.entrySet()){
                bf.write(entry.getKey() + ": " + entry.getValue());
                bf.newLine();
            }
            bf.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
