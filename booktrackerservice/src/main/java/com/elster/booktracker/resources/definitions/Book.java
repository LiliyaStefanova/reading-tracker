package com.elster.booktracker.resources.definitions;

import freemarker.template.SimpleDate;
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
    private String author;

    @NotNull
    private String genre;

    private String notes;

    @NotNull
    private String category;

    @NotNull
    private String status;

    @NotNull
    private boolean favourite;

    private SimpleDate dateRead;
}
