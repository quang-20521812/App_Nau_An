package com.example.cookingapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.cookingapp.Adapter.AdapterSearching_Hint;
import com.example.cookingapp.Adapter.AdapterSearching_Item;
import com.example.cookingapp.Model.Tips;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentSearching#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSearching extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentSearching() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentSearching.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentSearching newInstance(String param1, String param2) {
        FragmentSearching fragment = new FragmentSearching();
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

    private androidx.recyclerview.widget.RecyclerView recyclerViewSearchingHint;
    private AdapterSearching_Hint adapterSearching_hint;
    private GridView gridViewSearchingItem;
    private AdapterSearching_Item adapterSearching_item;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewMain = inflater.inflate(R.layout.fragment_searching, container, false);

        recyclerViewSearchingHint = viewMain.findViewById(R.id.recyclerViewSearchingHint);
        adapterSearching_hint = new AdapterSearching_Hint(this.getContext());
        recyclerViewSearchingHint.setAdapter(adapterSearching_hint);

        //gridViewSearchingItem = viewMain.findViewById(R.id.gridViewSearchingItem);
        //adapterSearching_item = new AdapterSearching_Item(this.getContext());
        //gridViewSearchingItem.setAdapter(adapterSearching_item);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), androidx.recyclerview.widget.RecyclerView.HORIZONTAL, false);
        recyclerViewSearchingHint.setLayoutManager(linearLayoutManager);

        adapterSearching_hint.setData(getListSearchingHint());
        //adapterSearching_item.setData(getListSearchingItem());

        return viewMain;
    }

    private List<String> getListSearchingHint() {
        List<String> listHints = new ArrayList<String>();

        listHints.add("Món chay");
        listHints.add("Món xào");
        listHints.add("Món canh");
        listHints.add("Món chiên");
        listHints.add("Món mặn");

        return listHints;
    }

    private List<Tips> getListSearchingItem() {
        List<Tips> listFoods = new ArrayList<Tips>();

        listFoods.add(new Tips(R.drawable.ic_launcher_background, "Món 1", "Mô tả 1"));
        listFoods.add(new Tips(R.drawable.ic_launcher_background, "Món 2", "Mô tả 2"));
        listFoods.add(new Tips(R.drawable.ic_launcher_background, "Món 3", "Mô tả 3"));
        listFoods.add(new Tips(R.drawable.ic_launcher_background, "Món 4", "Mô tả 4"));
        listFoods.add(new Tips(R.drawable.ic_launcher_background, "Món 5", "Mô tả 5"));
        listFoods.add(new Tips(R.drawable.ic_launcher_background, "Món 6", "Mô tả 6"));

        return listFoods;
    }
}