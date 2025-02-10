package com.mrkt.admin.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderStatus {

    CREATED("The order was created succesfully", 7),
    PAYED("Payment completed succesfully", 7),
    READY("The order is ready", 4),
    ON_ROAD("The order is on road", 2),
    DELIVERED("The order is delivered", 0),
    COMPLETED("The order is completed", 0),
    RETURNED("The order is returned", 0);


    private final String description;
    private final int timeToDelivery;

    OrderStatus(String description, int timeToDelivery) {
        this.timeToDelivery = timeToDelivery;
        this.description = description;
    }


    public static OrderStatus forValue(String value) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (orderStatus.name().equals(value)) {
                return orderStatus;
            }
        }
        return null;
    }

    public String getDescription() {
        return description;
    }

    public int getTimeToDelivery() {
        return timeToDelivery;
    }

    @Override
    @JsonValue
    public String toString() {
        return this.name();
    }
}
