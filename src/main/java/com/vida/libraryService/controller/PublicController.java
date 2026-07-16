package com.vida.libraryService.controller;


import com.vida.libraryService.entity.User;
import com.vida.libraryService.repository.UserRepositoryImpl;
import com.vida.libraryService.service.UserDetailsServiceImpl;
import com.vida.libraryService.service.UserService;
import com.vida.libraryService.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/public")
public class PublicController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepositoryImpl userRepositoryImpl;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    JWTUtils jwtUtils;

    @GetMapping("/health-check")
    public String healthCheck(){
        return "Ok";
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user){
        try{
            userService.saveNewUser(user);
//            List<User> users =userRepositoryImpl.getUserforSA();
//            log.info("Filtered user :" + users);
            return new ResponseEntity<>("User with " + user.getUserName() + " is created!!", HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>("Please check the request again;",HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));  //internally calls loadUserbyUsername and passwordEncoder
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
            String jwt = jwtUtils.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Please check the request again;",HttpStatus.BAD_REQUEST);
        }

    }
}
