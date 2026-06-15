package com.vida.libraryService.controller;


import com.vida.libraryService.entity.JournalEntry;
import com.vida.libraryService.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalAppController {

   //private Map<Long, JournalEntry> entries = new HashMap<>();

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll(){
        return journalEntryService.getAllJournals();
    }

    @PostMapping
    public JournalEntry createJournal(@RequestBody JournalEntry entry){
        return journalEntryService.saveEntry(entry);


    }

    @GetMapping("/id/{myId}")
    public Optional<JournalEntry> getbyId(@PathVariable ObjectId myId){
        return journalEntryService.getJournalbyId(myId);
    }

    @DeleteMapping("/id/{myId}")
    public boolean deletebyId(@PathVariable ObjectId myId){
        journalEntryService.deleteEntrybyId(myId);
        return true;
    }

    @PutMapping("/{id}")
    public JournalEntry updateJournal(@PathVariable ObjectId id,@RequestBody JournalEntry entry){
        JournalEntry old = journalEntryService.getJournalbyId(id).orElse(null);

        if(old!=null){
            old.setTitle(entry.getTitle()!= null && !entry.getTitle().equals("") ? entry.getTitle() : old.getTitle());
            old.setContent(entry.getContent()!= null && !entry.getContent().equals("") ? entry.getContent() : old.getContent());
        }
        journalEntryService.saveEntry(old);
        return old;
    }

}
