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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Adapter_SelectedFood extends RecyclerView.Adapter<Adapter_SelectedFood.FoodViewHolder>{

    private ArrayList<Food> foodList;
    private OnClickListener onClickListener;
    private String time;


    public void setFoodList(ArrayList<Food> a, OnClickListener onClickListener, String time){
        this.foodList = a;
        this.onClickListener = onClickListener;
        this.time = time;
        notifyDataSetChanged();
    }

    public String getTime(){
        return time;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.selected_food, parent, false);
        return new FoodViewHolder(view, onClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Food food = foodList.get(position);
        if (food == null)
            return;
        holder.setImage(foodList.get(position));
        holder.nameFood.setText(food.getFoodName());
    }

    @Override
    public int getItemCount() {
        if(foodList != null)
            return foodList.size();
        return 0;
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView imgFood;
        private TextView nameFood;
        OnClickListener onClickListener;


        public FoodViewHolder(@NonNull View itemView, OnClickListener onClickListener) {
            super(itemView);

            imgFood = itemView.findViewById(R.id.imageFood);
            nameFood = itemView.findViewById(R.id.nameFood);
            this.onClickListener = onClickListener;

            itemView.setOnClickListener(this);
        }

        void setImage(Food food){
            Picasso.get().load(food.getFoodURL()).into(imgFood);
        }

        @Override
        public void onClick(View view) {
            onClickListener.onClickListener(foodList, getAdapterPosition(), Adapter_SelectedFood.this);
        }
    }

    public Food getItem (int pos){
        return foodList.get(pos);
    }

    public interface OnClickListener{
        void onClickListener(ArrayList<Food> foodArrayList, int pos, Adapter_SelectedFood adapter_selectedFood);
    }
}
