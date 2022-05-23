package com.example.cookingapp.Model;

import java.util.ArrayList;
import java.util.List;

public class Food {
    private String foodKey;
    private String foodName;
    private String foodCate;
    private ArrayList<Ingredient> getIngredients;
    private ArrayList<String> cookingSteps;
    private int resourceID;

    public Food(String foodName, int resourceID) {
        this.foodName = foodName;
        this.resourceID = resourceID;
    }

    public int getResourceID() {
        return resourceID;
    }

    public void setResourceID(int resourceID) {
        this.resourceID = resourceID;
    }

    public Food(String foodKey, String foodName, String foodCate, ArrayList<Ingredient> ingredients, ArrayList<String> cookingSteps, int resourceID) {
        this.foodKey = foodKey;
        this.foodName = foodName;
        this.foodCate = foodCate;
        this.getIngredients = ingredients;
        this.cookingSteps = cookingSteps;
        this.resourceID = resourceID;
    }


    public String getFoodKey() {
        return foodKey;
    }

    public void setFoodKey(String foodKey) {
        this.foodKey = foodKey;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodCate() {
        return foodCate;
    }

    public void setFoodCate(String foodCate) {
        this.foodCate = foodCate;
    }

    public ArrayList<Ingredient> getGetIngredients() {
        return getIngredients;
    }

    public void setGetIngredients(ArrayList<Ingredient> getIngredients) {
        this.getIngredients = getIngredients;
    }

    public ArrayList<String> getCookingSteps() {
        return cookingSteps;
    }

    public void setCookingSteps(ArrayList<String> cookingSteps) {
        this.cookingSteps = cookingSteps;
    }
}
