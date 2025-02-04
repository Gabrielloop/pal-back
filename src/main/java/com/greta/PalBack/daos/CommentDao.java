package com.greta.PalBack.daos;

import com.greta.PalBack.entities.Comment;
import com.greta.PalBack.exceptions.ResourceNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDao {

private final JdbcTemplate JdbcTemplate;

public CommentDao(JdbcTemplate jdbcTemplate) {
    this.JdbcTemplate = jdbcTemplate;
}

private final RowMapper<Comment> commentRowMapper = (rs, _) -> new Comment(
        rs.getString("isbn"),
        rs.getInt("user_id"),
        rs.getString("comment_content"),
        rs.getString("create_time")
);

public List<Comment> findAll() {
    String sql = "SELECT * FROM comment";
    return JdbcTemplate.query(sql, commentRowMapper);
}

public List<Comment> findAllForUserId(Integer userId) {
    String sql = "SELECT * FROM comment WHERE user_id = ?";
    return JdbcTemplate.query(sql, commentRowMapper, userId);
}

public Comment findByIsbnAndUser(String isbn, Integer userId) {
    String sql = "SELECT * FROM comment WHERE isbn = ? & user_id = ?";
    return JdbcTemplate.query(sql, commentRowMapper, isbn)
            .stream()
            .findFirst()
            .orElseThrow(() -> new ResourceNotFoundException("Pas de correspondante avec l'isbn " + isbn + "."));
}


public Comment save(Comment comment) {
    String sql = "INSERT INTO comment (isbn, user_id, comment_content, create_time) VALUES (?, ?, ?, ?)";
    JdbcTemplate.update(sql, comment.getIsbn(), comment.getUserId(), comment.getCommentContent(), comment.getCreateTime());

    String sqlGetIsbn = "SELECT LAST_INSERT_ID()";
    String isbn = JdbcTemplate.queryForObject(sqlGetIsbn, String.class);

    comment.setIsbn(isbn);
    return comment;
}

public Comment update(String isbn, Integer userId, Comment comment) {
    String sql = "UPDATE comment SET comment_content = ? WHERE isbn = ? & user_id = ?";
    int rowAffected = JdbcTemplate.update(sql, comment.getIsbn(), comment.getUserId(), comment.getCommentContent(), comment.getCreateTime(), isbn, userId);
    if(rowAffected <= 0) {
        throw new ResourceNotFoundException("Echec de la modification avec l'isbn " + isbn + ".");
    }
    return  this.findByIsbnAndUser(isbn, userId);
}

public boolean delete(String isbn, Integer userId) {
    String sql = "DELETE FROM comment WHERE isbn = ? & user_id = ?";
    int rowsAffected = JdbcTemplate.update(sql,isbn);
    return rowsAffected > 0;
}
}