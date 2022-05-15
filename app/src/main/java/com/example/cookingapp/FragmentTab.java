package com.example.cookingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cookingapp.Adapter.AdapterForTablayout;
import com.example.cookingapp.Adapter.AdapterViewPager;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class FragmentTab extends Fragment {

    TabLayout tabLayoutDay;
    ViewPager2 viewPagerDay;
    AdapterForTablayout adapter;

    public FragmentTab() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tab, container, false);

        tabLayoutDay = v.findViewById(R.id.tabLayoutDay);
        viewPagerDay = v.findViewById(R.id.viewPagerTab);

        adapter = new AdapterForTablayout(this.getActivity());
        viewPagerDay.setAdapter(adapter);

        new TabLayoutMediator(tabLayoutDay, viewPagerDay, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("Hôm nay");
                        break;
                    case 1:
                        tab.setText("Ngày mai");
                        break;
                    case 2:
                        tab.setText("Ngày kia");
                        break;
                }
            }
        }).attach();

        return v;
    }


}