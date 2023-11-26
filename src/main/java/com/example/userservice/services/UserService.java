package com.example.userservice.services;

import com.example.userservice.dtos.AssignRoleDTO;
import com.example.userservice.dtos.UserDTO;
import com.example.userservice.exceptions.UserNotFoundException;
import com.example.userservice.models.Role;
import com.example.userservice.models.User;
import com.example.userservice.repositories.RoleRepository;
import com.example.userservice.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.UnknownServiceException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public  ResponseEntity<UserDTO> getUserDetails(Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.map(user -> new ResponseEntity<>(
                user.toUserDTO(),
                HttpStatus.OK
        )).orElseGet(() -> new ResponseEntity<>(
                null,
                HttpStatus.NOT_FOUND
        ));

    }

    public ResponseEntity<UserDTO> assignRolesToUser(Long id, AssignRoleDTO assignRoleDTO) {
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isEmpty())
            return new ResponseEntity<>(
                    null,
                    HttpStatus.NOT_FOUND
            );
        User user = optionalUser.get();
//        List<Role> roleList = new ArrayList<>();
//
//        user.getRoles().addAll(assignRoleDTO.getRoles());

        for(Role role : assignRoleDTO.getRoles()){
            user.getRoles().add(roleRepository.save(role));
        }
        User savedUser = userRepository.save(user);
        return new ResponseEntity<>(
                savedUser.toUserDTO(),
                HttpStatus.OK
        );
    }
}
