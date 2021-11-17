package com.example.tusaproject;

public class Party {

    private String name;
    private String numMans;
    private int imagePath;
    private String location;

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
