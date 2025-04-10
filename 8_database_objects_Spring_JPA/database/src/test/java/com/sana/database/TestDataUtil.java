package com.sana.database;

import com.sana.database.domain.Author;
import com.sana.database.domain.Book;

//Util type classes are final and constructors are private.
public final class TestDataUtil {
    private TestDataUtil(){}

    public static Author createTestAuthorA() {
        return Author.builder()
//                .id(1L)
                .name("Sanaulla")
                .age(80)
                .build();
    }

    public static Author createTestAuthorB() {
        return Author.builder()
//                .id(2L)
                .name("Emon")
                .age(44)
                .build();
    }

    public static Author createTestAuthorC() {
        return Author.builder()
//                .id(3L)
                .name("Akib")
                .age(26)
                .build();
    }

    public static Book createTestBookA(final Author author) {
        return Book.builder()
                .isbn("123-4-5678-9-0")
                .title("The Shadow in the Attic")
                .author(author)
                .build();
    }

    public static Book createTestBookB(final Author author) {
        return Book.builder()
                .isbn("345-6-1234-1-1")
                .title("A Tale of Beauty")
                .author(author)
                .build();
    }

    public static Book createTestBookC(final Author author) {
        return Book.builder()
                .isbn("999-1-9876-3-0")
                .title("History Again")
                .author(author)
                .build();
    }
}