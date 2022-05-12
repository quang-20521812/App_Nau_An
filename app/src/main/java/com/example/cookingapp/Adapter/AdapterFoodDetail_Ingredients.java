package com.example.cookingapp.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cookingapp.Model.Ingredient;
import com.example.cookingapp.R;

import java.util.ArrayList;

public class AdapterFoodDetail_Ingredients extends BaseAdapter {

    final ArrayList<Ingredient> ingredientArrayList;

    public AdapterFoodDetail_Ingredients(ArrayList<Ingredient> ingredientArrayList) {
        this.ingredientArrayList = ingredientArrayList;
    }

    @Override
    public int getCount() {
        return ingredientArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return ingredientArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewIngredient;
        if (view == null){
            viewIngredient = View.inflate(viewGroup.getContext(), R.layout.list_view_ingredient_item, null);
        }
        else
            viewIngredient = view;

        Ingredient ingredient = (Ingredient) getItem(i);
        ((TextView) viewIngredient.findViewById(R.id.tvIngName)).setText(ingredient.getIngName());
        ((TextView) viewIngredient.findViewById(R.id.tvIngQuantity)).setText(String.format("%s %s", ingredient.getIngQuantity(), ingredient.getIngUnit()));

        return viewIngredient;
    }
}
