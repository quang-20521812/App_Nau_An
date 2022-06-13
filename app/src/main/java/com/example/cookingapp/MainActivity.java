package com.example.cookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;
    FirebaseFirestore firestore;
    String username;
    String fullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Hôm nay ăn gì");

        retrieveUsername();

        setupDrawerNavigationView();

        setupBottomNavigationView();
    }

    private void retrieveUsername() {
        //get username from intent
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
    }

    public String getUsername(){
        return username;
    }

    private void setupBottomNavigationView() {
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav);
        //Setup fragments on MainActivity
        FragmentMeoHay fragmentMeoHay = new FragmentMeoHay();
        FragmentTab fragmentTab = new FragmentTab();
        FragmentRandom fragmentRandom = new FragmentRandom();
        IngredientBag fragmentIngredient = new IngredientBag();
        getSupportFragmentManager().beginTransaction().replace(R.id.NavHost, fragmentTab).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.FrameRandomFood, fragmentRandom).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.home_bot_nav:{
                        (findViewById(R.id.FrameRandomFood)).setVisibility(View.VISIBLE);
                        (findViewById(R.id.tvRandomFood)).setVisibility(View.VISIBLE);
                        getSupportFragmentManager().beginTransaction().replace(R.id.NavHost, fragmentTab).commit();
                        break;
                    }
                    case R.id.shopping_bot_nav:{
                        (findViewById(R.id.FrameRandomFood)).setVisibility(View.INVISIBLE);
                        (findViewById(R.id.tvRandomFood)).setVisibility(View.INVISIBLE);
                        getSupportFragmentManager().beginTransaction().replace(R.id.NavHost, fragmentIngredient).commit();
                        break;
                    }
                    case R.id.tips_bot_nav:{
                        (findViewById(R.id.FrameRandomFood)).setVisibility(View.INVISIBLE);
                        (findViewById(R.id.tvRandomFood)).setVisibility(View.INVISIBLE);
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

        firestore = FirebaseFirestore.getInstance();
        //Get full name from FireStore with username
        firestore
                .collection("User")
                .document(username)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                fullName = document.getString("Name");
                                View headerView = navigationView.getHeaderView(0);
                                ((TextView) headerView.findViewById(R.id.tvNavName)).setText(fullName);
                                ((TextView) headerView.findViewById(R.id.tvNavUsername)).setText(username);
                            }
                        }
                    }
                });

    //Menu item click event
        navigationView.getMenu().getItem(0).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(MainActivity.this, ActivitySearching.class);
                intent.putExtra("username", username);
                startActivity(intent);
                return true;
            }
        });

        navigationView.getMenu().getItem(1).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(getApplicationContext(), ActivityNavigationFood.class);
                intent.putExtra("foodCate", "all");
                intent.putExtra("username", username);
                startActivity(intent);
                return true;
            }
        });

        navigationView.getMenu().getItem(2).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(getApplicationContext(), ActivityNavigationFood.class);
                intent.putExtra("foodCate", "simple");
                intent.putExtra("username", username);
                startActivity(intent);
                return true;
            }
        });

        navigationView.getMenu().getItem(3).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(getApplicationContext(), ActivityNavigationFood.class);
                intent.putExtra("foodCate", "saving");
                intent.putExtra("username", username);
                startActivity(intent);
                return true;
            }
        });

        navigationView.getMenu().getItem(4).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(getApplicationContext(), ActivityNavigationFood.class);
                intent.putExtra("foodCate", "snack");
                intent.putExtra("username", username);
                startActivity(intent);
                return true;
            }
        });

        navigationView.getMenu().getItem(5).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(getApplicationContext(), ActivityNavigationFood.class);
                intent.putExtra("foodCate", "veg");
                intent.putExtra("username", username);
                startActivity(intent);
                return true;
            }
        });

        navigationView.getMenu().getItem(6).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(getApplicationContext(), ActivityNavigationFood.class);
                intent.putExtra("foodCate", "healthy");
                intent.putExtra("username", username);
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