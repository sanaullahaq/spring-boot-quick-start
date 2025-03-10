package com.sana.database.dao;

import com.sana.database.domain.Book;

import java.util.Optional;

public interface BookDao {
    void create(Book book);

    Optional<Book> findOne(String isbn);
}