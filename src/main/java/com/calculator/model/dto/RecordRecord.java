package com.calculator.model.dto;

import java.time.Instant;

public record RecordRecord(Long id, Long operationId, Long userId, Integer amount, Integer userBalance,
                           Integer operationResponse, Instant date) {
}
