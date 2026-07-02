package com.vida.libraryService.controller;


import com.vida.libraryService.entity.User;
import com.vida.libraryService.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

   //private Map<Long, JournalEntry> entries = new HashMap<>();

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<User> all = userService.getAllUsers();
        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No User Found",HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user){
        try{
            userService.saveUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>("Please check the request again;",HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<?> getbyId(@PathVariable ObjectId myId){
        Optional<User> entr =  userService.getUserbyId(myId);
        if(entr.isPresent()){
            return new ResponseEntity<>(entr.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("User Not Found",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> deletebyId(@PathVariable ObjectId myId){
        userService.deleteUserbyId(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateJournal(@PathVariable ObjectId id,@RequestBody User user){
        User old = userService.getUserbyUserName(user.getUserName());

        if(old!=null){
            old.setUserName(user.getUserName());
            old.setPassword(user.getPassword());
            userService.saveUser(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }
        return new ResponseEntity<>("Content Not Found",HttpStatus.NOT_FOUND);

    }

}
