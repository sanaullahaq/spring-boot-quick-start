package com.sana.database.dao.impl;

import com.sana.database.dao.AuthorDao;
import com.sana.database.domain.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoImpl(final JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    @Override
    public void create(Author author) {
        jdbcTemplate.update(
                "INSERT INTO authors (id, name, age) VALUES (?, ?, ?)",
                author.getId(),
                author.getName(),
                author.getAge()
        );
    }

    public static class AuthorRowMapper implements RowMapper<Author>{

        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Author.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .age(rs.getInt("age"))
                    .build();
        }
    }

    @Override
    public Optional<Author> findOne(long authorId) {
//        return Optional.empty();
        List<Author> results = jdbcTemplate.query(
                "SELECT id, name, age FROM authors where id = ? LIMIT 1",
                new AuthorRowMapper(), authorId);
        return results.stream().findFirst();
    }

    @Override
    public List<Author> find() {
        return jdbcTemplate.query(
                "Select id, name, age From authors",
                new AuthorRowMapper());
    }

    @Override
    public void update(long id, Author author) {
        jdbcTemplate.update("Update authors SET id = ?, name = ?, age = ? Where id = ?",
                author.getId(), author.getName(), author.getAge(), id);
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update("DELETE From authors Where id = ?", id);
    }
}
