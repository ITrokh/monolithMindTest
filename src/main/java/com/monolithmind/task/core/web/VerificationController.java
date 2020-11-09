package com.monolithmind.task.core.web;

import com.monolithmind.task.core.model.dto.VerificationRequestDto;
import com.monolithmind.task.core.model.dto.VerificationResponseDto;
import com.monolithmind.task.core.service.VerificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
@Slf4j
@Controller
@RequiredArgsConstructor
public class VerificationController {
    private final VerificationService verificationService;

    @MessageMapping("/verification/request")
    @SendTo("/verification/response")
    public VerificationResponseDto send(VerificationRequestDto verificationRequest) {
        log.info("Processing verification {}", verificationRequest);
        VerificationResponseDto verify = verificationService.verify(verificationRequest);
        log.info("Processed verification {}", verify);
        return verify;
    }
}
