package com.mrkt.admin.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TicketStatus {

    New("New"),
    Working("Working"),
    Waiting("Waiting"),
    Closed("Closed"),
    Discarded("Discarded"),
    Draft("Draft");


    private final String description;

    TicketStatus(String description) {

        this.description = description;
    }


    public static TicketStatus forValue(String value) {
        for (TicketStatus orderStatus : TicketStatus.values()) {
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
