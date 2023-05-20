package com.example.lastproject.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lastproject.Admin.MyAdapter;
import com.example.lastproject.R;
import com.example.lastproject.User.DataClass;
import com.example.lastproject.User.Detail;
import com.example.lastproject.User.UserDashboard;

import java.util.ArrayList;
import java.util.List;

public class Connect extends AppCompatActivity {

    Button nah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        nah = findViewById(R.id.nah);

    }
}




