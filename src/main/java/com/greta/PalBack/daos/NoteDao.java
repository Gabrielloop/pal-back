package com.greta.PalBack.daos;


import com.greta.PalBack.entities.Note;
import com.greta.PalBack.exceptions.ResourceNotFoundException;
import com.greta.PalBack.services.DateTimeService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NoteDao {

    private final JdbcTemplate JdbcTemplate;
    private final DateTimeService dateTimeService;

public NoteDao(JdbcTemplate jdbcTemplate, DateTimeService dateTimeService) {
    this.JdbcTemplate = jdbcTemplate;
    this.dateTimeService = dateTimeService;
}

private final RowMapper<Note> noteRowMapper = (rs, _) -> new Note(
        rs.getString("isbn"),
        rs.getInt("user_id"),
        rs.getInt("note_content"),
        rs.getTimestamp("create_time").toLocalDateTime()
);

public List<Note> findAll() {
    String sql = "SELECT * FROM note";
    return JdbcTemplate.query(sql, noteRowMapper);
}

public Note findByIsbnAndUser(String isbn, Integer userId) {
    String sql = "SELECT * FROM note WHERE isbn = ? AND user_id = ?";
    return JdbcTemplate.query(sql, noteRowMapper, isbn, userId)
            .stream()
            .findFirst()
            .orElseThrow(() -> new ResourceNotFoundException("Pas de correspondante avec l'isbn " + isbn + "/" + userId + "."));
}
public List<Note> findByContent(String content) {
    String sql = "Select * FROM note WHERE note_content LIKE ?";
    return JdbcTemplate.query(sql, noteRowMapper, content);
}

public Note save(Note note) {
    String sql = "INSERT INTO note (isbn, user_id, note_content, create_time) VALUES (?, ?, ?, ?)";
    JdbcTemplate.update(sql, note.getIsbn(), note.getUserId(), note.getNoteContent(), dateTimeService.getCurrentDateTime());
    return note;
}

public Note update(String isbn, Integer userId, Note note) {
    String sql = "UPDATE note SET note_content = ? WHERE isbn = ? AND user_id = ?";
    int rowAffected = JdbcTemplate.update(sql, note.getNoteContent(), isbn, userId);
    if(rowAffected <= 0) {
        throw new ResourceNotFoundException("Echec de la modification avec l'isbn " + isbn + ".");
    }
    return  this.findByIsbnAndUser(isbn, userId);
}

public boolean delete(String isbn, Integer userId) {
    String sql = "DELETE FROM note WHERE isbn = ? AND user_id = ?";
    int rowsAffected = JdbcTemplate.update(sql,isbn, userId);
    return rowsAffected > 0;
}
}