package com.example.cookingapp.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cookingapp.Model.Ingredient;
import com.example.cookingapp.Model.ShoppingBag;
import com.example.cookingapp.R;

public class AdapterShoppingBag extends BaseAdapter {
    ShoppingBag shoppingBag;

    public AdapterShoppingBag(ShoppingBag shoppingBag) {
        this.shoppingBag = shoppingBag;
    }

    @Override
    public int getCount() {
        return shoppingBag.getIngredientList().size();
    }

    @Override
    public Object getItem(int i) {
        return shoppingBag.getIngredientList().get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v;
        if(view == null){
            v = View.inflate(viewGroup.getContext(), R.layout.shopping_bag_view, null);
        }
        else {
            v = view;
        }
            Ingredient ingredient = (Ingredient) getItem(i);
            ((TextView)v.findViewById(R.id.textViewName)).setText(ingredient.getIngName());
            ((TextView)v.findViewById(R.id.textViewUnit)).setText(ingredient.getIngQuantity()
                    + " " + ingredient.getIngUnit());
        return v;
    }
}
