package com.example.cookingapp.Model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingBag {
    ArrayList<Ingredient> ingredientList;

    public ShoppingBag() {
        ingredientList = new ArrayList<>();
    }

     public void addFood(Food food){

        int size;
        if(this.ingredientList.size() < food.getIngredients().size()){
            size = this.ingredientList.size();
        }
        else {
            size = food.getIngredients().size();
        }
        int j = 0;
        int temp1 = 0;
        int temp2 = 0;
        for(int i = 0; i < size; i++){
            for( j = 0; j < size; j++){
                if(this.ingredientList.get(i).getIngKey().compareTo(food.getIngredients().get(j).getIngKey()) == 0 && food.getIngredients().get(i).getIngQuantity() != 0)
                {
                    temp1 = this.ingredientList.get(i).getIngQuantity();
                    temp2 = food.getIngredients().get(j).getIngQuantity();
                    this.ingredientList.get(i).setIngQuantity(temp1 + temp2);
                    food.getIngredients().get(j).setIngQuantity(0);
                }
            }

        }
        for(j = 0 ; j < food.getIngredients().size(); j ++){
            if(food.getIngredients().get(j).getIngQuantity() != 0){
                this.ingredientList.add(food.getIngredients().get(j));
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
