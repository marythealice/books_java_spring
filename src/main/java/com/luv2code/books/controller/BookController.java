package com.luv2code.books.controller;

import com.luv2code.books.entity.Book;
import com.luv2code.books.request.BookRequest;
import jakarta.validation.constraints.Min;
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
                new Book(1, "Computer Science Pro","Chad Darby", "Computer Science", 5),
                new Book(2, "Java Spring Master","Eric Roby", "Computer Science", 5),
                new Book(3, "Why 1 + 1 Rocks","Adil A.", "Math", 5),
                new Book(4, "How Bears Hibernate","Bob B.", "Science", 2),
                new Book(5, "A Pirate's Treasure","Curt C", "History", 3),
                new Book(6, "Why 2 + 2 is Better","Dan D.", "Math", 1)
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

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable @Min(1) long id) {

        return books.stream().filter(book -> book.getId() == id).findFirst().orElse(null);
    }

    @PostMapping
    public void createNewBook(@RequestBody BookRequest bookRequest) {

        long id = books.isEmpty() ? 1 : books.get(books.size() - 1).getId() + 1;

        Book newBook = convertToBook(id, bookRequest);
        books.add(newBook);
    }

    @PutMapping("/{id}")
    public void updateBook(@PathVariable @Min(1) long id, @RequestBody BookRequest bookRequest) {
        for(int i = 0; i< books.size(); i++){
            if(books.get(i).getId() == id){
                Book newBook = convertToBook(id, bookRequest);
                books.set(i, newBook);
                return;
            }
        }
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable @Min(1) long id) {
        books.removeIf(book -> book.getId() == id);
    }

    private Book convertToBook(long id, BookRequest bookRequest){

        return new Book(
                id,
                bookRequest.getTitle(),
                bookRequest.getAuthor(),
                bookRequest.getCategory(),
                bookRequest.getRating()
        );

    }

}
