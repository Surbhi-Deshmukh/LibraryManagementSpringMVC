package com.nagarro.advjavaassgn5a.repository;

import com.nagarro.advjavaassgn5a.entity.Users;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@ExtendWith(SpringExtension.class)
//@DataJpaTest
@SpringBootTest
@Transactional
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveUser() {
        Users user = new Users("john_doe", "password123");
        Users savedUser = userRepository.save(user);
        Optional<Users> foundUser = userRepository.findById(savedUser.getUsername());
        assertTrue(foundUser.isPresent());
        assertEquals(user.getUsername(), foundUser.get().getUsername());
    }

    @Test
    public void testFindById() {
        Users user = new Users("jane_doe", "password456");
        user = userRepository.save(user);
        Optional<Users> foundUser = userRepository.findById(user.getUsername());
        assertTrue(foundUser.isPresent());
        assertEquals(user.getPassword(), foundUser.get().getPassword());
    }

    @Test
    public void testDeleteUser() {
        Users user = new Users("delete_me", "password789");
        user = userRepository.save(user);
        String username = user.getUsername();
        userRepository.deleteById(username);
        Optional<Users> foundUser = userRepository.findById(username);
        assertTrue(foundUser.isEmpty());
    }

    @Test
    public void testUpdateUser() {
        Users user = new Users("update_me", "password000");
        user = userRepository.save(user);
        user.setPassword("updated_password");
        userRepository.save(user);
        Optional<Users> foundUser = userRepository.findById(user.getUsername());
        assertTrue(foundUser.isPresent());
        assertEquals("updated_password", foundUser.get().getPassword());
    }
}
