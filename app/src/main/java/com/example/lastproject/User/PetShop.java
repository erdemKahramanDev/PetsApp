package com.example.lastproject.User;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.lastproject.R;

public class PetShop extends AppCompatActivity {
ImageView backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_shop);

        backBtn = findViewById(R.id.back_pressed4);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PetShop.super.onBackPressed();
            }
        });
    }
}