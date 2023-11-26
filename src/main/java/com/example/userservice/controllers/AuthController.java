package com.example.userservice.controllers;

import com.example.userservice.dtos.UserDetailsDTO;
import com.example.userservice.models.Session;
import com.example.userservice.models.User;
import com.example.userservice.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/")
public class AuthController {

    private AuthService authService;
    @GetMapping("/login")
    public String login(@RequestBody  UserDetailsDTO userDetails){
        User user = User.builder()
                .fullName(userDetails.getFullName())
                .email(userDetails.getEmail())
                .password(userDetails.getPassword())
                .build();
        Session session = authService.login(user);
        return session.getToken();
    }

    @GetMapping("/validate")
    public Boolean validate(){
        return false;
    }


}
