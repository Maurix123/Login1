package com.example.loginsystem.config;

import com.example.loginsystem.entity.Role;
import com.example.loginsystem.entity.User;
import com.example.loginsystem.repository.RoleRepository;
import com.example.loginsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class DataLoader {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner loadData() {
        return args -> {
            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            roleRepository.save(adminRole);

            Role secretaryRole = new Role();
            secretaryRole.setName("ROLE_SECRETARY");
            roleRepository.save(secretaryRole);

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("adminpass"));
            admin.setRoles(Set.of(adminRole));
            userRepository.save(admin);

            User secretary = new User();
            secretary.setUsername("secretary");
            secretary.setPassword(passwordEncoder.encode("secretarypass"));
            secretary.setRoles(Set.of(secretaryRole));
            userRepository.save(secretary);
        };
    }
}
