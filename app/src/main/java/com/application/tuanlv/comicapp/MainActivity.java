package com.application.tuanlv.comicapp;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.application.tuanlv.comicapp.fragment.CategoriesFragment;
import com.application.tuanlv.comicapp.fragment.FavoritesFragment;
import com.application.tuanlv.comicapp.fragment.HomeFragment;
import com.application.tuanlv.comicapp.fragment.SettingsFragment;
import com.application.tuanlv.comicapp.view.StartActivity;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView mMainNav;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mAuth = FirebaseAuth.getInstance();
        updateUI();

        //set fragment by click navigation
        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        setFragment(HomeFragment.newInstance());
                        return true;
                    case R.id.nav_categories:
                        setFragment(CategoriesFragment.newInstance());
                        return true;
                    case R.id.nav_favorites:
                        setFragment(FavoritesFragment.newInstance());
                        return true;
                    case R.id.nav_settings:
                        setFragment(SettingsFragment.newInstance());
                        return true;
                    default: return false;
                }
            }
        });



    }

    private void updateUI() {
        if (mAuth.getCurrentUser() != null){
            Log.i("MainActivity", "fAuth != null");
        } else {
            Intent startIntent = new Intent(MainActivity.this, StartActivity.class);
            startActivity(startIntent);
            finish();
            Log.i("MainActivity", "fAuth == null");
        }
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_main, fragment);
        fragmentTransaction.commit();
    }

    private void initView() {
        mMainNav = findViewById(R.id.nav_main);
    }
}
