package com.application.tuanlv.comicapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.view.Menu;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.application.tuanlv.comicapp.R;

import com.application.tuanlv.comicapp.adapter.MyComicAdapter;
import com.application.tuanlv.comicapp.model.Comic;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

import static com.application.tuanlv.comicapp.SupportClass.allListComic;


public class SearchActivity extends AppCompatActivity {

    private Toolbar toolbar_search;
    private MaterialSearchView materialSearchView;
    private RecyclerView recycler_search;
    private TextView tvNotFound;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_comic_activity);
        initView();
        eventSearchClicked();
    }

    private void initView() {
        toolbar_search = findViewById(R.id.toolbar_search);
        materialSearchView = findViewById(R.id.material_search);
        recycler_search = findViewById(R.id.recycler_search);
        tvNotFound = findViewById(R.id.tv_not_found);

        setSupportActionBar(toolbar_search);
        getSupportActionBar().setTitle("Search");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_item, menu);
        MenuItem menuItem = menu.findItem(R.id.search_home);
        materialSearchView.setMenuItem(menuItem);
        return true;
    }




    private void eventSearchClicked() {

        materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                //Do some magic
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                newText = newText.toLowerCase().trim();
                if(newText != null && !newText.isEmpty()) {
                    ArrayList<Comic> lstFound = new ArrayList<>();
                    int i = 0;
                    for(Comic item: allListComic) {
                        if(allListComic.get(i).getName().toLowerCase().contains(newText)) {
                            lstFound.add(item);
                        }
                        i++;
                    }
                    showByRecyclerView(lstFound);
                }

                return false;
            }
        });
    }

    private void showByRecyclerView(ArrayList<Comic> lstFound) {
        if(lstFound == null) {
            tvNotFound.setVisibility(View.VISIBLE);
            recycler_search.setVisibility(View.INVISIBLE);
        } else {
            tvNotFound.setVisibility(View.INVISIBLE);
            recycler_search.setVisibility(View.VISIBLE);

            MyComicAdapter myComicAdapter = new MyComicAdapter(this, lstFound);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
            recycler_search.setHasFixedSize(true);
            recycler_search.setLayoutManager(layoutManager);
            recycler_search.setAdapter(myComicAdapter);
        }

    }
}
