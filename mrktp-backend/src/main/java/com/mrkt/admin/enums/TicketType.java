package com.mrkt.admin.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TicketType {

    Problem("What steps will reproduce the problem"),
    Question("Ask a question. We'll answer you promptly."),
    Feedback("Give us your feedback. We really appreciate it");


    private final String description;

    TicketType(String description) {

        this.description = description;
    }


    public static TicketType forValue(String value) {
        for (TicketType orderStatus : TicketType.values()) {
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
