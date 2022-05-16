package com.example.cookingapp.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cookingapp.Model.Tips;
import com.example.cookingapp.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterSearching_Item extends BaseAdapter {
    private ArrayList<Tips> listFoods;
    private Context mContext;

    public AdapterSearching_Item(ArrayList<Tips> listFoods, Context mContext) {
        this.listFoods = listFoods;
        this.mContext = mContext;
    }


    public void setData(ArrayList<Tips> listFoods) {
        this.listFoods = listFoods;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listFoods.size();
    }

    @Override
    public Object getItem(int i) {
        return listFoods.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listFoods.get(i).getResourceID();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewItem;
        if (view == null){
            viewItem = View.inflate(viewGroup.getContext(), R.layout.search_item_card, null);
        }
        else
            viewItem = view;

        Tips tips = (Tips) getItem(i);
        ((ImageView) viewItem.findViewById(R.id.imageViewSearchingItem)).setImageResource(tips.getResourceID());
        ((TextView) viewItem.findViewById(R.id.textViewSearchingItem)).setText(tips.getTitle());

        return viewItem;
    }
}
