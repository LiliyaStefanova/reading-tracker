package org.elstere.booktrkr.api.entities.inbound;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class AuthorInbound {

    private String name;

    private String bio;

    private String website;

    private String notes;

}
