package com.example.cookingapp.Model;

import java.util.List;

public class Food {
    private String foodKey;
    private String foodName;
    private List<Ingredient> ingredients;
    private String[] cookingSteps;

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
}
