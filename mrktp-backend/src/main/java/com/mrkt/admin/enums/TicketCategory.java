package com.mrkt.admin.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TicketCategory {

    Request("Request"),
    Complaint("Complaint"),
    Issue("Issue");


    private final String description;

    TicketCategory(String description) {

        this.description = description;
    }


    public static TicketCategory forValue(String value) {
        for (TicketCategory orderStatus : TicketCategory.values()) {
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
