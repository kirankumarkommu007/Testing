package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE) 
public class UserRepositoryTest {

	@Autowired
	private TestRepository userRepository;

	@BeforeEach
	public void setUp() {
		userRepository.deleteAll(); // Clear all entities before each test
	}

	@Test
	public void testSave() {
		// Given
		TestUser user = new TestUser(1L, "John Doe", "john.doe@example.com");
		userRepository.save(user);

		// When
		List<TestUser> foundUser = userRepository.findAll();

		// Then
		assertThat(foundUser).isNotNull().hasSize(1);

	}

	@Test
	public void testFindbyID() {
		// Given
		TestUser user = new TestUser(1L, "John Doe", "john.doe@example.com");
		userRepository.save(user);

		// When
		Optional<TestUser> foundUser = userRepository.findById(user.getId());

		// Then
		assertThat(foundUser).isNotNull();

	}

	@Test
	public void testFindAllUsers() {
		// Given
		TestUser user1 = new TestUser(1L, "Alice", "alice@example.com");
		TestUser user2 = new TestUser(2L, "Bob", "bob@example.com");
		userRepository.save(user1);
		userRepository.save(user2);

		// When
		List<TestUser> users = (List<TestUser>) userRepository.findAll();

		// Then
		assertThat(users).isNotNull().hasSize(2);

	}
}
