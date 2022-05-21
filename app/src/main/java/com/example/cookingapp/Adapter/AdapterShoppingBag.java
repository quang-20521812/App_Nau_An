package com.example.cookingapp.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cookingapp.Model.ShoppingBag;
import com.example.cookingapp.R;

public class AdapterShoppingBag extends BaseAdapter {
    ShoppingBag shoppingBag;

    public AdapterShoppingBag(ShoppingBag shoppingBag) {
        this.shoppingBag = shoppingBag;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
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
        else{
            v = view;
            ((TextView)v.findViewById(R.id.textViewName)).setText(shoppingBag.getIngredientList().get(i).getIngName());
            ((TextView)v.findViewById(R.id.textViewUnit)).setText(shoppingBag.getIngredientList().get(i).getIngQuantity()
                    + " " + shoppingBag.getIngredientList().get(i).getIngUnit());
        }
        return v;
    }
}
