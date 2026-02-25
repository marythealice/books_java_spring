package com.luv2code.books.request;

import lombok.Data;

@Data
public class BookRequest {
    private String title;
    private String author;
    private String category;
    private int rating;

    public BookRequest(String title, String author, String category, int rating) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.rating = rating;
    }
}
