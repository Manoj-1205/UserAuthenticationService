package com.example.userservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignUpRequestDTO {
    private String email;
    private String password;
}
