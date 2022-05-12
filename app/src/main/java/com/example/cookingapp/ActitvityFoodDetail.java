package com.example.cookingapp;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TabHost;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cookingapp.Adapter.AdapterFoodDetail_CookingSteps;
import com.example.cookingapp.Adapter.AdapterFoodDetail_Ingredients;
import com.example.cookingapp.Model.Ingredient;

import java.util.ArrayList;

public class ActitvityFoodDetail extends AppCompatActivity {

    TabHost tabHost;
    ArrayList<Ingredient> ingredientList;
    String[] cookingSteps;
    AdapterFoodDetail_Ingredients adapterFoodDetailIngredients;
    AdapterFoodDetail_CookingSteps adapterFoodDetail_cookingSteps;
    ListView lvIngredient;
    ListView lvCookingStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        setupTabHost();

        setupTabIngredient();

        setuptabCookingStep();
    }

    private void setuptabCookingStep() {

        cookingSteps = new String[] {
                "Đầu cá hồi làm sạch, chặt làm 4 để ráo. Cà chua thái múi. Thơm(dứa) xắt lát. Măng chua để ráo.",
                "Tẳm ướp đầu cá hồi với muối, đường, hạt nêm, hành tím, bột nghệ. Sau đó, bắc chảo lên."
        };

        adapterFoodDetail_cookingSteps = new AdapterFoodDetail_CookingSteps(cookingSteps);

        lvCookingStep = (ListView) findViewById(R.id.listViewCookingSteps);

        lvCookingStep.setAdapter(adapterFoodDetail_cookingSteps);
    }

    private void setupTabIngredient() {

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
