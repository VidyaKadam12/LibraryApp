package com.vida.libraryService.controller;


import com.vida.libraryService.entity.User;
import com.vida.libraryService.repository.UserRepositoryImpl;
import com.vida.libraryService.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/health-check")
    public String healthCheck(){
        return "Ok";
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody User user){
        try{
            userService.saveNewUser(user);
//            List<User> users =userRepositoryImpl.getUserforSA();
//            log.info("Filtered user :" + users);
            return new ResponseEntity<>("User with " + user.getUserName() + " is created!!", HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>("Please check the request again;",HttpStatus.BAD_REQUEST);
        }

    }
}
