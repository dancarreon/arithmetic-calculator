package com.calculator.controller;

import com.calculator.model.Type;
import com.calculator.model.dto.Message;
import com.calculator.model.dto.OperationRecord;
import com.calculator.model.dto.UserRecord;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WithMockUser(username = "user1@test.com")
public class CalculatorControllerTest extends GenericControllerTest {

    @Before
    public void beforeEach() {
        when(userService.getAuthenticatedUser()).thenReturn(new UserRecord(1L, "username", "password", true));
        when(operationService.getOperationByType(any())).thenReturn(new OperationRecord(1L, Type.ADDITION, 1));
        when(calculatorService.divide(any(), any())).thenReturn(new BigDecimal(1));
        when(calculatorService.squareRoot(new BigDecimal(1))).thenReturn(new BigDecimal(1));
        when(calculatorService.squareRoot(new BigDecimal(-1))).thenThrow(ArithmeticException.class);
    }

    @Test
    public void shouldDoAddition() throws Exception {
        mockMvc.perform(get("/calculator/addition?a=1&b=2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDoSubtraction() throws Exception {
        mockMvc.perform(get("/calculator/subtraction?a=1&b=2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDoMultiplication() throws Exception {
        mockMvc.perform(get("/calculator/multiplication?a=1&b=2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDoDivision() throws Exception {
        mockMvc.perform(get("/calculator/division?a=1&b=2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDoSquareRoot() throws Exception {
        mockMvc.perform(get("/calculator/square-root?a=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDoSquareRootNegativeNumber() throws Exception {
        mockMvc.perform(get("/calculator/square-root?a=-1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Error attempting to square root of a negative number")));
    }

    @Test
    public void shouldDoRandomString() throws Exception {
        mockMvc.perform(get("/calculator/random-string")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
