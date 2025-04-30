package com.sana.database.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sana.database.TestDataUtil;
import com.sana.database.domain.dto.BookDto;
import com.sana.database.domain.entities.BookEntity;
import com.sana.database.services.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.nullValue;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class BookControllerIntegrationTests {
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final BookService bookService;

    @Autowired
    public BookControllerIntegrationTests(MockMvc mockMvc, BookService bookService) {
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
        this.bookService = bookService;

    }

    @Test
    public void testThatCreateBookReturnsHttpStatus201Created() throws Exception {
        BookDto testBookDtoA = TestDataUtil.createTestBookDtoA(
                TestDataUtil.createTestAuthorEntityA());

        String isbn = testBookDtoA.getIsbn();
        String bookJson = objectMapper.writeValueAsString(testBookDtoA);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/%s".formatted(isbn))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatUpdateBookReturnsHttpStatus200Ok() throws Exception {
        BookEntity testBookEntityB = TestDataUtil.createTestBookEntityB(
                TestDataUtil.createTestAuthorEntityB());

        String isbn = testBookEntityB.getIsbn();
        bookService.save(isbn, testBookEntityB);

        testBookEntityB.setTitle("Updated title");
        String updatedBookJson = objectMapper.writeValueAsString(testBookEntityB);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/%s".formatted(isbn))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedBookJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value("Updated title")
        );
    }

    @Test
    public void testThatCreateBookReturnsCreatedBook() throws Exception {
        BookDto testBookDtoA = TestDataUtil.createTestBookDtoA(
                TestDataUtil.createTestAuthorEntityA());

        String isbn = testBookDtoA.getIsbn();
        String bookJson = objectMapper.writeValueAsString(testBookDtoA);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/%s".formatted(isbn))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").isString()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value("The Shadow in the Attic")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.author.name").value("Sanaulla")
        );
    }

    @Test
    public void testThatListBooksReturnsHttpStatus200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatListBooksReturnsListOfBooks() throws Exception {
        BookEntity bookEntityA = TestDataUtil.createTestBookEntityA(null);
        bookEntityA.setIsbn("999-888-777");
        bookService.save(bookEntityA.getIsbn(), bookEntityA);

        BookEntity bookEntityB = TestDataUtil.createTestBookEntityB(null);
        bookEntityB.setIsbn("777-888-999");
        bookService.save(bookEntityB.getIsbn(), bookEntityB);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.size()").value(2)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].isbn").value("999-888-777")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].isbn").value("777-888-999")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].title").value("The Shadow in the Attic")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].title").value("A Tale of Beauty")
        );


    }
    @Test
    public void testThatGetBookReturnsHttpStatus200WhenBookExist() throws Exception {
        BookEntity bookEntityA = TestDataUtil.createTestBookEntityA(null);
        bookEntityA.setIsbn("999-888-777");
        bookService.save(bookEntityA.getIsbn(), bookEntityA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/%s".formatted(bookEntityA.getIsbn()))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }
    @Test
    public void testThatGetBookReturnsBookWhenBookExist() throws Exception {
        BookEntity bookEntityA = TestDataUtil.createTestBookEntityA(null);
        bookService.save(bookEntityA.getIsbn(), bookEntityA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/%s".formatted(bookEntityA.getIsbn()))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value("123-4-5678-9-0")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value("The Shadow in the Attic")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.author").value(nullValue())
        );
    }

    @Test
    public void testThatGetBookReturnsHttpStatus404WhenNoBookExists() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/falseisbn")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }
}