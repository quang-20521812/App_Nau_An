package com.example.cookingapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookingapp.MainActivity;
import com.example.cookingapp.Model.Food;
import com.example.cookingapp.R;
import com.google.firebase.database.core.Context;

import java.util.List;

public class Adapter_SelectedFood extends RecyclerView.Adapter<Adapter_SelectedFood.FoodViewHolder> {

    private MainActivity aContext;
    private List<Food> foodList;

    public Adapter_SelectedFood(MainActivity context) {
        this.aContext = context;
    }

    public void setFoodList(List<Food> a){
        this.foodList = a;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.selected_food, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Food food = foodList.get(position);
        if (food == null)
            return;
        holder.imgFood.setImageResource(food.getResourceID());
        holder.nameFood.setText(food.getFoodName());
    }

    @Override
    public int getItemCount() {
        if(foodList != null)
            return foodList.size();
        return 0;
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgFood;
        private TextView nameFood;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);

            imgFood = itemView.findViewById(R.id.imageFood);
            nameFood = itemView.findViewById(R.id.nameFood);
        }
    }
}
