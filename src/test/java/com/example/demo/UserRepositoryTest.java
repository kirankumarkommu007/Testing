package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TestRepository userRepository;

    @Test
    public void testSaveUser() {
        // Given
        TestUser user = new TestUser();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");

        // When
        TestUser savedUser = entityManager.persistAndFlush(user);

        // Then
        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getName()).isEqualTo("John Doe");
        assertThat(savedUser.getEmail()).isEqualTo("john.doe@example.com");
    }

    @Test
    public void testFindUserById() {
        // Given
        TestUser user = new TestUser();
        user.setName("Jane Smith");
        user.setEmail("jane.smith@example.com");
        TestUser savedUser = entityManager.persistAndFlush(user);

        // When
        TestUser foundUser = userRepository.findById(savedUser.getId()).orElse(null);

        // Then
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getName()).isEqualTo("Jane Smith");
        assertThat(foundUser.getEmail()).isEqualTo("jane.smith@example.com");
    }
    
}
