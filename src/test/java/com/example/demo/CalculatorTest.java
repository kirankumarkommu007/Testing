package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class CalculatorTest {

	@MockBean
	private Calculator calculator;

	@Test
	public void testAdd() {
		// Given
		int a = 5;
		int b = 3;

		// When
		when(calculator.add(a, b)).thenReturn(a + b);

		// Then
		assertEquals(8, calculator.add(a, b));
	}

	@Test
	public void testSubtract() {
		// Given
		int a = 5;
		int b = 3;

		// When
		when(calculator.subtract(a, b)).thenReturn(a - b);

		// Then
		assertEquals(2, calculator.subtract(a, b));
	}

}
