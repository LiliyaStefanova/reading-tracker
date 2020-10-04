package org.elstere.reading.tracker.api.entities.outbound;

import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.elstere.reading.tracker.api.entities.common.AuthorPayload;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class AuthorOutbound extends AuthorPayload {

    private UUID id;
}
