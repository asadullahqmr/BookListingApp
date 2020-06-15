package com.example.booklistingapp.models;

import androidx.appcompat.widget.DialogTitle;

public class BookInfo {
    private int index;
    private String title;
    private String cover;

    public BookInfo(){
        this.index = 0;
        this.title = null;
        this.cover = null;
    }

    public BookInfo(int index, String title, String cover){
        this.index = index;
        this.title = title;
        this.cover = cover;
    }

    public void setIndex(int index){
        this.index = index;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setCover(String cover){
        this.cover = cover;
    }

    public void setBookInfo(int index, String title, String cover){
        this.index = index;
        this.title = title;
        this.cover = cover;
    }

    public void setBookInfo(BookInfo bookInfo){
        this.index = bookInfo.getIndex();
        this.title = bookInfo.getTitle();
        this.cover = bookInfo.getCover();
    }

    public int getIndex(){
        return this.index;
    }

    public String getTitle(){
        return this.title;
    }

    public String getCover(){
        return this.cover;
    }

    public BookInfo getBookInfo(){
        return this;
    }
}
