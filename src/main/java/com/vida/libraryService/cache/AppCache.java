package com.vida.libraryService.cache;


import com.vida.libraryService.entity.ConfigJournalAppEntry;
import com.vida.libraryService.repository.ConfigRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    @Autowired
    private ConfigRepository configRepository;

    public Map<String, String> cache;  //In-Memory Cache

    @PostConstruct
    public void init(){
        cache = new HashMap<>();
        List<ConfigJournalAppEntry> all = configRepository.findAll();
        for(ConfigJournalAppEntry cja: all){
            cache.put(cja.getKey(), cja.getValue());
        }
    }



}
