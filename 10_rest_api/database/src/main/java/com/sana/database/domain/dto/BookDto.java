package com.sana.database.domain.dto;

import com.sana.database.domain.entities.AuthorEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {
    private String isbn;
    private String title;
    private AuthorEntity author;
}
//DTO - Data Transfer Object