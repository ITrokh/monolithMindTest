package com.monolithmind.task.core.service;

import com.monolithmind.task.core.model.dto.VerificationRequestDto;
import com.monolithmind.task.core.model.dto.VerificationResponseDto;

public interface VerificationService {
    VerificationResponseDto verify(VerificationRequestDto verificationRequest);
}
