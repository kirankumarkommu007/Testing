package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProductRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    @Test
    @Sql("/data.sql")
    public void testFindProductById() {
        // Given
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(10.99);
        entityManager.persist(product);
        entityManager.flush();

        // When
        Product foundProduct = productRepository.findById(product.getId()).orElse(null);

        // Then
        assertNotNull(foundProduct, "Product should not be null");
        assertEquals(product.getName(), foundProduct.getName(), "Product name should match");
        assertEquals(product.getPrice(), foundProduct.getPrice(), "Product price should match");
    }
}
