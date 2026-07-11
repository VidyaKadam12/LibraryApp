package com.vida.libraryService.repository;

import com.vida.libraryService.entity.ConfigJournalAppEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ConfigRepository extends MongoRepository<ConfigJournalAppEntry, ObjectId> {

}
