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
        switch (item.getItemId()){
            case R.id.nav_dog:
                startActivity( new Intent (getApplicationContext(),Pets.class));
                break;
        }
        switch (item.getItemId()){
            case R.id.nav_petshop1:
                startActivity( new Intent (getApplicationContext(),PetShop.class));
                break;
        }
        switch (item.getItemId()){
            case R.id.nav_vet:
                startActivity( new Intent (getApplicationContext(),Vet.class));
                break;
        }
        switch (item.getItemId()){
            case R.id.nav_map:
                startActivity( new Intent (getApplicationContext(),UserDashboard.class));
                break;
        }
        return true;
    }

    private void categoriesRecycler() {
        categoriesRecycler.setHasFixedSize(true);
        categoriesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<CategoriesHelperClass> CategoriesLocations = new ArrayList<>();
        CategoriesLocations.add(new CategoriesHelperClass(R.drawable.kara, "MİNİK"));
        CategoriesLocations.add(new CategoriesHelperClass(R.drawable.k2, "UYKULU"));
        CategoriesLocations.add(new CategoriesHelperClass(R.drawable.k3, "KARABAŞ"));
        CategoriesLocations.add(new CategoriesHelperClass(R.drawable.k4, "TEKİR"));
        CategoriesLocations.add(new CategoriesHelperClass(R.drawable.k5, "MAX"));
        CategoriesLocations.add(new CategoriesHelperClass(R.drawable.k6, "KRAL"));
        adapter = new CategoriesAdapter(CategoriesLocations);
        categoriesRecycler.setAdapter(adapter);
    }
    private void mostViewedRecycler() {
        mostViewedRecycler.setHasFixedSize(true);
        mostViewedRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<MostViewedHelperClass> mostViewedLocations = new ArrayList<>();
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.ve1, "VET.BÜŞRA KAZAN"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.ve2, "VET.JACK HOPE"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.ve3, "VET.HATİCE ÜN"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.ve4, "VET.MUSTAFA OK"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.ve5, "VET.MAGGIE"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.ve6, "VET.MARRY WILSON"));
        adapter = new MostViewedAdpater(mostViewedLocations);
        mostViewedRecycler.setAdapter(adapter);
    }
    private void featuredRecycler() {
        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();
        featuredLocations.add(new FeaturedHelperClass(R.drawable.pe1, "ÇOK SEVENLER", "Sizin değer verdiklerinize değer veriyoruz."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.pe2, "HALKALI", "Sizin sevdikleriniz için buradayız."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.pe3, "LACİVERT", "Güvende hissettirip mutlu olmak için buradayız."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.pe4, "ÇELİK", "İlk önceliğimiz sizsiniz."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.pe5, "VETRIO", "Her zaman sizinleyiz."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.pe6, "MAYWELL", "Bizi tercih etmeden önce değer verdiğiniz canlıları bir kez daha düşünün."));
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



