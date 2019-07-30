package org.elstere.booktrkr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadingOutbound {

    private UUID id;

    private String title;

    private String authors;

    private String genre;

    private ReadingType type;

    private Medium medium;

    //TODO enum or db table
    private String language;

    //TODO make this a DB entity
    private String publisher;

    private String edition;

    public enum Medium {

        LETTERS("letters"),
        AUDIO("audio");

        private String medium;

        Medium(String medium) {
            this.medium = medium;
        }

        static Medium valueFrom(String medium) {
            for (Medium value : values()) {
                if (medium.equals(value.medium)) {
                    return value;
                }
            }
            throw new IllegalArgumentException(
                    "'" + medium + "' is not a supported medium");
        }
    }
}
