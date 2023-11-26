package com.example.userservice.services;

import com.example.userservice.models.Session;
import com.example.userservice.models.User;
import com.example.userservice.repositories.SessionRepository;
import com.example.userservice.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class AuthService {
    private UserRepository userRepository;
    private SessionRepository sessionRepository;
    public Session login(User user){
        String token = generateToken();
        if(userRepository.findByEmail(user.getEmail()).isEmpty()){
            userRepository.save(user);
        }


        System.out.println("User Fetched : "+user.getFullName()+" "+user.getEmail());
        User savedUser = userRepository.findByEmail(user.getEmail()).get(0);
        Session session = new Session();
        session.setToken(token);
        session.setUser(savedUser);
        return sessionRepository.save(session);
    }
    public Boolean validate(){
        return null;
    }

    String generateToken(){
        Random random=new Random();
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder token=new StringBuilder("");
        //Token size of 20
        for(int k=0; k<20;k++)
            token.append(characters.charAt(random.nextInt(characters.length())));

        return token.toString();

    }
}
