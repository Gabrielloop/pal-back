package com.greta.PalBack.controllers;

import com.greta.PalBack.daos.CommentDao;
import com.greta.PalBack.entities.Comment;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments") // http://localhost:8808/comments
public class CommentController {

    private final CommentDao commentDao;
    public CommentController(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Comment>> getAllComments() {
        return ResponseEntity.ok(commentDao.findAll());
    }

    @GetMapping("/isbn/{isbn}/{userId}")
    public ResponseEntity<Comment> getCommentbyUserAndIsbn(@PathVariable String isbn, @PathVariable Integer userId) {
        return ResponseEntity.ok(commentDao.findByIsbnAndUser(isbn, userId));
    }
    @GetMapping("/content/{content}")
    public ResponseEntity<List<Comment>> getCommentbyContent(@PathVariable String content) {
        return ResponseEntity.ok(commentDao.findByContent(content));
    }

    @PostMapping("/add")
    public ResponseEntity<Comment> saveComment(@Valid @RequestBody Comment comment) {
        Comment createdBook = commentDao.save(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @PutMapping("/update/{isbn}/{userId}")
    public ResponseEntity<Comment> updateComment(@Valid @PathVariable String isbn, @PathVariable Integer userId, @RequestBody Comment comment) {
    Comment updatedComment = commentDao.update(isbn, userId, comment);
    return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/delete/{isbn}/{userId}")
        public ResponseEntity<Void> deleteComment(@PathVariable String isbn, @PathVariable Integer userId) {
        if (commentDao.delete(isbn, userId)) {
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