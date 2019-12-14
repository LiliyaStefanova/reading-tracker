package org.elstere.booktrkr.api.entities.inbound;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.elstere.booktrkr.api.entities.common.ReadingEntryPayload;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class ReadingEntryInbound extends ReadingEntryPayload {

}
