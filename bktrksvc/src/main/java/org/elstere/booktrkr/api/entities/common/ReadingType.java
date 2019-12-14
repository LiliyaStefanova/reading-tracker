package org.elstere.booktrkr.api.entities.common;

public enum ReadingType {

    BOOK("book"),
    PAPER("paper"),
    JOURNAL("journal"),
    ARTICLE("article");

    private String type;

    ReadingType(String type) {
        this.type = type;
    }

    static ReadingType valueFrom(String type) {
        for (ReadingType value : values()) {
            if (type.equals(value.type)) {
                return value;
            }
        }
        throw new IllegalArgumentException(
                "'" + type + "' is not a valid event type");
    }
}
