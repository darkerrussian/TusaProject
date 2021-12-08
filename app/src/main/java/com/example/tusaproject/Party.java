package com.example.tusaproject;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class Party {

    private String name;
    private String numMans;
    private int imagePath;
    private String location;
    private ArrayList <FirebaseUser> usersList;
    private ArrayList<String> usersMails;
    private String PartyPath;

    public String getPartyPath(){
        return PartyPath;
    }
    public void setPartyPath(String path){
        PartyPath = path;
    }

    public ArrayList<String> getUsersMails() {
        return usersMails;
    }

    public void setUsersMails(ArrayList<String> usersMails) {
        this.usersMails = usersMails;
    }

    public int getImagePath() {
        return imagePath;
    }

    public void setImagePath(int imagePath) {
        this.imagePath = imagePath;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    /*public String getPartyUsers() {
        List<String> users = new ArrayList<>();

        for(FirebaseUser user: usersList){
            users.add(user.getEmail());


        }
        return users.toString();
    }*/


    public List<FirebaseUser> getUsersList() {

        return usersList;
    }

    /*public List<String> getUsersNames(){
        usersList.
    }*/

    public void setUsersList(ArrayList<FirebaseUser> usersList) {
        this.usersList = usersList;
    }

    Party(String name, String numMans, int imagePath, String location){

        this.name = name;
        this.numMans = numMans;
        this.imagePath = imagePath;
        this.location = location;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumMans() {
        return numMans;
    }

    public void setNumMans(String numMans) {
        this.numMans = numMans;
    }

}
