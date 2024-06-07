package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.TestingEmployee;
import com.example.repo.TestRepo;

@Service
public class EmployeeService {

	private TestRepo testRepo;

	public EmployeeService(TestRepo testRepo) {
		this.testRepo = testRepo;
	}

	public TestingEmployee addEmployee(TestingEmployee employee) {
		return testRepo.save(employee);
	}

	public List<TestingEmployee> getAllEmployees() {
		return testRepo.findAll();
	}
}
