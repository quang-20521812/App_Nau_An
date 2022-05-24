package com.example.cookingapp.Model;

import java.util.ArrayList;
import java.util.List;

public class Food {
    private String foodKey;
    private String foodName;
    private String foodCate;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<String> cookingSteps;
    private int resourceID;

    public Food(){
        return;
    }

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

    public void setFoodCate(String foodCate) {
        this.foodCate = foodCate;
    }

    public void setCookingSteps(ArrayList<String> cookingSteps) {
        this.cookingSteps = cookingSteps;
    }

    public Food(String foodKey, String foodName, String foodCate, ArrayList<Ingredient> ingredients, ArrayList<String> cookingSteps, int resourceID) {
        this.foodKey = foodKey;
        this.foodName = foodName;
        this.foodCate = foodCate;
        this.ingredients = ingredients;
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

    public String getFoodCate() {
        return foodCate;
    }

    public ArrayList<String> getCookingSteps() {
        return cookingSteps;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }


}
