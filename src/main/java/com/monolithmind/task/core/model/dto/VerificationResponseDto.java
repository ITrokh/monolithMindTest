package com.monolithmind.task.core.model.dto;

import com.monolithmind.task.core.model.PurchaseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerificationResponseDto {
    private Boolean success;
    private PurchaseStatus purchaseStatus;
}
