package com.greta.PalBack.daos;

import com.greta.PalBack.entities.Book;
import com.greta.PalBack.exceptions.ResourceNotFoundException;
import com.greta.PalBack.services.DateTimeService;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Repository
public class BookDao {

private final JdbcTemplate JdbcTemplate;
private final DateTimeService dateTimeService;

public BookDao(JdbcTemplate jdbcTemplate, DateTimeService dateTimeService) {
    this.JdbcTemplate = jdbcTemplate;
    this.dateTimeService = dateTimeService;
}

private final RowMapper<Book> bookRowMapper = (rs, _) -> new Book(
        rs.getString("isbn"),
        rs.getString("book_title"),
        rs.getString("book_author"),
        rs.getString("book_publisher"),
        rs.getLong("book_year"),
        rs.getTimestamp("book_updated_time").toLocalDateTime(),
        rs.getTimestamp("book_create_time").toLocalDateTime()
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
            .orElseThrow(() -> new ResourceNotFoundException("Pas de correspondante avec l'isbn " + isbn + "."));
}

public List<Book> findByTitle(String title) {
    String titleSearch = '%'+title+'%';
    String sql = "Select * FROM book WHERE book_title LIKE ?";
    return JdbcTemplate.query(sql, bookRowMapper, titleSearch);
}

public Book save(Book book) {
    String sql = "INSERT INTO book (isbn, book_title, book_author, book_publisher, book_year, book_updated_time, book_create_time) VALUES (?, ?, ?, ?, ?, ?, ?)";
    JdbcTemplate.update(sql, book.getIsbn(), book.getTitle(), book.getAuthor(), book.getPublisher(), book.getYear(), dateTimeService.getCurrentDateTime(),dateTimeService.getCurrentDateTime());

    String sqlGetIsbn = "SELECT LAST_INSERT_ID()";
    String isbn = JdbcTemplate.queryForObject(sqlGetIsbn, String.class);

    book.setIsbn(isbn);
    return book;
}

public Book update(String isbn, Book book) {
    String sql = "UPDATE book SET book_title = ?,book_author = ?, book_publisher = ?, book_year = ?, book_updated_time = ?, book_create_time = ? WHERE isbn = ?";
    int rowAffected = JdbcTemplate.update(sql, book.getTitle(), book.getAuthor(), book.getPublisher(), book.getYear(), dateTimeService.getCurrentDateTime(), book.getCreateTime(), isbn);
    if(rowAffected <= 0) {
        throw new ResourceNotFoundException("Echec de la modification avec l'isbn " + isbn + ".");
    }
    return  this.findByIsbn(isbn);
}

public boolean delete(String isbn) {
    String sql = "DELETE FROM book WHERE isbn = ?";
    int rowsAffected = JdbcTemplate.update(sql,isbn);
    return rowsAffected > 0;
}
}