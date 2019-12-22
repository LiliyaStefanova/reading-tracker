package org.elstere.booktrkr.api.entities.common;


import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@SuperBuilder
@Data
public class GenrePayload {

    @NotNull
    @NotEmpty
    String name;

    String category;

    String description;
}
