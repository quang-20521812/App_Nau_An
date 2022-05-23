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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ActitvityFoodDetail extends AppCompatActivity {

    FirebaseFirestore firestore;
    TabHost tabHost;
    ArrayList<Ingredient> ingredientList;
    String[] cookingSteps;
    AdapterFoodDetail_Ingredients adapterFoodDetailIngredients;
    AdapterFoodDetail_CookingSteps adapterFoodDetail_cookingSteps;
    ListView lvIngredient;
    ListView lvCookingStep;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        setupView();

        setupTabHost();

        setupTabIngredient();

        setuptabCookingStep();
    }

    private void setupView() {
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setuptabCookingStep() {

        ArrayList<String> cookingSteps = new ArrayList<>();
        cookingSteps.add("Đầu cá hồi làm sạch, chặt làm 4 để ráo. Cà chua thái múi. Thơm(dứa) xắt lát. Măng chua để ráo.");
        cookingSteps.add("Tẳm ướp đầu cá hồi với muối, đường, hạt nêm, hành tím, bột nghệ. Sau đó, bắc chảo lên.");

        adapterFoodDetail_cookingSteps = new AdapterFoodDetail_CookingSteps(cookingSteps);

        lvCookingStep = (ListView) findViewById(R.id.listViewCookingSteps);

        lvCookingStep.setAdapter(adapterFoodDetail_cookingSteps);
    }

    private void setupTabIngredient() {

//        test();



        ingredientList = new ArrayList<Ingredient>();

        ingredientList.add(new Ingredient("beef", "Thịt bò", "g", 300));
        ingredientList.add(new Ingredient("beef", "Thịt bò", "g", 300));
        ingredientList.add(new Ingredient("beef", "Thịt bò", "g", 300));
        ingredientList.add(new Ingredient("beef", "Thịt bò", "muỗng cà phê", 300));
        ingredientList.add(new Ingredient("beef", "Thịt bò", "g", 300));
        ingredientList.add(new Ingredient("beef", "Thịt bò", "g", 300));
        ingredientList.add(new Ingredient("beef", "Thịt bò", "g", 300));
        ingredientList.add(new Ingredient("beef", "Thịt bò", "muỗng cà phê", 300));
        ingredientList.add(new Ingredient("beef", "Thịt bò", "g", 300));
        ingredientList.add(new Ingredient("beef", "Thịt bò", "g", 300));
        ingredientList.add(new Ingredient("beef", "Thịt bò", "g", 300));
        ingredientList.add(new Ingredient("beef", "Thịt bò", "muỗng cà phê", 300));





        adapterFoodDetailIngredients = new AdapterFoodDetail_Ingredients(ingredientList);

        lvIngredient = (ListView) findViewById(R.id.listViewIngredient);

        lvIngredient.setAdapter(adapterFoodDetailIngredients);
    }

    private void test() {
        firestore = FirebaseFirestore.getInstance();
        CollectionReference test = firestore.collection("test");
        String selectedFood = "canh_soup_cu_den_nau_thit";
        ArrayList<Ingredient> ingredientArrayList = new ArrayList<>();

        firestore.collection("Food").document(selectedFood)
                .collection("ingredients")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> ingredients = document.getData();
                                ingredientArrayList.add(new Ingredient( document.getId().toString(),
                                                        ingredients.get("ingName").toString(),
                                                        ingredients.get("ingUnit").toString(),
                                                        Integer.parseInt(ingredients.get("ingQuantity").toString())));
                            }
                            Toast.makeText(getApplicationContext(), ingredientArrayList.get(1).getIngName(), Toast.LENGTH_LONG).show();
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
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
