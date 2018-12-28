package com.elster.booktracker.resources.definitions;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Category {

    private long id;

    @NotNull
    private String name;

    private String description;
}
