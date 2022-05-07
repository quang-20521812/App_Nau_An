package com.example.cookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityMeoHay extends AppCompatActivity {
    private int resourceID;
    private String title, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meo_hay);

        ImageView imageViewMain = (ImageView) findViewById(R.id.imageViewActivityMeoHay);
        TextView textViewTitleMain = (TextView) findViewById(R.id.textViewTitleActivityMeoHay);
        TextView textViewDesMain = (TextView) findViewById(R.id.textViewDesActivityMeoHay);

        Intent intent = getIntent();
        resourceID = intent.getIntExtra("key_Image", 0);
        title = intent.getStringExtra("key_Title");
        description = intent.getStringExtra("key_Description");

        imageViewMain.setImageResource(resourceID);
        textViewTitleMain.setText(title);
        textViewDesMain.setText(description);
    }
}