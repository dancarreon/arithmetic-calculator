package com.calculator.service;

import com.calculator.model.dto.UserRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CalculatorServiceTest {

    @MockBean
    private RestTemplate restTemplate;
    @MockBean
    private CalculatorService calculatorService;

    @Test
    public void shouldDoAddition() {
        when(calculatorService.add(any(), any())).thenReturn(new BigDecimal(2));
        BigDecimal result = calculatorService.add(new BigDecimal(1), new BigDecimal(1));
        assertThat(result, equalTo(new BigDecimal(2)));
    }

    @Test
    public void shouldDoSubtract() {
        when(calculatorService.subtract(any(), any())).thenReturn(new BigDecimal(2));
        BigDecimal result = calculatorService.subtract(new BigDecimal(4), new BigDecimal(2));
        assertThat(result, equalTo(new BigDecimal(2)));
    }

    @Test
    public void shouldDoMultiply() {
        when(calculatorService.multiply(any(), any())).thenReturn(new BigDecimal(6));
        BigDecimal result = calculatorService.multiply(new BigDecimal(3), new BigDecimal(2));
        assertThat(result, equalTo(new BigDecimal(6)));
    }

    @Test
    public void shouldDoDivide() {
        when(calculatorService.divide(any(), any())).thenReturn(new BigDecimal(5));
        BigDecimal result = calculatorService.divide(new BigDecimal(10), new BigDecimal(2));
        assertThat(result, equalTo(new BigDecimal(5)));
    }

    @Test
    public void shouldDoSquareRoot() {
        when(calculatorService.squareRoot(any())).thenReturn(new BigDecimal(2));
        BigDecimal result = calculatorService.squareRoot(new BigDecimal(4));
        assertThat(result, equalTo(new BigDecimal(2)));
    }

    @Test
    public void shouldDoRandomString() {
        when(calculatorService.randomString()).thenReturn("random string");
        String result = calculatorService.randomString();
        assertThat(result, equalTo("random string"));
    }
}
