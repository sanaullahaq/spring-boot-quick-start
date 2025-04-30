package com.sana.database;

import com.sana.database.domain.dto.AuthorDto;
import com.sana.database.domain.dto.BookDto;
import com.sana.database.domain.entities.AuthorEntity;
import com.sana.database.domain.entities.BookEntity;

//Util type classes are final and constructors are private.
public final class TestDataUtil {
    private TestDataUtil(){}

    public static AuthorDto createTestAuthorDtoA() {
        return AuthorDto.builder()
//                .id(1L)
                .name("Sanaulla")
                .age(80)
                .build();
    }

    public static AuthorEntity createTestAuthorEntityA() {
        return AuthorEntity.builder()
//                .id(1L)
                .name("Sanaulla")
                .age(80)
                .build();
    }

    public static AuthorDto createTestAuthorDtoB() {
        return AuthorDto.builder()
//                .id(2L)
                .name("Emon")
                .age(44)
                .build();
    }

    public static AuthorEntity createTestAuthorEntityB() {
        return AuthorEntity.builder()
//                .id(2L)
                .name("Emon")
                .age(44)
                .build();
    }

    public static AuthorDto createTestAuthorDtoC() {
        return AuthorDto.builder()
//                .id(3L)
                .name("Akib")
                .age(26)
                .build();
    }

    public static AuthorEntity createTestAuthorEntityC() {
        return AuthorEntity.builder()
//                .id(3L)
                .name("Akib")
                .age(26)
                .build();
    }


    public static BookDto createTestBookDtoA(final AuthorEntity authorEntity) {
        return BookDto.builder()
                .isbn("123-4-5678-9-0")
                .title("The Shadow in the Attic")
                .author(authorEntity)
                .build();
    }

    public static BookEntity createTestBookEntityA(final AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("123-4-5678-9-0")
                .title("The Shadow in the Attic")
                .authorEntity(authorEntity)
                .build();
    }

    public static BookDto createTestBookDtoB(final AuthorEntity authorDto) {
        return BookDto.builder()
                .isbn("345-6-1234-1-1")
                .title("A Tale of Beauty")
                .author(authorDto)
                .build();
    }

    public static BookEntity createTestBookEntityB(final AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("345-6-1234-1-1")
                .title("A Tale of Beauty")
                .authorEntity(authorEntity)
                .build();
    }

    public static BookDto createTestBookDtoC(final AuthorEntity authorEntity) {
        return BookDto.builder()
                .isbn("999-1-9876-3-0")
                .title("History Again")
                .author(authorEntity)
                .build();
    }
}