package com.example.cookingapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;

import com.example.cookingapp.Adapter.Adapter_SelectedFood;
import com.example.cookingapp.Model.Food;

import java.util.ArrayList;
import java.util.List;


public class FragmentMainPage extends Fragment {

    int tabDayPos;
    TabHost tabHost;
    private Adapter_SelectedFood adapter_selectedFood;
    private RecyclerView rcv_selectedFood;
    public FragmentMainPage(int tabDayPos) {
        this.tabDayPos = tabDayPos;
    }


//    public static FragmentMainPage newInstance(String param1, String param2) {
//        FragmentMainPage fragment = new FragmentMainPage();
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main_page, container, false);

        rcv_selectedFood = v.findViewById(R.id.rcv_selectedFood);
        tabHost = v.findViewById(R.id.tabHost);

        tabHost.setup();
        creatTabs();
        setupTabBreakfast();
        return v;
    }

    private void setupTabBreakfast() {

        adapter_selectedFood = new Adapter_SelectedFood();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        rcv_selectedFood.setLayoutManager(linearLayoutManager);

        adapter_selectedFood.setFoodList(getFoodList(tabDayPos,0));
        rcv_selectedFood.setAdapter(adapter_selectedFood);

    }

    private List<Food> getFoodList(int dayPos, int mealPos) {
        List<Food> foodList = new ArrayList<>();

        if(dayPos == 0)
        {
            foodList.add(new Food("Bún Bò", R.drawable.bunbo));
            foodList.add(new Food("Bánh Canh", R.drawable.banhcanh));
            foodList.add(new Food("Bánh Khọt", R.drawable.banhkhot));
        }

        else if(dayPos == 1){
            foodList.add(new Food("Bánh Mì", R.drawable.banhmi));
            foodList.add(new Food("Bánh Xèo", R.drawable.banhxeo));
        }
        else{
            foodList.add(new Food("Cơm Tấm", R.drawable.comtam));
            foodList.add(new Food("Phở", R.drawable.pho));
        }


        return foodList;

    }


    private void creatTabs() {
        //tab for breakfast
        TabHost.TabSpec tabBreakfast;
        tabBreakfast = tabHost.newTabSpec("tabBreakfast");
        tabBreakfast.setContent(R.id.tab1);
        tabBreakfast.setIndicator("Bữa sáng");
        tabHost.addTab(tabBreakfast);
        //tab for lunch
        TabHost.TabSpec tabLunch;
        tabLunch = tabHost.newTabSpec("tabLunch");
        tabLunch.setContent(R.id.tab2);
        tabLunch.setIndicator("Bữa trưa");
        tabHost.addTab(tabLunch);
        //tab for dinner
        TabHost.TabSpec tabDinner;
        tabDinner = tabHost.newTabSpec("tabDinner");
        tabDinner.setContent(R.id.tab3);
        tabDinner.setIndicator("Bữa tối");
        tabHost.addTab(tabDinner);


    }


}