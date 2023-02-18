package se.bettercode.scrum.team;

//import javax.mail.*;
//import javax.mail.internet.AddressException;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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
//            for (Map.Entry<String, ArrayList<String>> entry : addUsersMap.entrySet()){
//                bf.write(entry.getKey() + ": " + entry.getValue());
            bf.write(email + ": " + values);
            bf.newLine();
//            }
            bf.flush();
//            sendEmail("ljain3@asu.edu", this.email);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendEmail(String from, String to) {
//        Throws error
//        String host = "smtp.asu.edu";
//        String password = "Labdhi1234";
//        Properties properties = System.getProperties();
//        properties.put("mail.smtp.host", host);
//        properties.put("mail.smtp.port", "465");
//        properties.put("mail.smtp.ssl.enable", "true");
//        properties.put("mail.smtp.auth", "true");
//        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                System.out.println(from + ": " + password);
//                return new PasswordAuthentication(from, "Labdhi1234");
//            }
//        });
//        session.setDebug(true);
//        try {
//            // Create a default MimeMessage object.
//            MimeMessage message = new MimeMessage(session);
//
//            // Set From: header field of the header.
//            message.setFrom(new InternetAddress(from));
//
//            // Set To: header field of the header.
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//
//            // Set Subject: header field
//            message.setSubject("This is the Subject Line!");
//
//            // Now set the actual message
//            message.setText("You have been added to a project on the scrum board!");
//
//            System.out.println("sending...");
//            // Send message
//            Transport.send(message);
//            System.out.println("Sent message successfully....");
//        } catch (MessagingException mex) {
//            mex.printStackTrace();
//        }
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
