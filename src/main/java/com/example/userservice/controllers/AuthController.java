package com.example.userservice.controllers;

import com.example.userservice.dtos.*;

import com.example.userservice.models.SessionStatus;
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
    public SessionStatus validate(@RequestBody ValidateTokenRequestDTO validateTokenRequestDTO){
        return authService.validateToken(validateTokenRequestDTO.getUserId(), validateTokenRequestDTO.getToken());
    }


    @PostMapping("/logout")
    public SessionStatus logout(@RequestBody LogoutRequestDTO logoutRequestDTO){
        return authService.logout(logoutRequestDTO.getUserId(),logoutRequestDTO.getToken());
    }


}
