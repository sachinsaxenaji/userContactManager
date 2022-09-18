package com.userContactManager.userContactManager.repository;

import com.userContactManager.userContactManager.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.query.Param;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    User user;


    @Test
    public void getUserByUserName()

    {

         User u = userRepository.getUserByUserName("sachiin24saxena@gmail.com");
         assertNotNull(u);
         



    }


}