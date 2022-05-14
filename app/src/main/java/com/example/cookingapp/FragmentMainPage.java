package com.example.cookingapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cookingapp.Adapter.AdapterViewPager;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class FragmentMainPage extends Fragment {

    TabLayout tabLayoutDay, tabLayoutMeal;
    ViewPager2 viewPagerDay, viewPagerMeal;
    AdapterViewPager adapterViewPager;
    String date;
    public FragmentMainPage() {
        // Required empty public constructor
    }


    public static FragmentMainPage newInstance(String param1, String param2) {
        FragmentMainPage fragment = new FragmentMainPage();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        DateFormat df = new SimpleDateFormat("dd.MM");
//        date = df.format(Calendar.getInstance().getTime());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main_page, container, false);

        tabLayoutDay = v.findViewById(R.id.tabLayoutDay);
        tabLayoutMeal = v.findViewById(R.id.tabLayoutMeal);
        viewPagerDay = v.findViewById(R.id.viewPager);
        viewPagerMeal = v.findViewById(R.id.viewPager);

        adapterViewPager = new AdapterViewPager(this.getActivity());
        viewPagerDay.setAdapter(adapterViewPager);

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