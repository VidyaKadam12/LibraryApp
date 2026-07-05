package com.vida.libraryService.service;

import com.vida.libraryService.entity.User;
import com.vida.libraryService.repository.UserRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
//public class UserServiceTests {
//
//    @Autowired
//    private UserRepository userRepository;
//
////    @BeforeAll
////    @BeforeEach
////    @AfterAll
//
//    @Test
//    public void testFindByUserName(){
//        assertEquals(4,2+2);
//        User user = userRepository.findByUserName("ram");
//        assertTrue(!user.getJournalEntries().isEmpty());
//        assertNotNull(userRepository.findByUserName("ram"));
//    }
//
//    @ParameterizedTest
//    @CsvSource({
//            "1","3","4",
//            "2","10","12",
//            "30","10","40"
//
//    })
//    public void test(int a, int b, int exp){
//        assertEquals(exp, a+b);
//    }
//
//}
