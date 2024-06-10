package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestRestTemplateTesting {

	    @Autowired
	    private TestRestTemplate restTemplate;

	    @Test
	    public void testCreateUser() {
	        Product product = new Product(1L,"John",12.00);

	        ResponseEntity<Product> response = restTemplate.postForEntity("/add", product, Product.class);

	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertNotNull(response.getBody());
	        assertEquals("John", response.getBody().getName());
	        assertEquals(12.00, response.getBody().getPrice());
	    }
	

}
