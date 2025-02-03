package com.greta.PalBack.daos;

import com.greta.PalBack.entities.Book;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Repository
public class BookDao {

private final JdbcTemplate JdbcTemplate;

public BookDao(JdbcTemplate jdbcTemplate) {
    this.JdbcTemplate = jdbcTemplate;
}

private final RowMapper<Book> bookRowMapper = (rs, _) -> new Book(
        rs.getString("isbn"),
        rs.getString("book_title"),
        rs.getString("book_author"),
        rs.getString("book_publisher"),
        rs.getLong("book_year"),
        rs.getString("book_updated_time"),
        rs.getString("book_create_time")
);

public List<Book> findAll() {
    String sql = "SELECT * FROM book";
    return JdbcTemplate.query(sql, bookRowMapper);
}

public List<Book> findAllForUserId(Integer userId) {
    String sql = "SELECT * FROM book WHERE user_id = ?";
    return JdbcTemplate.query(sql, bookRowMapper, userId);
}

public Book findByIsbn(String isbn) {
    String sql = "SELECT * FROM book WHERE isbn = ?";
    return JdbcTemplate.query(sql, bookRowMapper, isbn)
            .stream()
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Pas de correspondante avec l'isbn " + isbn + "."));
}

public List<Book> findByTitle(String title) {
    String sql = "Select * FROM book WHERE book_title = ?";
    return JdbcTemplate.query(sql, bookRowMapper, title);
}

public Book save(Book book) {
    String sql = "INSERT INTO book (isbn, book_title, book_author, book_publisher, book_year, book_updated_time, book_create_time) VALUES (?, ?, ?, ?, ?, ?, ?)";
    JdbcTemplate.update(sql, book.getIsbn(), book.getTitle(), book.getAuthor(), book.getPublisher(), book.getYear(), book.getUpdatedTime(), book.getCreateTime());

    String sqlGetIsbn = "SELECT LAST_INSERT_ID()";
    String isbn = JdbcTemplate.queryForObject(sqlGetIsbn, String.class);

    book.setIsbn(isbn);
    return book;
}

public Book update(String isbn, Book book) {
    if(!bookExists(isbn)) {
        throw new RuntimeException("Pas de correspondante avec l'isbn " + isbn + ".");
    }

    String sql = "UPDATE book SET book_title = ?,book_author = ?, book_publisher = ?, book_year = ?, book_updated_time = ?, book_create_time = ? WHERE isbn = ?";
    int rowAffected = JdbcTemplate.update(sql, book.getTitle(), book.getAuthor(), book.getPublisher(), book.getYear(), book.getUpdatedTime(), book.getCreateTime(), isbn);
    if(rowAffected <= 0) {
        throw new RuntimeException("Echec de la modification avec l'isbn " + isbn + ".");
    }
    return  this.findByIsbn(isbn);
    }

    private boolean bookExists(String isbn) {
    String checkSql = "SELECT COUNT(*) FROM book WHERE isbn = ?";
    Book book = JdbcTemplate.query(checkSql, bookRowMapper, isbn)
            .stream()
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Pas de correspondante avec l'isbn " + isbn + "."));
    return book != null;

}

public boolean delete(String isbn) {
    String sql = "DELETE FROM book WHERE isbn = ?";
    int rowsAffected = JdbcTemplate.update(sql,isbn);
    return rowsAffected > 0;
}
}