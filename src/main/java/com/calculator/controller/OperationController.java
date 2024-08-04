package com.calculator.controller;

import com.calculator.model.dto.OperationRecord;
import com.calculator.service.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
* Operations Controller lists all the available operations
* */
@RestController
@RequestMapping("/operations")
@RequiredArgsConstructor
public class OperationController {

    private final OperationService operationService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<OperationRecord> getAllOperations() {
        return operationService.getAllOperations();
    }
}
