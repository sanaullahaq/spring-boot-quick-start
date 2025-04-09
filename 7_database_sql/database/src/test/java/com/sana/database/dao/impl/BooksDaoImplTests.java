//Unit Test

package com.sana.database.dao.impl;

import com.sana.database.TestDataUtil;
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
        Book book = TestDataUtil.createTestBookA();
        underTest.create(book);
        verify(jdbcTemplate).update(
                eq("INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)"),
                eq("123-4-5678-9-0"), eq("The Shadow in the Attic"), eq(1L)
        );
    }

    @Test
    public void testThatFindOneGeneratesCorrectSql(){
        underTest.findOne("123-4-5678-9-0");
        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id FROM books where isbn = ? LIMIT 1"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any(), eq("123-4-5678-9-0"));
    }

    @Test
    public void testThatFindManyGeneratesCorrectSql(){
        underTest.find();
        verify(jdbcTemplate).query(
                eq("Select isbn, title, author_id From books"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any()
        );
    }

    @Test
    public void testThatUpdateGeneratesCorrectSql(){
        Book bookA = TestDataUtil.createTestBookA();
        String isbn = "123-4-5678-9-0";

        underTest.update(isbn, bookA);
        verify(jdbcTemplate).update("Update books SET isbn = ?, title = ?, author_id = ? Where isbn = ?",
                "123-4-5678-9-0", "The Shadow in the Attic", 1L, isbn);
    }

    @Test
    public void testThatDeleteGeneratesCorrectSql(){
        underTest.delete("123-4-5678-9-0");
        verify(jdbcTemplate).update("DELETE FROM books Where isbn = ?", "123-4-5678-9-0");
    }
}
