package com.example.cookingapp.Model;

import java.util.ArrayList;
import java.util.List;

public class Food {
    private String foodKey;
    private String foodName;
    private String foodCate;
    private ArrayList<Ingredient> getIngredients;
    private String[] cookingSteps;
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

    public Food(String foodKey, String foodName, String foodCate, ArrayList<Ingredient> ingredients, String[] cookingSteps, int resourceID) {
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

    public String getFoodCate() {
        return foodCate;
    }

    public String[] getCookingSteps() {
        return cookingSteps;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public List<Ingredient> getIngredients() {
        return getIngredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.getIngredients = ingredients;
    }

    public String[] getCookingStep() {
        return cookingSteps;
    }

    public void setCookingStep(String[] cookingStep) {
        this.cookingSteps = cookingStep;
    }

    public void setFoodCate(String foodCate) {
        this.foodCate = foodCate;
    }

    public void setCookingSteps(String[] cookingSteps) {
        this.cookingSteps = cookingSteps;
    }


}
