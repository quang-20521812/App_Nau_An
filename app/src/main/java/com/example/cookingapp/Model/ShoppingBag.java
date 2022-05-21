package com.example.cookingapp.Model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingBag {
    ArrayList<String> foodKeyList;
    ArrayList<String> foodNameList;
    ArrayList<Ingredient> ingredientList;

    public ShoppingBag() {
        foodKeyList = new ArrayList<>();
        ingredientList = new ArrayList<>();
        foodNameList = new ArrayList<>();
    }

     public void addFood(Food food){
        foodKeyList.add(food.getFoodKey());
        foodNameList.add(food.getFoodName());

        int size;
        if(this.ingredientList.size() < food.getIngredients().size()){
            size = this.ingredientList.size();
        }
        else {
            size = food.getIngredients().size();
        }
        int j = 0;
        String list = "";
        for(int i = 0; i < size; i++){
            if(this.ingredientList.get(i).getIngKey().contains(food.getIngredients().get(j).getIngKey()))
            {
                this.ingredientList.get(i).setIngQuantity(this.ingredientList.get(i).getIngQuantity()
                        + food.getIngredients().get(i).getIngQuantity());
                food.getIngredients().get(i).setIngQuantity(0);
            }
            j++;
        }
        for(j = 0 ; j < food.getIngredients().size(); j ++){
            if(food.getIngredients().get(j).getIngQuantity() != 0){
                this.ingredientList.add(food.getIngredients().get(j));
            }
        }
    }

    public ArrayList<String> getFoodKeyList() {
        return foodKeyList;
    }

    public void setFoodKeyList(ArrayList<String> foodKeyList) {
        this.foodKeyList = foodKeyList;
    }

    public ArrayList<String> getFoodNameList() {
        return foodNameList;
    }

    public void setFoodNameList(ArrayList<String> foodNameList) {
        this.foodNameList = foodNameList;
    }

    public ArrayList<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(ArrayList<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }
}
