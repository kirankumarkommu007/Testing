package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestRestTemplateTesting {

	@Autowired
	private ProductController controller;
	
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testSayHello() {
		ResponseEntity<String> response = restTemplate.getForEntity("/hello", String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Hello, World!", response.getBody());
	}
}
