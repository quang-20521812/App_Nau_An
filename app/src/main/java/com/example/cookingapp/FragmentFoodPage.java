package com.example.cookingapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentFoodPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentFoodPage extends Fragment  {

    int tabMealPos, tabDayPos;

    public FragmentFoodPage(int tabDayPos,int tabMealPos) {
        this.tabDayPos = tabDayPos;
        this.tabMealPos = tabMealPos;
    }


    // TODO: Rename and change types and number of parameters
    public static FragmentFoodPage newInstance(int tabMealPos, int tabDayPos) {
        FragmentFoodPage fragment = new FragmentFoodPage(tabDayPos,tabMealPos);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_food_page, container, false);


        return v;
    }



}