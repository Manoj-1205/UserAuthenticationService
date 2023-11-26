package com.example.userservice.services;

import com.example.userservice.dtos.SignUpRequestDTO;
import com.example.userservice.dtos.UserDTO;
import com.example.userservice.exceptions.InvalidPasswordException;
import com.example.userservice.exceptions.UserAlreadyExistException;
import com.example.userservice.exceptions.UserDoesNotExistException;
import com.example.userservice.exceptions.UserNotFoundException;
import com.example.userservice.models.Session;
import com.example.userservice.models.SessionStatus;
import com.example.userservice.models.User;
import com.example.userservice.repositories.SessionRepository;
import com.example.userservice.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;

import java.util.*;

@Service

public class AuthService {
    private UserRepository userRepository;
    private SessionRepository sessionRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthService(UserRepository userRepository, SessionRepository sessionRepository){
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
    }
    public ResponseEntity<UserDTO> login(String email, String password){
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByEmail(email));
        if(optionalUser.isEmpty())
            throw new UserDoesNotExistException("User doesn't exist found. Please Sign up");
        User user = optionalUser.get();

        //Check password matching or not
        if(!bCryptPasswordEncoder.matches(password, user.getPassword())){
            throw new InvalidPasswordException("Invalid Password. please try again..");
        }

        String token = RandomStringUtils.randomAscii(20);

        Session session=new Session();
        session.setToken(token);
        session.setUser(user);
        session.setSessionStatus(SessionStatus.ACTIVE);
        sessionRepository.save(session);

        MultiValueMapAdapter<String, String> headers = new MultiValueMapAdapter<>(new HashMap<>());
//        MultiValueMap<String, String> headers2 = new LinkedMultiValueMap<>();
        headers.add("AUTH_TOKEN", token);
        return new ResponseEntity<>(
                user.toUserDTO(),
                headers,
                HttpStatus.OK
        );
    }
    public Boolean validate(){
        return null;
    }



    public ResponseEntity<UserDTO> signup(SignUpRequestDTO signUpRequestDTO) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByEmail(signUpRequestDTO.getEmail()));
        if(optionalUser.isPresent())
            throw new UserAlreadyExistException("User already exists. Try log in..");
        User user=new User();

        user.setEmail(signUpRequestDTO.getEmail());

        String encodedPassword = bCryptPasswordEncoder.encode(signUpRequestDTO.getPassword());
        user.setPassword(encodedPassword);

        User savedUser = userRepository.save(user);

        return new ResponseEntity<>(
                savedUser.toUserDTO(),
                HttpStatus.OK
        );

    }
}
