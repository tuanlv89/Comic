package com.application.tuanlv.comicapp.model;

import java.util.ArrayList;

public class Comic {
    private String Name;
    private String Image;
    private ArrayList<Chapter> Chapters;

    public Comic() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public ArrayList<Chapter> getChapters() {
        return Chapters;
    }

    public void setChapters(ArrayList<Chapter> chapters) {
        Chapters = chapters;
    }
}
