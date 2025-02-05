package com.greta.PalBack.controllers;


import com.greta.PalBack.daos.UserlistDao;
import com.greta.PalBack.entities.Userlist;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userlists") // http://localhost:8808/userlists
public class UserlistController {
    private final UserlistDao userlistDao;
    public UserlistController(UserlistDao userlistDao) {
        this.userlistDao = userlistDao;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Userlist>> getAllUserLists() {
        return ResponseEntity.ok(userlistDao.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Userlist> getUserlistsById(@PathVariable Integer id) {
        return ResponseEntity.ok(userlistDao.findById(id));
    }
    @GetMapping("/userid/{userid}")
    public ResponseEntity<List<Userlist>> getUserlistsByUserId(@PathVariable Integer userid) {
        return ResponseEntity.ok(userlistDao.findAllForUserId(userid));
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<Userlist>> getListsByTitle(@PathVariable String title) {
        return ResponseEntity.ok(userlistDao.findByTitle(title));
    }

    @PostMapping("/add")
    public ResponseEntity<Userlist> saveList(@RequestBody Userlist userlist) {
        Userlist createdBook = userlistDao.save(userlist);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Userlist> updateUserlist(@PathVariable Integer id, @RequestBody Userlist userlist) {
    Userlist updatedUserlist = userlistDao.update(id, userlist);
    return ResponseEntity.ok(updatedUserlist);
    }

    @DeleteMapping("/delete/{id}")
        public ResponseEntity<Void> deleteBook(@PathVariable Integer id) {
        if (userlistDao.delete(id)) {
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