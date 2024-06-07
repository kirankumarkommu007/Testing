package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.entity.TestingEmployee;

public class TestEntity {

	@Test
	public void testUserEntity() {
		TestingEmployee user = new TestingEmployee();
		user.setId(1L);
		user.setName("testUser");
		user.setDepartment("test@example.com");

		assertEquals(1L, user.getId());
		assertEquals("testUser", user.getName());
		assertEquals("test@example.com", user.getDepartment());
	}
}
