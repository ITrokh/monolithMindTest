package com.monolithmind.task.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PurchaseStatus {
    SUCCESS(0), FAILURE(1), PENDING(2);

    private int stateNumber;

    public static PurchaseStatus forValue(int state) {
        for (PurchaseStatus status : PurchaseStatus.values()) {
            if (status.getStateNumber() == state) return status;
        }
        throw new RuntimeException("Status is not defined");
    }
}
