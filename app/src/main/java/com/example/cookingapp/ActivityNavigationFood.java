package com.example.cookingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cookingapp.Adapter.AdapterNavigationFood;
import com.example.cookingapp.Model.Food;
import com.example.cookingapp.Model.Ingredient;

import java.util.ArrayList;

public class ActivityNavigationFood extends AppCompatActivity {

    ListView lvFesFood;
    ArrayList<Food> foodArrayList;
    AdapterNavigationFood adapterNavigationFood;
    Button btnBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_food);

        setupView();

        setupListView();

        retriveData();

    }

    private void setupView() {
        lvFesFood = (ListView) findViewById(R.id.lvFesFood);
        btnBack = (Button) findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupListView() {
        foodArrayList = new ArrayList<Food>();
        AddFoodtoArrayList(foodArrayList);
        adapterNavigationFood = new AdapterNavigationFood(foodArrayList);
        lvFesFood.setAdapter(adapterNavigationFood);
    }

    private void retriveData() {
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        ((TextView) findViewById(R.id.tvTitle)).setText(title);
    }

    private void AddFoodtoArrayList(ArrayList foodArrayList) {
        ArrayList<Ingredient> ingredientArrayList = new ArrayList<Ingredient>();

        ingredientArrayList.add(new Ingredient("beef", "Thịt bò", "g", 300));
        ingredientArrayList.add(new Ingredient("beef", "Thịt bò", "g", 300));
        ingredientArrayList.add(new Ingredient("beef", "Thịt bò", "g", 300));
        ingredientArrayList.add(new Ingredient("beef", "Thịt bò", "muỗng cà phê", 300));

        ArrayList<String> cookingSteps = new ArrayList<>();
        cookingSteps.add("Đầu cá hồi làm sạch, chặt làm 4 để ráo. Cà chua thái múi. Thơm(dứa) xắt lát. Măng chua để ráo.");
        cookingSteps.add("Tẳm ướp đầu cá hồi với muối, đường, hạt nêm, hành tím, bột nghệ. Sau đó, bắc chảo lên.");


        Food food = new Food("abc", "dsd", "asda", ingredientArrayList, cookingSteps, 3);

        foodArrayList.add(food);
    }
}
