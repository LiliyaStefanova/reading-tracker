package org.elstere.reading.tracker.api.entities.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.elstere.reading.tracker.enums.Medium;

@Data
@AllArgsConstructor
@SuperBuilder
public class ReadingEntryPayload {

    private String title;
    private String authors;
    private String genre;
    private ReadingType type;
    private Medium medium;
    //TODO enum or DB entity
    private String language;
    //TODO make this a DB entity
    private String publisher;
    private String edition;
}
