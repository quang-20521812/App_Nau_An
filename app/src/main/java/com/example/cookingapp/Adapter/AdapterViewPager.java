package com.example.cookingapp.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cookingapp.FragmentFoodPage;
import com.google.android.material.tabs.TabLayout;

public class AdapterViewPager extends FragmentStateAdapter {
    public AdapterViewPager(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new FragmentFoodPage(0,position);
    }


    @Override
    public int getItemCount() {
        return 3;
    }
}
