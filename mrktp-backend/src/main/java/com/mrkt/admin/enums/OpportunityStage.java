package com.mrkt.admin.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OpportunityStage {

    Prospecting("Prospecting"),
    ProposalMade("Proposal made"),
    Negotiation("Negotiation"),
    ClosedWon("Closed Won"),
    ClosedLost("Prospecting"),
    Investigation("Closed Lost");


    private final String description;

    OpportunityStage(String description) {

        this.description = description;
    }


    public static OpportunityStage forValue(String value) {
        for (OpportunityStage orderStatus : OpportunityStage.values()) {
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
