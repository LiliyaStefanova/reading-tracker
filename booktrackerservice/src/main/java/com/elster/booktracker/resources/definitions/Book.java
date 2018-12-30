package com.elster.booktracker.resources.definitions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private long id;

    @NotNull
    private String title;

    @NotNull
    private long author;

    @NotNull
    private long category;

    @NotNull
    private String genre;

    private String notes;

    @NotNull
    private String status;

    private boolean favourite;

}
