package com.application.tuanlv.comicapp.view;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.application.tuanlv.comicapp.R;
import com.application.tuanlv.comicapp.SupportClass;
import com.application.tuanlv.comicapp.adapter.ViewPagerAdapter;
import com.application.tuanlv.comicapp.dialog.ProgressLoading;
import com.application.tuanlv.comicapp.model.Chapter;
import com.wajahatkarim3.easyflipviewpager.BookFlipPageTransformer;

import java.util.ArrayList;

public class ViewComicActivity extends AppCompatActivity {
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_comic);

        viewPager = findViewById(R.id.view_pager);
        ArrayList<String> links = SupportClass.chapterSelected.getLinks();
        fetchLinks(links);

    }

    private void fetchLinks(ArrayList<String> links) {
        if(links!=null) {
            if(links.size()>0) {
                ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getBaseContext(), SupportClass.chapterSelected.getLinks());
                if(viewPagerAdapter!=null) {
                    viewPager.setAdapter(viewPagerAdapter);

                    //Animation
                    BookFlipPageTransformer flipPageTransformer = new BookFlipPageTransformer();
                    flipPageTransformer.setScaleAmountPercent(3f);
                    viewPager.setPageTransformer(true, flipPageTransformer);
                }
            }
        } else {
            Toast.makeText(this, "No image here", Toast.LENGTH_LONG).show();
        }
    }
}
