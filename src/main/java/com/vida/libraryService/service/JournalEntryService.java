package com.vida.libraryService.service;

import com.vida.libraryService.entity.JournalEntry;
import com.vida.libraryService.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    // even for the interface springboot will create bean and implement all methods at runtime
    private JournalEntryRepository journalEntryRepository;

    public JournalEntry saveEntry(JournalEntry journalEntry){
        journalEntry.setDate(LocalDateTime.now());
        return journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAllJournals(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getJournalbyId(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public void deleteEntrybyId(ObjectId id){
        journalEntryRepository.deleteById(id);
    }






}
