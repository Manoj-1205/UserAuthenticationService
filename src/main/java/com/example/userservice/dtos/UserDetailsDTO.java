package com.example.userservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserDetailsDTO {
    private String fullName;
    private String email;
    private String password;
}
