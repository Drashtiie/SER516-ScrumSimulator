package se.bettercode.scrum.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import se.bettercode.scrum.team.User;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class UserWindow {

    private HBox toolBar = new HBox();
    private Button addButton = new Button();

    TextField userNameField = new TextField("Add User Name" );
    TextField userEmailField = new TextField("Add Email Id");
    //ComboBox<String> userRoleField = new ComboBox<String>();
     String roles[] =
    { "Developer","Owner", "Scrum Master", "Stakeholder", "Design", "Front", "Back"};

    // Create a combo box
    ComboBox userRoleField =
            new ComboBox(FXCollections
                    .observableArrayList(roles));

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


    public UserWindow() {
    }

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
        User y = new User();
        System.out.println(y.getUsers());

        ObservableList<String> names = FXCollections.observableArrayList();
//Add a single entry

        try {
            File myObj = new File("src/main/java/se/bettercode/scrum/team/usersInfo");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                names.add(data);
                //System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }



        //ObservableList<ArrayList<String>> names = FXCollections.observableList(y.getUsers());
                //FXCollections.observableArrayList("Engineering", "MCA", "MBA", "Graduation", "MTECH", "Mphil", "Phd");
        ListView<String> listView = new ListView<String>(names);
        //listView.setMaxSize(500, 500);
        //Creating the layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(5, 50, 5, 50));
        layout.getChildren().addAll(listView);
       // layout.setStyle("-fx-background-color: BEIGE");


        secondaryLayout.getChildren().add(layout);
        //Setting the stage
        primaryStage.setScene(new Scene(borderPane, 800, 600));
        primaryStage.show();

    }



    public void setAddUsersButtonAction(EventHandler<ActionEvent> eventHandler) {
        addButton.setOnAction(eventHandler);
    }

    private void toolBarSetup() {
        toolBar.setPadding(new Insets(15, 12, 15, 12));
        toolBar.setSpacing(10);
        toolBar.setStyle("-fx-background-color: #336699;");
        toolBar.getChildren().addAll( userNameField, userEmailField, userRoleField, addButton);


        addButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if ((userNameField.getText() != null && !userNameField.getText().isEmpty())) {
                    System.out.println(userNameField.getText());
                    //sendemail(userNameField.getText());

                    //************function to add user to file
                    User x = new User();
                    x.addUser(userNameField.getText(), userEmailField.getText(),userRoleField.getValue().toString());
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

