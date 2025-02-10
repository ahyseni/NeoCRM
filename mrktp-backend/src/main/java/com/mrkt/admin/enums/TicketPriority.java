package com.mrkt.admin.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TicketPriority {

    High("High"),
    Normal("Normal"),
    Low("Low");


    private final String description;

    TicketPriority(String description) {

        this.description = description;
    }


    public static TicketPriority forValue(String value) {
        for (TicketPriority orderStatus : TicketPriority.values()) {
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
