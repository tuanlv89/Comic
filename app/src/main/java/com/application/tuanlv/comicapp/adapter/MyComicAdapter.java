package com.application.tuanlv.comicapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.tuanlv.comicapp.R;
import com.application.tuanlv.comicapp.SupportClass;
import com.application.tuanlv.comicapp.interfaces.IRecyclerItemCLickListener;
import com.application.tuanlv.comicapp.model.Comic;
import com.application.tuanlv.comicapp.service.PicassoLoadingService;
import com.application.tuanlv.comicapp.view.ChaptersActivity;

import java.util.ArrayList;

public class MyComicAdapter extends RecyclerView.Adapter<MyComicAdapter.MyViewHolder> {

    Context context;
    LayoutInflater inflater;
    ArrayList<Comic> comics;



    public MyComicAdapter(Context context, ArrayList<Comic> comics) {
        this.context = context;
        this.comics = comics;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.comic_item, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        PicassoLoadingService picaso = new PicassoLoadingService();
        picaso.loadImage(comics.get(i).getImage(), myViewHolder.imgComic);
        myViewHolder.tvComicName.setText(comics.get(i).getName());

        //Event
        myViewHolder.setRecyclerItemClickListener(new IRecyclerItemCLickListener() {
            @Override
            public void onClick(View view, int position) {
                SupportClass.comicSelected = comics.get(position);
                Intent intent = new Intent(context, ChaptersActivity.class);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return comics.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imgComic;
        TextView tvComicName;
        IRecyclerItemCLickListener recyclerItemClickListener;

        public void setRecyclerItemClickListener(IRecyclerItemCLickListener recyclerItemClickListener) {
            this.recyclerItemClickListener = recyclerItemClickListener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgComic = itemView.findViewById(R.id.img_comic);
            tvComicName = itemView.findViewById(R.id.tv_comic_name);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            recyclerItemClickListener.onClick(view, getAdapterPosition());
        }
    }
}

