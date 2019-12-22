package org.elstere.booktrkr.api.entities.outbound;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.elstere.booktrkr.api.entities.common.GenrePayload;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class GenreOutbound extends GenrePayload {

    private UUID id;

    private Timestamp created_ts;
}

