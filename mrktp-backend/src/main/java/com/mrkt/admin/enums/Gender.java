package com.mrkt.admin.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {

    Male("Male"),
    Female("Female");


    private final String description;

    Gender(String description) {

        this.description = description;
    }


    public static Gender forValue(String value) {
        for (Gender orderStatus : Gender.values()) {
            if (orderStatus.name().equals(value)) {
                return orderStatus;
            }
        }
        return null;
    }

    public String getDescription() {
        return description;
    }

    @Override
    @JsonValue
    public String toString() {
        return this.name();
    }
}
