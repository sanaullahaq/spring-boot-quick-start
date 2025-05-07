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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private AuthorEntity authorEntity;
}

//@ManyToOne
//Purpose: Defines a many-to-one relationship between two entities.
//In our example, many books can be written by one authorEntity

/* *********************** In the Tutorial the creator used @ManyToOne(cascade = CascadeType.ALL) what we have ignored for this project.
* Thus nested Object part also ignored for this.
*
* If we use @ManyToOne(cascade = CascadeType.ALL) in the current project we don't need to modify the
* MapperConfig further to set modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
* to achieve nested object creating.For example a new Author can created while creating a book, that author does not exists previously.
*
* We can achieve this by only add @ManyToOne(cascade = CascadeType.ALL) and no need to modify MapperConfig class
************************* */

//@JoinColumn(name = "author_id")
//Purpose: Specifies the foreign key column in the BookEntity table that joins it to the AuthorEntity table.
//So in the database, your book table will have a column named author_id that points to the primary key of the authorEntity table.