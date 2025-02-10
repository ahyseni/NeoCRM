package com.mrkt.admin.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TicketSeverity {

    High("High"),
    Normal("Normal"),
    Critical("Critical");

    private final String description;

    TicketSeverity(String description) {

        this.description = description;
    }


    public static TicketSeverity forValue(String value) {
        for (TicketSeverity orderStatus : TicketSeverity.values()) {
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
