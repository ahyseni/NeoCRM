package com.mrkt.admin.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OpportunityType {

    NewBusiness("New Business"),
    ExistingBusiness("ExistingBusiness");


    private final String description;

    OpportunityType(String description) {

        this.description = description;
    }


    public static OpportunityType forValue(String value) {
        for (OpportunityType orderStatus : OpportunityType.values()) {
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
