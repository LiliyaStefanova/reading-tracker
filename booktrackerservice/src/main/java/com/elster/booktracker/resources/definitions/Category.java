package com.elster.booktracker.resources.definitions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    private long id;

    @NotNull
    private String name;

    private String description;

    public Category(String name, String description){
        this(0L, name, description);
    }
}
