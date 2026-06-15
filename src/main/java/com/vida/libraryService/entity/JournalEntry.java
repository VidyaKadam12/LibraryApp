package com.vida.libraryService.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "journal_entries")
public class JournalEntry {

    @Id
    @Getter
    @Setter
    private ObjectId id;
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private String content;
    @Getter
    @Setter
    private LocalDateTime date;

}
