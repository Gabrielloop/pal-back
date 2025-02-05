package com.greta.PalBack.controllers;


import com.greta.PalBack.daos.FavoriteDao;
import com.greta.PalBack.entities.Favorite;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorite") // http://localhost:8808/favorite
public class FavoriteController {

    private final FavoriteDao favoriteDao;
    public FavoriteController(FavoriteDao favoriteDao) {
        this.favoriteDao = favoriteDao;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Favorite>> getAllFavorite() {
        return ResponseEntity.ok(favoriteDao.findAll());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Favorite>> getFavoriteByUser(@PathVariable Integer id) {
        return ResponseEntity.ok(favoriteDao.findAllByUserId(id));
    }

    @GetMapping("/isbn/{userId}/{isbn}")
    public ResponseEntity<Favorite> findByIsbn(@PathVariable Integer userId, @PathVariable String isbn) {
        return ResponseEntity.ok(favoriteDao.findByIsbnAndUser(isbn, userId));
    }

    @PostMapping("/add")
    public ResponseEntity<Favorite> saveFavorite(@RequestBody Favorite favorite) {
        Favorite createdFavorite = favoriteDao.save(favorite);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFavorite);
    }

    @DeleteMapping("/delete/{userId}/{isbn}")
        public ResponseEntity<Void> deleteFavorite(@PathVariable Integer userId, @PathVariable String isbn) {
        if (favoriteDao.delete(isbn, userId)) {
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