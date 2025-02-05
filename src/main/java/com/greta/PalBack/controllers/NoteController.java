package com.greta.PalBack.controllers;

import com.greta.PalBack.daos.NoteDao;
import com.greta.PalBack.entities.Note;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes") // http://localhost:8808/notes
public class NoteController {

    private final NoteDao noteDao;
    public NoteController(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Note>> getAllNotes() {
        return ResponseEntity.ok(noteDao.findAll());
    }

    @GetMapping("/isbn/{isbn}/{userId}")
    public ResponseEntity<Note> getNotebyUserAndIsbn(@PathVariable String isbn, @PathVariable Integer userId) {
        return ResponseEntity.ok(noteDao.findByIsbnAndUser(isbn, userId));
    }
    @GetMapping("/content/{content}")
    public ResponseEntity<List<Note>> getNotebyContent(@PathVariable String content) {
        return ResponseEntity.ok(noteDao.findByContent(content));
    }

    @PostMapping("/add")
    public ResponseEntity<Note> saveNote(@Valid @RequestBody Note note) {
        Note createdBook = noteDao.save(note);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @PutMapping("/update/{isbn}/{userId}")
    public ResponseEntity<Note> updateNote(@Valid @PathVariable String isbn, @PathVariable Integer userId, @RequestBody Note note) {
        Note updatedNote = noteDao.update(isbn, userId, note);
    return ResponseEntity.ok(updatedNote);
    }

    @DeleteMapping("/delete/{isbn}/{userId}")
        public ResponseEntity<Void> deleteNote(@PathVariable String isbn, @PathVariable Integer userId) {
        if (noteDao.delete(isbn, userId)) {
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