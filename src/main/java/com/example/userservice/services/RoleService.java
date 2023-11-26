package com.example.userservice.services;

import com.example.userservice.models.Role;
import com.example.userservice.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleService {
    private RoleRepository roleRepository;
    public ResponseEntity<Role> createRole(Role role){
        return new ResponseEntity<>(
                roleRepository.save(role),
                HttpStatus.OK
        );
    }
}
