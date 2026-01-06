package com.luv2code.books.books.controller;

import com.luv2code.books.books.entity.Book;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final List<Book> books = new ArrayList<>();

    public BookController() {
        initializeBooks();
    }

    public void initializeBooks() {
        books.addAll(List.of(
                new Book("Title one", "Author one", "Science"),
                new Book("Title two", "Author two", "Science"),
                new Book("Title three", "Author three", "History"),
                new Book("Title four", "Author four", "Science"),
                new Book("Title five", "Author five", "Math"),
                new Book("Title six", "Author six", "Math")
        ));
    }

    @GetMapping
    public List<Book> getBooks(@RequestParam(required = false) String category) {
        if (category == null) {
            return books;
        }
        return books.stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .toList();
    }

    @GetMapping("/{title}")
    public Book getBookByTitle(@PathVariable String title) {

        return books.stream().filter(book -> book.getTitle().equalsIgnoreCase(title)).
                findFirst()
                .orElse(null);
    }

    @PostMapping
    public void createNewBook(@RequestBody Book newBook) {
        boolean isNewBook = books
                .stream()
                .noneMatch(b -> b.getTitle().equalsIgnoreCase(newBook.getTitle()));
        if (isNewBook){
            books.add(newBook);
        }
    }


}
