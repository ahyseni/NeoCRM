package com.mrkt.admin.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TicketSource {

    Call("Call"),
    Visit("Visit"),
    Email("Email");


    private final String description;

    TicketSource(String description) {

        this.description = description;
    }


    public static TicketSource forValue(String value) {
        for (TicketSource orderStatus : TicketSource.values()) {
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
