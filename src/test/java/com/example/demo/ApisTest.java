package com.example.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(TestController.class)
public class ApisTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TestRepo testRepo;

    @Test
    public void testAddEmployee() throws Exception {
    	TestingEmployee employee = new TestingEmployee();
    	employee.setId((long) 1);
    	employee.setName("john");
    	employee.setEmployee("no");
    	when(testRepo.save(any(TestingEmployee.class))).thenReturn(employee);

        String requestBody = "{\"id\":1,\"name\":\"john\",\"employee\":\"no\"}";

        mockMvc.perform(post("/addemployee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().json(requestBody));

        verify(testRepo, Mockito.times(1)).save(any(TestingEmployee.class));
    }

    @Test
    public void testGetAll() throws Exception {
    	TestingEmployee employee1 = new TestingEmployee();
    	employee1.setId((long) 3);
    	employee1.setName("kiran");
    	employee1.setEmployee("no");

    	TestingEmployee employee2 = new TestingEmployee();
    	employee2.setId((long) 4);
    	employee2.setName("kommu");
    	employee2.setEmployee("yes");
        List<TestingEmployee> allEmployees = Arrays.asList(employee1, employee2);

        when(testRepo.findAll()).thenReturn(allEmployees);

        mockMvc.perform(get("/all"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id': 3, 'name': 'kiran', 'employee': 'no'}, {'id': 4, 'name': 'kommu', 'employee': 'yes'}]"));
    }
    
    
    @Test
    public void TestGetbyid  () throws Exception {
        Long id = 2L; // ID of the employee to retrieve

        TestingEmployee employee = new TestingEmployee();
        employee.setId(id);
        employee.setName("John");
        employee.setEmployee("yes");

        // Mock the behavior of TestRepo's findById() method
        when(testRepo.findById(id)).thenReturn(Optional.of(employee));

        mockMvc.perform(get("/" + id)) // Perform GET request with the ID in the path
                .andExpect(status().isOk())
                .andExpect(content().json("{'id': 2, 'name': 'John', 'employee': 'yes'}"));
    }
}
