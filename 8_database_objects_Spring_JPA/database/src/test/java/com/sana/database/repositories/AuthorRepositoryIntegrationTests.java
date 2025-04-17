package com.sana.database.repositories;

import com.sana.database.TestDataUtil;
import com.sana.database.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)//This will clear the Context (including DB) after each test method run
public class AuthorRepositoryIntegrationTests {

    private AuthorRepository underTest;

    @Autowired
    public AuthorRepositoryIntegrationTests(AuthorRepository underTest){
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.createTestAuthorA();
        underTest.save(author);
        Optional<Author> result = underTest.findById(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled(){
        Author authorA = TestDataUtil.createTestAuthorA();
        underTest.save(authorA);
        Author authorB = TestDataUtil.createTestAuthorB();
        underTest.save(authorB);
        Author authorC = TestDataUtil.createTestAuthorC();
        underTest.save(authorC);

        Iterable<Author> result = underTest.findAll();
        System.out.println(result);
//        assertThat(result).hasSize(3);
//        assertThat(result).containsExactly(authorA, authorB, authorC);
        assertThat(result)
                .hasSize(3)
                .containsExactly(authorA, authorB, authorC);
    }

    @Test
    public void testThatAuthorCanBeUpdated(){
        Author authorA = TestDataUtil.createTestAuthorA();

        underTest.save(authorA);
        System.out.println(authorA);
        authorA.setName("Updated");

        underTest.save(authorA);
        Optional<Author> result = underTest.findById(authorA.getId());
        System.out.println(result);

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorA);
    }

    @Test
    public void testThatAuthorCanBeDeleted(){
        Author authorB = TestDataUtil.createTestAuthorB();
        underTest.save(authorB);
        underTest.delete(authorB);
        Optional<Author> result = underTest.findById(authorB.getId());
        System.out.println(result);
        assertThat(result).isEmpty();
    }

    @Test
    public void testThatGetAuthorsWithAgeLessThan(){
        Author authorA = TestDataUtil.createTestAuthorA();
        Author authorB = TestDataUtil.createTestAuthorB();
        Author authorC = TestDataUtil.createTestAuthorC();

        underTest.save(authorA);
        underTest.save(authorB);
        underTest.save(authorC);

        Iterable<Author> result = underTest.ageLessThan(50);
        // custom query example
        // no definition of ageLessThan() has been implemented, only the declaration in the interface.
        // but Spring JPA is as smarter as it can understand what the function should do from its name.
        // and it works
        assertThat(result).containsExactly(authorB, authorC);
    }

    @Test
    public void testThatGetAuthorsWithAgeGreaterThan(){
        Author authorA = TestDataUtil.createTestAuthorA();
        Author authorB = TestDataUtil.createTestAuthorB();
        Author authorC = TestDataUtil.createTestAuthorC();

        underTest.save(authorA);
        underTest.save(authorB);
        underTest.save(authorC);

        Iterable<Author> result = underTest.findAuthorsWithAgeGreaterThan(50);
        //HQL (Hibernate Query Language) example
        // no definition of findAuthorsWithAgeGreaterThan() has been implemented, only the declaration in the interface.
        // but Spring JPA's smartness can't understand what the function should do from its complicated/ name.
        // that's why in the functions declaration we need to provide hint in HQL format to the Spring JPA.
        assertThat(result).containsExactly(authorA);
    }
}
