package com.example.cookingapp;

import static android.content.ContentValues.TAG;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cookingapp.Adapter.AdapterFoodDetail_CookingSteps;
import com.example.cookingapp.Adapter.AdapterFoodDetail_Ingredients;
import com.example.cookingapp.Model.Food;
import com.example.cookingapp.Model.Ingredient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Map;

public class ActitvityFoodDetail extends AppCompatActivity {

    FirebaseFirestore firestore;
    TabHost tabHost;
    AdapterFoodDetail_Ingredients adapterFoodDetailIngredients;
    AdapterFoodDetail_CookingSteps adapterFoodDetail_cookingSteps;
    ListView lvIngredient;
    ListView lvCookingStep;
    Button btnBack;
    Button btnAdd;
    Food food;
    Boolean isDelete;
    String day;
    String time;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        retrieveUsername();

        retrieveFood();

        setupView();

        setupTabHost();

    }

    private void retrieveUsername() {
        // Get username
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
    }

    private void retrieveIngredientsList(String foodKey) {
        firestore = FirebaseFirestore.getInstance();

        //Get Ingredients List from FireStore
        ArrayList<Ingredient> ingredientArrayList = new ArrayList<>();
            firestore.collection("Food").document(foodKey)
                    .collection("ingredients")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Map<String, Object> ingredients = document.getData();
                                    ingredientArrayList.add(new Ingredient(document.getId(),
                                            ingredients.get("ingName").toString(),
                                            ingredients.get("ingUnit").toString(),
                                            Integer.parseInt(ingredients.get("ingQuantity").toString())));
                                }
                                //Set IngredientsList for Food
                                food.setIngredients(ingredientArrayList);
                                //Setup view
                                setupTabIngredient();
                                setuptabCookingStep();
                            } else {
                                Log.d(TAG, "Error getting documents: ", task.getException());
                            }
                        }
                    });

    }

    private void retrieveFood() {

        Button btnDel = findViewById(R.id.btnAddToMenu);
        //Get Food from intent
        Intent intent = getIntent();
        food = new Food();
        food.setFoodKey(intent.getStringExtra("foodKey"));
        food.setFoodName(intent.getStringExtra("foodName"));
        food.setFoodCate(intent.getStringExtra("foodCate"));
        food.setCookingSteps(intent.getStringArrayListExtra("cookingSteps"));
        food.setfoodURL(intent.getStringExtra("foodURL"));

        isDelete = intent.getBooleanExtra("isDelete", false);
        if (isDelete){
            btnDel.setText("Xóa khỏi thực đơn");
            time = intent.getStringExtra("time");

            int getDay = intent.getIntExtra("day", -1);

            if (getDay == 0){
                day = "hom_nay";
            }
            else if (getDay == 1){
                day = "ngay_mai";
            }
            else if (getDay == 2){
                day = "ngay_kia";
            }


        }

        Picasso.get().load(food.getFoodURL()).into((ImageView) findViewById(R.id.imageViewFoodDetail));
        retrieveIngredientsList(food.getFoodKey());
    }

    private void setupView() {
        btnBack = findViewById(R.id.btnBack);
        btnAdd = findViewById(R.id.btnAddToMenu);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isDelete){
                    //Get FoodKey
                    Intent intent = getIntent();
                    String foodKey = intent.getStringExtra("foodKey");

                    openConfirmDialog(Gravity.CENTER, foodKey);
                }
                else{
                    openOptionDialog(Gravity.CENTER);
                }
            }
        });
    }

    private void openConfirmDialog(int gravity, String foodKey) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.confirm_dialog_layout);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }

        //Set attribute for Dialog
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        dialog.setCancelable(true);

        //Setup view
        Button btnConfirm = dialog.findViewById(R.id.btnConfirmDelete);
        Button btnCancel = dialog.findViewById(R.id.btnCancelDelete);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firestore = FirebaseFirestore.getInstance();

                firestore
                        .collection("User")
                        .document(username)
                        .collection("MenuFood")
                        .document(day)
                        .update(time, FieldValue.arrayRemove(foodKey));


                dialog.dismiss();
                Intent reloadMainActivity = new Intent(ActitvityFoodDetail.this, MainActivity.class);
                reloadMainActivity.putExtra("username", username);
                startActivity(reloadMainActivity);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        //Show dialog
        dialog.show();
    }

    public void openOptionDialog(int gravity) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.option_dialog_layout);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }

        //Setup Attribute for dialog
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        dialog.setCancelable(true);

        // Setup View Dialog
        Spinner spnCategoryDay = (Spinner) dialog.findViewById(R.id.spnCategoryDay);
        Spinner spnCategoryTime = (Spinner) dialog.findViewById(R.id.spnCategoryTime);
        Button btnConfirm = (Button) dialog.findViewById(R.id.btnConfirmAddToMenu);
        Button btnCancle = (Button) dialog.findViewById(R.id.btnCancelAddToMenu);

        ArrayList<String> listDay = new ArrayList<String>();
        listDay.add("Hôm nay");
        listDay.add("Ngày mai");
        listDay.add("Ngày kia");
        ArrayAdapter<String> adapterDay = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listDay);
        adapterDay.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spnCategoryDay.setAdapter(adapterDay);

        ArrayList<String> listTime = new ArrayList<String>();
        listTime.add("Buổi sáng");
        listTime.add("Buổi trưa");
        listTime.add("Buổi tối");

        ArrayAdapter<String> adapterTime = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listTime);
        adapterTime.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spnCategoryTime.setAdapter(adapterTime);

        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                String foodKey = intent.getStringExtra("foodKey");
                String day = spnCategoryDay.getSelectedItem().toString();
                String time = spnCategoryTime.getSelectedItem().toString();

                Intent reloadMainActivity = new Intent(ActitvityFoodDetail.this, MainActivity.class);
                reloadMainActivity.putExtra("username", username);
                startActivity(reloadMainActivity);

                FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

                if (day.equals("Hôm nay")) {

                    if (time.equals("Buổi sáng")) {

                        firebaseFirestore.collection("User").document(username).collection("MenuFood")
                                .document("hom_nay").update("sang", FieldValue.arrayUnion(foodKey));

                    }
                    else if (time.equals("Buổi trưa")) {

                        firebaseFirestore.collection("User").document(username).collection("MenuFood")
                                .document("hom_nay").update("trua", FieldValue.arrayUnion(foodKey));

                    }
                    else if (time.equals("Buổi tối")) {

                        firebaseFirestore.collection("User").document(username).collection("MenuFood")
                                .document("hom_nay").update("toi", FieldValue.arrayUnion(foodKey));

                    }

                }
                else if (day.equals("Ngày mai")) {

                    if (time.equals("Buổi sáng")) {

                        firebaseFirestore.collection("User").document(username).collection("MenuFood")
                                .document("ngay_mai").update("sang", FieldValue.arrayUnion(foodKey));

                    }
                    else if (time.equals("Buổi trưa")) {

                        firebaseFirestore.collection("User").document(username).collection("MenuFood")
                                .document("ngay_mai").update("trua", FieldValue.arrayUnion(foodKey));

                    }
                    else if (time.equals("Buổi tối")) {

                        firebaseFirestore.collection("User").document(username).collection("MenuFood")
                                .document("ngay_mai").update("toi", FieldValue.arrayUnion(foodKey));

                    }

                }
                else if (day.equals("Ngày kia")) {

                    if (time.equals("Buổi sáng")) {

                        firebaseFirestore.collection("User").document(username).collection("MenuFood")
                                .document("ngay_kia").update("sang", FieldValue.arrayUnion(foodKey));

                    }
                    else if (time.equals("Buổi trưa")) {

                        firebaseFirestore.collection("User").document(username).collection("MenuFood")
                                .document("ngay_kia").update("trua", FieldValue.arrayUnion(foodKey));

                    }
                    else if (time.equals("Buổi tối")) {

                        firebaseFirestore.collection("User").document(username).collection("MenuFood")
                                .document("ngay_kia").update("toi", FieldValue.arrayUnion(foodKey));
                    }
                }
                //Close dialog
                dialog.dismiss();
            }
        });

        //Show dialog
        dialog.show();
    }

    private void setuptabCookingStep() {

        adapterFoodDetail_cookingSteps = new AdapterFoodDetail_CookingSteps(food.getCookingSteps());

        lvCookingStep = (ListView) findViewById(R.id.listViewCookingSteps);

        lvCookingStep.setAdapter(adapterFoodDetail_cookingSteps);
    }

    private void setupTabIngredient() {

        adapterFoodDetailIngredients = new AdapterFoodDetail_Ingredients(food.getIngredients());

        lvIngredient = (ListView) findViewById(R.id.listViewIngredient);

        lvIngredient.setAdapter(adapterFoodDetailIngredients);
    }


    private void setupTabHost() {
        tabHost = findViewById(R.id.tabHost);
        tabHost.setup();
        createTab();
    }

    private void createTab() {
        //Set up tab Nguyên liệu
        TabHost.TabSpec tabIngSpec;
        tabIngSpec = tabHost.newTabSpec("tabIngredients");
        tabIngSpec.setContent(R.id.tab1);
        tabIngSpec.setIndicator("Nguyên liệu");
        tabHost.addTab(tabIngSpec);

        //Set up tab Chế biến
        TabHost.TabSpec tabCookingStepsSpec;
        tabCookingStepsSpec = tabHost.newTabSpec("tabCookingSteps");
        tabCookingStepsSpec.setContent(R.id.tab2);
        tabCookingStepsSpec.setIndicator("Chế biến");
        tabHost.addTab(tabCookingStepsSpec);
    }
}
