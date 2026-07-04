package com.vida.libraryService.controller;


import com.vida.libraryService.entity.JournalEntry;
import com.vida.libraryService.entity.User;
import com.vida.libraryService.service.JournalEntryService;
import com.vida.libraryService.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalAppController {

   //private Map<Long, JournalEntry> entries = new HashMap<>();

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("/{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName){
        User byuserName = userService.getUserbyUserName(userName);
        List<JournalEntry> all = byuserName.getJournalEntries();
        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No Content Available",HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{userName}")
    public ResponseEntity<?> createJournal(@RequestBody JournalEntry entry, @PathVariable String userName){
        try{
            journalEntryService.saveEntry(entry, userName);
            return new ResponseEntity<>(entry, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>("Please check the request again;",HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<?> getbyId(@PathVariable ObjectId myId){
        Optional<JournalEntry> entr =  journalEntryService.getJournalbyId(myId);
        if(entr.isPresent()){
            return new ResponseEntity<>(entr.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Content Not Found",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/id/{userName}/{myId}")
    public ResponseEntity<?> deletebyId(@PathVariable ObjectId myId, @PathVariable String userName){
        journalEntryService.deleteEntrybyId(myId, userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{username}/{id}")
    public ResponseEntity<?> updateJournal(@PathVariable String userName,@PathVariable ObjectId id,@RequestBody JournalEntry entry){
        JournalEntry old = journalEntryService.getJournalbyId(id).orElse(null);

        if(old!=null){
            old.setTitle(entry.getTitle()!= null && !entry.getTitle().equals("") ? entry.getTitle() : old.getTitle());
            old.setContent(entry.getContent()!= null && !entry.getContent().equals("") ? entry.getContent() : old.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }
        return new ResponseEntity<>("Content Not Found",HttpStatus.NOT_FOUND);

    }

}
