package com.sana.database.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Author {
//    Long is an object, and it can be null, Where long is primitive and cant be null. Same for Integer and int
    private Long id;
    private String name;
    private Integer age;
}

// In Java Spring Boot, the @Builder annotation (from Lombok) is used to implement the Builder pattern,
// which simplifies object creation, especially for classes with many fields.
// It provides a clean way to construct immutable objects step by step.
// This avoids constructors with many parameters and improves readability.