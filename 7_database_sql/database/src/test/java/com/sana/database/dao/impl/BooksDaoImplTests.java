package com.sana.database.dao.impl;

import com.sana.database.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BooksDaoImplTests {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookDaoImpl underTest;

    @Test
    public void testThatCreateBookGeneratesCorrectSql(){
        Book book = Book.builder()
                .isbn("123-4-5678-9-0")
                .title("The Shadow in the Attic")
                .authorId(1L)
                .build();
        underTest.create(book);
        verify(jdbcTemplate).update(
                eq("INSERT INTO books (isbn, title, authorId) VALUES (?, ?, ?)"),
                eq("123-4-5678-9-0"), eq("The Shadow in the Attic"), eq(1L)
        );
    }

    @Test
    public void testThatFindOneBookGeneratesCorrectSql(){
        underTest.findOne("123-4-5678-9-0");
        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, authorId FROM books where isbn = ? LIMIT 1"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any(), eq("123-4-5678-9-0"));
    }
}
