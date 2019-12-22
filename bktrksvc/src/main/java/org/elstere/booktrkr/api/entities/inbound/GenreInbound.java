package org.elstere.booktrkr.api.entities.inbound;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.elstere.booktrkr.api.entities.common.GenrePayload;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class GenreInbound extends GenrePayload {

}

