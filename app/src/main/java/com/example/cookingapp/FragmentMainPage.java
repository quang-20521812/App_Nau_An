package com.example.cookingapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cookingapp.Adapter.AdapterViewPager;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class FragmentMainPage extends Fragment {

    TabLayout  tabLayoutMeal;
    ViewPager2  viewPagerMeal;
    AdapterViewPager adapterViewPager;
    int tabDayPos;
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

        tabLayoutMeal = v.findViewById(R.id.tabLayoutMeal);
        viewPagerMeal = v.findViewById(R.id.viewPager);

        adapterViewPager = new AdapterViewPager(this.getActivity());
        viewPagerMeal.setAdapter(adapterViewPager);

        new TabLayoutMediator(tabLayoutMeal, viewPagerMeal, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("Bữa sáng");
                        break;
                    case 1:
                        tab.setText("Bữa trưa");
                        break;
                    case 2:
                        tab.setText("Bữa tối");
                        break;
                }
            }
        }).attach();

        return v;
    }

}