package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.entity.TestingEmployee;
import com.example.repo.TestRepo;
import com.example.service.EmployeeService;

public class EmployeeServiceTest {

	private EmployeeService employeeService;
	private TestRepo testRepo;

	@BeforeEach
	void setUp() {
		testRepo = mock(TestRepo.class);
		employeeService = new EmployeeService(testRepo);
	}

	@Test
	void testAddEmployee() {
		TestingEmployee employee = new TestingEmployee();
		employee.setName("John");
		employee.setDepartment("IT");

		when(testRepo.save(employee)).thenReturn(employee);

		TestingEmployee savedEmployee = employeeService.addEmployee(employee);

		assertNotNull(savedEmployee);
		assertEquals("John", savedEmployee.getName());
		assertEquals("IT", savedEmployee.getDepartment());
	}

	@Test
	void testGetAllEmployees() {
		TestingEmployee employee1 = new TestingEmployee();
		employee1.setName("John");
		employee1.setDepartment("IT");

		TestingEmployee employee2 = new TestingEmployee();
		employee2.setName("Jane");
		employee2.setDepartment("HR");

		List<TestingEmployee> employees = Arrays.asList(employee1, employee2);

		when(testRepo.findAll()).thenReturn(employees);

		List<TestingEmployee> allEmployees = employeeService.getAllEmployees();

		assertNotNull(allEmployees);
		assertEquals(2, allEmployees.size());
	}
}
