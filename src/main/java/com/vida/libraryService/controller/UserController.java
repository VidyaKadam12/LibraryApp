package com.vida.libraryService.controller;


import com.vida.libraryService.entity.User;
import com.vida.libraryService.repository.UserRepository;
import com.vida.libraryService.response.WeatherResponse;
import com.vida.libraryService.service.UserService;
import com.vida.libraryService.service.WeatherService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

   //private Map<Long, JournalEntry> entries = new HashMap<>();

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/id/{myId}")
    public ResponseEntity<?> getbyId(@PathVariable ObjectId myId){
        Optional<User> entr =  userService.getUserbyId(myId);
        if(entr.isPresent()){
            return new ResponseEntity<>(entr.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("User Not Found",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deletebyUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userService.deleteUserbyName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<?> updateJournal(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User old = userService.getUserbyUserName(username);
        old.setUserName(user.getUserName());
        old.setPassword(user.getPassword());
        userService.saveUser(old);
        //return new ResponseEntity<>(old, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping
    public ResponseEntity<?> getbyUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherService.getWeather("Mumbai");
        String greeting ="";
        if(weatherResponse!=null){
            greeting = "Weather feels like " + weatherResponse.getCurrent().getFeelslike();
        }
        String userName = authentication.getName();
        return new ResponseEntity<>("Hi " + userName +" "+ greeting ,HttpStatus.OK);
    }

}
