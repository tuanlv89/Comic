package com.application.tuanlv.comicapp;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    private FrameLayout mMainFrame;
    private BottomNavigationView mMainNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        //set fragment by click navigation
        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:


                        return true;
                    case R.id.nav_categories:


                        return true;
                    case R.id.nav_favorites:


                        return true;
                    case R.id.nav_settings:


                        return true;
                    default: return false;
                }
            }
        });
    }

    private void initView() {
        mMainFrame = findViewById(R.id.frame_main);
        mMainNav = findViewById(R.id.nav_main);
    }
}
