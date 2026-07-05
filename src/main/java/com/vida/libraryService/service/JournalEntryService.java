package com.vida.libraryService.service;

import com.vida.libraryService.entity.JournalEntry;
import com.vida.libraryService.entity.User;
import com.vida.libraryService.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    private static final Logger logger = LoggerFactory.getLogger(JournalEntryService.class);

    @Autowired
    // even for the interface springboot will create bean and implement all methods at runtime
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        User user = userService.getUserbyUserName(userName);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.saveUser(user);

    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntry.setDate(LocalDateTime.now());
        journalEntryRepository.save(journalEntry);
    }


    public List<JournalEntry> getAllJournals(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getJournalbyId(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteEntrybyId(ObjectId id, String userName){
        boolean removed = false;
        try{
            User user = userService.getUserbyUserName(userName);
            Optional<JournalEntry> entryToRemove = journalEntryRepository.findById(id);
            removed =user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if(removed){
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
            }
        }catch(Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occured while deleting entry", e);
        }
        return removed;
    }

    public List<JournalEntry> findByUserName(String userName){
        return new ArrayList<>();
    }






}
