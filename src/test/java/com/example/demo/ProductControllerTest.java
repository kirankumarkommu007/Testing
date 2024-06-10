package com.example.demo;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

	@MockBean
	private ProductService productService;
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testSayHello() throws Exception {
		mockMvc.perform(get("/hello")).andExpect(status().isOk()).andExpect(content().string("Hello, World!"));
	}

	@Test
	public void testGetAllProducts() throws Exception {
		// Mocking the productService behavior
		List<Product> productList = Arrays.asList(new Product(1L, "Product 1", 10.0),
				new Product(2L, "Product 2", 20.0));
		Mockito.when(productService.getAllProducts()).thenReturn(productList);

		// Performing GET request to /products
		mockMvc.perform(get("/all").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$[0].id").value(1))
				.andExpect(jsonPath("$[0].name").value("Product 1")).andExpect(jsonPath("$[0].price").value(10.0))
				.andExpect(jsonPath("$[1].id").value(2)).andExpect(jsonPath("$[1].name").value("Product 2"))
				.andExpect(jsonPath("$[1].price").value(20.0));
	}
}
