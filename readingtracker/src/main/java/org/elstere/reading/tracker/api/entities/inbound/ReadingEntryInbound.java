package org.elstere.reading.tracker.api.entities.inbound;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.elstere.reading.tracker.api.entities.common.ReadingEntryPayload;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class ReadingEntryInbound extends ReadingEntryPayload {

}
