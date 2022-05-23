package com.example.cookingapp.Model;

public class Ingredient {
    private String ingKey;
    private String ingName;
    private String ingUnit;
    private int ingQuantity;

    public Ingredient(){
        return;
    }

    public Ingredient(String key, String name, String unit, int quantity) {
        this.ingKey = key;
        this.ingName = name;
        this.ingUnit = unit;
        this.ingQuantity = quantity;
    }

    public String getIngKey() {
        return ingKey;
    }

    public void setIngKey(String ingKey) {
        this.ingKey = ingKey;
    }

    public String getIngName() {
        return ingName;
    }

    public void setIngName(String ingName) {
        this.ingName = ingName;
    }

    public String getIngUnit() {
        return ingUnit;
    }

    public void setIngUnit(String ingUnit) {
        this.ingUnit = ingUnit;
    }

    public int getIngQuantity() {
        return ingQuantity;
    }

    public void setIngQuantity(int ingQuantity) {
        this.ingQuantity = ingQuantity;
    }


}
