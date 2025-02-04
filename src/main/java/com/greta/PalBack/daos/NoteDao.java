package com.greta.PalBack.daos;

import com.greta.PalBack.entities.Note;
import com.greta.PalBack.exceptions.ResourceNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NoteDao {

private final JdbcTemplate JdbcTemplate;

public NoteDao(JdbcTemplate jdbcTemplate) {
    this.JdbcTemplate = jdbcTemplate;
}

private final RowMapper<Note> noteRowMapper = (rs, _) -> new Note(
        rs.getString("isbn"),
        rs.getInt("user_id"),
        rs.getInt("note_content"),
        rs.getString("create_time")
);

public List<Note> findAll() {
    String sql = "SELECT * FROM note";
    return JdbcTemplate.query(sql, noteRowMapper);
}

public List<Note> findAllForUserId(Integer userId) {
    String sql = "SELECT * FROM note WHERE user_id = ?";
    return JdbcTemplate.query(sql, noteRowMapper, userId);
}

public Note findByIsbnAndUser(String isbn, Integer userId) {
    String sql = "SELECT * FROM note WHERE isbn = ? & user_id = ?";
    return JdbcTemplate.query(sql, noteRowMapper, isbn, userId)
            .stream()
            .findFirst()
            .orElseThrow(() -> new ResourceNotFoundException("Pas de correspondante avec l'isbn " + isbn + "."));
}


public Note save(Note note) {
    String sql = "INSERT INTO note (isbn, user_id, note_content, create_time) VALUES (?, ?, ?, ?)";
    JdbcTemplate.update(sql, note.getIsbn(), note.getUserId(), note.getNoteContent(), note.getCreateTime());

    String sqlGetIsbn = "SELECT LAST_INSERT_ID()";
    String isbn = JdbcTemplate.queryForObject(sqlGetIsbn, String.class);

    note.setIsbn(isbn);
    return note;
}

public Note update(String isbn, Integer userId, Note note) {
    String sql = "UPDATE note SET note_content = ? WHERE isbn = ? & user_id = ?";
    int rowAffected = JdbcTemplate.update(sql, note.getIsbn(), note.getUserId(), note.getNoteContent(), note.getCreateTime(), isbn, userId);
    if(rowAffected <= 0) {
        throw new ResourceNotFoundException("Echec de la modification avec l'isbn " + isbn + ".");
    }
    return  this.findByIsbnAndUser(isbn, userId);
}

public boolean delete(String isbn, Integer userId) {
    String sql = "DELETE FROM note WHERE isbn = ? & user_id = ?";
    int rowsAffected = JdbcTemplate.update(sql,isbn);
    return rowsAffected > 0;
}
}