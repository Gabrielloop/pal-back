package com.greta.PalBack.daos;

import com.greta.PalBack.entities.Comment;
import com.greta.PalBack.exceptions.ResourceNotFoundException;
import com.greta.PalBack.services.DateTimeService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class CommentDao {

private final JdbcTemplate JdbcTemplate;
private final DateTimeService dateTimeService;

public CommentDao(JdbcTemplate jdbcTemplate, DateTimeService dateTimeService) {
    this.JdbcTemplate = jdbcTemplate;
    this.dateTimeService = dateTimeService;
}

private final RowMapper<Comment> commentRowMapper = (rs, _) -> new Comment(
        rs.getString("isbn"),
        rs.getInt("user_id"),
        rs.getString("comment_content"),
        rs.getTimestamp("create_time").toLocalDateTime()
);

public List<Comment> findAll() {
    String sql = "SELECT * FROM comment";
    return JdbcTemplate.query(sql, commentRowMapper);
}

public Comment findByIsbnAndUser(String isbn, Integer userId) {
    String sql = "SELECT * FROM comment WHERE isbn = ? AND user_id = ?";
    return JdbcTemplate.query(sql, commentRowMapper, isbn, userId)
            .stream()
            .findFirst()
            .orElseThrow(() -> new ResourceNotFoundException("Pas de correspondante avec l'isbn " + isbn + "/" + userId + "."));
}
public List<Comment> findByContent(String content) {
    String contentSearch = '%'+content+'%';
    String sql = "Select * FROM comment WHERE comment_content LIKE ?";
    return JdbcTemplate.query(sql, commentRowMapper, contentSearch);
}

public Comment save(Comment comment) {
    String sql = "INSERT INTO comment (isbn, user_id, comment_content, create_time) VALUES (?, ?, ?, ?)";
    JdbcTemplate.update(sql, comment.getIsbn(), comment.getUserId(), comment.getCommentContent(), dateTimeService.getCurrentDateTime());
    return comment;
}

public Comment update(String isbn, Integer userId, Comment comment) {
    String sql = "UPDATE comment SET comment_content = ? WHERE isbn = ? AND user_id = ?";
    int rowAffected = JdbcTemplate.update(sql, comment.getCommentContent(), isbn, userId);
    if(rowAffected <= 0) {
        throw new ResourceNotFoundException("Echec de la modification avec l'isbn " + isbn + ".");
    }
    return  this.findByIsbnAndUser(isbn, userId);
}

public boolean delete(String isbn, Integer userId) {
    String sql = "DELETE FROM comment WHERE isbn = ? AND user_id = ?";
    int rowsAffected = JdbcTemplate.update(sql,isbn, userId);
    return rowsAffected > 0;
}
}