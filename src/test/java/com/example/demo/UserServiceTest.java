package com.example.demo;


import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

public class UserServiceTest {

 @Mock
 private UserRepository userRepository;

 @InjectMocks
 private UserService userService;

 @BeforeEach
 void setUp() {
     MockitoAnnotations.openMocks(this);
 }

 @Test
 void testGetUserById() {
     User user = new User();
     user.setId(1L);
     user.setName("John Doe");

     when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));

     User foundUser = userService.getUserById(1L);
     assertEquals("John Doe", foundUser.getName());

     User notFoundUser = userService.getUserById(2L);
     assertNull(notFoundUser);
 }
}
