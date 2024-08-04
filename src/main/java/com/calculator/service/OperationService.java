package com.calculator.service;

import com.calculator.model.Operation;
import com.calculator.model.Type;
import com.calculator.model.dto.OperationRecord;
import com.calculator.repository.OperationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

/*
* Operation Service that retrieves all Operation information
* */
@Service
@RequiredArgsConstructor
@Log4j2
public class OperationService {

    private final OperationRepository operationRepository;

    public List<OperationRecord> getAllOperations() {
        log.info("Requesting all Operations");
        return operationRepository.findAll().stream().map(Operation::toRecord).toList();
    }

    public OperationRecord getOperationByType(Type type) {
        log.info("Requesting Operation of Type: {}", type);
        return operationRepository.findByType(type).map(Operation::toRecord).orElse(null);
    }
}
