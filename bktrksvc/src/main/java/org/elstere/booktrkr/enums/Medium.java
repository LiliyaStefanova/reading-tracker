package org.elstere.booktrkr.enums;

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
