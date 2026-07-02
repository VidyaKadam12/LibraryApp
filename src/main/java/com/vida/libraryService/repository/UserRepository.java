package com.vida.libraryService.repository;

import com.vida.libraryService.entity.JournalEntry;
import com.vida.libraryService.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findByUserName(String userName);
}
