package com.calculator.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CalculatorServiceTest {

    @Autowired
    private CalculatorService calculatorService;

    @Test
    void testAdd() {
        double result = calculatorService.add(5, 3);
        assertEquals(8, result, 0.001);
    }

    @Test
    void testSubtract() {
        double result = calculatorService.subtract(10, 4);
        assertEquals(6, result, 0.001);
    }
}