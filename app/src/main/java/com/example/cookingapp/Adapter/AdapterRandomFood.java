package com.example.cookingapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.cookingapp.Model.Food;
import com.example.cookingapp.R;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class AdapterRandomFood extends RecyclerView.Adapter<AdapterRandomFood.RandomFoodViewHolder> {
    private List<Food> randomFoodList;
    private ViewPager2 viewPager2;

    public AdapterRandomFood(List<Food> randomFoodList, ViewPager2 viewPager2) {
        this.randomFoodList = randomFoodList;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public RandomFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RandomFoodViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.card,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RandomFoodViewHolder holder, int position) {
        holder.setImage(randomFoodList.get(position));
    }

    @Override
    public int getItemCount() {
        return randomFoodList.size();
    }

    class RandomFoodViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgView;
        private TextView txtRandom;

        RandomFoodViewHolder(@NonNull View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.imgRandom);
            txtRandom = itemView.findViewById(R.id.txtRandomName);
        }

        void setImage(Food randomFood){
            imgView.setImageResource(randomFood.getResourceID());
            txtRandom.setText(randomFood.getFoodName());
        }
    }
}