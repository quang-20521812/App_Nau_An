package com.example.cookingapp.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cookingapp.FragmentMainPage;

public class AdapterForTablayout extends FragmentStateAdapter {
    public AdapterForTablayout(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        return new FragmentMainPage(position);
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
