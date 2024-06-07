package com.example.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.TestingEmployee;
import com.example.repo.TestRepo;
import com.example.service.EmployeeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public TestingEmployee addEmployee(@RequestBody TestingEmployee employee) {
        return employeeService.addEmployee(employee);
    }

    @GetMapping
    public List<TestingEmployee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
}

