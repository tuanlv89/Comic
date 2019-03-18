package com.application.tuanlv.comicapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.application.tuanlv.comicapp.R;
import com.application.tuanlv.comicapp.SupportClass;
import com.application.tuanlv.comicapp.model.Chapter;

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

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvChapterName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvChapterName = itemView.findViewById(R.id.tv_chapter_name_item);
        }
    }
}
