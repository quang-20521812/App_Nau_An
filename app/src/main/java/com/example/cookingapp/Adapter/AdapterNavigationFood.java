package com.example.cookingapp.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cookingapp.Model.Food;
import com.example.cookingapp.R;

import java.util.ArrayList;

public class AdapterNavigationFood extends BaseAdapter {

    final ArrayList<Food> foodArrayList;

    public AdapterNavigationFood(ArrayList<Food> foodArrayList) {
        this.foodArrayList = foodArrayList;
    }

    @Override
    public int getCount() {
        return foodArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return foodArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewNavigationFood;
        if (view == null){
            viewNavigationFood = View.inflate(viewGroup.getContext(), R.layout.food_item_card, null);
        }
        else
            viewNavigationFood = view;

        Food food = foodArrayList.get(i);
        ((TextView) viewNavigationFood.findViewById(R.id.textViewTitle)).setText(food.getFoodName());
        ((TextView) viewNavigationFood.findViewById(R.id.textViewDescription)).setText(food.getFoodCate());

        return viewNavigationFood;
    }
}
