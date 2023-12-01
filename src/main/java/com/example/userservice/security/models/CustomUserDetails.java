package com.example.userservice.security.models;

import com.example.userservice.models.Role;
import com.example.userservice.models.User;
import com.example.userservice.security.services.CustomUserDetailService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@JsonDeserialize
@NoArgsConstructor
public class CustomUserDetails implements UserDetails {

    private User user;
    private String username;
    private String password;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    private List<CustomGrantedAuthority> authorities;
    public CustomUserDetails(User user){
        this.user=user;
        username = user.getEmail();
        password=user.getPassword();
        accountNonExpired=true;
        accountNonLocked=true;
        credentialsNonExpired=true;
        enabled=true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        authorities=new ArrayList<>();
        for(Role role : user.getRoles()){
            authorities.add(new CustomGrantedAuthority(role));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
