package com.nagarro.advjavaassgn5a.service.impl;

import com.nagarro.advjavaassgn5a.entity.Users;
import com.nagarro.advjavaassgn5a.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;
    private Users user1;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user1 = new Users("username", "password");
    }

    @Test
    void testAuthenticateUser() {
        when(userRepository.findById(user1.getUsername())).thenReturn(Optional.of(user1));
        Users user = userService.authenticateUser(user1.getUsername(), user1.getPassword());
        assertEquals(user1.getUsername(), user.getUsername());
        assertEquals(user1.getPassword(), user.getPassword());

    }

    @Test
    void testAddUser() {
        when(userRepository.save(user1)).thenReturn(user1);
        Users user = userService.addUser(user1);
        assertEquals(user1.getUsername(), user.getUsername());

    }

    @Test
    void testDeleteUser() {
        when(userRepository.existsById(any())).thenReturn(true);
        userService.deleteUser(user1.getUsername());
        verify(userRepository, times(1)).deleteById("username");
    }
}