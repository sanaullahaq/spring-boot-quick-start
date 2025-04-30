package com.sana.database.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "authors")
public class AuthorEntity {
//    Long is an object, and it can be null, Where long is primitive and cant be null. Same for Integer and int

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_id_seq")
    @SequenceGenerator(name = "author_id_seq", sequenceName = "author_id_seq", allocationSize = 1)
    private Long id;
    private String name;
    private Integer age;
}

// In Java Spring Boot, the @Builder annotation (from Lombok) is used to implement the Builder pattern,
// which simplifies object creation, especially for classes with many fields.
// It provides a clean way to construct immutable objects step by step.
// This avoids constructors with many parameters and improves readability.

//@Entity
//Purpose: Marks this class as a JPA entity — meaning it's mapped to a table in your database.
//Usage: You put this on a class to tell Hibernate (or any JPA provider) that this is a table.
//Example: If you have a class AuthorEntity, and it's annotated with @Entity, Hibernate will know it should create or manage a table for it.

//@Table(name = "authors")
//Purpose: Specifies the name of the table in the database that this entity maps to.
//Why it's useful: If your entity is named AuthorEntity but your database table is authors, this annotation handles that mismatch.
//Without it: The table name would default to the class name (AuthorEntity) by default.

//@Id
//Purpose: Marks a field as the primary key.
//Every JPA entity needs exactly one field with this annotation.
//It’s required to uniquely identify each record in the table.

//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_id_seq")
//Purpose: Specifies how the primary key value is generated.
//GenerationType.SEQUENCE: Uses a database sequence to generate unique values.
//generator = "author_id_seq": Refers to a sequence generator that should be defined elsewhere using @SequenceGenerator.

//@SequenceGenerator defines the name and the actual database sequence name.
//allocationSize = 1 ensures IDs are allocated one by one (this is important for keeping them in sync with DB-generated sequences).