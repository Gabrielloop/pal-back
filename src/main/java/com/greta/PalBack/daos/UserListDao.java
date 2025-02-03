package com.greta.PalBack.daos;

import com.greta.PalBack.entities.UserList;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserListDao {

private final JdbcTemplate JdbcTemplate;

public UserListDao(JdbcTemplate jdbcTemplate) {
    this.JdbcTemplate = jdbcTemplate;
}

private final RowMapper<UserList> userListRowMapper = (rs, _) -> new UserList(
        rs.getInt("userList_id"),
        rs.getInt("user_id"),
        rs.getString("userList_name"),
        rs.getString("userList_description"),
        rs.getString("userList_titre"),
        rs.getString("book_create_time")
);

public List<UserList> findAll() {
    String sql = "SELECT * FROM list";
    return JdbcTemplate.query(sql, userListRowMapper);
}

public List<UserList> findAllForUserId(Integer userId) {
    String sql = "SELECT * FROM list WHERE user_id = ?";
    return JdbcTemplate.query(sql, userListRowMapper, userId);
}

public UserList findById(Integer id) {
    String sql = "SELECT * FROM list WHERE userList_id = ?";
    return JdbcTemplate.query(sql, userListRowMapper, id)
            .stream()
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Pas de correspondante avec l'id " + id + "."));
}

public UserList save(UserList userList) {
    String sql = "INSERT INTO list (list_id, user_id, list_name, list_description, list_type, create_time) VALUES (?, ?, ?, ?, ?, ?)";
    JdbcTemplate.update(sql, userList.getUserId(), userList.getUserListName(), userList.getUserListName(), userList.getUserList_description(), userList.getUserList_type(), userList.getCreateTime());

    String sqlGetListId = "SELECT LAST_INSERT_ID()";
    Integer listId = JdbcTemplate.queryForObject(sqlGetListId, Integer.class);

    userList.setUserListId(listId);
    return userList;
}

public UserList update(Integer userListId, UserList userList) {
    if(!userListExists(userListId)) {
        throw new RuntimeException("Pas de correspondante avec l'isbn " + userListId + ".");
    }

    String sql = "UPDATE book SET book_title = ?, book_author = ?, book_publisher = ?, book_year = ?, book_updated_time = ?, book_create_time = ? WHERE isbn = ?";
    int rowAffected = JdbcTemplate.update(sql, userList.getUserId(), userList.getUserListName(), userList.getUserListName(), userList.getUserList_description(), userList.getUserList_type(), userList.getCreateTime());
    if(rowAffected <= 0) {
        throw new RuntimeException("Echec de la modification avec l'isbn " + userListId + ".");
    }
    return  this.findById(userListId);
    }

    private boolean userListExists(Integer userListId) {
    String checkSql = "SELECT COUNT(*) FROM list WHERE list_id = ?";
    UserList userList = JdbcTemplate.query(checkSql, userListRowMapper, userListId)
            .stream()
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Pas de correspondante avec l'isbn " + userListId + "."));
    return userList != null;

}

public boolean delete(Integer userListId) {
    String sql = "DELETE FROM list WHERE list_id = ?";
    int rowsAffected = JdbcTemplate.update(sql,userListId);
    return rowsAffected > 0;
}
}