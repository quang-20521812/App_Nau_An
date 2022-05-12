package com.example.cookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.cookingapp.Adapter.Adapter_SelectedFood;
import com.example.cookingapp.Model.Food;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;

    private Adapter_SelectedFood adapter_selectedFood;
    private RecyclerView rcv_selectedFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("HÔM NAY ĂN GÌ?");

        setupDrawerNavigationView();

        setupBottomNavigationView();

        rcv_selectedFood = findViewById(R.id.rcv_selectedFood);
        adapter_selectedFood = new Adapter_SelectedFood(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        rcv_selectedFood.setLayoutManager(linearLayoutManager);

        adapter_selectedFood.setFoodList(getFoodList());
        rcv_selectedFood.setAdapter(adapter_selectedFood);

        rcv_selectedFood.setClipToPadding(false);
        rcv_selectedFood.setClipChildren(false);
    }

    private List<Food> getFoodList() {
        List<Food> foodList = new ArrayList<>();

        foodList.add(new Food("Bún Bò", R.drawable.bunbo));
        foodList.add(new Food("Bánh Canh", R.drawable.banhcanh));
        foodList.add(new Food("Bánh Khọt", R.drawable.banhkhot));
        foodList.add(new Food("Bánh Mì", R.drawable.banhmi));
        foodList.add(new Food("Bánh Xèo", R.drawable.banhxeo));
        foodList.add(new Food("Cơm Tấm", R.drawable.comtam));
        foodList.add(new Food("Phở", R.drawable.pho));

        return foodList;

    }

    private void setupBottomNavigationView() {
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav);

        FragmentMeoHay fragmentMeoHay = new FragmentMeoHay();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.home_bot_nav:{
                        break;
                    }
                    case R.id.shopping_bot_nav:{
                        break;
                    }
                    case R.id.tips_bot_nav:{
                        getSupportFragmentManager().beginTransaction().replace(R.id.NavHost, fragmentMeoHay).commit();
                        break;
                    }
                    default:{
                        break;
                    }
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drawer_nav_menu, menu);
        return true;
    }

    private void setupDrawerNavigationView() {
        navigationView = findViewById(R.id.NavView);
        NavController navController = Navigation.findNavController(this, R.id.NavHost);
        NavigationUI.setupWithNavController(navigationView, navController);
    }


}