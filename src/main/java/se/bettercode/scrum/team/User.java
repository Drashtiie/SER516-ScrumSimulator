package se.bettercode.scrum.team;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private String email;
    private String role;
    private String team;
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

    public void addUser(String name, String email, String role, String team){
        this.role = role;
        this.name = name;
        this. email = email;
        this.team = team;
        ArrayList<String> values = new ArrayList<>();
        values.add(name);
        values.add(role);
        values.add(team);
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
<<<<<<< HEAD
            String team;
            ArrayList<String> temp = new ArrayList<>();
=======
>>>>>>> labdhi
            BufferedReader reader = new BufferedReader(new FileReader(usersFilePath));
            String line = reader.readLine();
            while (line != null){
                ArrayList<String> temp = new ArrayList<>();
                token = line.split(":");
                commaLoc = token[1].indexOf(',');
                name = token[1].substring(2,commaLoc);
                role = token[1].substring(commaLoc + 2);
                team = token[1].substring(commaLoc + commaLoc + 2);
                temp.add(token[0]);
                temp.add(name);
                temp.add(role);
                temp.add(team);
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