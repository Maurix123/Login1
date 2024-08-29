package com.example.loginsystem;

import com.example.loginsystem.entity.User;
import com.example.loginsystem.repository.UserRepository;
import com.example.loginsystem.service.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserDetailsServiceImplTests {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testLoadUserByUsername() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        userRepository.save(user);

        var userDetails = userDetailsService.loadUserByUsername("testuser");
        assertThat(userDetails).isNotNull();
        assertThat(userDetails.getUsername()).isEqualTo("testuser");
    }

    @Test
    public void testLoadUserByUsernameNotFound() {
        try {
            userDetailsService.loadUserByUsername("nonexistentuser");
        } catch (UsernameNotFoundException e) {
            assertThat(e.getMessage()).isEqualTo("User not found");
        }
    }
}
