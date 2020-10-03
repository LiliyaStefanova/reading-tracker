package org.elstere.reading.tracker.api.entities.outbound;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.elstere.reading.tracker.api.entities.common.ReadingEntryPayload;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class ReadingEntryOutbound extends ReadingEntryPayload {

    private UUID id;

}
