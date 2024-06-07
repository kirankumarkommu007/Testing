package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

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

        // Stubbing repository method
        when(productRepository.findAll()).thenReturn(productList);

        // Invoke service method
        List<Product> result = productService.getAllProducts();

        // Verify the result
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getName()).isEqualTo("Product 1");
        assertThat(result.get(1).getPrice()).isEqualTo(20.0);

        // Verify repository method was called
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void testGetProductById() {
        // Prepare data
        Product product = new Product(1L, "Product 1", 10.0);

        // Stubbing repository method
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        // Invoke service method
        Optional<Product> result = productService.getProductById(1L);

        // Verify the result
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getName()).isEqualTo("Product 1");

        // Verify repository method was called
        verify(productRepository, times(1)).findById(1L);
    }

    // Similarly, write tests for other methods like createProduct, updateProduct, and deleteProduct
}
