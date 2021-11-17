package com.example.tusaproject;

public class Users {

    private String id, username, imageURL;

    //Constructors

    public Users()
    {

    }

    public Users(String id, String username){
        this.id = id;
        this.username = username;
        this.imageURL = imageURL;


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
