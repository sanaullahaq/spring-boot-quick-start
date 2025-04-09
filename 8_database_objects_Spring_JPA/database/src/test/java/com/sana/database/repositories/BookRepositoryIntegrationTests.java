package com.sana.database.repositories;

import com.sana.database.TestDataUtil;
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
public class BookRepositoryIntegrationTests {

    private AuthorRepository authorRepo;
    private BookRepository undertest;

    @Autowired
    public BookRepositoryIntegrationTests(BookRepository undertest, AuthorRepository authorRepo){
//    public BookRepositoryIntegrationTests(BookRepository undertest){
        this.undertest = undertest;
        this.authorRepo = authorRepo;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled(){
        Author authorA = TestDataUtil.createTestAuthorA();
        authorRepo.save(authorA);
        Book book = TestDataUtil.createTestBookA(authorA);
        undertest.save(book);
        Optional<Book> result = undertest.findById(book.getIsbn());

        System.out.println(result);

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled(){
        Author authorA = TestDataUtil.createTestAuthorA();
        authorRepo.save(authorA);

        Book bookA = TestDataUtil.createTestBookA(authorA);
        undertest.save(bookA);

        Book bookB = TestDataUtil.createTestBookB(authorA);
        undertest.save(bookB);

        Book bookC = TestDataUtil.createTestBookC(authorA);
        undertest.save(bookC);

        Iterable<Book> result = undertest.findAll();

        System.out.println(result);

        assertThat(result)
                .hasSize(3)
                .containsExactly(bookA, bookB, bookC);
    }

    @Test
    public void testThatBookCanBeUpdated(){
        Author authorA = TestDataUtil.createTestAuthorA();
        authorRepo.save(authorA);
        Author authorB = TestDataUtil.createTestAuthorB();
        authorRepo.save(authorB);

        Book bookA = TestDataUtil.createTestBookA(authorA);
        undertest.save(bookA);

        bookA.setTitle("Updated");
        bookA.setAuthor(authorB);

        undertest.save(bookA);
        Optional<Book> result = undertest.findById(bookA.getIsbn());

        System.out.println(result);

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookA);
    }

    @Test
    public void testThatBookCanBeDeleted(){
        Author authorA = TestDataUtil.createTestAuthorA();
        authorRepo.save(authorA);
        Book bookA = TestDataUtil.createTestBookA(authorA);
        undertest.save(bookA);

        undertest.delete(bookA);
        Optional<Book> result = undertest.findById(bookA.getIsbn());

        System.out.println(result);

        assertThat(result).isEmpty();
    }
}