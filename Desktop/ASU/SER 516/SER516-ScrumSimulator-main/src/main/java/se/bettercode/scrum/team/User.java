package se.bettercode.scrum.team;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private String email;
    private String role;
    HashMap<String, ArrayList<String>> addUsersMap = new HashMap<String, ArrayList<String>>();
    ArrayList<ArrayList<String>> usersList = new ArrayList<>();
    private final String usersFilePath = "src/main/java/se/bettercode/scrum/team/usersInfo";
    public User(){
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getEmail(){return this.email;}

    public void addUser(String name, String email, String role){
        this.role = role;
        this.name = name;
        this. email = email;
        ArrayList<String> values = new ArrayList<>();
        values.add(name);
        values.add(role);
        addUsersMap.put(email,values);
        File file = new File(usersFilePath);
        BufferedWriter bf;
        try {
            bf = new BufferedWriter(new FileWriter(file, true));
            for (Map.Entry<String, ArrayList<String>> entry : addUsersMap.entrySet()){
                bf.write(entry.getKey() + ": " + entry.getValue());
                bf.newLine();
            }
            bf.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<ArrayList<String>> getUsers(){
        String[] token;
        int commaLoc;
        try {
            String name;
            String role;
            ArrayList<String> temp = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(usersFilePath));
            String line = reader.readLine();
            while (line != null){
                token = line.split(":");
                commaLoc = token[1].indexOf(',');
                name = token[1].substring(2,commaLoc);
                role = token[1].substring(commaLoc + 2);
                temp.add(token[0]);
                temp.add(name);
                temp.add(role);
                usersList.add(temp);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(usersList);
        return usersList;
    }

}

