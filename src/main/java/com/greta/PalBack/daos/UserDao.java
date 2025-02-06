package com.greta.PalBack.daos;

import com.greta.PalBack.entities.User;
import com.greta.PalBack.exceptions.ResourceNotFoundException;
import com.greta.PalBack.services.DateTimeService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {

private final JdbcTemplate JdbcTemplate;
private final DateTimeService dateTimeService;

public UserDao(JdbcTemplate jdbcTemplate, DateTimeService dateTimeService) {
    this.JdbcTemplate = jdbcTemplate;
    this.dateTimeService = dateTimeService;
}

    private final RowMapper<User> userRowMapper = (rs, _) -> new User(
            rs.getInt("user_id"),
            rs.getString("user_name"),
            rs.getString("user_mail"),
            rs.getString("user_password"),
            rs.getString("user_role"),
            rs.getTimestamp("user_last_login").toLocalDateTime(),
            rs.getTimestamp("create_time").toLocalDateTime()
    );

    public List<User> findAll() {
        String sql = "SELECT * FROM user";
        return JdbcTemplate.query(sql, userRowMapper);
    }

    public User findUserById(Integer userId) {
        String sql = "SELECT * FROM user WHERE user_id = ?";
        return JdbcTemplate.query(sql, userRowMapper, userId)
                .stream()
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Pas d'utilisateur trouvé avec l'id " + userId + "."));
    }

    public User save(User user) {
        String sql = "INSERT INTO user (user_name, user_mail, user_password, user_last_login, create_time) VALUES (?, ?, ?, ?, ?)";
        JdbcTemplate.update(sql, user.getUserName(), user.getUserMail(), user.getUserPassword(), dateTimeService.getCurrentDateTime(), dateTimeService.getCurrentDateTime());

        String sqlGetUserId = "SELECT LAST_INSERT_ID()";
        Integer userId = JdbcTemplate.queryForObject(sqlGetUserId, Integer.class);

        user.setUserId(userId);
        return user;
    }

    public User update(Integer userId, User user) {
        String sql = "UPDATE user SET user_name = ?, user_mail = ?, user_password = ?, user_last_login = ?, create_time = ? WHERE user_id = ?";
        int rowsAffected = JdbcTemplate.update(sql, user.getUserName(), user.getUserMail(), user.getUserPassword(), user.getUserLastLogin(), user.getCreateTime(), userId);
        if (rowsAffected <= 0) {
            throw new ResourceNotFoundException("Echec de la modification de l'utilisateur avec l'id " + userId + ".");
        }
        return this.findUserById(userId);
    }

    public boolean delete(Integer userId) {
        String sql = "DELETE FROM user WHERE user_id = ?";
        int rowsAffected = JdbcTemplate.update(sql, userId);
        return rowsAffected > 0;
    }
}