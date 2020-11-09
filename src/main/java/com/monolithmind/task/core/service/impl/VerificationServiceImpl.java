package com.monolithmind.task.core.service.impl;

import com.google.api.services.androidpublisher.model.ProductPurchase;
import com.monolithmind.task.core.model.*;
import com.monolithmind.task.core.model.dto.VerificationRequestDto;
import com.monolithmind.task.core.model.dto.VerificationResponseDto;
import com.monolithmind.task.core.repository.PurchaseRepository;
import com.monolithmind.task.core.rest.GoogleVerificationService;
import com.monolithmind.task.core.service.UserService;
import com.monolithmind.task.core.service.VerificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class VerificationServiceImpl implements VerificationService {
    private final UserService userService;
    private final PurchaseRepository purchaseRepository;
    private final GoogleVerificationService googleVerificationService;

    @Override
    public VerificationResponseDto verify(VerificationRequestDto verificationRequest) {
        User user = userService.getById(verificationRequest.getUserId());
        try {
            Optional<Purchase> byId = purchaseRepository.findById(verificationRequest.getPurchase().getPurchaseToken());
            if (!byId.isPresent() || !byId.get().getPurchaseStatus().equals(PurchaseStatus.SUCCESS)) {
                ProductPurchase verified = googleVerificationService.verify(verificationRequest.getPurchase(), true);
                Purchase build = Purchase.builder()
                        .id(verificationRequest.getPurchase().getPurchaseToken())
                        .productId(verificationRequest.getPurchase().getProductId())
                        .purchaseStatus(PurchaseStatus.forValue(verified.getPurchaseState()))
                        .user(user)
                        .build();
                Purchase save = purchaseRepository.save(build);
                return new VerificationResponseDto(true, save.getPurchaseStatus());
            } else {
                return new VerificationResponseDto(true, PurchaseStatus.SUCCESS);
            }
        } catch (Exception e) {
            log.error("There is exception", e);
            return new VerificationResponseDto(false, PurchaseStatus.FAILURE);
        }
    }
}
