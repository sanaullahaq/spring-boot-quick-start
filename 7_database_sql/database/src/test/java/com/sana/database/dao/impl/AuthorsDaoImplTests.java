//Unit Test

package com.sana.database.dao.impl;

import com.sana.database.TestDataUtil;
import com.sana.database.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuthorsDaoImplTests{
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private AuthorDaoImpl underTest;

//    @Test Annotation (JUnit 5)
//    The @Test annotation in JUnit 5 is used to mark a method as a test case. When you run your test class, JUnit will execute all methods annotated with @Test.
    @Test
    public void testThatCreateAuthorGeneratesCorrectSql(){
        Author author = TestDataUtil.createTestAuthorA();

        underTest.create(author);
        verify(jdbcTemplate).update(
                eq("INSERT INTO authors (id, name, age) VALUES (?, ?, ?)"),
                eq(1L), eq("Sanaulla"), eq(27)
        );
    }

    @Test
    public void testThatFindOneGeneratesCorrectSql(){
        underTest.findOne(1L);
        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors where id = ? LIMIT 1"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(),
                eq(1L));
    }

    @Test
    public void testThatFindManyGeneratesCorrectSql(){
        underTest.find();
        verify(jdbcTemplate).query(
                eq("Select id, name, age From authors"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any()
        );
    }

    @Test
    public void testThatUpdateGeneratesCorrectSql(){
        Author authorA = TestDataUtil.createTestAuthorA();
        long id = 3L;
        underTest.update(id, authorA);
        verify(jdbcTemplate).update("Update authors SET id = ?, name = ?, age = ? Where id = ?",
                1L, "Sanaulla", 27, id);
    }

    @Test
    public void testThatDeleteGeneratesCorrectSql(){
        underTest.delete(1L);
        verify(jdbcTemplate).update("DELETE From authors Where id = ?", 1L);
    }
}



//@ExtendWith Annotation (JUnit 5)
//@ExtendWith is a JUnit 5 annotation used to register extensions, which allow additional behavior (like mocking, logging, or dependency injection)
// to be added to test cases. It replaces the @RunWith annotation from JUnit 4.

//Example:
/*
@ExtendWith(MyCustomExtension.class)
class MyTest {
     Test methods
}
*/
//Here, MyCustomExtension is a custom extension that modifies the test execution.

//@ExtendWith(MockitoExtension.class)
//When you use @ExtendWith(MockitoExtension.class), you are integrating Mockito with JUnit 5. This enables features like:

// - Automatic initialization of @Mock fields
// - Avoiding the need for MockitoAnnotations.initMocks(this);
// - Enabling @InjectMocks to inject dependencies into mocks
//Example:

/*
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    // Test methods
}
*/

//Here, MockitoExtension automatically initializes @Mock and @InjectMocks, making testing easier.