package com.example.cookingapp.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cookingapp.LandingPage.FoodPage1;
import com.example.cookingapp.LandingPage.FoodPage2;
import com.example.cookingapp.LandingPage.FoodPage3;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new FoodPage1();
            case 1:
                return new FoodPage2();
            default:
                return new FoodPage3();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
