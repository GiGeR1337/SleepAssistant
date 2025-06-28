package com.example.sleepproject.Services;

import com.example.sleepproject.Models.Role;
import com.example.sleepproject.Repositories.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init(){
        roleRepository.save(new Role("ROLE_USER"));
        roleRepository.save(new Role("ROLE_ADMIN"));
    }
}
