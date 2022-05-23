package com.example.cookingapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cookingapp.Adapter.AdapterShoppingBag;
import com.example.cookingapp.Model.Food;
import com.example.cookingapp.Model.Ingredient;
import com.example.cookingapp.Model.ShoppingBag;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class IngredientBag extends Fragment {

    ArrayList<String> listFoodID ;
    String username = "anhquan";
    FirebaseFirestore firestore;
    ArrayList<String> listFood = new ArrayList<String>() ;
    ArrayList<Ingredient> ingredients = new ArrayList<>();
    String[] string = new String[]{};
    ArrayList<Food> food = new ArrayList<>();
    AdapterShoppingBag adapterShoppingBag;
    ShoppingBag shoppingBag;
    ListView listView;
    public IngredientBag() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get foodList in bag

        List<String> listFoodID = new ArrayList<String>() ;


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ingredient_bag, container, false);

//        list1.add(new Ingredient("beef","Bo","g",200));
//        list1.add(new Ingredient("veg","Rau","bo",1));
//        list1.add(new Ingredient("salt","Muoi","muong",3));
//        list1.add(new Ingredient("pork","Heo","g",300));
//
//        list2.add(new Ingredient("beef","Bo","g",700));
//        list2.add(new Ingredient("sugar","Duong","muong",2));
//        list2.add(new Ingredient("veg","Rau","bo",1));
//        list2.add(new Ingredient("veg","Rau","bo",6));
//
//        f.add(new Food("f1","Bo Heo","", list1,string,0));

        listView = v.findViewById(R.id.listIng);
        shoppingBag = new ShoppingBag();
        adapterShoppingBag = new AdapterShoppingBag(shoppingBag);
        listView.setAdapter(adapterShoppingBag);
        getListFood();



        return v;
    }

    private void getListFood() {
        firestore = FirebaseFirestore.getInstance();
        CollectionReference getListFoodID = firestore.collection("User")
                .document(username).collection("MenuFood");
        getListFoodID.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            List<String> groupFood;

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot document : task.getResult()) {
                    groupFood = (List<String>) document.get("sang");
                    for (int i = 0; i < groupFood.size(); i++) {
                        listFoodID.add(groupFood.get(i));
                    }
                    groupFood = (List<String>) document.get("trua");
                    for (int i = 0; i < groupFood.size(); i++) {
                        listFoodID.add(groupFood.get(i));
                    }
                    groupFood = (List<String>) document.get("toi");
                    for (int i = 0; i < groupFood.size(); i++) {
                        listFoodID.add(groupFood.get(i));
                    }
                }
                getIngerdients();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
    private void getIngerdients(){
        for(int i = 0; i < listFoodID.size(); i++){
            firestore = FirebaseFirestore.getInstance();
            CollectionReference getIngredients = firestore.collection("Food")
                    .document(listFood.get(i).toString()).collection("ingredients");
            getIngredients.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot document : task.getResult()) {
                        Ingredient ingredient = new Ingredient(document.getId(),document.get("ingName").toString(),
                                document.get("ingUnit").toString(),(int)document.get("ingQuantity"));
                        ingredients.add(ingredient);
                    }
                    Food f = new Food("","","",ingredients,string,0);
                    shoppingBag.addFood(f);

                    adapterShoppingBag.notifyDataSetChanged();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
        }
    }
}