package com.vida.libraryService.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@AllArgsConstructor
@Data
public class User {

    @Id
    private ObjectId id;

    @NonNull
    @Indexed(unique = true) //indexing for faster research
    private String userName;

    @NonNull
    private String password;

    public User(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    @DBRef
    private List<JournalEntry> journalEntries = new ArrayList<>();

    private List<String> roles;


}
