package org.elstere.booktrkr.api.entities.outbound;

import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.elstere.booktrkr.api.entities.common.AuthorPayload;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class AuthorOutbound extends AuthorPayload {

    private UUID id;
}
