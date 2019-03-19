package com.application.tuanlv.comicapp.view;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.application.tuanlv.comicapp.R;
import com.application.tuanlv.comicapp.SupportClass;
import com.application.tuanlv.comicapp.adapter.MyChapterAdapter;
import com.application.tuanlv.comicapp.model.Chapter;
import com.application.tuanlv.comicapp.model.Comic;
import com.application.tuanlv.comicapp.service.PicassoLoadingService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChaptersActivity extends AppCompatActivity {

    private FloatingActionButton btnFav;
    private RecyclerView recycler_chapter;
    private Toolbar toolbar;
    private ImageView imgBannerComic;
    private FirebaseAuth mAuth;
    private DatabaseReference user;

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


        mAuth = FirebaseAuth.getInstance();
        user = FirebaseDatabase.getInstance().getReference().child("Users");
        //add to Favorite
        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.child(mAuth.getCurrentUser().getUid()).child("Favorites").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        boolean isExist = false;
                        for (DataSnapshot data: dataSnapshot.getChildren()) {
                            if(SupportClass.comicSelected.getImage().equals(data.getValue(Comic.class).getImage())) {
                                isExist = true;
                                break;
                            }
                        }
                        if(!isExist) {
                            user.child(mAuth.getCurrentUser().getUid()).child("Favorites").push().setValue(SupportClass.comicSelected);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getBaseContext(), "Can not add into Favorites", Toast.LENGTH_SHORT).show();
                    }
                });
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }

        return true;
    }

}
