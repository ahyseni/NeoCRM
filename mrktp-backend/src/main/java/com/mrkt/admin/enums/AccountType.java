package com.mrkt.admin.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AccountType {

    Company("The account is company"),
    Customer("The account is private customer");


    private final String description;

    AccountType(String description) {

        this.description = description;
    }


    public static AccountType forValue(String value) {
        for (AccountType orderStatus : AccountType.values()) {
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
