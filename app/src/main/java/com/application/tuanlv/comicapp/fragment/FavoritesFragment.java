package com.application.tuanlv.comicapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.tuanlv.comicapp.R;
import com.application.tuanlv.comicapp.SupportClass;
import com.application.tuanlv.comicapp.adapter.MyComicAdapter;
import com.application.tuanlv.comicapp.model.Comic;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment {
    private RecyclerView recycler_favorites;
    public static FavoritesFragment newInstance() {
        FavoritesFragment favoritesFragment = new FavoritesFragment();
        return favoritesFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favorites_fragment, container, false);
        recycler_favorites = view.findViewById(R.id.recycler_favorites);
        ArrayList<Comic> listFavorite = SupportClass.comicFavorites;
        if(listFavorite!=null) {
            MyComicAdapter comicAdapter = new MyComicAdapter(getContext(), listFavorite);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
            recycler_favorites.setHasFixedSize(true);
            recycler_favorites.setLayoutManager(layoutManager);
            recycler_favorites.setAdapter(comicAdapter);
        }


        return view;
    }
}
