package com.example.cookingapp;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;

import com.example.cookingapp.Adapter.Adapter_SelectedFood;
import com.example.cookingapp.Model.Food;
import com.example.cookingapp.Model.Ingredient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class FragmentMainPage extends Fragment implements Adapter_SelectedFood.OnClickListener {

    int tabDayPos;
    TabHost tabHost;
    Adapter_SelectedFood adapter_selectedFood;
    RecyclerView rcv_selectedFoodTabMorning, rcv_selectedFoodTabNoon, rcv_selectedFoodTabEvening;
    FirebaseFirestore firestore;
    ArrayList<String> listFoodIDMorning;
    ArrayList<String> listFoodIDNoon;
    ArrayList<String> listFoodIDEvening;
    ArrayList<Food> listFoodItemMorning;
    ArrayList<Food> listFoodItemNoon;
    ArrayList<Food> listFoodItemEvening;
    String username = "anhquan";
    public FragmentMainPage(int tabDayPos) {
        this.tabDayPos = tabDayPos;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getListFoodID(tabDayPos, "sang");
        getListFoodID(tabDayPos, "trua");
        getListFoodID(tabDayPos, "toi");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main_page, container, false);

        rcv_selectedFoodTabMorning = v.findViewById(R.id.rcv_selectedFoodTabMorning);
        rcv_selectedFoodTabNoon = v.findViewById(R.id.rcv_selectedFoodTabNoon);
        rcv_selectedFoodTabEvening = v.findViewById(R.id.rcv_selectedFoodTabEvening);
        tabHost = v.findViewById(R.id.tabHost);
        adapter_selectedFood = new Adapter_SelectedFood();

        tabHost.setup();
        createTabs();

        setupTabBreakfast();
        setupTabLunch();
        setupTabDinner();

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                switch (s){
                    case "tabBreakfast":
                        setupTabBreakfast();
                        break;
                    case "tabLunch":
                        setupTabLunch();
                        break;
                    case "tabDinner":
                        setupTabDinner();
                        break;
                }
            }
        });
        return v;
    }

    private void setupTabBreakfast() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        rcv_selectedFoodTabMorning.setLayoutManager(linearLayoutManager);

        adapter_selectedFood.setFoodList(listFoodItemMorning, this, "sang");
        rcv_selectedFoodTabMorning.setAdapter(adapter_selectedFood);
    }

    private void setupTabLunch() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        rcv_selectedFoodTabNoon.setLayoutManager(linearLayoutManager);

        adapter_selectedFood.setFoodList(listFoodItemNoon, this, "trua");
        rcv_selectedFoodTabNoon.setAdapter(adapter_selectedFood);
    }

    private void setupTabDinner() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        rcv_selectedFoodTabEvening.setLayoutManager(linearLayoutManager);

        adapter_selectedFood.setFoodList(listFoodItemEvening, this, "toi");
        rcv_selectedFoodTabEvening.setAdapter(adapter_selectedFood);
    }

    private void createTabs() {
        //tab for breakfast
        TabHost.TabSpec tabBreakfast;
        tabBreakfast = tabHost.newTabSpec("tabBreakfast");
        tabBreakfast.setContent(R.id.tab1);
        tabBreakfast.setIndicator("Bữa sáng");
        tabHost.addTab(tabBreakfast);
        //tab for lunch
        TabHost.TabSpec tabLunch;
        tabLunch = tabHost.newTabSpec("tabLunch");
        tabLunch.setContent(R.id.tab2);
        tabLunch.setIndicator("Bữa trưa");
        tabHost.addTab(tabLunch);
        //tab for dinner
        TabHost.TabSpec tabDinner;
        tabDinner = tabHost.newTabSpec("tabDinner");
        tabDinner.setContent(R.id.tab3);
        tabDinner.setIndicator("Bữa tối");
        tabHost.addTab(tabDinner);
    }

    private void getListFoodID(int tabDayPos, String time) {
        firestore = FirebaseFirestore.getInstance();

        listFoodIDMorning = new ArrayList<>();
        listFoodIDNoon = new ArrayList<>();
        listFoodIDEvening = new ArrayList<>();

        String day = "";
        if (tabDayPos == 0)
            day = "hom_nay";
        else if (tabDayPos == 1)
            day = "ngay_mai";
        else
            day = "ngay_kia";

        DocumentReference getListFoodID = firestore
                .collection("User")
                .document(username)
                .collection("MenuFood")
                .document(day);
        getListFoodID.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        listFoodIDMorning = (ArrayList<String>) document.get("sang");
                        listFoodIDNoon = (ArrayList<String>) document.get("trua");
                        listFoodIDEvening = (ArrayList<String>) document.get("toi");
                        if (time == "sang") {
                            getListFoodItem(listFoodIDMorning, time);
                        }
                        else if (time == "trua"){
                            getListFoodItem(listFoodIDNoon, time);
                        }
                        else {
                            getListFoodItem(listFoodIDEvening, time);
                        }
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                        Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }

    private void getListFoodItem(ArrayList<String> listFoodID, String time) {

        if (time == "sang") {
            listFoodItemMorning = new ArrayList<>();
        }
        else if (time == "trua"){
            listFoodItemNoon = new ArrayList<>();
        }
        else {
            listFoodItemEvening = new ArrayList<>();
        }

        for (String ID : listFoodID) {
            DocumentReference documentReference = firestore
                    .collection("Food")
                    .document(ID);

                    documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    //CookingSteps
                                    ArrayList<String> cookingSteps = (ArrayList<String>) document.get("cookingSteps");
                                    //AddToFoodList
                                    if (time == "sang") {
                                        listFoodItemMorning.add(new Food(document.getId(),
                                                document.getString("foodName"),
                                                document.getString("foodCate"),
                                                new ArrayList<Ingredient>(),
                                                cookingSteps,
                                                document.getString("foodURL")));
                                        setupTabBreakfast();
                                    }
                                    else if (time == "trua"){
                                        listFoodItemNoon.add(new Food(document.getId(),
                                                document.getString("foodName"),
                                                document.getString("foodCate"),
                                                new ArrayList<Ingredient>(),
                                                cookingSteps,
                                                document.getString("foodURL")));
                                        setupTabLunch();
                                    }
                                    else {
                                        listFoodItemEvening.add(new Food(document.getId(),
                                                document.getString("foodName"),
                                                document.getString("foodCate"),
                                                new ArrayList<Ingredient>(),
                                                cookingSteps,
                                                document.getString("foodURL")));
                                        setupTabLunch();
                                    }
                                } else {
                                    Log.d(TAG, "No such document");
                                }
                            } else {
                                Log.d(TAG, "get failed with ", task.getException());
                            }
                        }
                    });
        }
    }


    @Override
    public void onClickListener(ArrayList<Food> foodArrayList, int pos, Adapter_SelectedFood adapter_selectedFood) {
        Intent intent = new Intent(getContext(), ActitvityFoodDetail.class);
        Food food = adapter_selectedFood.getItem(pos);
        intent.putExtra("foodKey", food.getFoodKey());
        intent.putExtra("foodName", food.getFoodName());
        intent.putExtra("foodCate", food.getFoodCate());
        intent.putExtra("cookingSteps", food.getCookingSteps());
        intent.putExtra("foodURL", food.getFoodURL());
        intent.putExtra("isDelete", true);
        intent.putExtra("day", tabDayPos);
        intent.putExtra("time", adapter_selectedFood.getTime());
        startActivity(intent);
    }
}