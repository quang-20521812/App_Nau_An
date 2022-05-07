package com.example.cookingapp.Model;

public class Tips {
    private int resourceID;
    private String title;
    private String description;

    public Tips(int resourceID, String title, String description) {
        this.resourceID = resourceID;
        this.title = title;
        this.description = description;
    }

    public Tips() {

    }

    public int getResourceID() {
        return resourceID;
    }

    public void setResourceID(int resourceID) {
        this.resourceID = resourceID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}