package com.example.cookingapp.Model;

import java.util.ArrayList;
import java.util.List;

public class Food {
    private String foodKey;
    private String foodName;
    private String foodCate;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<String> cookingSteps;
    private String foodURL;

    public Food(){
        return;
    }

    public Food(String foodKey, String foodName, String foodCate, ArrayList<Ingredient> ingredients, ArrayList<String> cookingSteps, String foodURL) {
        this.foodKey = foodKey;
        this.foodName = foodName;
        this.foodCate = foodCate;
        this.ingredients = ingredients;
        this.cookingSteps = cookingSteps;
        this.foodURL = foodURL;
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

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<String> getCookingSteps() {
        return cookingSteps;
    }

    public void setCookingSteps(ArrayList<String> cookingSteps) {
        this.cookingSteps = cookingSteps;
    }

    public String getFoodURL() {
        return foodURL;
    }

    public void setfoodURL(String foodURL) {
        this.foodURL = foodURL;
    }
}
