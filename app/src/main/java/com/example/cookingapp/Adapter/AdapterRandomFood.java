package com.example.cookingapp.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.cookingapp.ActitvityFoodDetail;
import com.example.cookingapp.FragmentMainPage;
import com.example.cookingapp.FragmentRandom;
import com.example.cookingapp.MainActivity;
import com.example.cookingapp.Model.Food;
import com.example.cookingapp.R;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterRandomFood extends RecyclerView.Adapter<AdapterRandomFood.RandomFoodViewHolder> {
    private List<Food> randomFoodList;
    private ViewPager2 viewPager2;
    private OnItemClickListener mOnItemClickListener;

    public AdapterRandomFood(List<Food> randomFoodList, ViewPager2 viewPager2, OnItemClickListener onItemClickListener) {
        this.randomFoodList = randomFoodList;
        this.viewPager2 = viewPager2;
        this.mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public RandomFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RandomFoodViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false),
                        mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RandomFoodViewHolder holder, int position) {
        int data_pos = position;
        holder.setImage(randomFoodList.get(position));
    }


    @Override
    public int getItemCount() {
        return randomFoodList.size();
    }

    class RandomFoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView imgView;
        private TextView txtRandom;
        OnItemClickListener onItemClickListener;

        RandomFoodViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            imgView = itemView.findViewById(R.id.imgRandom);
            txtRandom = itemView.findViewById(R.id.txtRandomName);
            this.onItemClickListener = onItemClickListener;

            imgView.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        void setImage(Food randomFood){
            Picasso.get().load(randomFood.getFoodURL()).into(imgView);
            txtRandom.setText(randomFood.getFoodName());
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.OnItemClick(getAdapterPosition());
        }
    }

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }
}