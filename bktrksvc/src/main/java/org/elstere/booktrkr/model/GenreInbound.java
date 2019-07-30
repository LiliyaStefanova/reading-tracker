package org.elstere.booktrkr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenreInbound {

    private String name;

    private String category;

    private String description;
}

