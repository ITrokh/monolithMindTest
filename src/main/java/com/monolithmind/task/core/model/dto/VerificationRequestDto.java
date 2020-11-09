package com.monolithmind.task.core.model.dto;

import lombok.Data;

@Data
public class VerificationRequestDto {
    private Long userId;
    private PurchaseDto purchase;
}
