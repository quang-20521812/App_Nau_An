package com.example.cookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavHost;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("HÔM NAY ĂN GÌ?");

        setupDrawerNavigationView();

        setupBottomNavigationView();
    }


    private void setupBottomNavigationView() {
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav);

        FragmentMeoHay fragmentMeoHay = new FragmentMeoHay();
        FragmentTab fragmentTab = new FragmentTab();
        FragmentRandom fragmentRandom = new FragmentRandom();
        IngredientBag fragmentIngredient = new IngredientBag();
        getSupportFragmentManager().beginTransaction().replace(R.id.NavHost, fragmentTab).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.firstFrame, fragmentRandom).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.home_bot_nav:{
                        getSupportFragmentManager().beginTransaction().replace(R.id.NavHost, fragmentTab).commit();
                        break;
                    }
                    case R.id.shopping_bot_nav:{
                        getSupportFragmentManager().beginTransaction().replace(R.id.NavHost, fragmentIngredient).commit();
                        break;
                    }
                    case R.id.tips_bot_nav:{
                        getSupportFragmentManager().beginTransaction().replace(R.id.NavHost, fragmentMeoHay).commit();
                        break;
                    }
                }
                return true;
            }
        });
    }


    private void setupDrawerNavigationView() {
        navigationView = findViewById(R.id.NavView);
        NavController navController = Navigation.findNavController(this, R.id.NavHost);
        NavigationUI.setupWithNavController(navigationView, navController);


        navigationView.getMenu().getItem(0).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(MainActivity.this, ActivitySearching.class));
                return true;
            }
        });

        navigationView.getMenu().getItem(1).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(getApplicationContext(), ActivityNavigationFood.class);
                intent.putExtra("foodCate", "all");
                startActivity(intent);
                return true;
            }
        });

        navigationView.getMenu().getItem(2).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(getApplicationContext(), ActivityNavigationFood.class);
                intent.putExtra("foodCate", "simple");
                startActivity(intent);
                return true;
            }
        });

        navigationView.getMenu().getItem(3).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(getApplicationContext(), ActivityNavigationFood.class);
                intent.putExtra("foodCate", "saving");
                startActivity(intent);
                return true;
            }
        });

        navigationView.getMenu().getItem(4).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(getApplicationContext(), ActivityNavigationFood.class);
                intent.putExtra("foodCate", "snack");
                startActivity(intent);
                return true;
            }
        });

        navigationView.getMenu().getItem(5).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(getApplicationContext(), ActivityNavigationFood.class);
                intent.putExtra("foodCate", "veg");
                startActivity(intent);
                return true;
            }
        });

        navigationView.getMenu().getItem(6).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(getApplicationContext(), ActivityNavigationFood.class);
                intent.putExtra("foodCate", "healthy");
                startActivity(intent);
                return true;
            }
        });


        navigationView.getMenu().getItem(7).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(getApplicationContext(), SignInScreen.class);
                startActivity(intent);
                return true;
            }
        });
    }



}