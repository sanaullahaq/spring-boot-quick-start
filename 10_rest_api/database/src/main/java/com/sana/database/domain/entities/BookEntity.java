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

//cascade = CascadeType.ALL
//-it means, if we execute a crud operation on the parent then same crud operation will be executed to the child.
//-in our current implementation, in the assertThat section we are comparing object to object with isEqualTo(), but when we are creating an AuthorEntity
// we do need to put the id for an AuthorEntity, it is done by the JPA while saving the AuthorEntity in the db. but the `in-memory` AuthorEntity remains with id=null.
// so, to make isEqualTo() works properly we are saving the AuthorEntity object first then assign that AuthorEntity to the BookEntity then saving the book.
//-with saving the AuthorEntity first we are NOT leveraging the power of cascade = CascadeType.ALL, however we are keeping it.
//-also we need to be careful as an AuthorEntity may have multiple books, but deleting one book will delete the AuthorEntity as well.

//@JoinColumn(name = "author_id")
//Purpose: Specifies the foreign key column in the BookEntity table that joins it to the AuthorEntity table.
//So in the database, your book table will have a column named author_id that points to the primary key of the authorEntity table.