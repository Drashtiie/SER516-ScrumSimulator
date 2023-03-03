package se.bettercode.scrum.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import se.bettercode.scrum.team.User;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
public class UserWindow {

    private HBox toolBar = new HBox();
    private Button addButton = new Button();

    TextField userNameField = new TextField("Add User Name" );
    TextField userEmailField = new TextField("Add Email Id");
    //ComboBox<String> userRoleField = new ComboBox<String>();
     String roles[] =
    { "Developer","Owner", "Scrum Master", "Stakeholder", "Design", "Front", "Back"};

     String linesarray[] = {"Team 1", "Team 2", "Team 3"};
     //read from teams text file

    public static String[] readLinesFromFile(String filePath) {
        List<String> linesList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            //br.skip(line.length());
            while ((line = br.readLine()) != null) {
                linesList.add(line);
            }
        } catch (IOException e) {
            // Handle the exception
        }

        String[] linesArray = linesList.toArray(new String[0]);
        return linesArray;
    }
    // Create a combo box
    ComboBox userRoleField =
            new ComboBox(FXCollections
                    .observableArrayList(roles));


    ComboBox userTeamField =
            new ComboBox(FXCollections.observableArrayList(readLinesFromFile("src/main/java/se/bettercode/scrum/gui/Teams")));

    //TextField userRoleField = new TextField("Add Role");
    //userRoleField.getItems().addAll("Developer", "Owner");
    //Setting the prompt text of the combo box
     // userRoleField.setPromptText("Select Role");
    //Getting the observable list of the combo box
//    ObservableList<String> list = userRoleField.getItems();
//    //Adding items to the combo box
//      list.add("Java");
//      list.add("C++");
//      list.add("Python");
//      list.add("Big Data");
//      list.add("Machine Learning");
//    userNameField.setPromptText("Enter your name");
//userNameField.setFocusTraversable(false);


    public void show() {
        toolBarSetup();

        addButton.setPrefSize(100, 20);
        addButton.setText("Add User");
        Stage primaryStage = new Stage();
        StackPane secondaryLayout = new StackPane();
        BorderPane borderPane = new BorderPane();
        secondaryLayout.prefWidthProperty().bind(primaryStage.widthProperty());

        borderPane.setCenter(secondaryLayout);
        borderPane.setTop(toolBar);
//        User y = new User();
//        System.out.println(y.getUsers());

        ObservableList<String> emails = FXCollections.observableArrayList();
        ObservableList<String> roles = FXCollections.observableArrayList();
        ObservableList<String> names = FXCollections.observableArrayList();
        ObservableList<String> teams = FXCollections.observableArrayList();
//Add a single entry

        try {
            File myObj = new File("src/main/java/se/bettercode/scrum/team/usersInfo");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                System.out.println(data);
                String[] arrOfStr = data.split(":");
                String[] arrOfStr2 = arrOfStr[1].split(",");
                String[] arrOfStr3 = arrOfStr2[1].split(",");
                arrOfStr2[1] = arrOfStr2[1].substring(0, arrOfStr2[1].length() );
                arrOfStr2[0] = arrOfStr2[0].substring(2, arrOfStr2[0].length() );
                arrOfStr2[2] = arrOfStr2[2].substring(0, arrOfStr2[2].length() - 1);
                System.out.println(arrOfStr[0]+  arrOfStr2[1]);
                emails.add(arrOfStr[0]);
                roles.add(arrOfStr2[1]);
                names.add(arrOfStr2[0]);
                teams.add(arrOfStr2[2]);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }



        //ObservableList<ArrayList<String>> names = FXCollections.observableList(y.getUsers());
                //FXCollections.observableArrayList("Engineering", "MCA", "MBA", "Graduation", "MTECH", "Mphil", "Phd");
        ListView<String> listView = new ListView<String>(emails);
        listView.setMaxSize(300, 500);
        //Creating the layout
        ListView<String> listView2 = new ListView<String>(roles);
        listView.setMaxSize(300, 500);
        ListView<String> listView3 = new ListView<String>(names);
        listView.setMaxSize(300, 500);
        ListView<String> listView4 = new ListView<>(teams);
        listView.setMaxSize(300,500);

        HBox hbox = new HBox();
        hbox.getChildren().addAll(listView,listView2, listView3, listView4);

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(5, 50, 5, 50));
        layout.getChildren().addAll(hbox);
       // layout.setStyle("-fx-background-color: BEIGE");


        secondaryLayout.getChildren().add(layout);
        //Setting the stage
        primaryStage.setScene(new Scene(borderPane, 1000, 600));
        primaryStage.show();

    }



    public void setAddUsersButtonAction(EventHandler<ActionEvent> eventHandler) {
        addButton.setOnAction(eventHandler);
    }

    private void toolBarSetup() {
        toolBar.setPadding(new Insets(15, 12, 15, 12));
        toolBar.setSpacing(10);
        toolBar.setStyle("-fx-background-color: #336699;");
        toolBar.getChildren().addAll( userNameField, userEmailField, userRoleField, userTeamField, addButton);


        addButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if ((userNameField.getText() != null && !userNameField.getText().isEmpty())) {
                    System.out.println(userNameField.getText());
                    //sendemail(userNameField.getText());

                    //************function to add user to file
                    User x = new User();
                    x.addUser(userNameField.getText(), userEmailField.getText(),userRoleField.getValue().toString(),userTeamField.getValue().toString());
                    //System.out.println("Email sent");

                }
                else {
                }
            }
        });



    }

    private void sendemail(String email) {
        String to = email;

        // Sender's email ID needs to be mentioned
//        String from = "dpate191@asu.edu";
//
//        // Assuming you are sending email from localhost
//        String host = "localhost";
//
//        // Get system properties
//        Properties properties = System.getProperties();
//
//        // Setup mail server
//        properties.setProperty("mail.smtp.host", host);
//
//        // Get the default Session object.
//        Session session = Session.getDefaultInstance(properties);
//
//        try {
//            // Create a default MimeMessage object.
//            MimeMessage message = new MimeMessage(session);
//
//            // Set From: header field of the header.
//            message.setFrom(new InternetAddress(from));
//
//            // Set To: header field of the header.
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//            message.setSubject("This is the Subject Line!");
//
//            // Now set the actual message
//            message.setText("This is actual message");
//
//            // Send message
//            Transport.send(message);
//            System.out.println("Sent message successfully....");
//        } catch (MessagingException mex) {
//            mex.printStackTrace();
//        }

        //String to = "sonoojaiswal1988@gmail.com";//change accordingly
        String from = "dpate191@asu.edu";//change accordingly
        String host = "localhost";//or IP address

        //Get the session object
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        Session session = Session.getDefaultInstance(properties);

        //compose the message
        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject("Ping");
            message.setText("Hello, this is example of sending email  ");

            // Send message
            Transport.send(message);
            System.out.println("message sent successfully....");

        }catch (MessagingException mex) {mex.printStackTrace();}

    }


        }
