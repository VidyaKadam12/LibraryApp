package com.vida.libraryService.entity;

import com.vida.libraryService.enums.Sentiment;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "${collection.name}")
//@Getter
//@Setter
@Data
//@AllArgsConstructor
@NoArgsConstructor
//@Builder
//@Slf4j
//@ToString
//@EqualsAndHashCode
public class JournalEntry {

    @Id
    private ObjectId id;

    @NonNull
    private String title;

    private String content;

    private LocalDateTime date;

    private Sentiment sentiment;

}
