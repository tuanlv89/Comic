package com.application.tuanlv.comicapp.view;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.application.tuanlv.comicapp.R;
import com.application.tuanlv.comicapp.SupportClass;
import com.application.tuanlv.comicapp.adapter.MyChapterAdapter;
import com.application.tuanlv.comicapp.model.Chapter;
import com.application.tuanlv.comicapp.service.PicassoLoadingService;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class ChaptersActivity extends AppCompatActivity {

    private FloatingActionButton btnFav;
    private RecyclerView recycler_chapter;
    private Toolbar toolbar;
    private ImageView imgBannerComic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapters);
        initView();
        //Load comic banner image
        PicassoLoadingService picasso = new PicassoLoadingService();
        picasso.loadImage(SupportClass.comicSelected.getImage(), imgBannerComic);
        ArrayList<Chapter> chapters = SupportClass.comicSelected.getChapters();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recycler_chapter.setLayoutManager(layoutManager);
        recycler_chapter.setHasFixedSize(true);
        recycler_chapter.addItemDecoration(new DividerItemDecoration(this, ((LinearLayoutManager) layoutManager).getOrientation()));
        recycler_chapter.setAdapter(new MyChapterAdapter(this, chapters));


        //add to Favorite
        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "Added to favorites", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initView() {
        imgBannerComic = findViewById(R.id.img_comic_chapter_view);
        btnFav = findViewById(R.id.btn_fav);
        recycler_chapter = findViewById(R.id.recycler_chapter);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); //tao nut thoat tren toolbar
    }

}
