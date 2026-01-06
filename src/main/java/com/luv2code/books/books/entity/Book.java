package com.luv2code.books.books.entity;

import lombok.*;

@Data
public class Book {
    private  String title;
    private String author;
    private String category;

    public Book(String title, String author, String category) {
        this.title = title;
        this.author = author;
        this.category = category;
    }
}
