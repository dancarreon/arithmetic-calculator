package com.calculator.controller;

import com.calculator.model.dto.Message;
import com.calculator.service.CalculatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/*
 * Calculator Controller that exposes the endpoints for all the algorithmic operations available
 * */
@RestController
@RequestMapping(value = "/calculator")
@RequiredArgsConstructor
@Log4j2
public class CalculatorController {

    private final CalculatorService calculatorService;

    @GetMapping("/addition")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getAddition(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        return calculatorService.add(a, b);
    }

    @GetMapping("/subtraction")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getSubtraction(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        return calculatorService.subtract(a, b);
    }

    @GetMapping("/multiplication")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getMultiplication(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        return calculatorService.multiply(a, b);
    }

    @GetMapping("/division")
    @ResponseStatus(HttpStatus.OK)
    public Message getDivision(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        try {
            return new Message(calculatorService.divide(a, b).toString());
        } catch (ArithmeticException e) {
            log.error(e);
            return new Message("Cannot divide by zero");
        }
    }

    @GetMapping("/square-root")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getSquareRoot(@RequestParam BigDecimal a) {
        return calculatorService.squareRoot(a);
    }

    @GetMapping("/random-string")
    @ResponseStatus(HttpStatus.OK)
    public Message getRandomString() {
        return new Message(calculatorService.randomString());
    }
}
