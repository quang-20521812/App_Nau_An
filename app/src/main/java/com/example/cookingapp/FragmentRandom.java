package com.example.cookingapp;

import static android.content.ContentValues.TAG;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cookingapp.Adapter.AdapterRandomFood;
import com.example.cookingapp.Model.Food;
import com.example.cookingapp.Model.Ingredient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FragmentRandom extends Fragment implements AdapterRandomFood.OnItemClickListener{

    private ViewPager2 viewPager2;
    private ArrayList<Food> randomFoodList;
    private FirebaseFirestore firestore;
    private ArrayList<String> foodKeyList;
    String username;

    public FragmentRandom() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        randomFoodList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_random, container, false);

        retrieveUsername();

        viewPager2 = view.findViewById(R.id.viewPagerImageSlider);

        retrieveFood();

        return view;
    }

    private void retrieveUsername() {
        username = ((MainActivity) getActivity()).getUsername();
    }

    private void updateView(){
        viewPager2.setAdapter(new AdapterRandomFood(randomFoodList, viewPager2, this));

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);
    }

    private void retrieveFood(){
        firestore = FirebaseFirestore.getInstance();
        foodKeyList = new ArrayList<String>();

        firestore.collection("Food")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Random random = new Random();
                                int rand = random.nextInt(2);
                                if (rand == 1 && randomFoodList.size() < 5) {
                                    foodKeyList.add(document.getId());
                                    //Ingredients arraylist
                                    ArrayList<Ingredient> ingredientTempArrayList = new ArrayList<>();
                                    //CookingSteps
                                    ArrayList<String> cookingSteps = new ArrayList<String>();
                                    cookingSteps = (ArrayList<String>) document.get("cookingSteps");
                                    //Image
                                    String foodURL = document.getString("foodURL");
                                    //AddToFoodList
                                    randomFoodList.add(new Food(document.getId(),
                                            document.getString("foodName"),
                                            document.getString("foodCate"),
                                            ingredientTempArrayList,
                                            cookingSteps,
                                            foodURL));
                                }
                            }
                            updateView();
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    @Override
    public void OnItemClick(int position) {
        Intent intent = new Intent(getContext(), ActitvityFoodDetail.class);
        Food food = (Food) randomFoodList.get(viewPager2.getCurrentItem());
        intent.putExtra("foodKey", food.getFoodKey());
        intent.putExtra("foodName", food.getFoodName());
        intent.putExtra("foodCate", food.getFoodCate());
        intent.putExtra("cookingSteps", food.getCookingSteps());
        intent.putExtra("foodURL", food.getFoodURL());
        intent.putExtra("username", username);
        startActivity(intent);
    }
}