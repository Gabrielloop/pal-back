package com.greta.PalBack.controllers;


import com.greta.PalBack.daos.BookDao;
import com.greta.PalBack.entities.Book;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books") // http://localhost:8808/books
public class BookController {
    private final BookDao bookDao;
    public BookController(BookDao bookDao) {
        this.bookDao = bookDao;
    }
    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookDao.findAll());
    }
    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@PathVariable String isbn) {
        return ResponseEntity.ok(bookDao.findByIsbn(isbn));
    }
    @GetMapping("/title/{title}")
    public ResponseEntity<List<Book>> getBooksByTitle(@PathVariable String title) {
        return ResponseEntity.ok(bookDao.findByTitle(title));
    }

    @PostMapping("/add")
    public ResponseEntity<Book> saveBook(@Valid @RequestBody Book book) {
        Book createdBook = bookDao.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @PutMapping("/update/{isbn}")
    public ResponseEntity<Book> updateBook(@Valid @PathVariable String isbn, @RequestBody Book book) {
    Book updatedBook = bookDao.update(isbn, book);
    return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/delete/{isbn}")
        public ResponseEntity<Void> deleteBook(@PathVariable String isbn) {
        if (bookDao.delete(isbn)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/error")
    public ResponseEntity<String> error() {
        // error message
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
    }

}