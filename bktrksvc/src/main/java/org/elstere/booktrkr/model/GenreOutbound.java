package org.elstere.booktrkr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenreOutbound {

    private UUID id;

    @NotNull
    private String name;

    private String category;

    private String description;

    private Timestamp created_ts;
}

