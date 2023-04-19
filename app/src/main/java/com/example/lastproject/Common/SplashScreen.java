package com.example.lastproject.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lastproject.R;
import com.example.lastproject.User.Login;
import com.example.lastproject.User.RetailerStartUpScreen;
import com.example.lastproject.User.UserDashboard;
import com.google.firebase.auth.FirebaseAuth;


public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIMER = 5000;
    private FirebaseAuth firebaseAuth;

    ImageView backgroundImage;
    TextView poweredByLine;

    Animation sideAnim, bottomAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        firebaseAuth = FirebaseAuth.getInstance();

        backgroundImage = findViewById(R.id.backgroundimage);
        poweredByLine = findViewById(R.id.powerederdem);

        sideAnim = AnimationUtils.loadAnimation(this,R.anim.side_anim);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_anim);

        backgroundImage.setAnimation(sideAnim);
        poweredByLine.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (firebaseAuth.getCurrentUser() != null) {
                    startActivity(new Intent(SplashScreen.this, UserDashboard.class));
                    finish();
                } else {
                    startActivity(new Intent(SplashScreen.this, Login.class));
                    finish();
                }
            }
        },SPLASH_TIMER);

    }
}