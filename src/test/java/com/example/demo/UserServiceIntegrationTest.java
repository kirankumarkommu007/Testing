package com.example.demo;

//UserServiceIntegrationTest.java

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@Transactional
public class UserServiceIntegrationTest {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@BeforeEach
	public void setup() {
		User user = new User();
		user.setId(1L);
		user.setName("John Doe");
		userRepository.save(user);
	}

	@Test
	@Rollback
	public void testGetUserById() {
		User user = userService.getUserById(1L);
		assertNotNull(user);
		assertNull(userService.getUserById(2L));
	}
}
