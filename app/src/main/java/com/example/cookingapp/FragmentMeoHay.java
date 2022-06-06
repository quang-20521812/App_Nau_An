package com.example.cookingapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cookingapp.Adapter.AdapterMeoHay;
import com.example.cookingapp.Model.Tips;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentMeoHay#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMeoHay extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentMeoHay() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentMeoHay.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentMeoHay newInstance(String param1, String param2) {
        FragmentMeoHay fragment = new FragmentMeoHay();
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

    private RecyclerView recyclerViewMain;
    private AdapterMeoHay adapterMain;
    private ArrayList<Tips> listTips;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewMain = inflater.inflate(R.layout.fragment_meo_hay, container, false);

        recyclerViewMain = viewMain.findViewById(R.id.recyclerViewMeoHay);
        adapterMain = new AdapterMeoHay(this.getContext());
        recyclerViewMain.setAdapter(adapterMain);

        listTips = new ArrayList<Tips>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerViewMain.setLayoutManager(linearLayoutManager);

        getListItem();

        return viewMain;
    }

    private void getListItem() {
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        // Get list tips from database
        firebaseFirestore.collection("Tips").get().addOnCompleteListener(task -> {
            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                Tips tips = new Tips(documentSnapshot.getString("tipTitle"), documentSnapshot.getString("tipDescription"), documentSnapshot.getString("tipURL"));
                listTips.add(tips);
                adapterMain.setData(listTips);
            }
        }).addOnFailureListener(e -> Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show());
    }
}