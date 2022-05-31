package com.example.cookingapp.Model;

public class Tips {
    private String title;
    private String description;
    private String tipURL;

    public Tips() {

    }

    public Tips(String title, String description, String tipURL) {
        this.title = title;
        this.description = description;
        this.tipURL = tipURL;
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

    public String getTipURL() {
        return tipURL;
    }

    public void setTipURL(String tipURL) {
        this.tipURL = tipURL;
    }
}