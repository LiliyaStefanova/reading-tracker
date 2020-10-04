package org.elstere.reading.tracker.api.entities.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@SuperBuilder
public class AuthorPayload {

    private String name;

    private String bio;

    private String website;

    private String notes;

}
