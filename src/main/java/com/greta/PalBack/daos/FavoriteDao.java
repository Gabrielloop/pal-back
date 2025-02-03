package com.greta.PalBack.daos;

import com.greta.PalBack.entities.Favorite;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FavoriteDao {

    private final JdbcTemplate JdbcTemplate;

    public FavoriteDao(JdbcTemplate jdbcTemplate) {
        this.JdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Favorite> favoriteRowMapper = (rs, _) -> new Favorite(
            rs.getInt("user_id"),
            rs.getString("isbn"),
            rs.getString("create_time")
    );

    public List<Favorite> findAll() {
        String sql = "SELECT * FROM favorite";
        return JdbcTemplate.query(sql, favoriteRowMapper);
    }

    public List<Favorite> findAllForUserId(Integer userId) {
        String sql = "SELECT * FROM favorite WHERE user_id = ?";
        return JdbcTemplate.query(sql, favoriteRowMapper, userId);
    }

    public Favorite findByIsbnAndUser(String isbn, Integer userId) {
        String sql = "SELECT * FROM favorite WHERE isbn = ? & user_id = ?";
        return JdbcTemplate.query(sql, favoriteRowMapper, isbn, userId)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Pas de correspondante avec l'isbn " + isbn + "."));
    }


    public Favorite save(Favorite favorite) {
        String sql = "INSERT INTO favorite (user_id, isbn, create_time) VALUES (?, ?, ?)";
        JdbcTemplate.update(sql, favorite.getUserId(), favorite.getIsbn(), favorite.getCreateTime());

        String sqlGetIsbn = "SELECT LAST_INSERT_ID()";
        String isbn = JdbcTemplate.queryForObject(sqlGetIsbn, String.class);

        favorite.setIsbn(isbn);
        return favorite;
    }

    public boolean delete(String isbn, Integer userId) {
        String sql = "DELETE FROM favorite WHERE isbn = ? & user_id = ?";
        int rowsAffected = JdbcTemplate.update(sql, isbn);
        return rowsAffected > 0;
    }

    public Favorite update(String isbn, Integer userId, Favorite favorite) {
        if (!favoriteExists(isbn, userId)) {
            return save(favorite);
        } else {
            return delete(isbn, userId) ? favorite : null;
        }
    }

    public boolean favoriteExists(String isbn, Integer userId) {
        String checkSql = "SELECT COUNT(*) FROM favorite WHERE isbn = ? & user_id = ?";
        Favorite favorite = JdbcTemplate.query(checkSql, favoriteRowMapper, isbn, userId)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Pas de correspondance avec l'isbn"));
        return favorite != null;
    }

    }