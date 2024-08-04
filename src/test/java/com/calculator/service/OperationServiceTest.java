package com.calculator.service;

import com.calculator.model.Operation;
import com.calculator.model.Type;
import com.calculator.model.dto.OperationRecord;
import com.calculator.repository.OperationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OperationServiceTest {

    @MockBean
    private OperationRepository operationRepository;
    @MockBean
    private OperationService operationService;

    @Test
    public void shouldGetAllOperations() {
        Operation operation = new Operation(1L, Type.ADDITION, 1, null);
        when(operationRepository.findAll()).thenReturn(Collections.singletonList(operation));
        List<OperationRecord> allOperations = operationService.getAllOperations();
        assertThat(allOperations, notNullValue());
    }

    @Test
    public void shouldGetOperationByType() {
        Operation operation = new Operation(1L, Type.ADDITION, 1, null);
        when(operationRepository.findByType(Type.ADDITION)).thenReturn(Optional.of(operation));
        when(operationService.getOperationByType(any())).thenReturn(operation.toRecord());
        OperationRecord result = operationService.getOperationByType(Type.ADDITION);
        assertThat(result.type(), equalTo(Type.ADDITION));
    }
}
