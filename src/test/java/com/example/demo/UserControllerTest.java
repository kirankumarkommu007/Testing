package com.example.demo;

import com.example.controllers.EmployeeController;
import com.example.entity.TestingEmployee;
import com.example.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(EmployeeController.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void testAddEmployee() throws Exception {
        // Create a new employee object
        TestingEmployee employee = new TestingEmployee();
        employee.setId(1L);
        employee.setName("John Doe");
        employee.setDepartment("IT");

        // Mock the service method to return the created employee
        when(employeeService.addEmployee(any(TestingEmployee.class))).thenReturn(employee);

        // Perform POST request to add employee
        mockMvc.perform(MockMvcRequestBuilders.post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"John Doe\",\"department\":\"IT\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.department").value("IT"));
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        // Create a list of employees
        TestingEmployee employee = new TestingEmployee();
        employee.setId(1L);
        employee.setName("John Doe");
        employee.setDepartment("IT");
        when(employeeService.getAllEmployees()).thenReturn(Collections.singletonList(employee));

        // Perform GET request to get all employees
        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("John Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].department").value("IT"));
    }
}
