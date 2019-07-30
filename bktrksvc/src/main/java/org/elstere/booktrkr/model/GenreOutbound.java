package org.elstere.booktrkr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenreOutbound {

    private long id;

    @NotNull
    private String name;

    private String category;

    private String description;
}

