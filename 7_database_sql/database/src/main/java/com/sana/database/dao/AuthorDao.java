package com.sana.database.dao;

import com.sana.database.domain.Author;

import java.util.Optional;

public interface AuthorDao {
    void create(Author author);

    Optional<Author> findOne(long authorId);
}
