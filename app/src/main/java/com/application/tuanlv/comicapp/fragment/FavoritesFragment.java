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
import android.widget.TextView;

import com.application.tuanlv.comicapp.R;
import com.application.tuanlv.comicapp.SupportClass;
import com.application.tuanlv.comicapp.adapter.MyComicAdapter;
import com.application.tuanlv.comicapp.model.Comic;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment {
    private RecyclerView recycler_favorites;
    private DatabaseReference mUsers;
    private FirebaseAuth mAuth;
    public static FavoritesFragment newInstance() {
        FavoritesFragment favoritesFragment = new FavoritesFragment();
        return favoritesFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favorites_fragment, container, false);
        recycler_favorites = view.findViewById(R.id.recycler_favorites);
        mAuth = FirebaseAuth.getInstance();
        mUsers = FirebaseDatabase.getInstance().getReference().child("Users");
        loadListFavoriteComics();




        return view;
    }

    private void loadListFavoriteComics() {
        mUsers.child(mAuth.getCurrentUser().getUid())
                .child("Favorites")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<Comic> comics = new ArrayList<>();
                        for(DataSnapshot data: dataSnapshot.getChildren()) {
                            comics.add(data.getValue(Comic.class));
                        }
                        if(comics!=null) {
                            MyComicAdapter comicAdapter = new MyComicAdapter(getContext(), comics);
                            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
                            recycler_favorites.setHasFixedSize(true);
                            recycler_favorites.setLayoutManager(layoutManager);
                            recycler_favorites.setAdapter(comicAdapter);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }
}
