package com.example.cookingapp.LandingPage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cookingapp.Adapter.ViewPagerAdapter;
import com.example.cookingapp.R;
import com.google.android.material.tabs.TabLayout;

public class MainPage extends Fragment {

    TabLayout tabLayout ;
    ViewPager2 viewPager;
    ViewPagerAdapter viewPagerAdapter;

    public MainPage() {
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
        return inflater.inflate(R.layout.fragment_main_page, container, false);
    }
}