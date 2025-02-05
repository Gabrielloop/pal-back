package com.greta.PalBack.controllers;


import com.greta.PalBack.daos.FavoriteDao;
import com.greta.PalBack.daos.WishlistDao;
import com.greta.PalBack.entities.Favorite;
import com.greta.PalBack.entities.Wishlist;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist") // http://localhost:8808/wishlist
public class WishlistController {

    private final WishlistDao wishlistDao;
    public WishlistController(WishlistDao wishlistDao) {
        this.wishlistDao = wishlistDao;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Wishlist>> getAllWishlisted() {
        return ResponseEntity.ok(wishlistDao.findAll());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Wishlist>> getWishlistedByUser(@PathVariable Integer id) {
        return ResponseEntity.ok(wishlistDao.findAllForUserId(id));
    }

    @GetMapping("/isbn/{userId}/{isbn}")
    public ResponseEntity<Wishlist> findByIsbn(@PathVariable Integer userId, @PathVariable String isbn) {
        return ResponseEntity.ok(wishlistDao.findByIsbnAndUser(isbn, userId));
    }

    @PostMapping("/add")
    public ResponseEntity<Wishlist> saveWishlist(@RequestBody Wishlist wishlist) {
        Wishlist createdWishlist = wishlistDao.save(wishlist);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWishlist);
    }

    @DeleteMapping("/delete/{userId}/{isbn}")
        public ResponseEntity<Void> deleteFavorite(@PathVariable Integer userId, @PathVariable String isbn) {
        if (wishlistDao.delete(isbn, userId)) {
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