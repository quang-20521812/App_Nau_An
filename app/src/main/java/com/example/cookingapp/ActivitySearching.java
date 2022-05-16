package com.example.cookingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.GridView;

import com.example.cookingapp.Adapter.AdapterSearching_Hint;
import com.example.cookingapp.Adapter.AdapterSearching_Item;
import com.example.cookingapp.Model.Food;
import com.example.cookingapp.Model.Tips;

import java.util.ArrayList;
import java.util.List;

public class ActivitySearching extends AppCompatActivity {
    private androidx.recyclerview.widget.RecyclerView recyclerViewSearchingHint;
    private AdapterSearching_Hint adapterSearching_hint;
    private GridView gridViewSearchingItem;
    private AdapterSearching_Item adapterSearching_item;
    private ArrayList<Tips> listFoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching);

        recyclerViewSearchingHint = (androidx.recyclerview.widget.RecyclerView) findViewById(R.id.recyclerViewSearchingHint);
        gridViewSearchingItem = (GridView) findViewById(R.id.gridViewSearchingItem);
        listFoods = new ArrayList<Tips>();

        adapterSearching_hint = new AdapterSearching_Hint(this);
        adapterSearching_item = new AdapterSearching_Item(listFoods, this);

        recyclerViewSearchingHint.setAdapter(adapterSearching_hint);
        gridViewSearchingItem.setAdapter(adapterSearching_item);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), androidx.recyclerview.widget.RecyclerView.HORIZONTAL, false);
        recyclerViewSearchingHint.setLayoutManager(linearLayoutManager);

        adapterSearching_hint.setData(getListSearchingHint());
        adapterSearching_item.setData(getListSearchingItem());
    }

    private List<String> getListSearchingHint() {
        List<String> listHints = new ArrayList<String>();

        listHints.add("Món chay");
        listHints.add("Món xào");
        listHints.add("Món canh");
        listHints.add("Món chiên");
        listHints.add("Món mặn");

        return listHints;
    }

    private ArrayList<Tips> getListSearchingItem() {
        listFoods.add(new Tips(R.drawable.ic_launcher_background, "Món 1", "Mô tả 1"));
        listFoods.add(new Tips(R.drawable.ic_launcher_background, "Món 2", "Mô tả 2"));
        listFoods.add(new Tips(R.drawable.ic_launcher_background, "Món 3", "Mô tả 3"));
        listFoods.add(new Tips(R.drawable.ic_launcher_background, "Món 4", "Mô tả 4"));
        listFoods.add(new Tips(R.drawable.ic_launcher_background, "Món 5", "Mô tả 5"));
        listFoods.add(new Tips(R.drawable.ic_launcher_background, "Món 6", "Mô tả 6"));

        listFoods.add(new Tips(R.drawable.ic_launcher_background, "Món 1", "Mô tả 1"));
        listFoods.add(new Tips(R.drawable.ic_launcher_background, "Món 2", "Mô tả 2"));
        listFoods.add(new Tips(R.drawable.ic_launcher_background, "Món 3", "Mô tả 3"));
        listFoods.add(new Tips(R.drawable.ic_launcher_background, "Món 4", "Mô tả 4"));
        listFoods.add(new Tips(R.drawable.ic_launcher_background, "Món 5", "Mô tả 5"));
        listFoods.add(new Tips(R.drawable.ic_launcher_background, "Món 6", "Mô tả 6"));

        return listFoods;
    }
}