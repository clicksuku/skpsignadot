package com.calculator.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorServiceTest {

    private final CalculatorService calculatorService = new CalculatorService();

    @Test
    void testAdd() {
        assertEquals(5.0, calculatorService.add(2.0, 3.0));
    }

    @Test
    void testSubtract() {
        assertEquals(1.0, calculatorService.subtract(3.0, 2.0));
    }

    @Test
    void testMultiply() {
        assertEquals(6.0, calculatorService.multiply(2.0, 3.0));
    }

    @Test
    void testDivide() {
        assertEquals(2.0, calculatorService.divide(6.0, 3.0));
    }

    @Test
    void testDivideByZero() {
        assertThrows(IllegalArgumentException.class, () -> calculatorService.divide(6.0, 0.0));
    }
}
