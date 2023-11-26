package com.example.userservice.controllers;

import com.example.userservice.dtos.LoginRequestDTO;

import com.example.userservice.dtos.SignUpRequestDTO;
import com.example.userservice.dtos.UserDTO;
import com.example.userservice.models.Session;
import com.example.userservice.models.User;
import com.example.userservice.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
       return authService.login(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());

    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signup(@RequestBody SignUpRequestDTO signUpRequestDTO){
        return authService.signup(signUpRequestDTO);
    }


    @GetMapping("/validate")
    public Boolean validate(){
        return false;
    }


}
