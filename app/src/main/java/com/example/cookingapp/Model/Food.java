package com.example.cookingapp.Model;

import java.util.List;

public class Food {
    private String foodKey;
    private String foodName;
    private String foodCate;
    private List<Ingredient> ingredients;
    private String[] cookingSteps;

    public Food(String foodKey, String foodName, String foodCate, List<Ingredient> ingredients, String[] cookingSteps) {
        this.foodKey = foodKey;
        this.foodName = foodName;
        this.foodCate = foodCate;
        this.ingredients = ingredients;
        this.cookingSteps = cookingSteps;
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
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
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
