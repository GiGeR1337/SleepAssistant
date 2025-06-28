package com.example.sleepproject.Services;

import com.example.sleepproject.DTOs.UserDto;
import com.example.sleepproject.Models.Role;
import com.example.sleepproject.Models.User;
import com.example.sleepproject.Repositories.RoleRepository;
import com.example.sleepproject.Repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public void registerUser(UserDto userDto){
        User user = new User();

        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_USER")
                .orElseGet(() -> roleRepository.save(new Role("ROLE_USER")));

        user.getRoles().add(role);

        userRepository.save(user);
    }
}
