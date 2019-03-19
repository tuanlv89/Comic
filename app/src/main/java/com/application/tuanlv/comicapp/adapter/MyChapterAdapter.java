package com.application.tuanlv.comicapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.application.tuanlv.comicapp.R;
import com.application.tuanlv.comicapp.SupportClass;
import com.application.tuanlv.comicapp.interfaces.IRecyclerItemCLickListener;
import com.application.tuanlv.comicapp.model.Chapter;
import com.application.tuanlv.comicapp.view.ViewComicActivity;

import java.util.ArrayList;

public class MyChapterAdapter extends RecyclerView.Adapter<MyChapterAdapter.MyViewHolder>{

    Context context;
    LayoutInflater inflater;
    ArrayList<Chapter> chapters;

    public MyChapterAdapter(Context context, ArrayList<Chapter> chapters) {
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.chapters = chapters;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.chapter_item, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        //Event
        myViewHolder.setRecyclerItemCLickListener(new IRecyclerItemCLickListener() {
            @Override
            public void onClick(View view, int position) {
                SupportClass.chapterSelected = chapters.get(position);
                Intent intent = new Intent(context, ViewComicActivity.class);
                context.startActivity(intent);
            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.tvChapterName.setText(chapters.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return chapters.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvChapterName;
        IRecyclerItemCLickListener recyclerItemCLickListener;

        public void setRecyclerItemCLickListener(IRecyclerItemCLickListener recyclerItemCLickListener) {
            this.recyclerItemCLickListener = recyclerItemCLickListener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvChapterName = itemView.findViewById(R.id.tv_chapter_name_item);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            recyclerItemCLickListener.onClick(view, getAdapterPosition());
        }
    }
}
