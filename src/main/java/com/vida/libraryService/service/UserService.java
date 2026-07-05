package com.vida.libraryService.service;


import com.vida.libraryService.entity.JournalEntry;
import com.vida.libraryService.entity.User;
import com.vida.libraryService.repository.JournalEntryRepository;
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

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public User saveNewUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        return userRepository.save(user);
    }

    public User saveAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
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

    public void deleteUserbyName(String userName){
        User user = userRepository.findByUserName(userName);
        List<JournalEntry> entries = user.getJournalEntries();
        for(JournalEntry entry: entries){
                journalEntryRepository.delete(entry);
        }
        userRepository.deleteByUserName(userName);
    }






}
