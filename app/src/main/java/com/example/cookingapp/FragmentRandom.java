package com.example.cookingapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cookingapp.Adapter.AdapterRandomFood;
import com.example.cookingapp.Model.Food;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

public class FragmentRandom extends Fragment {

    private ViewPager2 viewPager2;
    private List<Food> randomFoodList;
    private MaterialCardView materialCardView;

    public FragmentRandom() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        randomFoodList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_random, container, false);

        viewPager2 = view.findViewById(R.id.viewPagerImageSlider);
        materialCardView = view.findViewById(R.id.materialCardView);

        randomFoodList = RandomFood();


        viewPager2.setAdapter(new AdapterRandomFood(randomFoodList, viewPager2));

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);

        return view;
    }

    public List<Food> RandomFood(){
        randomFoodList.clear();

        randomFoodList.add(new Food("Bánh Canh", R.drawable.banhcanh));
        randomFoodList.add(new Food("Bánh Khọt", R.drawable.banhkhot));
        randomFoodList.add(new Food("Bánh Mì", R.drawable.banhmi));
        randomFoodList.add(new Food("Bánh Xèo", R.drawable.banhxeo));
        randomFoodList.add(new Food("Bún Bò", R.drawable.bunbo));
        randomFoodList.add(new Food("Cơm Tấm", R.drawable.comtam));
        randomFoodList.add(new Food("Phở", R.drawable.pho));

        return randomFoodList;
    }
}