package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllProducts() {
        // Prepare data
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1L, "Product 1", 10.0));
        productList.add(new Product(2L, "Product 2", 20.0));

        // Stubbing service method
        when(productService.getAllProducts()).thenReturn(productList);

        // Invoke controller method
        List<Product> result = productController.getAllProducts();

        // Verify the result
        assertThat(result.size()).isEqualTo(productList.size());
    }

    @Test
    public void testGetProductById() {
        // Prepare data
        Product product = new Product(1L, "Product 1", 10.0);

        // Stubbing service method
        when(productService.getProductById(1L)).thenReturn(Optional.of(product));

        // Invoke controller method
        Product result = productController.getProductById(1L);

        // Verify the result
        assertThat(result).isEqualTo(product);
    }

    @Test
    public void testCreateProduct() {
        // Prepare data
        Product product = new Product(1L, "Product 1", 10.0);

        // Stubbing service method
        when(productService.createProduct(product)).thenReturn(product);

        // Invoke controller method
        Product result = productController.createProduct(product);

        // Verify the result
        assertThat(result).isEqualTo(product);
    }

    @Test
    public void testUpdateProduct() {
        // Prepare data
        Product product = new Product(1L, "Product 1", 10.0);

        // Stubbing service method
        when(productService.updateProduct(1L, product)).thenReturn(product);

        // Invoke controller method
        Product result = productController.updateProduct(1L, product);

        // Verify the result
        assertThat(result).isEqualTo(product);
    }

    @Test
    public void testDeleteProduct() {
        // Invoke controller method
        productController.deleteProduct(1L);

        // Verify the method was called
        verify(productService, times(1)).deleteProduct(1L);
    }
}
