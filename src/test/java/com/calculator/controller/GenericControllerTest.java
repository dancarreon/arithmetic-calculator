package com.calculator.controller;

import com.calculator.interceptor.CalculatorInterceptor;
import com.calculator.service.CalculatorService;
import com.calculator.service.OperationService;
import com.calculator.service.RecordService;
import com.calculator.service.UserService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.h2.H2ConsoleProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest
public abstract class GenericControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    protected CalculatorService calculatorService;

    @MockBean
    protected RecordService recordService;

    @MockBean
    protected OperationService operationService;

    @MockBean
    protected UserService userService;

    @MockBean
    protected H2ConsoleProperties h2ConsoleProperties;

    @Before
    public void setUp() {
        Mockito.reset(userService, recordService, operationService, calculatorService, h2ConsoleProperties);
    }
}
