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
@Table(name="books")
public class BookEntity {
    @Id
    //isbn will be provided by the user each time
    private String isbn;

    private String title;

    //With JPA, we can deal with the AuthorEntity Object rather than just author_id
    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorEntity authorEntity;
}

//@ManyToOne
//Purpose: Defines a many-to-one relationship between two entities.
//In our example, many books can be written by one authorEntity

//@JoinColumn(name = "author_id")
//Purpose: Specifies the foreign key column in the BookEntity table that joins it to the AuthorEntity table.
//So in the database, your book table will have a column named author_id that points to the primary key of the authorEntity table.