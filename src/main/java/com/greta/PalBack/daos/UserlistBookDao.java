package com.greta.PalBack.daos;

import com.greta.PalBack.entities.UserlistBook;
import com.greta.PalBack.exceptions.ResourceNotFoundException;
import com.greta.PalBack.services.DateTimeService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserlistBookDao {

private final JdbcTemplate JdbcTemplate;
private final DateTimeService dateTimeService;

public UserlistBookDao(JdbcTemplate jdbcTemplate, DateTimeService dateTimeService) {
    this.JdbcTemplate = jdbcTemplate;
    this.dateTimeService = dateTimeService;
}

private final RowMapper<UserlistBook> userListBookRowMapper = (rs, _) -> new UserlistBook(
        rs.getInt("userlist_id"),
        rs.getString("isbn"),
        rs.getTimestamp("create_time").toLocalDateTime()
);

public List<UserlistBook> findAll() {
    String sql = "SELECT * FROM userlist_book";
    return JdbcTemplate.query(sql, userListBookRowMapper);
}
public UserlistBook findByUserlistId(Integer listId) {
    String sql = "SELECT * FROM userlist_book WHERE userlist_id = ?";
    return JdbcTemplate.query(sql, userListBookRowMapper, listId)
            .stream()
            .findFirst()
            .orElseThrow(() -> new ResourceNotFoundException("Pas de correspondante " + listId + "."));
}

public List<UserlistBook> joinUserToListBook(Integer ListId, Integer userId) {
    String sql = "SELECT ub.userlist_id, ub.isbn, ub.create_time, ul.user_id FROM userlist_book ub INNER JOIN userlist ul ON ub.userlist_id = ul.userlist_id WHERE ub.userlist_id = ? AND ul.user_id = ?";
    return JdbcTemplate.query(sql, userListBookRowMapper, ListId, userId);
}


public UserlistBook save(UserlistBook userlistBook) {
    String sql = "INSERT INTO userlist_book (userlist_id, isbn, create_time) VALUES (?, ?, ?)";
    JdbcTemplate.update(sql, userlistBook.getUserListId(), userlistBook.getIsbn(), dateTimeService.getCurrentDateTime());
    return userlistBook;
}

public boolean delete(Integer listId, String isbn) {
    String sql = "DELETE FROM userlist_book WHERE userList_id = ? AND isbn= ?";
    int rowsAffected = JdbcTemplate.update(sql,listId, isbn);
    return rowsAffected > 0;
    }
}