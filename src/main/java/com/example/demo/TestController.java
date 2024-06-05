package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@Autowired
	private TestRepo testRepo;
	
	
	@PostMapping("/addemployee")
	public TestingEmployee addemp(@RequestBody TestingEmployee employee) {
		return testRepo.save(employee);
	}
	
	@GetMapping("/all")
	public List<TestingEmployee> getall(){
		return testRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<TestingEmployee> getByid(@PathVariable Long id) {
		return testRepo.findById(id);
	}

}
