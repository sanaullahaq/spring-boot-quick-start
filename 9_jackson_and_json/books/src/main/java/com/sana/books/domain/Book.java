package com.sana.books.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
    private String isbn;
    private String title;
    private String author;

    @JsonProperty("year")
    // this will rename `yearPublished` attribute to `year` while create Json from Java Object
    // and will expect a `year` attribute while create Java Object from Json object
    // if we don't use this annotation default attribute names will be used
    private String yearPublished;
}