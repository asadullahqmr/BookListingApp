package com.example.booklistingapp.models;

public class Rack {
    private BookInfo bookOne = new BookInfo();
    private BookInfo bookTwo = new BookInfo();

    public Rack(){

    }

    public Rack(BookInfo bookOne){
        bookOne.setBookInfo(bookOne);
    }

    public Rack(BookInfo bookOne, BookInfo bookTwo){
        bookOne.setBookInfo(bookOne);
        bookTwo.setBookInfo(bookTwo);
    }

    public void setBookOne(BookInfo bookOne) {
        this.bookOne.setBookInfo(bookOne);
    }

    public void setBookTwo(BookInfo bookTwo){
        this.bookTwo.setBookInfo(bookTwo);
    }

    public void setBooks(BookInfo bookOne, BookInfo bookTwo) {
        this.bookOne.setBookInfo(bookOne);
        this.bookTwo.setBookInfo(bookTwo);
    }

    public BookInfo getBookOne(){
        return this.bookOne;
    }

    public BookInfo getBookTwo(){
        return this.bookTwo;
    }

}
