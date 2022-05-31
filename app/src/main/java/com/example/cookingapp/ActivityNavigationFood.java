package com.example.cookingapp;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cookingapp.Adapter.AdapterNavigationFood;
import com.example.cookingapp.Model.Food;
import com.example.cookingapp.Model.Ingredient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class ActivityNavigationFood extends AppCompatActivity {

    ListView lvFood;
    ArrayList<Food> foodArrayList = new ArrayList<>();
    AdapterNavigationFood adapterNavigationFood;
    Button btnBack;
    TextView tvTitle;
    String foodCate;
    FirebaseFirestore firestore;
    ArrayList<String> foodKeyList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_food);

        setupView();

        retriveFoodCate_FoodKey();

    }

    private void setupView() {
        lvFood = (ListView) findViewById(R.id.lvFesFood);
        btnBack = (Button) findViewById(R.id.btnBack);
        tvTitle = (TextView) findViewById(R.id.tvTitle);



        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        lvFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ActivityNavigationFood.this, ActitvityFoodDetail.class);
                Food food = (Food) lvFood.getItemAtPosition(i);
                intent.putExtra("foodKey", food.getFoodKey());
                intent.putExtra("foodName", food.getFoodName());
                intent.putExtra("foodCate", food.getFoodCate());
                intent.putExtra("cookingSteps", food.getCookingSteps());
                intent.putExtra("foodURL", food.getFoodURL());
                startActivity(intent);
            }
        });
    }

    private void updateListView() {
        adapterNavigationFood = new AdapterNavigationFood(foodArrayList);
        lvFood.setAdapter(adapterNavigationFood);
    }

    private void retriveFoodCate_FoodKey() {
        Intent intent = getIntent();
        foodCate = intent.getStringExtra("foodCate");

        switch (foodCate) {
            case "simple": {
                tvTitle.setText("Các món ăn đơn giản");
                getFoodKey(foodCate);
                break;
            }
            case "saving": {
                tvTitle.setText("Các món ăn tiết kiệm");
                getFoodKey(foodCate);
                break;
            }
            case "snack": {
                tvTitle.setText("Các món ăn vặt");
                getFoodKey(foodCate);
                break;
            }
            case "veg": {
                tvTitle.setText("Các món chay");
                getFoodKey(foodCate);
                break;
            }
            case "healthy": {
                tvTitle.setText("Các món ăn giảm cân");
                getFoodKey(foodCate);
                break;
            }
            default: {
                firestore = FirebaseFirestore.getInstance();
                tvTitle.setText("Hôm nay thiên hạ ăn gì!");

                firestore.collection("Food")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        foodKeyList.add(document.getId());
                                        //Ingredients arraylist
                                        ArrayList<Ingredient> ingredientTempArrayList = new ArrayList<>();
                                        //CookingSteps
                                        ArrayList<String> cookingSteps = (ArrayList<String>) document.get("cookingSteps");
                                        //Image
                                        String foodURL = document.getString("foodURL");
                                        //AddToFoodList
                                        foodArrayList.add(new Food(document.getId(),
                                                document.getString("foodName"),
                                                document.getString("foodCate"),
                                                ingredientTempArrayList,
                                                cookingSteps,
                                                foodURL));
                                    }
                                    updateListView();
                                } else {
                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                }
                            }
                        });
            }
        }
    }

    private void getFoodKey(String foodCate) {
        firestore = FirebaseFirestore.getInstance();

        firestore.collection("Food")
                .whereEqualTo("foodCate", foodCate)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                foodKeyList.add(document.getId());
                                //Ingredients arraylist
                                ArrayList<Ingredient> ingredientTempArrayList = new ArrayList<>();
                                //CookingSteps
                                ArrayList<String> cookingSteps = (ArrayList<String>) document.get("cookingSteps");
                                //Image
                                String foodURL = document.getString("foodURL");
                                //AddToFoodList
                                foodArrayList.add(new Food(document.getId(),
                                        document.getString("foodName"),
                                        foodCate,
                                        ingredientTempArrayList,
                                        cookingSteps,
                                        foodURL));
                            }
                            updateListView();
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

    }
}
