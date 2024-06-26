package com.nagarro.assgn5frontend.service.impl;

import com.nagarro.assgn5frontend.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private UserServiceImpl userService;

    private User user1;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user1 = new User("testuser", "password");
    }

    @Test
    void testAuthenticateUser() {
        String url = "http://localhost:9191/api/user";
        HttpEntity<User> requestEntity = new HttpEntity<>(user1);
        ResponseEntity<User> responseEntity = ResponseEntity.ok(user1);

        when(restTemplate.exchange(eq(url), eq(HttpMethod.POST), eq(requestEntity), eq(User.class)))
                .thenReturn(responseEntity);

        boolean result = userService.authenticateUser(user1);
        assertTrue(result);
        verify(restTemplate, times(1)).exchange(eq(url), eq(HttpMethod.POST), eq(requestEntity), eq(User.class));
    }

    @Test
    void testAuthenticateUserWithInvalidUser() {
        String url = "http://localhost:9191/api/user";
        HttpEntity<User> requestEntity = new HttpEntity<>(user1);
        ResponseEntity<User> responseEntity = ResponseEntity.ok(null);

        when(restTemplate.exchange(eq(url), eq(HttpMethod.POST), eq(requestEntity), eq(User.class)))
                .thenReturn(responseEntity);
        boolean result = userService.authenticateUser(user1);
        assertFalse(result);
        verify(restTemplate, times(1)).exchange(eq(url), eq(HttpMethod.POST), eq(requestEntity), eq(User.class));
    }

    @Test
    void testAddUser() {
        String url = "http://localhost:9191/api/user/add";
        when(restTemplate.postForObject(eq(url), eq(user1), eq(User.class))).thenReturn(user1);

        User result = userService.addUser(user1);
        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
        verify(restTemplate, times(1)).postForObject(eq(url), eq(user1), eq(User.class));
    }

    @Test
    void testUpdateUser() {
        String url = "http://localhost:9191/api/user/add";
        when(restTemplate.postForObject(eq(url), eq(user1), eq(User.class))).thenReturn(user1);

        User result = userService.updateUser(user1);
        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
        verify(restTemplate, times(1)).postForObject(eq(url), eq(user1), eq(User.class));
    }
}
