package com.example.booklistingapp.models;

import androidx.appcompat.widget.DialogTitle;

public class BookInfo {
    private String title;
    private String cover;

    public BookInfo(String title, String cover){
        this.title = title;
        this.cover = cover;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setCover(String cover){
        this.cover = cover;
    }

    public void setBookInfo(BookInfo bookInfo){
        this.title = bookInfo.getTitle();
        this.cover = bookInfo.getCover();
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
