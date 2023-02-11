package se.bettercode.scrum.team;

public class User {
    String name;
    String email;
    String role;

    public User(String name, String email, String role){
        this.role = role;
        this.name = name;
        this. email = email;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getEmail(){return this.email;}
}

