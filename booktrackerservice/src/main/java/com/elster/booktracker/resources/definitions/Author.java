package com.elster.booktracker.resources.definitions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    private long id;

    @NotNull
    private String name;

    private String bio;

    private String notes;

    public Author(String name, String bio, String notes){
        this(0L, name, bio, notes);
    }
}
