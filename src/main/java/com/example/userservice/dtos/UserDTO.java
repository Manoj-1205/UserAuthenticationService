package com.example.userservice.dtos;

import com.example.userservice.models.Role;
import com.example.userservice.models.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class UserDTO {
    private String email;
    private List<Role> roles;


}
