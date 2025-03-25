package com.sana.database.dao.impl;

import com.sana.database.TestDataUtil;
import com.sana.database.dao.AuthorDao;
import com.sana.database.domain.Author;
import com.sana.database.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)//This will clear the Context (including DB) after each test method run
public class BookDaoImplIntegrationTests {

    private AuthorDao authorDao;
    private BookDaoImpl undertest;

    @Autowired
    public BookDaoImplIntegrationTests(BookDaoImpl undertest, AuthorDao authorDao){
        this.undertest = undertest;
        this.authorDao = authorDao;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.createTestAuthorA();
        authorDao.create(author);

        Book book = TestDataUtil.createTestBookA();
        book.setAuthorId(author.getId());
        undertest.create(book);
        Optional<Book> result = undertest.findOne(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled(){
        Author authorA = TestDataUtil.createTestAuthorA();
        authorDao.create(authorA);
        Book bookA = TestDataUtil.createTestBookA();
        bookA.setAuthorId(authorA.getId());
        undertest.create(bookA);

        Author authorB = TestDataUtil.createTestAuthorB();
        authorDao.create(authorB);
        Book bookB = TestDataUtil.createTestBookB();
        bookB.setAuthorId(authorB.getId());
        undertest.create(bookB);

        Author authorC = TestDataUtil.createTestAuthorC();
        authorDao.create(authorC);
        Book bookC = TestDataUtil.createTestBookC();
        bookC.setAuthorId(authorC.getId());
        undertest.create(bookC);

        List<Book> result = undertest.find();
        assertThat(result)
                .hasSize(3)
                .containsExactly(bookA, bookB, bookC);
    }
}
