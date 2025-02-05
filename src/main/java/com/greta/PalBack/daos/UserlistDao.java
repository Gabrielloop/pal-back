package com.greta.PalBack.daos;

import com.greta.PalBack.entities.Userlist;
import com.greta.PalBack.exceptions.ResourceNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserlistDao {

private final JdbcTemplate JdbcTemplate;

public UserlistDao(JdbcTemplate jdbcTemplate) {
    this.JdbcTemplate = jdbcTemplate;
}

private final RowMapper<Userlist> userlistRowMapper = (rs, _) -> new Userlist(
        rs.getInt("userlist_id"),
        rs.getInt("user_id"),
        rs.getString("userlist_name"),
        rs.getString("userlist_description"),
        rs.getString("userlist_type"),
        rs.getTimestamp("userlist_create_time").toLocalDateTime()
);

public List<Userlist> findAll() {
    String sql = "SELECT * FROM `userlist`";
    return JdbcTemplate.query(sql, userlistRowMapper);
}

public List<Userlist> findAllForUserId(Integer userId) {
    String sql = "SELECT * FROM userlist WHERE user_id = ?";
    return JdbcTemplate.query(sql, userlistRowMapper, userId);
}

public Userlist findById(Integer id) {
    String sql = "SELECT * FROM userlist WHERE userlist_id = ?";
    return JdbcTemplate.query(sql, userlistRowMapper, id)
            .stream()
            .findFirst()
            .orElseThrow(() -> new ResourceNotFoundException("Pas de correspondante avec l'id " + id + "."));
}

public List<Userlist> findByTitle(String title) {
    String titleSearch = '%'+title+'%';
    String sql = "Select * FROM userlist WHERE userlist_name LIKE ?";
    return JdbcTemplate.query(sql, userlistRowMapper, titleSearch);
}

public Userlist save(Userlist userlist) {
    String sql = "INSERT INTO userlist (user_id, userlist_name, userlist_description, userlist_type, userlist_create_time) VALUES (?, ?, ?, ?, ?)";
    JdbcTemplate.update(sql, userlist.getUserId(), userlist.getUserlistName(), userlist.getUserlistDescription(), userlist.getUserlistType(), userlist.getUserlistCreatetime());

    String sqlGetUserlistId = "SELECT LAST_INSERT_ID()";
    Integer userlistId = JdbcTemplate.queryForObject(sqlGetUserlistId, Integer.class);

    userlist.setUserlistId(userlistId);
    return userlist;
}

public Userlist update(Integer userlistId, Userlist userlist) {
    String sql = "UPDATE userlist SET userlist_name = ?, userlist_description = ?, userlist_create_time = ? WHERE userlist_id = ?";
    int rowAffected = JdbcTemplate.update(sql, userlist.getUserlistName(), userlist.getUserlistDescription(), userlist.getUserlistCreatetime());
    if(rowAffected <= 0) {
        throw new ResourceNotFoundException("Echec de la modification avec l'isbn " + userlistId + ".");
    }
    return  this.findById(userlistId);
    }


public boolean delete(Integer userlistId) {
    String sql = "DELETE FROM userList WHERE userlist_id = ?";
    int rowsAffected = JdbcTemplate.update(sql,userlistId);
    return rowsAffected > 0;
}
}
