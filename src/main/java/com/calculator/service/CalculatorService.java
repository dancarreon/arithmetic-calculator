package com.calculator.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/*
* Calculator Service, manages available arithmetic operations, also generates a random string by calling a third-party API
* */
@Service
@RequiredArgsConstructor
@Log4j2
public class CalculatorService {

    private static final String RANDOM_STRING_URL = "https://www.random.org/strings/?num=1&len=32&digits=on&upperalpha=on&loweralpha=on&unique=on&format=plain&rnd=new";

    private final RestTemplate restTemplate;

    public BigDecimal add(BigDecimal a, BigDecimal b) {
        log.info("Attending addition request: {} + {}", a, b);
        return a.add(b);
    }

    public BigDecimal subtract(BigDecimal a, BigDecimal b) {
        log.info("Attending subtraction request: {} - {}", a, b);
        return a.subtract(b);
    }

    public BigDecimal multiply(BigDecimal a, BigDecimal b) {
        log.info("Attending multiplication request: {} * {}", a, b);
        return a.multiply(b);
    }

    public BigDecimal divide(BigDecimal a, BigDecimal b) {
        log.info("Attending division request: {} / {}", a, b);
        return a.divide(b, 5, RoundingMode.HALF_UP);
    }

    public BigDecimal squareRoot(BigDecimal a) {
        log.info("Attending square root request of: {}", a);
        return a.sqrt(new MathContext(10));
    }

    public String randomString() {
        log.info("Attending random string request");
        return restTemplate.getForObject(RANDOM_STRING_URL, String.class);
    }

}
