package com.greta.PalBack.daos;

import com.greta.PalBack.entities.Wishlist;
import com.greta.PalBack.exceptions.ResourceNotFoundException;
import com.greta.PalBack.services.DateTimeService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishlistDao {

    private final JdbcTemplate JdbcTemplate;
    private final DateTimeService dateTimeService;

    public WishlistDao(JdbcTemplate jdbcTemplate, DateTimeService dateTimeService) {
        this.JdbcTemplate = jdbcTemplate;
        this.dateTimeService = dateTimeService;
    }

    private final RowMapper<Wishlist> wishlistRowMapper = (rs, _) -> new Wishlist(
            rs.getInt("user_id"),
            rs.getString("isbn"),
            rs.getString("create_time")
    );

    public List<Wishlist> findAll() {
        String sql = "SELECT * FROM wishlist";
        return JdbcTemplate.query(sql, wishlistRowMapper);
    }

    public List<Wishlist> findAllForUserId(Integer userId) {
        String sql = "SELECT * FROM wishlist WHERE user_id = ?";
        return JdbcTemplate.query(sql, wishlistRowMapper, userId);
    }

    public Wishlist findByIsbnAndUser(String isbn, Integer userId) {
        String sql = "SELECT * FROM wishlist WHERE isbn = ? AND user_id = ?";
        return JdbcTemplate.query(sql, wishlistRowMapper, isbn, userId)
                .stream()
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Pas de correspondante avec l'isbn " + isbn + "."));
    }


    public Wishlist save(Wishlist wishlist) {
        String sql = "INSERT INTO wishlist (user_id, isbn, create_time) VALUES (?, ?, ?)";
        JdbcTemplate.update(sql, wishlist.getUserId(), wishlist.getIsbn(), dateTimeService.getCurrentDateTime());

        String sqlGetIsbn = "SELECT LAST_INSERT_ID()";
        String isbn = JdbcTemplate.queryForObject(sqlGetIsbn, String.class);

        wishlist.setIsbn(isbn);
        return wishlist;
    }

    public boolean delete(String isbn, Integer id) {
        String sql = "DELETE FROM wishlist WHERE isbn = ? AND user_id = ?";
        int rowsAffected = JdbcTemplate.update(sql, isbn, id);
        return rowsAffected > 0;
    }

    public Wishlist update(String isbn, Integer userId, Wishlist wishlist) {
        if (!wishlistExists(isbn, userId)) {
            return save(wishlist);
        } else {
            return delete(isbn, userId) ? wishlist : null;
        }
    }

    public boolean wishlistExists(String isbn, Integer userId) {
        String checkSql = "SELECT COUNT(*) FROM wishlist WHERE isbn = ? AND user_id = ?";
        Wishlist wishlist = JdbcTemplate.query(checkSql, wishlistRowMapper, isbn, userId)
                .stream()
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Pas de correspondance avec l'isbn"));
        return wishlist != null;
    }

    }