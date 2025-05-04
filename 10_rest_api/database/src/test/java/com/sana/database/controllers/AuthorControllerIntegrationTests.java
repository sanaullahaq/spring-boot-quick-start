/*@Autowired annotation: https://stackoverflow.com/questions/76896286/when-should-i-use-spring-bean-and-use-autowired-in-it */
package com.sana.database.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sana.database.TestDataUtil;
import com.sana.database.domain.dto.AuthorDto;
import com.sana.database.domain.entities.AuthorEntity;
import com.sana.database.services.AuthorService;
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

import java.util.Dictionary;
import java.util.Hashtable;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class AuthorControllerIntegrationTests {
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final AuthorService authorService;

    @Autowired
    public AuthorControllerIntegrationTests(MockMvc mockMvc, AuthorService authorService) {
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
        this.authorService = authorService;
    }

    @Test
    public void testThatCreateAuthorSuccessfullyReturnsHttp201Created() throws Exception {
        AuthorDto authorDtoA = TestDataUtil.createTestAuthorDtoA();
        authorDtoA.setId(null);
        String authorJson = objectMapper.writeValueAsString(authorDtoA);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateAuthorSuccessfullyReturnsSavedAuthor() throws Exception {
        AuthorDto authorDtoA = TestDataUtil.createTestAuthorDtoA();
        authorDtoA.setId(null);
        String authorJson = objectMapper.writeValueAsString(authorDtoA);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Sanaulla")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(80)
        );
    }

    @Test
    public void testThatListAuthorsReturnsHttpStatus200() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatListAuthorsReturnsListOfAuthors() throws Exception{
        AuthorEntity authorEntityA = TestDataUtil.createTestAuthorEntityA();
        AuthorEntity authorEntityB = TestDataUtil.createTestAuthorEntityB();
        authorService.save(authorEntityA);
        authorService.save(authorEntityB);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].name").value("Sanaulla")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].age").value(80)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.size()").value(2)
        );
    }

    @Test
    public void testThatGetAuthorReturnsHttpStatus200WhenAuthorExist() throws Exception {
        AuthorEntity authorEntityC = TestDataUtil.createTestAuthorEntityC();
        AuthorEntity savedAuthorEntity = authorService.save(authorEntityC);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/%s".formatted(savedAuthorEntity.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatGetAuthorReturnsAuthorWhenAuthorExist() throws Exception {
        AuthorEntity authorEntityB = TestDataUtil.createTestAuthorEntityB();
        AuthorEntity savedAuthorEntity = authorService.save(authorEntityB);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/%s".formatted(savedAuthorEntity.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(savedAuthorEntity.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Emon")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(44)
        );
    }

    @Test
    public void testThatGetAuthorReturnsHttpStatus404WhenNoAuthorExists() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/99")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatFullUpdateAuthorReturnsHttpStatus404WhenNoAuthorExists() throws Exception {
        AuthorDto authorDtoC = TestDataUtil.createTestAuthorDtoC();
        String authorEntityJson = objectMapper.writeValueAsString(authorDtoC);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/authors/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorEntityJson)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatFullUpdateAuthorReturnsHttpStatus200WhenAuthorExists() throws Exception {
        AuthorEntity authorEntityB = TestDataUtil.createTestAuthorEntityB();
        AuthorEntity savedAuthorEntityB = authorService.save(authorEntityB);
        String authorEntityJson = objectMapper.writeValueAsString(authorEntityB);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/authors/"+savedAuthorEntityB.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorEntityJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatFullUpdateUpdatesExistingAuthor() throws Exception {
        AuthorEntity authorEntityB = TestDataUtil.createTestAuthorEntityB();
        authorEntityB.setName("Test Name");
        AuthorEntity savedAuthorEntityB = authorService.save(authorEntityB);
        String authorEntityJson = objectMapper.writeValueAsString(savedAuthorEntityB);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/authors/"+savedAuthorEntityB.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorEntityJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value(authorEntityB.getName())
        );
    }

    @Test
    public void testThatPartialUpdateExistingAuthorReturnsHttpStatus20Ok() throws Exception {
        AuthorEntity authorEntityA = TestDataUtil.createTestAuthorEntityA();
        AuthorEntity savedAuthor = authorService.save(authorEntityA);

        Dictionary<String, String> partialData = new Hashtable<>();
        partialData.put("name", "Partially Updated");
        String partialString = objectMapper.writeValueAsString(partialData);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/authors/"+savedAuthor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(partialString)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );        
    }

    @Test
    public void testThatPartialUpdateExistingAuthorReturnsUpdatedAuthor() throws Exception {
        AuthorEntity authorEntityA = TestDataUtil.createTestAuthorEntityA();
        AuthorEntity savedAuthor = authorService.save(authorEntityA);

        Dictionary<String, String> partialDict = new Hashtable<>();
        partialDict.put("name", "partial updated");
        String jsonString = objectMapper.writeValueAsString(partialDict);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/authors/" + savedAuthor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(savedAuthor.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value(partialDict.get("name"))
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(savedAuthor.getAge())
        );
    }

    @Test
    public void testThatDeleteAuthorReturnsHttpStatus404ForNonExistingAuthor() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/authors/99")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatDeleteAuthorReturnsHttpStatus204ForExistingAuthor() throws Exception {
        AuthorEntity savedAuthorA = authorService.save(TestDataUtil.createTestAuthorEntityA());
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/authors/"+savedAuthorA.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNoContent()
        );
    }
}