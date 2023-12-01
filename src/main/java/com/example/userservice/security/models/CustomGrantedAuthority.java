package com.example.userservice.security.models;

import com.example.userservice.models.Role;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;


@JsonDeserialize
@NoArgsConstructor
public class CustomGrantedAuthority implements GrantedAuthority {

    private Role role;
    private String authority;
    public CustomGrantedAuthority(Role role){
        authority = role.getRoleName();
    }
    @Override
    public String getAuthority() {
        return authority;
    }
}
