package com.vida.libraryService.controller;


import com.vida.libraryService.entity.User;
import com.vida.libraryService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    UserService userService;

    @GetMapping("/health-check")
    public String healthCheck(){
        return "Ok";
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody User user){
        try{
            userService.saveNewUser(user);
            return new ResponseEntity<>("User with " + user.getUserName() + " is created!!", HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>("Please check the request again;",HttpStatus.BAD_REQUEST);
        }

    }
}
