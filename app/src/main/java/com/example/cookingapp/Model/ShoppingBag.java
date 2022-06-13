package com.example.cookingapp.Model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingBag {
    ArrayList<Ingredient> ingredientList;

    public ShoppingBag() {
        ingredientList = new ArrayList<>();
    }

    public void addFood(ArrayList<Ingredient> ingredients) {
        int j = 0;
        int temp1 = 0;
        int temp2 = 0;
        for (int i = 0; i < this.ingredientList.size(); i++) {
            for (j = 0; j < ingredients.size(); j++) {
                if (this.ingredientList.get(i).getIngKey().compareTo(ingredients.get(j).getIngKey()) == 0) {
                    temp1 = this.ingredientList.get(i).getIngQuantity();
                    temp2 = ingredients.get(j).getIngQuantity();
                    this.ingredientList.get(i).setIngQuantity(temp1 + temp2);
                    ingredients.get(j).setIngQuantity(0);
                }
            }

        }
        for (j = 0; j < ingredients.size(); j++) {
            if (ingredients.get(j).getIngQuantity() != 0) {
                this.ingredientList.add(ingredients.get(j));
            }
        }
    }


    public ArrayList<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(ArrayList<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }
}