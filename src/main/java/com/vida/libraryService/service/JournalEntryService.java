package com.vida.libraryService.service;

import com.vida.libraryService.entity.JournalEntry;
import com.vida.libraryService.entity.User;
import com.vida.libraryService.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

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

    public void deleteEntrybyId(ObjectId id, String userName){
        User user = userService.getUserbyUserName(userName);
        Optional<JournalEntry> entryToRemove = journalEntryRepository.findById(id);
        user.getJournalEntries().remove(entryToRemove);
        userService.saveUser(user);
        journalEntryRepository.deleteById(id);
    }






}
