package com.greta.PalBack.controllers;


import com.greta.PalBack.daos.UserDao;
import com.greta.PalBack.entities.Book;
import com.greta.PalBack.entities.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user") // http://localhost:8808/user
public class UserController {

    private final UserDao userDao;
    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userDao.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userDao.findUserById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Boolean> saveUser(@RequestBody User user) {
        boolean createdUser = userDao.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user) {
    User updatedUser = userDao.update(id, user);
    return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete/{id}")
        public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        if (userDao.delete(id)) {
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