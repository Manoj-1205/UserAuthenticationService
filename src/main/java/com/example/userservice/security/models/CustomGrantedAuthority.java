package com.example.userservice.security.models;

import com.example.userservice.models.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public class CustomGrantedAuthority implements GrantedAuthority {
    private Role role;
    @Override
    public String getAuthority() {
        return role.getRoleName();
    }
}
