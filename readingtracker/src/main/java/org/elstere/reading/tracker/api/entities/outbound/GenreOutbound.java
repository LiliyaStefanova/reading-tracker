package org.elstere.reading.tracker.api.entities.outbound;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenreOutbound {

    private UUID id;

    @NotNull
    private String name;

    private String category;

    private String description;

    private Timestamp created_ts;
}

