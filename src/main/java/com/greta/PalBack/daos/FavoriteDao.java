package com.greta.PalBack.daos;

import com.greta.PalBack.entities.Favorite;
import com.greta.PalBack.exceptions.ResourceNotFoundException;
import com.greta.PalBack.services.DateTimeService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class FavoriteDao {

    private final JdbcTemplate JdbcTemplate;
    private final DateTimeService dateTimeService;

    public FavoriteDao(JdbcTemplate jdbcTemplate, DateTimeService dateTimeService) {
        this.JdbcTemplate = jdbcTemplate;
        this.dateTimeService = dateTimeService;
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

    public List<Favorite> findAllByUserId(Integer userId) {
        String sql = "SELECT * FROM favorite WHERE user_id = ?";
        return JdbcTemplate.query(sql, favoriteRowMapper, userId);
    }

    public Favorite findByIsbnAndUser(String isbn, Integer userId) {
        String sql = "SELECT * FROM favorite WHERE isbn = ? AND user_id = ?";
        return JdbcTemplate.query(sql, favoriteRowMapper, isbn, userId)
                .stream()
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Pas de correspondante avec l'isbn " + isbn + "/" + userId + "."));
    }

    public Favorite save(Favorite favorite) {
        String sql = "INSERT INTO favorite (user_id, isbn, create_time) VALUES (?, ?, ?)";
        JdbcTemplate.update(sql, favorite.getUserId(), favorite.getIsbn(), dateTimeService.getCurrentDateTime());

        String sqlGetIsbn = "SELECT LAST_INSERT_ID()";
        String isbn = JdbcTemplate.queryForObject(sqlGetIsbn, String.class);

        favorite.setIsbn(isbn);
        return favorite;
    }

    public boolean delete(String isbn, Integer id) {
        String sql = "DELETE FROM favorite WHERE isbn = ? AND user_id = ?";
        int rowsAffected = JdbcTemplate.update(sql, isbn, id);
        return rowsAffected > 0;
    }

    }