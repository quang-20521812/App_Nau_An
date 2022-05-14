package com.example.cookingapp.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cookingapp.FragmentFoodPage1;
import com.example.cookingapp.FragmentFoodPage2;
import com.example.cookingapp.FragmentFoodPage3;

public class AdapterViewPager extends FragmentStateAdapter {
    public AdapterViewPager(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new FragmentFoodPage1();
            case 1:
                return new FragmentFoodPage2();
            default:
                return new FragmentFoodPage3();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
