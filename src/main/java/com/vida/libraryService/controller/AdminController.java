package com.vida.libraryService.controller;


import com.vida.libraryService.entity.User;
import com.vida.libraryService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/all-users") //make only for admins
    public ResponseEntity<?> getAll(){
        List<User> all = userService.getAllUsers();
        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No User Found",HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create-admin-user")
    public ResponseEntity<?> createUser(@RequestBody User user){
        try{
            userService.saveAdmin(user);
            return new ResponseEntity<>( HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>("Please check the request again;",HttpStatus.BAD_REQUEST);
        }

    }

}
