package com.mrkt.admin.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum DepartmentType {

    Department("Department"),
    DepartmentUnit("DepartmentUnit");


    private final String description;

    DepartmentType(String description) {

        this.description = description;
    }


    public static DepartmentType forValue(String value) {
        for (DepartmentType orderStatus : DepartmentType.values()) {
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
