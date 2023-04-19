package com.example.lastproject.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.lastproject.HelperClasses.CategoriesAdapter;
import com.example.lastproject.HelperClasses.CategoriesHelperClass;
import com.example.lastproject.HelperClasses.FeaturedAdpater;
import com.example.lastproject.HelperClasses.FeaturedHelperClass;
import com.example.lastproject.HelperClasses.MostViewedAdpater;
import com.example.lastproject.HelperClasses.MostViewedHelperClass;
import com.example.lastproject.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;


import java.util.ArrayList;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static final float END_SCALE = 0.7f;
    //private FirebaseAuth firebaseAuth;
    RecyclerView featuredRecycler;
    RecyclerView.Adapter adapter;
    ImageView menuIcon;
    LinearLayout contentView;
    RecyclerView mostViewedRecycler, categoriesRecycler;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        featuredRecycler = findViewById(R.id.featured_recycler);
        menuIcon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.content);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        mostViewedRecycler = findViewById(R.id.most_viewed);
        categoriesRecycler = findViewById(R.id.most_categories);
        featuredRecycler();
        mostViewedRecycler();
        categoriesRecycler();

        navigationDrawer();

        featuredRecycler();

    }

    public void callRetailerScreens(View view){
        startActivity(new Intent(getApplicationContext(), UserDashboard.class));
    }
    private void navigationDrawer() {

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);

                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        animateNavigationDrawer();
    }

    private void animateNavigationDrawer() {
        drawerLayout.setScrimColor(getResources().getColor(R.color.colorPrimary));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);


                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }

        });
    }


    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);

        }else

            super.onBackPressed();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_all_categories:
                startActivity( new Intent (getApplicationContext(),AllCategories.class));
                break;
        }
        switch (item.getItemId()){
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity( new Intent (getApplicationContext(),RetailerStartUpScreen.class));
                break;
        }
        switch (item.getItemId()){
            case R.id.nav_home:
                startActivity( new Intent (getApplicationContext(),UserDashboard.class));
                break;
        }
        switch (item.getItemId()){
            case R.id.nav_profile:
                startActivity( new Intent (getApplicationContext(),Profile.class));
                break;
        }
        return true;
    }

    private void categoriesRecycler() {
        categoriesRecycler.setHasFixedSize(true);
        categoriesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<CategoriesHelperClass> CategoriesLocations = new ArrayList<>();
        CategoriesLocations.add(new CategoriesHelperClass(R.drawable.a, "McDonald's"));
        CategoriesLocations.add(new CategoriesHelperClass(R.drawable.petshop, "Edenrobe"));
        CategoriesLocations.add(new CategoriesHelperClass(R.drawable.petshop, "J."));
        CategoriesLocations.add(new CategoriesHelperClass(R.drawable.petshop, "Walmart"));
        adapter = new CategoriesAdapter(CategoriesLocations);
        categoriesRecycler.setAdapter(adapter);
    }
    private void mostViewedRecycler() {
        mostViewedRecycler.setHasFixedSize(true);
        mostViewedRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<MostViewedHelperClass> mostViewedLocations = new ArrayList<>();
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.petshop, "McDonald's"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.petshop, "Edenrobe"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.petshop, "J."));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.petshop, "Walmart"));
        adapter = new MostViewedAdpater(mostViewedLocations);
        mostViewedRecycler.setAdapter(adapter);
    }
    private void featuredRecycler() {
        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();
        featuredLocations.add(new FeaturedHelperClass(R.drawable.petshop, "Mcdonald's", "asbkd asudhlasn saudnas jasdjasl hisajdl asjdlnas"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.petshop, "Edenrobe", "asbkd asudhlasn saudnas jasdjasl hisajdl asjdlnas"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.petshop, "Walmart", "asbkd asudhlasn saudnas jasdjasl hisajdl asjdlnas"));
        adapter = new FeaturedAdpater(featuredLocations);
        featuredRecycler.setAdapter(adapter);
    }


    public void callAnim(View view) {
        startActivity( new Intent (getApplicationContext(),Pets.class));
    }

    public void callPet(View view) {
        startActivity( new Intent (getApplicationContext(),PetShop.class));
    }

    public void callVet(View view) {
        startActivity( new Intent (getApplicationContext(),Vet.class));
    }

    public void callMaps(View view) {

        startActivity( new Intent (getApplicationContext(),UserDashboard.class));
    }
}



