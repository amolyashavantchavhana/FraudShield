package com.fraudshield.investigation.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RecordOutcomeRequest(@NotNull Integer case_id,@NotBlank String result,@NotBlank String remarks)
{

}
