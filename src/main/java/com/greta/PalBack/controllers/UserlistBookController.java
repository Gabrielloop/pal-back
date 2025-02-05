package com.greta.PalBack.controllers;


import com.greta.PalBack.daos.UserlistBookDao;
import com.greta.PalBack.entities.UserlistBook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userlistBooks")
public class UserlistBookController {
    private final UserlistBookDao userlistBookDao;
    public UserlistBookController(UserlistBookDao userlistBookDao) {
        this.userlistBookDao = userlistBookDao;
    }

    // GET METHOD

    @GetMapping("/all")
    public ResponseEntity<List<UserlistBook>> getAllBooksInUserlist() {
        return ResponseEntity.ok(userlistBookDao.findAll());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<UserlistBook> getBooksInUserslistById(@PathVariable Integer id) {
        return ResponseEntity.ok(userlistBookDao.findByUserlistId(id));
    }

    @GetMapping("/join/{userId}/{listId}")
    public ResponseEntity<List<UserlistBook>> joinUserToListBook(@PathVariable Integer userId, @PathVariable Integer listId) {
        return ResponseEntity.ok(userlistBookDao.joinUserToListBook(listId, userId));
    }

    @PostMapping("/add")
    public ResponseEntity<UserlistBook> saveList(@RequestBody UserlistBook userlistBook) {
        UserlistBook createdBook = userlistBookDao.save(userlistBook);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @DeleteMapping("/delete/{listId}/{isbn}")
        public ResponseEntity<Void> deleteBook(@PathVariable Integer listId, @PathVariable String isbn) {
        if (userlistBookDao.delete(listId, isbn)) {
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