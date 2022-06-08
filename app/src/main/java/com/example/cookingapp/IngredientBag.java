package com.example.cookingapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
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
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class IngredientBag extends Fragment {

    ArrayList<String> listFoodID = new ArrayList<>();
    String username;
    FirebaseFirestore firestore;
    AdapterShoppingBag adapterShoppingBag;
    ShoppingBag shoppingBag;
    ListView listView;
    public IngredientBag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ingredient_bag, container, false);

        retrieveUsername();

        listView = v.findViewById(R.id.listIng);
        shoppingBag = new ShoppingBag();

        getListFood();


        return v;
    }

    private void retrieveUsername() {
        username = ((MainActivity) getActivity()).getUsername();
    }
    //Get chosen food list of current user
    private void getListFood() {
        listFoodID.clear();
        firestore = FirebaseFirestore.getInstance();
        CollectionReference getListFoodID = firestore
                .collection("User")
                .document(username)
                .collection("MenuFood");
        getListFoodID.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            List<String> groupFood;

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot document : task.getResult()) {
                    groupFood = (List<String>) document.get("sang");
                    for (int i = 0; i < groupFood.size(); i++) {
                        if (groupFood.get(i) != "null")
                            listFoodID.add(groupFood.get(i));
                    }
                    groupFood = (List<String>) document.get("trua");
                    for (int i = 0; i < groupFood.size(); i++) {
                        if (groupFood.get(i) != "null")
                            listFoodID.add(groupFood.get(i));
                    }
                    groupFood = (List<String>) document.get("toi");
                    for (int i = 0; i < groupFood.size(); i++) {
                        if (groupFood.get(i) != "null")
                            listFoodID.add(groupFood.get(i));
                    }
                }
                getIngerdients(listFoodID);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
    //Get list ingredient from firestore
    private void getIngerdients(ArrayList<String> listFoodID){
        firestore = FirebaseFirestore.getInstance();
        for(int i = 0; i < listFoodID.size(); i++){
            ArrayList<Ingredient> ingredientTempArrayList = new ArrayList<>();
            firestore.collection("Food").document(listFoodID.get(i))
                    .collection("ingredients")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Map<String, Object> ingredients = document.getData();
                                    ingredientTempArrayList.add(new Ingredient(document.getId(),
                                            ingredients.get("ingName").toString(),
                                            ingredients.get("ingUnit").toString(),
                                            Integer.parseInt(ingredients.get("ingQuantity").toString())));
                                }
                                shoppingBag.addFood(ingredientTempArrayList);
                                updateListView();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
        }
    }

    private void updateListView() {
        adapterShoppingBag = new AdapterShoppingBag(shoppingBag);
        listView.setAdapter(adapterShoppingBag);
        adapterShoppingBag.notifyDataSetChanged();
    }
}