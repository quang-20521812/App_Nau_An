package com.example.cookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ActivityMeoHay extends AppCompatActivity {
    //private int resourceID;
    private String title, description, url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meo_hay);

        ImageView imageViewMain = (ImageView) findViewById(R.id.imageViewActivityMeoHay);
        TextView textViewTitleMain = (TextView) findViewById(R.id.textViewTitleActivityMeoHay);
        TextView textViewDesMain = (TextView) findViewById(R.id.textViewDesActivityMeoHay);

        Intent intent = getIntent();
        title = intent.getStringExtra("tipTitle");
        description = intent.getStringExtra("tipDescription");
        url = intent.getStringExtra("tipURL");

        textViewTitleMain.setText(title);
        textViewDesMain.setText(description);
        Picasso.get().load(url).into(imageViewMain);
    }
}