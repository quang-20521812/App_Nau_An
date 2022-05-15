package com.example.cookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
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


//        rcv_selectedFood = findViewById(R.id.rcv_selectedFood);
//        adapter_selectedFood = new Adapter_SelectedFood(this);
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
//        rcv_selectedFood.setLayoutManager(linearLayoutManager);
//
//        adapter_selectedFood.setFoodList(getFoodList());
//        rcv_selectedFood.setAdapter(adapter_selectedFood);

    }

//    private List<Food> getFoodList() {
//        List<Food> foodList = new ArrayList<>();
//
//        foodList.add(new Food("Bún Bò", R.drawable.bunbo));
//        foodList.add(new Food("Bánh Canh", R.drawable.banhcanh));
//        foodList.add(new Food("Bánh Khọt", R.drawable.banhkhot));
//        foodList.add(new Food("Bánh Mì", R.drawable.banhmi));
//        foodList.add(new Food("Bánh Xèo", R.drawable.banhxeo));
//        foodList.add(new Food("Cơm Tấm", R.drawable.comtam));
//        foodList.add(new Food("Phở", R.drawable.pho));
//
//        return foodList;
//
//    }

    private void setupBottomNavigationView() {
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav);

        FragmentMeoHay fragmentMeoHay = new FragmentMeoHay();
        FragmentTab fragmentTab = new FragmentTab();
        FragmentMainPage fragmentMainPage = new FragmentMainPage(0);
        getSupportFragmentManager().beginTransaction().replace(R.id.NavHost, fragmentTab).commit();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drawer_nav_menu, menu);
        return true;
    }

    private void setupDrawerNavigationView() {
        navigationView = findViewById(R.id.NavView);
        NavController navController = Navigation.findNavController(this, R.id.NavHost);
        NavigationUI.setupWithNavController(navigationView, navController);


        navigationView.getMenu().getItem(0).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                return true;
            }
        });

        navigationView.getMenu().getItem(1).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(getApplicationContext(), ActivityNavigationFood.class);
                intent.putExtra("title", "Món ăn yêu thích");
                startActivity(intent);
                return true;
            }
        });

        navigationView.getMenu().getItem(2).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(getApplicationContext(), ActivityNavigationFood.class);
                intent.putExtra("title", "Hôm nay thiên hạ ăn gì!");
                startActivity(intent);
                return true;
            }
        });

        navigationView.getMenu().getItem(3).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(getApplicationContext(), ActivityNavigationFood.class);
                intent.putExtra("title", "Công thức nhà mình");
                startActivity(intent);
                return true;
            }
        });

        navigationView.getMenu().getItem(4).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(getApplicationContext(), ActivityNavigationFood.class);
                intent.putExtra("title", "Món ăn đơn giản");
                startActivity(intent);
                return true;
            }
        });

        navigationView.getMenu().getItem(5).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(getApplicationContext(), ActivityNavigationFood.class);
                intent.putExtra("title", "Món ăn tiết kiệm");
                startActivity(intent);
                return true;
            }
        });

        navigationView.getMenu().getItem(6).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(getApplicationContext(), ActivityNavigationFood.class);
                intent.putExtra("title", "Món ăn vặt");
                startActivity(intent);
                return true;
            }
        });

        navigationView.getMenu().getItem(7).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(getApplicationContext(), ActivityNavigationFood.class);
                intent.putExtra("title", "Món ăn ngày lễ");
                startActivity(intent);
                return true;
            }
        });

        navigationView.getMenu().getItem(8).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(getApplicationContext(), ActivityNavigationFood.class);
                intent.putExtra("title", "Món ăn chay");
                startActivity(intent);
                return true;
            }
        });

        navigationView.getMenu().getItem(9).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(getApplicationContext(), ActivityNavigationFood.class);
                intent.putExtra("title", "Món ăn giảm cân");
                startActivity(intent);
                return true;
            }
        });

        navigationView.getMenu().getItem(10).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(getApplicationContext(), ActivityNavigationFood.class);
                intent.putExtra("title", "Món ăn bánh ngon");
                startActivity(intent);
                return true;
            }
        });

        navigationView.getMenu().getItem(11).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(getApplicationContext(), ActivityNavigationFood.class);
                intent.putExtra("title", "Món nhậu");
                startActivity(intent);
                return true;
            }
        });

        navigationView.getMenu().getItem(12).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(getApplicationContext(), SignUpScreen.class);
                startActivity(intent);
                return true;
            }
        });
    }



}