package com.example.lastproject.User;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;

import com.example.lastproject.R;


public class RetailerStartUpScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_start_up_screen);
    }

    public void callLoginScreen (View View) {
        startActivity(new Intent(getApplicationContext(), Login.class));

    }

    public void callSignUpScreen (View View) {

        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));

    }



}