package com.example.cookingapp;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cookingapp.Adapter.AdapterFoodDetail_CookingSteps;
import com.example.cookingapp.Adapter.AdapterFoodDetail_Ingredients;
import com.example.cookingapp.Model.Food;
import com.example.cookingapp.Model.Ingredient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class ActitvityFoodDetail extends AppCompatActivity {

    FirebaseFirestore firestore;
    TabHost tabHost;
    AdapterFoodDetail_Ingredients adapterFoodDetailIngredients;
    AdapterFoodDetail_CookingSteps adapterFoodDetail_cookingSteps;
    ListView lvIngredient;
    ListView lvCookingStep;
    Button btnBack;
    Food food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        retriveFood();

        setupView();

        setupTabHost();

    }

    private void retriveIngredientsList(String foodKey) {
        firestore = FirebaseFirestore.getInstance();

        ArrayList<Ingredient> ingredientArrayList = new ArrayList<>();
        firestore.collection("Food").document(foodKey)
                .collection("ingredients")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> ingredients = document.getData();
                                ingredientArrayList.add(new Ingredient(document.getId(),
                                        ingredients.get("ingName").toString(),
                                        ingredients.get("ingUnit").toString(),
                                        Integer.parseInt(ingredients.get("ingQuantity").toString())));
                            }
                            food.setIngredients(ingredientArrayList);
                            setupTabIngredient();
                            setuptabCookingStep();
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

    }

    private void retriveFood() {
        Intent intent = getIntent();
        food = new Food();
        food.setFoodKey(intent.getStringExtra("foodKey"));
        food.setFoodName(intent.getStringExtra("foodName"));
        food.setFoodCate(intent.getStringExtra("foodCate"));
        food.setCookingSteps(intent.getStringArrayListExtra("cookingSteps"));
        retriveIngredientsList(food.getFoodKey());
    }

    private void setupView() {
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setuptabCookingStep() {

        adapterFoodDetail_cookingSteps = new AdapterFoodDetail_CookingSteps(food.getCookingSteps());

        lvCookingStep = (ListView) findViewById(R.id.listViewCookingSteps);

        lvCookingStep.setAdapter(adapterFoodDetail_cookingSteps);
    }

    private void setupTabIngredient() {

        adapterFoodDetailIngredients = new AdapterFoodDetail_Ingredients(food.getIngredients());

        lvIngredient = (ListView) findViewById(R.id.listViewIngredient);

        lvIngredient.setAdapter(adapterFoodDetailIngredients);
    }


    private void setupTabHost() {
        tabHost = findViewById(R.id.tabHost);
        tabHost.setup();
        createTab();
    }

    private void createTab() {
        //Set up tab Nguyên liệu
        TabHost.TabSpec tabIngSpec;
        tabIngSpec = tabHost.newTabSpec("tabIngredients");
        tabIngSpec.setContent(R.id.tab1);
        tabIngSpec.setIndicator("Nguyên liệu");
        tabHost.addTab(tabIngSpec);

        //Set up tab Chế biến
        TabHost.TabSpec tabCookingStepsSpec;
        tabCookingStepsSpec = tabHost.newTabSpec("tabCookingSteps");
        tabCookingStepsSpec.setContent(R.id.tab2);
        tabCookingStepsSpec.setIndicator("Chế biến");
        tabHost.addTab(tabCookingStepsSpec);
    }
}
