package com.example.demo;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private static Calculator calculator;

    @BeforeAll
    static void setUp() {
        calculator = new Calculator();
    }

    @AfterAll
    static void tearDown() {
        calculator = null;
    }

    @Test
    void testAdd() {
        assertEquals(5, calculator.add(2, 3));
        assertEquals(-1, calculator.add(-4, 3));
    }

    @Test
    void testSubtract() {
        assertEquals(5, calculator.subtract(8, 3));
        assertEquals(-1, calculator.subtract(2, 3));
    }

    @Test
    void testMultiply() {
        assertEquals(15, calculator.multiply(5, 3));
        assertEquals(0, calculator.multiply(0, 3));
    }

    @Test
    void testDivide() {
        assertEquals(2, calculator.divide(6, 3));
        assertEquals(0, calculator.divide(0, 3));
    }

    @Test
    void testDivideByZero() {
        assertThrows(IllegalArgumentException.class, () -> calculator.divide(5, 0));
    }
}
