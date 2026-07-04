package com.vida.libraryService.service;


import com.vida.libraryService.entity.User;
import com.vida.libraryService.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    // even for the interface springboot will create bean and implement all methods at runtime
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User saveNUser(User user){
        return userRepository.save(user);
    }

    public User saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserbyId(ObjectId id){
        return userRepository.findById(id);
    }

    public User getUserbyUserName(String userName){
        return userRepository.findByUserName(userName);
    }

    public void deleteUserbyId(ObjectId id){
        userRepository.deleteById(id);
    }






}
