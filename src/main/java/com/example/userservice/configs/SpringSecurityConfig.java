package com.example.userservice.configs;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {
    //By default spring security add authentication to all endpoints
    //Disabling spring security
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> {
                            try {
                                requests
                                        .anyRequest().permitAll()
                                        .and().csrf().disable();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                );

        return http.build();
    }
}
