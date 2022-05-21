package com.example.cookingapp.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cookingapp.Model.Ingredient;
import com.example.cookingapp.R;

import java.util.ArrayList;

public class AdapterFoodDetail_CookingSteps extends BaseAdapter {

    final ArrayList<String> cookingSteps;

    public AdapterFoodDetail_CookingSteps(ArrayList<String>cookingSteps) {
        this.cookingSteps = cookingSteps;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return super.areAllItemsEnabled();
    }

    @Override
    public int getCount() {
        return cookingSteps.size();
    }

    @Override
    public Object getItem(int i) {
        return cookingSteps.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewCookingStep;
        if (view == null){
            viewCookingStep = View.inflate(viewGroup.getContext(), R.layout.list_view_cooking_steps_item, null);
        }
        else
            viewCookingStep = view;

        ((TextView) viewCookingStep.findViewById(R.id.tvStepTitle)).setText(String.format("Bước %s", String.valueOf(i + 1)));
        ((TextView) viewCookingStep.findViewById(R.id.tvStepInfo)).setText(cookingSteps.get(i));

        return viewCookingStep;
    }
}
