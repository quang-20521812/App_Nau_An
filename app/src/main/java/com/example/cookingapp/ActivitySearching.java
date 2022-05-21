package com.example.cookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.cookingapp.Adapter.AdapterSearching_Item;
import com.example.cookingapp.Model.Food;
import com.example.cookingapp.Model.Tips;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ActivitySearching extends AppCompatActivity {
    private androidx.appcompat.widget.SearchView searchViewMain;
    private GridView gridViewSearchingItem;
    private AdapterSearching_Item adapterSearching_item;
    private List<Food> listFoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching);

        searchViewMain = (androidx.appcompat.widget.SearchView) findViewById(R.id.searchViewMain);
        gridViewSearchingItem = (GridView) findViewById(R.id.gridViewSearchingItem);

        listFoods = new ArrayList<>();
        adapterSearching_item = new AdapterSearching_Item(listFoods, getApplicationContext());
        gridViewSearchingItem.setAdapter(adapterSearching_item);

        // Lấy toàn bộ danh sách món ăn
        searchListFoods(searchViewMain.getQuery().toString());

        searchViewMain.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchViewMain.clearFocus();
                searchListFoods(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchListFoods(newText);
                return false;
            }
        });

        gridViewSearchingItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Chuyển qua ActivityFoodDetail
            }
        });
    }

    public void searchListFoods(String query) {
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("Food").get()
                .addOnCompleteListener(task -> {
                    listFoods.clear();
                    adapterSearching_item.setData(listFoods);
                    for (DocumentSnapshot documentSnapshot: task.getResult().getDocuments()) {
                        if (documentSnapshot.getString("foodName").toLowerCase().contains(query.toLowerCase())) {
                            // Tạm thời là thế này, sẽ còn sửa sau
                            Food food = new Food(documentSnapshot.getString("foodName"), R.drawable.ic_launcher_background);
                            listFoods.add(food);
                            adapterSearching_item.setData(listFoods);
                        }
                    }
                }).addOnFailureListener(e -> Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show());
    }
}