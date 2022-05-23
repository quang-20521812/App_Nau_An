package com.example.cookingapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.cookingapp.Adapter.AdapterShoppingBag;
import com.example.cookingapp.Model.Food;
import com.example.cookingapp.Model.Ingredient;
import com.example.cookingapp.Model.ShoppingBag;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IngredientBag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IngredientBag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public IngredientBag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IngredientBag.
     */
    // TODO: Rename and change types and number of parameters
    public static IngredientBag newInstance(String param1, String param2) {
        IngredientBag fragment = new IngredientBag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ingredient_bag, container, false);

        ArrayList<Ingredient> list1 = new ArrayList<>();
        ArrayList<Ingredient> list2 = new ArrayList<>();

        ArrayList<String> string = new ArrayList<>();
        list1.add(new Ingredient("beef","Bo","g",200));
        list1.add(new Ingredient("veg","Rau","bo",1));
        list1.add(new Ingredient("salt","Muoi","muong",3));
        list1.add(new Ingredient("pork","Heo","g",300));

        list2.add(new Ingredient("beef","Bo","g",700));
        list2.add(new Ingredient("salt","Muoi","muong",7));
        list2.add(new Ingredient("sugar","Duong","muong",2));

        Food f1 = new Food("f1","Bo Heo","", list1,string,0);
        Food f2 = new Food("f2","Bo Heo","", list1,string,0);


        ListView listView = v.findViewById(R.id.listIng);
        ShoppingBag shoppingBag = new ShoppingBag();
        shoppingBag.addFood(f1);
        shoppingBag.addFood(f2);
        AdapterShoppingBag adapterShoppingBag = new AdapterShoppingBag(shoppingBag);
        listView.setAdapter(adapterShoppingBag);
        adapterShoppingBag.notifyDataSetChanged();

        return v;
    }
}