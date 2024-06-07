package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.TestingEmployee;

public interface TestRepo extends JpaRepository<TestingEmployee, Long> {

}
