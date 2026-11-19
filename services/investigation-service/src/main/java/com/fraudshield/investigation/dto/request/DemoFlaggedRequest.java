package com.fraudshield.investigation.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DemoFlaggedRequest(@NotNull Integer transaction_id,@NotNull Integer user_id,@NotNull @Min(1) Integer amount,@NotNull @Min(0)@Max(100) Integer risk_score,@NotBlank String reason) {

}
