package com.greta.PalBack.daos;

import com.greta.PalBack.entities.UserlistBook;
import com.greta.PalBack.exceptions.ResourceNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserlistBookDao {

private final JdbcTemplate JdbcTemplate;

public UserlistBookDao(JdbcTemplate jdbcTemplate) {
    this.JdbcTemplate = jdbcTemplate;
}

private final RowMapper<UserlistBook> userListBookRowMapper = (rs, _) -> new UserlistBook(
        rs.getInt("userlist_id"),
        rs.getString("isbn"),
        rs.getString("create_time")
);

public List<UserlistBook> findAll() {
    String sql = "SELECT * FROM userlist_book";
    return JdbcTemplate.query(sql, userListBookRowMapper);
}
public UserlistBook findBylistId(Integer id) {
    String sql = "SELECT * FROM userlist_book WHERE userlist_id = ?";
    return JdbcTemplate.query(sql, userListBookRowMapper, id)
            .stream()
            .findFirst()
            .orElseThrow(() -> new ResourceNotFoundException("Pas de correspondante avec l'id " + id + "."));
}

public UserlistBook add(UserlistBook userlistBook) {
    String sql = "INSERT INTO userlist_book (userlist_id, isbn, create_time) VALUES (?, ?, ?)";
    JdbcTemplate.update(sql, userlistBook.getList_id(), userlistBook.getIsbn(), userlistBook.getCreateTime());

    return userlistBook;
}

public boolean delete(Integer UserlistId, String isbn) {
    String sql = "DELETE FROM userlist_book WHERE isbn = ? && userList_id = ?";
    int rowsAffected = JdbcTemplate.update(sql,isbn, UserlistId);
    return rowsAffected > 0;
    }
}