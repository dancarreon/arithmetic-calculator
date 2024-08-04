package com.calculator.model.dto;

import com.calculator.model.Operation;
import com.calculator.model.Type;

public record OperationRecord(Long id, Type type, Integer cost) {
    public Operation toOperation() {
        return new Operation(id, type, cost, null);
    }
}
