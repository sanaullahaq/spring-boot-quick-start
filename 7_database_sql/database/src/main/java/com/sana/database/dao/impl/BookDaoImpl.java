package com.sana.database.dao.impl;

import com.sana.database.dao.BookDao;
import com.sana.database.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class BookDaoImpl implements BookDao {
    private final JdbcTemplate jdbcTemplate;

    public BookDaoImpl(final JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    @Override
    public void create(Book book) {
        jdbcTemplate.update("INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)",
                book.getIsbn(),
                book.getTitle(),
                book.getAuthorId());
    }

    public static class BookRowMapper implements RowMapper<Book>{

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Book.builder()
                    .isbn(rs.getString("isbn"))
                    .title(rs.getString("title"))
                    .authorId(rs.getLong("author_id"))
                    .build();
        }
    }

    @Override
    public Optional<Book> findOne(String isbn) {
        List<Book> results = jdbcTemplate.query(
                "SELECT isbn, title, author_id FROM books where isbn = ? LIMIT 1",
                new BookRowMapper(), isbn);
        return results.stream().findFirst();
    }

    @Override
    public List<Book> find() {
        return jdbcTemplate.query(
                "Select isbn, title, author_id From books",
                new BookRowMapper());
    }

    @Override
    public void update(String isbn, Book bookA) {
        jdbcTemplate.update("Update books SET isbn = ?, title = ?, author_id = ? Where isbn = ?",
                bookA.getIsbn(), bookA.getTitle(), bookA.getAuthorId(), isbn);
    }

    @Override
    public void delete(String isbn) {
        jdbcTemplate.update("DELETE FROM books Where isbn = ?", isbn);
    }
}