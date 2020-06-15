package com.example.booklistingapp.models;

public class Rack {
    private BookInfo bookOne;
    private BookInfo bookTwo;

    public Rack(){
        bookOne = null;
        bookTwo = null;
    }

    public void setBookOne(BookInfo bookOne) {
        this.bookOne = bookOne;
    }

    public void setBookTwo(BookInfo bookTwo){
        this.bookTwo = bookTwo;
    }

    public BookInfo getBookOne(){
        return this.bookOne;
    }

    public BookInfo getBookTwo(){
        return this.bookTwo;
    }
}
